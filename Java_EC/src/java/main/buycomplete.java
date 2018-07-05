/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ryo
 */
public class buycomplete extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        //セッションスタート
        HttpSession s = request.getSession();
        
        //ユーザー情報
        UserDataDTO ud = (UserDataDTO) s.getAttribute("login");
        
        //商品情報
        ArrayList<ItemData> ids = (ArrayList<ItemData>) s.getAttribute("ids");
        int type = Integer.parseInt(request.getParameter("type"));
        int sum = Integer.parseInt(request.getParameter("sum"));
        
        //表示ページはUTF8エンコード
        response.setContentType("text/html;charset=UTF-8");
        
        try {
            
            //文字はUTF-8でエンコード
            request.setCharacterEncoding("UTF-8");
            
            UserDataDAO dao = UserDataDAO.getInstance();
            
            //buyテーブルに商品情報追加
            dao.buyItems(ud, ids, type);
            
            //カートの中身を消去（実際にはdeleteフラグをいじるだけ）
            dao.clearCart(ud);
            
            //商品の合計値を記録
            sum += dao.getSum(ud);
            dao.recordSum(ud, sum);
            
            //総購入金額をログインしているユーザー情報に上書き
            dao.search(ud);
            s.setAttribute("login", ud);
            
            //商品情報を保持していたセッションを開放
            s.removeAttribute("ids");
            s.removeAttribute("cart");
            
            request.getRequestDispatcher("./Buycomplete.jsp").forward(request, response);
            
        } catch (SQLException ex) {
            request.setAttribute("error", "データベースとの接続エラーです。");
            System.out.print(ex.getStackTrace());
            request.getRequestDispatcher("./Error.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("error", "不正なアクセスです。TOPページから改めてログインしてください。");
            System.out.print(ex.getStackTrace());
            request.getRequestDispatcher("./Error.jsp").forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

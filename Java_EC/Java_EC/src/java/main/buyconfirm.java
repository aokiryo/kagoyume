/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
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
public class buyconfirm extends HttpServlet {

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

        HashMap<Integer, ArrayList<ItemData>> cart = (HashMap<Integer, ArrayList<ItemData>>) s.getAttribute("cart");

        //商品情報格納
        ArrayList<ItemData> ids = null;
        if (s.getAttribute("cart") != null) {
            ids = cart.get(ud.getUserID());
        }

        //例外が発生した時の判別フラグ
        int eFlag = 0;

        //表示ページはUTF8エンコード
        response.setContentType("text/html;charset=UTF-8");

        try {

            //カートの中身が空の時の処理
            if (ids == null || ids.isEmpty()) {
                throw new Exception();
            }

            //アクセスチェック
            if (!request.getParameter("accessCheck").equals("ok")) {
                eFlag = 1;
                throw new Exception();
            }

            s.setAttribute("ids", ids);

            request.getRequestDispatcher("./Buyconfirm.jsp").forward(request, response);

        } catch (Exception ex) {
            if (eFlag == 0) {
                request.setAttribute("error", "カートの中身が空です。実際に買うわけではないので、好きなだけ商品をカートに入れましょう。");
            } else {
                request.setAttribute("error", "不正なアクセスです。");
            }
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

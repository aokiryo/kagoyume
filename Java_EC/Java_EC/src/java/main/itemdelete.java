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
public class itemdelete extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");

        //セッションスタート
        HttpSession s = request.getSession();

        //ユーザー情報
        UserDataDTO ud = (UserDataDTO) s.getAttribute("login");

        //カートの中の商品
        HashMap cart = (HashMap) s.getAttribute("cart");
        ArrayList<ItemData> ids = (ArrayList<ItemData>) cart.get(ud.getUserID());

        try {
            
            //ログインしているユーザーのlistからコードが一致する商品を削除
            for (int i = 0; i < ids.size(); i++) {
                if (ids.get(i).getId().equals(request.getParameter("itemcode"))) {
                    UserDataDAO.getInstance().cartItemDelete(ids.get(i).getId());
                    ids.remove(i);
                    cart.put(ud.getUserID(), ids);
                    break;
                }
            }

            s.setAttribute("cart", cart);

            //戻す
            String access = (String) s.getAttribute("URL");
            response.sendRedirect(access);

        } catch (SQLException ex) {
            request.setAttribute("error", "データベースとの接続エラーです。");
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

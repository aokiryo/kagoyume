/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
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
public class login extends HttpServlet {

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
        
        //表示ページはUTF8エンコード
        response.setContentType("text/html;charset=UTF-8");
        
        //セッションスタート
        HttpSession s = request.getSession();

        //jspで表示内容を変更するためのフラグ
        boolean logoutFlag = false;

        try {

            //loginセッションの有無によって処理を変える
            if (s.getAttribute("login") != null) {
                s.removeAttribute("login");
                s.removeAttribute("cart");
                s.removeAttribute("id");
                s.removeAttribute("ids");
                logoutFlag = true;
            }

            //アクセス元を控えておく
            String access = "";
            if(s.getAttribute("URL") == null){
                throw new Exception();
            }
            access = (String)s.getAttribute("URL");
            
            request.setAttribute("logoutFlag", logoutFlag);
            request.setAttribute("access", access);
            request.getRequestDispatcher("./Login.jsp").forward(request, response);

        } catch (Exception ex) {
            request.setAttribute("error", "不正なアクセスです。TOPページから改めてログイン、もしくはユーザー登録してください。");
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

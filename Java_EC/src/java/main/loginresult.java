/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.io.PrintWriter;
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
public class loginresult extends HttpServlet {

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
//        response.setContentType("text/html;charset=UTF-8");

        //セッションスタート
        HttpSession s = request.getSession();
        
        ArrayList<ItemData> ids = (ArrayList<ItemData>)s.getAttribute("ids");

        try {
            request.setCharacterEncoding("UTF-8");//リクエストパラメータの文字コードをUTF-8に変更

            System.out.print(request.getParameter("access"));

            if (request.getParameter("access") == null) {
                throw new Exception();
            }

            //フォームからの入力を取得して、JavaBeansに格納
            UserData udb = new UserData();
            udb.setName(request.getParameter("name"));
            udb.setPassword(request.getParameter("password"));

            //DTOオブジェクトにマッピング。DB専用のパラメータに変換
            UserDataDTO searchData = new UserDataDTO();
            udb.UD2DTOMapping(searchData);
            
            UserDataDAO dao = UserDataDAO.getInstance();

            UserDataDTO searchResult = dao.search(searchData);

            if (searchResult.getDeleteFlg() == 1) {
                throw new Exception();
            }

            System.out.print(request.getParameter("access"));
            s.setAttribute("login", searchResult);
            
            if (s.getAttribute("cartlogin") != null) {
                s.removeAttribute("cartlogin");
                dao.insertCart(searchResult, ids);
                response.sendRedirect("http://localhost:8080/Java_EC/cart");
            } else {
                response.sendRedirect(request.getParameter("access"));
            }
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

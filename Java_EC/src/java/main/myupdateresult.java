/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.io.PrintWriter;
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
public class myupdateresult extends HttpServlet {

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
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            if(s.getAttribute("login") == null){
                throw new Exception();
            }
            
            UserData ud = new UserData();

            ud.setName(request.getParameter("name"));
            ud.setPassword(request.getParameter("password"));
            ud.setMail(request.getParameter("mail"));
            ud.setAddress(request.getParameter("address"));

            //DTOオブジェクトにマッピング。DB専用のパラメータに変換
            UserDataDTO userdata = new UserDataDTO();
            ud.UD2DTOMapping(userdata);

            //更新するレコードのuserIDを指定
            UserDataDTO login = (UserDataDTO) s.getAttribute("login");
            userdata.setUserID(login.getUserID());

            //DBへデータの挿入
            UserDataDAO.getInstance().update(userdata);

            //結果画面での表示用に入力パラメータ―をリクエストパラメータとして保持
            request.setAttribute("ud", ud);

            //詳細画面に戻った時のためセッションにも保存
            s.setAttribute("login", login);
            
            request.getRequestDispatcher("./Myupdateresult.jsp").forward(request, response);
            
            
        } catch (Exception ex) {
            Logger.getLogger(myupdateresult.class.getName()).log(Level.SEVERE, null, ex);
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

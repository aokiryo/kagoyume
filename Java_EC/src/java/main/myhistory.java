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
public class myhistory extends HttpServlet {

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
        
        UserDataDTO dto = (UserDataDTO)s.getAttribute("login");
        
        UserDataDAO dao = UserDataDAO.getInstance();
        
        ArrayList<ItemData> history = new ArrayList<ItemData>();
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            //リクエストパラメータの文字コードをUTF-8に変更
            request.setCharacterEncoding("UTF-8");
            
            //ログインしていなければ弾く
            if(s.getAttribute("login") == null){
                throw new Exception();
            }
            
            ArrayList<String> results = dao.searchBuyItems(dto.getUserID());
            
            if(results == null){
                s.setAttribute("history", null);
            }else{
                for (int i = 0; i < results.size(); i++) {
                    ItemData item = new ItemData();
                    JsonNode jn = dao.searchCodes(results.get(i));
                    item.setName(jn.get("ResultSet").get("0").get("Result").get("0").get("Name").asText());
//                    item.setId(jn.get("ResultSet").get("0").get("Result").get("0").get("Code").asText());
//                    item.setPrice(jn.get("ResultSet").get("0").get("Result").get("0").get("Price").get("_value").asInt());
                    URL image = new URL(jn.get("ResultSet").get("0").get("Result").get("0").get("ExImage").get("Url").asText());
                    item.setImage(image);
                    history.add(item);
                }
                s.setAttribute("history", history);
            }
            
            request.getRequestDispatcher("./Myhistory.jsp").forward(request, response);
            
        } catch (Exception ex) {
            Logger.getLogger(mydata.class.getName()).log(Level.SEVERE, null, ex);
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

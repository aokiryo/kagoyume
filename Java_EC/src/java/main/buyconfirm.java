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

        ArrayList<ItemData> ids = null;

        if (s.getAttribute("ids") != null) {
            ids = (ArrayList<ItemData>) s.getAttribute("ids");
        }
        
        UserDataDTO ud = (UserDataDTO) s.getAttribute("login");

        ArrayList<ItemData> cart = (ArrayList<ItemData>) s.getAttribute("cart");
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //アクセスチェック
            if (s.getAttribute("ids") == null || request.getParameter("accessCheck") == null) {
                throw new Exception();
            }
            
//            if (s.getAttribute("cart") != null && !request.getHeader("Referer").equals("http://localhost:8080/Java_EC/itemdelete")) {
//                if (s.getAttribute("login") == null) {
//                    ids = cart;
//                    s.removeAttribute("cart");
//                } else {
//                    UserDataDAO dao = UserDataDAO.getInstance();
//                    ArrayList<String> searchResult = dao.searchHistory(ud.getUserID());
//                    if (searchResult != null) {
//                        for (int i = 0; i < searchResult.size(); i++) {
//                            JsonNode jn = dao.searchCodes(searchResult.get(i));
//                            ItemData id = null;
//                            id.setName(jn.get("ResultSet").get("0").get("Result").get("0").get("Name").asText());
//                            id.setId(jn.get("ResultSet").get("0").get("Result").get("0").get("Code").asText());
//                            id.setPrice(jn.get("ResultSet").get("0").get("Result").get("0").get("Price").get("_value").asInt());
//                            URL image = new URL(jn.get("ResultSet").get("0").get("Result").get("0").get("ExImage").get("Url").asText());
//                            id.setImage(image);
//                            ids.add(id);
//                        }
//                    }
//                    for (int i = 0; i < cart.size(); i++) {
//                        ids.add(cart.get(i));
//                        System.out.print(ids.get(i));
//                    }
//                    s.removeAttribute("cart");
//                }
//            }

            s.setAttribute("ids", ids);

            request.getRequestDispatcher("./Buyconfirm.jsp").forward(request, response);

        } catch (SQLException ex) {
            Logger.getLogger(buyconfirm.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(buyconfirm.class.getName()).log(Level.SEVERE, null, ex);
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

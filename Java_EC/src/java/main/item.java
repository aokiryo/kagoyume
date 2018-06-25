/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ryo
 */
public class item extends HttpServlet {

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
            //文字はUTF-8でエンコード
            request.setCharacterEncoding("UTF-8");

            String encodedResult = URLEncoder.encode(request.getParameter("itemcode"), "UTF-8");

            //検索対象
            String search = encodedResult;

            JsonNode jn = UserDataDAO.getInstance().searchCodes(search);

            //検索結果をBeansに格納
//            ArrayList<ItemData> results = new ArrayList<ItemData>();
//            for (int i = 0; i < 10; i++) {
//            out.print(jn);
                ItemData id = new ItemData();
//                String ji = String.valueOf(i);
                id.setName(jn.get("ResultSet").get("0").get("Result").get("0").get("Name").asText());
                id.setId(jn.get("ResultSet").get("0").get("Result").get("0").get("Code").asText());
                id.setPrice(jn.get("ResultSet").get("0").get("Result").get("0").get("Price").get("_value").asInt());
                URL image = new URL(jn.get("ResultSet").get("0").get("Result").get("0").get("ExImage").get("Url").asText());
                id.setImage(image);
                id.setAbout(jn.get("ResultSet").get("0").get("Result").get("0").get("Description").asText());
                id.setRate(jn.get("ResultSet").get("0").get("Result").get("0").get("Review").get("Rate").asDouble());
//                out.println(id.getName());
//                out.println(id.getPrice());
//                out.println(id.getImage());
//                out.println(id.getAbout());
//                out.println(id.getRate());
//            }

            s.setAttribute("id", id);
//            s.setAttribute("searchWord", request.getParameter("search"));
//            s.setAttribute("searchItems", jn.get("ResultSet").get("totalResultsAvailable"));
            request.getRequestDispatcher("./Item.jsp").forward(request, response);
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

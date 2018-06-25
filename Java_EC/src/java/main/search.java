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
public class search extends HttpServlet {

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

            //無入力はエラー
            if (request.getParameter("search").equals("")) {
                throw new Exception();
            }

            String encodedResult = URLEncoder.encode(request.getParameter("search"), "UTF-8");

            //検索対象
            String search = encodedResult;

            //検索はDAOのメソッドで、結果はJsonNode形式で保持
            JsonNode jn = UserDataDAO.getInstance().searchItems(search);

            //表示件数（デフォルトで10件）
            int x = 10;

            //検索結果が0件ならばエラー、10件以下ならばあるだけ表示する
            if (jn.get("ResultSet").get("totalResultsAvailable").asInt() == 0) {
                throw new Exception();
            } else if (jn.get("ResultSet").get("totalResultsAvailable").asInt() < 10) {
                x = jn.get("ResultSet").get("totalResultsAvailable").asInt();
            }

            //検索結果をBeansに格納
            ArrayList<ItemData> results = new ArrayList<ItemData>();
            for (int i = 0; i < x; i++) {
                ItemData id = new ItemData();
                String ji = String.valueOf(i);
                id.setName(jn.get("ResultSet").get("0").get("Result").get(ji).get("Name").asText());
                id.setId(jn.get("ResultSet").get("0").get("Result").get(ji).get("Code").asText());
                id.setPrice(jn.get("ResultSet").get("0").get("Result").get(ji).get("Price").get("_value").asInt());
                URL image = new URL(jn.get("ResultSet").get("0").get("Result").get(ji).get("ExImage").get("Url").asText());
                id.setImage(image);
//                id.setAbout(jn.get("ResultSet").get("0").get("Result").get(ji).get("Description").asText());
//                id.setRate(jn.get("ResultSet").get("0").get("Result").get(ji).get("Review").get("Rate").asDouble());
                results.add(id);
//                out.println(id.getName());
//                out.println(id.getPrice());
//                out.println(id.getImage());
//                out.println(id.getAbout());
//                out.println(id.getRate());
            }

            s.setAttribute("results", results);
            s.setAttribute("searchWord", request.getParameter("search"));
            s.setAttribute("searchItems", jn.get("ResultSet").get("totalResultsAvailable"));
            request.getRequestDispatcher("./Search.jsp").forward(request, response);

//            out.println(jn.get("ResultSet").get("0").get("Result").get("0").get("Name").asText());
//            out.println(jn.get("ResultSet").get("0").get("Result").get("0").get("Image").get("Small"));
//            out.println(jn.get("ResultSet").get("0").get("Result").get("0").get("Price").get("_value"));
        } catch (Exception ex) {
            Logger.getLogger(search.class.getName()).log(Level.SEVERE, null, ex);
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

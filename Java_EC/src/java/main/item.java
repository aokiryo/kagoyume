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

        //表示ページはUTF8エンコード
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {

            //文字はUTF-8でエンコード
            request.setCharacterEncoding("UTF-8");

            String encodedCode = "";
            String access = (String) s.getAttribute("URL");

            //ログインからの戻りではない場合
            if (!access.equals("http://localhost:8080/Java_EC/item")) {
                //検索コードをエンコード
                encodedCode = URLEncoder.encode(request.getParameter("itemcode"), "UTF-8");
                //loginから戻ってきた時を想定して検索コードはセッションに
                s.setAttribute("encodedCode", encodedCode);
            }
            
            //検索対象
            String search = (String)s.getAttribute("encodedCode");

            //選択された商品の詳細を商品コード検索で取得
            JsonNode jn = UserDataDAO.getInstance().searchCodes(search);
            ItemData id = new ItemData();
            id.setName(jn.get("ResultSet").get("0").get("Result").get("0").get("Name").asText());
            id.setId(jn.get("ResultSet").get("0").get("Result").get("0").get("Code").asText());
            id.setPrice(jn.get("ResultSet").get("0").get("Result").get("0").get("Price").get("_value").asInt());
            URL image = new URL(jn.get("ResultSet").get("0").get("Result").get("0").get("ExImage").get("Url").asText());
            id.setImage(image);
            id.setAbout(jn.get("ResultSet").get("0").get("Result").get("0").get("Description").asText());
            id.setRate(jn.get("ResultSet").get("0").get("Result").get("0").get("Review").get("Rate").asDouble());

            s.setAttribute("URL", "http://localhost:8080/Java_EC/item");            
            s.setAttribute("id", id);
            
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

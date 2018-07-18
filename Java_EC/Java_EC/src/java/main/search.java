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

        //表示ページはUTF8エンコード
        response.setContentType("text/html;charset=UTF-8");

        try {

            //文字はUTF-8でエンコード
            request.setCharacterEncoding("UTF-8");

            String beforeEncodeWord = "";
            String encodedResult = "";
            String access = (String) s.getAttribute("URL");

            //ログインからの戻りではない場合
            if (!access.equals("http://localhost:8080/Java_EC/search")) {
                //無入力はエラー
                if (request.getParameter("search").equals("")) {
                    throw new Exception();
                }
                //検索ワードをエンコードしてセッションに保存
                beforeEncodeWord = request.getParameter("search");
                s.setAttribute("searchWord", beforeEncodeWord);
                //loginから戻ってきた時を想定してエンコードした検索ワードもセッションに
                encodedResult = URLEncoder.encode(beforeEncodeWord, "UTF-8");
                s.setAttribute("encodedResult", encodedResult);
            }

            //検索対象
            String search = (String) s.getAttribute("encodedResult");

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
                results.add(id);
            }

            s.setAttribute("URL", "http://localhost:8080/Java_EC/search");
            s.setAttribute("results", results);
            s.setAttribute("searchItems", jn.get("ResultSet").get("totalResultsAvailable"));
            request.getRequestDispatcher("./Search.jsp").forward(request, response);

        } catch (Exception ex) {
            request.setAttribute("error", "検索単語が入力されていません。");
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

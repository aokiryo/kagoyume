/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ryo
 */
public class sampleAPI extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            //文字はUTF-8でエンコード
            request.setCharacterEncoding("UTF-8");

            String encodedResult = URLEncoder.encode(request.getParameter("search"), "UTF-8");

            //検索対象
            String search = encodedResult;
            
            //基礎URL
            String baseurl = "https://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch?appid=dj00aiZpPTdqNWM0Szk0dk5DTCZzPWNvbnN1bWVyc2VjcmV0Jng9YTk-";

            //検索URL
            String url = baseurl + "&query=" + search + "&responsegroup=medium&image_size=300";

            out.println(url);
            //検索URL接続
            StringBuilder buffer = new StringBuilder();
            InputStream is = new URL(url).openStream();
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader in = new BufferedReader(isr);
            String s = null;
            while ((s = in.readLine()) != null) {
                buffer.append(s).append("\n");
            }

//            out.println(buffer);

            URL searchurl = new URL(url);
            ObjectMapper mapper = new ObjectMapper();
            JsonFactory jf = new JsonFactory();
            JsonParser jp = jf.createParser(searchurl);
            JsonNode jn = mapper.readTree(jp);
            
            out.println(jn);

//            out.println(jn.get("ResultSet").get("0").get("Result").get("0").get("Name").asText());
//            out.println(jn.get("ResultSet").get("0").get("Result").get("0").get("Image").get("Small"));
//            out.println(jn.get("ResultSet").get("0").get("Result").get("0").get("Price").get("_value"));

            //結果をjsonで受け取る
            //表示用に成形
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

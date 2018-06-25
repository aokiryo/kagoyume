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
public class add extends HttpServlet {

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

        //ログインしてるユーザー情報
        UserDataDTO ud = (UserDataDTO) s.getAttribute("login");

        //カートに加えられる商品
        ItemData id = (ItemData) s.getAttribute("id");

        //非ログイン時の商品情報格納及びJSPへの受け渡し用
        ArrayList<ItemData> ids = new ArrayList<ItemData>();

        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            //文字はUTF-8でエンコード
            request.setCharacterEncoding("UTF-8");

            //ログインからの戻りか判断用
            String access = request.getHeader("Referer");

            //item経由かログインからの戻りでなければ弾く
            if (s.getAttribute("id") == null && s.getAttribute("ids") == null) {
                throw new Exception();
            }

            //非ログインならばidsセッションに商品情報格納
            if (s.getAttribute("login") == null && s.getAttribute("ids") != null) {
                ids = (ArrayList<ItemData>) s.getAttribute("ids");
            }

            //ログイン済みならセッションの商品情報をレコード→idsセッションに
            if (access.equals("http://localhost:8080/Java_EC/login")) {
            } else if (s.getAttribute("login") == null) {
                ids.add(id);
            } else if (s.getAttribute("login") != null) {
                UserDataDAO dao = UserDataDAO.getInstance();
                dao.insertCart(ud, id);
                ArrayList<String> carts = dao.searchCart(ud.getUserID());
                for (int i = 0; i < carts.size(); i++) {
                    ItemData item = new ItemData();
                    JsonNode jn = dao.searchCodes(carts.get(i));
                    item.setName(jn.get("ResultSet").get("0").get("Result").get("0").get("Name").asText());
                    item.setId(jn.get("ResultSet").get("0").get("Result").get("0").get("Code").asText());
                    item.setPrice(jn.get("ResultSet").get("0").get("Result").get("0").get("Price").get("_value").asInt());
                    URL image = new URL(jn.get("ResultSet").get("0").get("Result").get("0").get("ExImage").get("Url").asText());
                    item.setImage(image);
                    ids.add(item);
                }
            }

            s.setAttribute("ids", ids);
            request.getRequestDispatcher("./Add.jsp").forward(request, response);

        } catch (Exception ex) {
            request.setAttribute("error", "不正なアクセスです。TOPページから改めてログインしてください。");
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

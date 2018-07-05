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
import java.util.HashMap;
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
        ArrayList<ItemData> ids = new ArrayList<ItemData>();
        
        //商品情報を格納し、cartで表示したりbuy_tへ挿入したりする際に使用するMap
        HashMap<Integer, ArrayList<ItemData>> cart = new HashMap<Integer, ArrayList<ItemData>>();

        //表示ページはUTF8エンコード
        response.setContentType("text/html;charset=UTF-8");

        try {

            //ログインからの戻りか判断用
            String access = (String) s.getAttribute("URL");

            //item経由でなければ弾く
            if (s.getAttribute("id") == null) {
                throw new Exception();
            }

            //非ログインかつ
            if (s.getAttribute("login") == null) {
                if (s.getAttribute("cart") != null) {
                    //カートに入れた商品がある場合
                    cart = (HashMap) s.getAttribute("cart");
                    cart.get(null).add(id);
                    cart.put(null, ids);
                } else {
                    //カートに入れた商品がない場合
                    ids.add(id);
                    cart.put(null, ids);
                }
            } else {
                //ログイン済みかつ
                if (access.equals("http://localhost:8080/Java_EC/add")) {
                    //ログインページからの戻りの場合
                    cart = (HashMap) s.getAttribute("cart");
                } else {
                    //そうでない場合
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
                        cart.put(ud.getUserID(), ids);
                    }
                }
            }

            s.setAttribute("URL", "http://localhost:8080/Java_EC/add");
            s.setAttribute("cart", cart);
            request.getRequestDispatcher("./Add.jsp").forward(request, response);

        } catch (SQLException ex) {
            request.setAttribute("error", "データベースとの接続エラーです。");
            System.out.print(ex.getStackTrace());
            request.getRequestDispatcher("./Error.jsp").forward(request, response);
        }catch (Exception ex) {
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

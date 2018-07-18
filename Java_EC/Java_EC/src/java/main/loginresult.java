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
public class loginresult extends HttpServlet {

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

        //表示ページはUTF8エンコード
        response.setContentType("text/html;charset=UTF-8");

        //セッションスタート
        HttpSession s = request.getSession();

        HashMap<Integer, ArrayList<ItemData>> cart = new HashMap<Integer, ArrayList<ItemData>>();

        //ログインするまでに保持していたカート
        if (s.getAttribute("cart") != null) {
            cart = (HashMap<Integer, ArrayList<ItemData>>) s.getAttribute("cart");
        }

        try {

            //リクエストパラメータの文字コードをUTF-8に変更
            request.setCharacterEncoding("UTF-8");

            if (request.getParameter("access") == null) {
                throw new Exception();
            }

            //フォームからの入力を取得して、JavaBeansに格納
            UserData loginform = new UserData();
            loginform.setName(request.getParameter("name"));
            loginform.setPassword(request.getParameter("password"));

            UserDataDTO searchData = new UserDataDTO();

            UserDataDAO dao = UserDataDAO.getInstance();

            UserDataDTO searchResult = dao.search(loginform, searchData);

            //退会済みならエラー
            if (searchResult.getDeleteFlg() == 1) {
                throw new Exception();
            }

            //検索結果のユーザー情報はログイン中保持し続ける
            s.setAttribute("login", searchResult);

            //cart_tの中身で、過去にユーザーが入れたが未購入のものをcartに入れる
            ArrayList<String> carts = dao.searchCart(searchResult.getUserID());
            ArrayList<ItemData> items = new ArrayList<ItemData>();
            if (!carts.isEmpty()) {
                for (int i = 0; i < carts.size(); i++) {
                    ItemData item = new ItemData();
                    JsonNode jn = dao.searchCodes(carts.get(i));
                    item.setName(jn.get("ResultSet").get("0").get("Result").get("0").get("Name").asText());
                    item.setId(jn.get("ResultSet").get("0").get("Result").get("0").get("Code").asText());
                    item.setPrice(jn.get("ResultSet").get("0").get("Result").get("0").get("Price").get("_value").asInt());
                    URL image = new URL(jn.get("ResultSet").get("0").get("Result").get("0").get("ExImage").get("Url").asText());
                    item.setImage(image);
                    items.add(item);
                }
                cart.put(searchResult.getUserID(), items);
            }

            //ログインしていない状態でカートに入れた商品群（キーがnullの商品群）があり
            if (s.getAttribute("cart") != null && cart.containsKey(null)) {
                //ログインユーザーのカートが既に存在するとき
                if (cart.containsKey(searchResult.getUserID())) {
                    ArrayList<ItemData> ids = cart.get(searchResult.getUserID());
                    for (int i = 0; i < cart.get(null).size(); i++) {
                        ItemData id = cart.get(null).get(i);
                        ids.add(id);
                        dao.insertCart(searchResult, id);
                    }
                    cart.put(searchResult.getUserID(), ids);
                    cart.remove(null);
                } else {
                    //ログインユーザーのカートが存在しないとき
                    ArrayList<ItemData> ids = new ArrayList<ItemData>();
                    for (int i = 0; i < cart.get(null).size(); i++) {
                        ItemData id = cart.get(null).get(i);
                        ids.add(id);
                        dao.insertCart(searchResult, id);
                    }
                    cart.put(searchResult.getUserID(), ids);
                    cart.remove(null);
                }
            }

            //セッションに保存
            s.setAttribute("cart", cart);

            if (s.getAttribute("cartlogin") != null) {
                s.removeAttribute("cartlogin");
                response.sendRedirect("http://localhost:8080/Java_EC/cart");
            } else {
                response.sendRedirect(request.getParameter("access"));
            }
        } catch (SQLException ex) {
            request.setAttribute("error", "データベースとの接続エラーです。");
            System.out.print(ex.getStackTrace());
            request.getRequestDispatcher("./Error.jsp").forward(request, response);
        } catch (Exception ex) {
            request.setAttribute("error", "不正なアクセスです。TOPページから改めてログイン、もしくはユーザー登録してください。");
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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author ryo
 */
import base.DBManager;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * ユーザー情報を格納するテーブルに対しての操作処理を包括する DB接続系はDBManagerクラスに一任 基本的にはやりたい1種類の動作に対して1メソッド
 *
 * @author hayashi-s
 */
public class UserDataDAO {

    //インスタンスオブジェクトを返却させてコードの簡略化
    public static UserDataDAO getInstance() {
        return new UserDataDAO();
    }

    /**
     * データの挿入処理を行う。現在時刻は挿入直前に生成
     *
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー
     */
    public void insert(UserDataDTO ud) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("INSERT INTO user_t(name,password,mail,address,newDate) VALUES(?,?,?,?,?)");
            st.setString(1, ud.getName());
            st.setString(2, ud.getPassword());
            st.setString(3, ud.getMail());
            st.setString(4, ud.getAddress());
            st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
            st.executeUpdate();
            System.out.println("insert completed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    /**
     * データの検索処理を行う。
     *
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー
     * @return 検索結果
     */
    public UserDataDTO search(UserData ud,UserDataDTO dto) throws SQLException,Exception {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();

            //
            String sql = "SELECT * FROM user_t WHERE name = ? AND password = ?";
            st = con.prepareStatement(sql);
            st.setString(1, ud.getName());
            st.setString(2, ud.getPassword());

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                System.out.println("search completed");
                dto.setUserID(rs.getInt(1));
                dto.setName(rs.getString(2));
                dto.setPassword(rs.getString(3));
                dto.setMail(rs.getString(4));
                dto.setAddress(rs.getString(5));
                dto.setTotal(rs.getInt(6));
                dto.setNewDate(rs.getTimestamp(7));
                dto.setDeleteFlg(rs.getInt(8));
            } else {
                System.out.println("search completed");
                throw new Exception();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new Exception(ex);
        } finally {
            if (con != null) {
                con.close();
            }
        }

        return dto;

    }

    public UserDataDTO search(UserDataDTO dto) throws SQLException,Exception {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();

            //
            String sql = "SELECT * FROM user_t WHERE userID = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, dto.getUserID());

            ResultSet rs = st.executeQuery();

            if (rs.next()) {
                System.out.println("search completed");
                dto.setUserID(rs.getInt(1));
                dto.setName(rs.getString(2));
                dto.setPassword(rs.getString(3));
                dto.setMail(rs.getString(4));
                dto.setAddress(rs.getString(5));
                dto.setTotal(rs.getInt(6));
                dto.setNewDate(rs.getTimestamp(7));
                dto.setDeleteFlg(rs.getInt(8));
            } else {
                System.out.println("search completed");
                throw new Exception();
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            throw new Exception(ex);
        } finally {
            if (con != null) {
                con.close();
            }
        }

        return dto;

    }

    /**
     * データの更新処理を行う。現在時刻は挿入直前に生成
     *
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー
     */
    public void update(UserDataDTO ud) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("UPDATE user_t SET name = ?, password = ?, mail = ?, address = ?, total = ?, newDate = ? WHERE userID = ?");
            st.setString(1, ud.getName());
            st.setString(2, ud.getPassword());
            st.setString(3, ud.getMail());
            st.setString(4, ud.getAddress());
            st.setInt(5, ud.getTotal());
            st.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
            st.setInt(7, ud.getUserID());
            st.executeUpdate();
            System.out.println("update completed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    /**
     * データの削除処理を行う。
     *
     * @param ud 対応したデータを保持しているJavaBeans
     * @throws SQLException 呼び出し元にcatchさせるためにスロー
     */
    public void delete(UserDataDTO ud) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("UPDATE user_t SET deleteFlg=1 WHERE userID =?");
            st.setInt(1, ud.getUserID());
            st.executeUpdate();
            System.out.println("delete completed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    public ArrayList<String> searchCart(int userID) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        ArrayList<String> codes = new ArrayList<String>();
        try {

            con = DBManager.getConnection();

            //未購入かつカートに追加した履歴のあるものを取得
            String sql = "SELECT * FROM cart_t WHERE userID = ? AND buyFlag = 0";
            st = con.prepareStatement(sql);
            st.setInt(1, userID);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String code = "";
                code = rs.getString("itemCode");
                codes.add(code);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } catch (Exception ex) {
            Logger.getLogger(UserDataDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (con != null) {
                con.close();
            }
        }

        return codes;

    }

    public ArrayList<String> searchBuyItems(int userID) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        ArrayList<String> codes = new ArrayList<String>();
        try {

            con = DBManager.getConnection();

            //buyテーブルから購入したものを取得
            String sql = "SELECT itemCode FROM buy_t WHERE userID = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, userID);

            ResultSet rs = st.executeQuery();

            boolean x = true;
            
            while(rs.next()){
                String code = "";
                code = rs.getString("itemCode");
                codes.add(code);
                x = false;
            }
            
            if (x) {
                return null;
            }
            
        } finally {
            if (con != null) {
                con.close();
            }
        }

        return codes;

    }

    public void insertCart(UserDataDTO ud, ItemData id) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("INSERT INTO cart_t(userID,itemCode) VALUES(?,?)");
            st.setInt(1, ud.getUserID());
            st.setString(2, id.getId());
            st.executeUpdate();
            System.out.println("insert completed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    public void insertCart(UserDataDTO ud, ArrayList<ItemData> ids) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            for (int i = 0; i < ids.size(); i++) {
                st = con.prepareStatement("INSERT INTO cart_t(userID,itemCode) VALUES(?,?)");
                st.setInt(1, ud.getUserID());
                st.setString(2, ids.get(i).getId());
                st.executeUpdate();
            }
            System.out.println("insert completed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    public void buyItems(UserDataDTO ud, ArrayList<ItemData> id, int type) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            for (int i = 0; i < id.size(); i++) {
                st = con.prepareStatement("INSERT INTO buy_t(userID,itemCode,type,buyDate) VALUES(?,?,?,?)");
                st.setInt(1, ud.getUserID());
                st.setString(2, id.get(i).getId());
                st.setInt(3, type);
                st.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                st.executeUpdate();
            }
            System.out.println("buyItems completed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    public void cartItemDelete(String ic) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("UPDATE cart_t SET buyFlag = 1 WHERE itemCode = ?");
            st.setString(1, ic);
            st.executeUpdate();
            System.out.println("clearCart completed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    public void clearCart(UserDataDTO ud) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("UPDATE cart_t SET buyFlag = 1 WHERE userID = ?");
            st.setInt(1, ud.getUserID());
            st.executeUpdate();
            System.out.println("clearCart completed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    public int getSum(UserDataDTO ud) throws SQLException {
        int sum = 0;
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();

            String sql = "SELECT total from user_t WHERE userID = ?";
            st = con.prepareStatement(sql);
            st.setInt(1, ud.getUserID());

            ResultSet rs = st.executeQuery();

            rs.next();
            sum = rs.getInt("total");
            System.out.println("recordSum completed");
            return sum;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }
    }

    public void recordSum(UserDataDTO ud, int sum) throws SQLException {
        Connection con = null;
        PreparedStatement st = null;
        try {
            con = DBManager.getConnection();
            st = con.prepareStatement("UPDATE user_t SET total = ? WHERE userID = ?");
            st.setInt(1, sum);
            st.setInt(2, ud.getUserID());
            st.executeUpdate();
            System.out.println("recordSum completed");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new SQLException(e);
        } finally {
            if (con != null) {
                con.close();
            }
        }

    }

    public JsonNode searchItems(String search) throws IOException {
        //基礎URL
        String baseurl = "https://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemSearch?appid=dj00aiZpPTdqNWM0Szk0dk5DTCZzPWNvbnN1bWVyc2VjcmV0Jng9YTk-";

        //検索URL
        String url = baseurl + "&query=" + search + "&image_size=300";

        //APIを利用してデータ取得し、それをjsonNode形式にする
        URL searchurl = new URL(url);
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory jf = new JsonFactory();
        JsonParser jp = jf.createParser(searchurl);
        JsonNode jn = mapper.readTree(jp);

        return jn;

    }

    public JsonNode searchCodes(String search) throws IOException {
        //基礎URL
        String baseurl = "https://shopping.yahooapis.jp/ShoppingWebService/V1/json/itemLookup?appid=dj00aiZpPTdqNWM0Szk0dk5DTCZzPWNvbnN1bWVyc2VjcmV0Jng9YTk-";

        //検索URL
        String url = baseurl + "&itemcode=" + search + "&responsegroup=medium&image_size=300";

        //APIを利用してデータ取得し、それをjsonNode形式にする
        URL searchurl = new URL(url);
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory jf = new JsonFactory();
        JsonParser jp = jf.createParser(searchurl);
        JsonNode jn = mapper.readTree(jp);

        return jn;

    }

}

<%-- 
    Document   : Cart
    Created on : 2018/05/28, 16:53:54
    Author     : ryo
--%>
<%@page import="main.UserDataDTO"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="main.ItemData"%>
<%
    HttpSession s = request.getSession();
    UserDataDTO ud = (UserDataDTO) s.getAttribute("login");
    HashMap<Integer, ArrayList<ItemData>> cart = new HashMap<Integer, ArrayList<ItemData>>();
    ArrayList<ItemData> ids = new ArrayList<ItemData>();
    if (s.getAttribute("cart") != null) {
        cart = (HashMap<Integer, ArrayList<ItemData>>) s.getAttribute("cart");
        ids = cart.get(ud.getUserID());
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="Error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./Css.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/earlyaccess/mplus1p.css" rel="stylesheet">
        <title>Cart</title>
    </head>
    <body>
        <header>
            <a class="bland" href="./Top.jsp">かごゆめ</a>
            <ul id="nav">
                <%if (s.getAttribute("login") != null) {%>
                <li class="nav"><a href="login">ログアウト</a></li>
                <li class="nav"><a href="mydata">マイデータ</a></li>
                    <%} else {%>
                    <li class="nav"><a href="login">ログイン</a></li>
                        <%}%>
            </ul>
        </header>

        <h4>カートから商品を削除したい場合は、削除したい商品の名前をクリックしてください。</h4>

        <ol>
            <%if (s.getAttribute("login") != null && !cart.isEmpty()) {%>
            <%for (int i = 0; i < ids.size(); i++) {%>
            <li id="cart">
                <p><img src="<%= ids.get(i).getImage()%>" title="商品画像"></p>
                <p><a href="itemdelete?itemcode=<%= ids.get(i).getId()%>"><%= ids.get(i).getName()%></a>:
                <%= ids.get(i).getPrice()%>円</p>
                <%}%>
                <%}%>
        </ol>

        <form class="buy" action="buyconfirm" method="POST">
            <input type="hidden" name="accessCheck" value="ok">
            <input type="submit" name="btnSubmit" value="カートの商品を購入する">
        </form><br>

    </body>
</html>

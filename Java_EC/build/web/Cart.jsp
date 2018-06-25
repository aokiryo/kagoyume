<%-- 
    Document   : Cart
    Created on : 2018/05/28, 16:53:54
    Author     : ryo
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="main.ItemData"%>
<%
    HttpSession s = request.getSession();
    ItemData id = (ItemData) s.getAttribute("id");
    ArrayList<ItemData> ids = (ArrayList<ItemData>)s.getAttribute("ids");

%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cart</title>
    </head>
    <body>
        <form action="buyconfirm" method="POST">
            <input type="hidden" name="accessCheck" value="">
            <input type="submit" name="btnSubmit" value="カートの商品を購入する">
        </form><br>
        カートから商品を削除したい場合は、削除したい商品の名前をクリックしてください。
        <ol>
            <%for (int i = 0; i < ids.size(); i++) {%>
            <li><img src="<%= ids.get(i).getImage()%>" title="商品画像"><br>
                <a href="itemdelete?itemcode=<%= ids.get(i).getId()%>"><%= ids.get(i).getName()%></a>:
                <%= ids.get(i).getPrice()%>円
                <%}%>
        </ol>
        <a href="./Top.jsp">トップ（検索）</a><br>
        <%--ログイン共通部--%>
        <%if (s.getAttribute("login") != null) {%>
        <a href="login">ログアウト</a><br>
        <%} else {%>
        <a href="login">ログイン</a><br>
        <%}%>
    </body>
</html>

<%-- 
    Document   : Item
    Created on : 2018/05/28, 16:52:01
    Author     : ryo
--%>
<%@page import="main.ItemData"%>
<%@page import="main.UserData"%>
<%
    HttpSession s = request.getSession();
    ItemData id = (ItemData) s.getAttribute("id");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="Error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Item</title>
    </head>
    <body>
        <form action="add" method="POST">
            <input type="submit" name="btnSubmit" value="この商品をカートに追加">
        </form><br>
        <img src="<%= id.getImage()%>" title="商品画像"><br>
        <%= id.getName()%>:<%= id.getPrice()%>円<br>
        商品概要:<%= id.getAbout()%><br>
        評価:<%= id.getRate()%>/5<br>
        <%--ログイン共通部--%>
        <a href="./Top.jsp">トップ(検索)</a><br>
        <%if (s.getAttribute("login") != null) {%>
        <a href="login">ログアウト</a><br>
        <a href="cart">カート</a><br>
        <%} else {%>
        <a href="login">ログイン</a><br>
        <%}%>

    </body>
</html>

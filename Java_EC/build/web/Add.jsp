<%-- 
    Document   : Add
    Created on : 2018/05/28, 16:52:24
    Author     : ryo
--%>
<%@page import="main.ItemData"%>
<%@page import="main.UserData"%>
<%@page import="java.util.ArrayList"%>
<%
    HttpSession s = request.getSession();
//    ArrayList<ItemData> results = (ArrayList<ItemData>) s.getAttribute("results");
//    ItemData id = null;

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="Error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add</title>
    </head>
    <body>
        商品をカートに追加しました<br>
        <a href="./Top.jsp">トップ（検索）</a><br>
        <a href="cart">カート</a><br>
        <%--ログイン共通部--%>
        <%if (s.getAttribute("login") != null) {%>
        <a href="login">ログアウト</a><br>
        <%} else {%>
        <a href="login">ログイン</a><br>
        <%}%>
    </body>
</html>

<%-- 
    Document   : Search
    Created on : 2018/05/28, 16:51:39
    Author     : ryo
--%>
<%@page import="main.ItemData"%>
<%@page import="main.UserData"%>
<%@page import="java.util.ArrayList"%>
<%
    HttpSession s = request.getSession();
    ArrayList<ItemData> results = (ArrayList<ItemData>) s.getAttribute("results");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="Error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>search</title>
    </head>
    <body>
        検索ワード:<%= (String)s.getAttribute("searchWord")%><br>
        検索結果:<%= s.getAttribute("searchItems")%>件<br>
        <ol>
            <%for (int i = 0; i < results.size(); i++) {%>
            <li><img src="<%= results.get(i).getImage()%>" title="商品画像"><br>
                <a href="item?itemcode=<%= results.get(i).getId()%>"><%= results.get(i).getName()%></a>:
                <%= results.get(i).getPrice()%>円
                <%}%>
        </ol>
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

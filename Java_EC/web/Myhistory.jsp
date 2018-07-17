<%-- 
    Document   : Myhistory
    Created on : 2018/06/22, 16:45:38
    Author     : ryo
--%>
<%@page import="main.ItemData"%>
<%@page import="java.util.ArrayList"%>
<%
    HttpSession s = request.getSession();
    ArrayList<ItemData> results = (ArrayList<ItemData>) s.getAttribute("history");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="Error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./Css.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/earlyaccess/mplus1p.css" rel="stylesheet">
        <title>Myhistory</title>
    </head>
    <body>
        <header>
            <a class="bland" href="./Top.jsp">かごゆめ</a>
            <ul id="nav">
                <li class="nav"><a href="login">ログアウト</a></li>
                <li class="nav"><a href="mydata">マイデータ</a></li>
            </ul>
        </header>
        
        <ol>
            <%for (int i = 0; i < results.size(); i++) {%>
            <li id="myHistory">
                <p><img src="<%= results.get(i).getImage()%>" title="商品画像">
                <%= results.get(i).getName()%></p>
                <%}%>
        </ol>
        
    </body>
</html>

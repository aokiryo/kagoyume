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
        <link href="./Css.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/earlyaccess/mplus1p.css" rel="stylesheet">
        <title>search</title>
    </head>
    <body>
        <header>
            <a class="bland" href="./Top.jsp">かごゆめ</a>
            <ul id="nav">
                <li class="nav">検索ワード:<%= (String) s.getAttribute("searchWord")%></li>
                <li class="nav">検索結果:<%= s.getAttribute("searchItems")%>件</li>
                    <%if (s.getAttribute("login") != null) {%>
                <li class="nav"><a href="login">ログアウト</a></li>
                <li class="nav"><a href="cart">カート</a></li>
                <li class="nav"><fa href="mydata">マイデータ</a></li>
                    <%} else {%>
                    <li class="nav"><a href="cart">カート</a></li>
                    <li class="nav"><a href="login">ログイン</a></li>
                        <%}%>
            </ul>
        </header>

        <ol>
            <%for (int i = 0; i < results.size(); i++) {%>
            <li id="search">
                <p><img src="<%= results.get(i).getImage()%>" title="商品画像"></p>
                <p><a href="item?itemcode=<%= results.get(i).getId()%>"><%= results.get(i).getName()%></a>:
                    <%= results.get(i).getPrice()%>円</p>
                    <%}%>
        </ol>
    </body>
</html>

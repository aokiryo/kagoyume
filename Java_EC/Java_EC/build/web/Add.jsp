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
        <link href="./Css.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/earlyaccess/mplus1p.css" rel="stylesheet">
        <title>Add</title>
    </head>
    <body>
        <header>
            <a class="bland" href="./Top.jsp">かごゆめ</a>
            <ul id="nav">
                <%if (s.getAttribute("login") != null) {%>
                <li class="nav"><a href="login">ログアウト</a></li>
                <li class="nav"><a href="cart">カート</a></li>
                <li class="nav"><a href="mydata">マイデータ</a></li>
                    <%} else {%>
                    <li class="nav"><a href="cart">カート</a></li>
                    <li class="nav"><a href="login">ログイン</a></li>
                        <%}%>
            </ul>
        </header>
        <p>商品をカートに追加しました。</p>
    </body>
</html>

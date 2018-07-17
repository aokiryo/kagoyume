<%-- 
    Document   : Top
    Created on : 2018/05/29, 10:13:35
    Author     : ryo
--%>

<%@page import="main.UserDataDTO"%>
<%
    HttpSession s = request.getSession();
    UserDataDTO login = null;
    if (s.getAttribute("login") != null) {
        login = (UserDataDTO) s.getAttribute("login");
    }
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./Css.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/earlyaccess/mplus1p.css" rel="stylesheet">
        <title>top page</title>
    </head>
    <body>
        <header>
            <a class="bland" href="./Top.jsp">かごゆめ</a>
                <ul id="nav">
                    <%s.setAttribute("UfRL", "http://localhost:8080/Java_EC/Top.jsp");%>
                    <%if (s.getAttribute("login") != null) {%>
                    <li class="nav">ようこそ<%= login.getName()%>さん</li>
                    <li class="nav"><a href="login">ログアウト</a></li>
                    <li class="nav"><a href="cart">カート</a></li>
                    <li class="nav"><a href="mydata">マイデータ</a></li>
                        <%} else {%>
                    <li class="nav"><a href="login">ログイン</a></li>
                        <%}%>
                </ul>
        </header>

        <h1>『かごゆめ』はYahooショッピングで
            好きなだけ買い物した気分になれるサイトです</h1><br>

        <form class="search" action="search" method="get">
            商品検索 : <input type="text" name="search">
            <input type="submit" name="btnSubmit" value="検索">
        </form>
        <%s.setAttribute("URL", "http://localhost:8080/Java_EC/Top.jsp");%>
    </body>
</html>

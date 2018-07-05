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
        <title>top page</title>
    </head>
    <body>
        <h1>かごゆめTOP</h1><br>
        <h3>このサイトはYahooショッピングで
            好きなだけ買い物した気分になれるサイトです</h3><br><br>

        <%if (s.getAttribute("login") != null) {%>
        ようこそ<%= login.getName()%>さん<br><br>
        <%}%>

        <form action="search" method="get">
            商品検索 : <input type="text" name="search">
            <input type="submit" name="btnSubmit" value="検索">
        </form>
        <%--ログイン部--%>
        <%s.setAttribute("URL", "http://localhost:8080/Java_EC/Top.jsp");%>
        <%if (s.getAttribute("login") != null) {%>
        <a href="login">ログアウト</a><br>
        <a href="cart">カート</a><br>
        <a href="mydata">マイデータ</a><br>
        <%} else {%>
        <a href="login">ログイン</a><br>
        <%}%>
    </body>
</html>

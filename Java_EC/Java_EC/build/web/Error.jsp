<%-- 
    Document   : error
    Created on : 2018/06/05, 13:06:52
    Author     : ryo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./Css.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/earlyaccess/mplus1p.css" rel="stylesheet">
        <title>Error</title>
    </head>
    <body>
        <header>
            <a class="bland" href="./Top.jsp">かごゆめ</a>
        </header>

        <h1>エラーが発生しました。以下の項目を確認してください。</h1>
        <% if (request.getAttribute("error") != null) {%>
        <h3><%= request.getAttribute("error")%></h3>
        <%} else {%>
        <h3>不正なアクセスです。</h3>
        <%}%><br>
    </body>
</html>

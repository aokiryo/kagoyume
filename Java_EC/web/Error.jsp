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
        <title>Error</title>
    </head>
    <body>
        エラーが発生しました。以下の項目を確認してください。<br><br>
        <% if(request.getAttribute("error") != null){%>
        <%= request.getAttribute("error")%>
        <%}else{%>
        不正なアクセスです。
        <%}%><br><br>
        <a href="./Top.jsp">トップ(検索)</a><br>
    </body>
</html>

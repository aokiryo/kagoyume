<%-- 
    Document   : Buycomplete
    Created on : 2018/05/28, 16:55:08
    Author     : ryo
--%>
<%
    HttpSession s = request.getSession();
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buycomplete</title>
    </head>
    <body>
        購入が完了しました。<br>
        <a href="./Top.jsp">トップ（検索）</a><br>
        <%--ログイン共通部--%>
        <a href="login">ログアウト</a><br>
    </body>
</html>

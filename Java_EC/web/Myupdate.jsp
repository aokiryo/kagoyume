<%-- 
    Document   : Myupdate
    Created on : 2018/06/22, 17:30:29
    Author     : ryo
--%>
<%@page import="main.UserDataDTO"%>
<%
    HttpSession s = request.getSession();
    UserDataDTO ud = (UserDataDTO) s.getAttribute("login");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="Error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./Css.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/earlyaccess/mplus1p.css" rel="stylesheet">
        <title>Myupdate</title>
    </head>
    <body>
        <header>
            <a class="bland" href="./Top.jsp">かごゆめ</a>
            <ul id="nav">
                <li class="nav"><a href="login">ログアウト</a></li>
                <li class="nav"><a href="mydata">マイデータ</a></li>
            </ul>
        </header>

        <form id="update" action="myupdateresult" method="POST">
            <p>名前:
                <input type="text" name="name" value="<%= ud.getName()%>"></p>

            <p>パスワード:
                <input type="text" name="password" value="<%= ud.getPassword()%>"></p>

            <p>メールアドレス:
                <input type="text" name="mail" value="<%= ud.getMail()%>"></p>

            <p>住所:
                <textarea name="address" rows=10 cols=50 style="resize:none" wrap="hard"><%= ud.getAddress()%></textarea></p>

            <p><input type="submit" name="btnSubmit" value="更新"></p>
        </form>
    </body>
</html>

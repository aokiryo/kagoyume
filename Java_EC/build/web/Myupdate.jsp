<%-- 
    Document   : Myupdate
    Created on : 2018/06/22, 17:30:29
    Author     : ryo
--%>
<%@page import="main.UserDataDTO"%>
<%
    HttpSession s = request.getSession();
    UserDataDTO ud = (UserDataDTO) s.getAttribute("userData");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Myupdate</title>
    </head>
    <body>
        <form action="myupdateresult" method="POST">
            名前:
            <input type="text" name="name" value="<%= ud.getName()%>">
            <br><br>

            パスワード:
            <input type="text" name="password" value="<%= ud.getPassword()%>">
            <br><br>

            メールアドレス:
            <input type="text" name="mail" value="<%= ud.getMail()%>">
            <br><br>

            住所
            <br>
            <textarea name="address" rows=10 cols=50 style="resize:none" wrap="hard"><%= ud.getAddress()%></textarea><br><br>

            <input type="submit" name="btnSubmit" value="更新"><br>
            <a href="./Top.jsp">トップへ</a><br>
            </body>
        </form>
</html>

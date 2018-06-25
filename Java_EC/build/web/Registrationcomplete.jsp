<%-- 
    Document   : Registrationcomplete
    Created on : 2018/05/28, 16:49:14
    Author     : ryo
--%>
<%@page import="main.UserData"%>
<%
    UserData ud = (UserData) request.getAttribute("ud");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrationcomplete</title>
    </head>
    <body>
        <h1>登録結果</h1><br>
        名前:<%= ud.getName()%><br>
        パスワード:<%= ud.getPassword()%><br>
        メールアドレス:<%= ud.getMail()%><br>
        住所:<%= ud.getAddress()%><br>
        以上の内容で登録しました。<br>
        <a href="./Top.jsp">トップへ戻る</a>
    </body>
</html>

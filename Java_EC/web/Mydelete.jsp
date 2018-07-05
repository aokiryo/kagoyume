<%-- 
    Document   : Mydelete
    Created on : 2018/06/22, 18:09:47
    Author     : ryo
--%>
<%@page import="main.UserDataDTO"%>
<%
    HttpSession s = request.getSession();
    UserDataDTO dto = (UserDataDTO) s.getAttribute("login");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="Error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mydelete</title>
    </head>
    <body>
        <h1>あなたの登録情報です</h1><br>
        名前:<%= dto.getName()%><br>
        パスワード:<%= dto.getPassword()%><br>
        メールアドレス:<%= dto.getMail()%><br>
        住所:<%= dto.getAddress()%><br>
        総購入金額:<%= dto.getTotal()%><br>
        最終更新日時:<%= dto.getNewDate()%><br><br>
        <h1>本当に削除してもよろしいですか？</h1><br>
        <a href="mydeleteresult">はい</a><br>
        <a href="./Top.jsp">いいえ(トップへ)</a><br>
    </body>
</html>

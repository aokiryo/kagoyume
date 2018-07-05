<%-- 
    Document   : Mydata
    Created on : 2018/05/28, 16:52:55
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
        <title>Mydata</title>
    </head>
    <body>
        <h1>あなたの登録情報です</h1><br>
        名前:<%= dto.getName()%><br>
        パスワード:<%= dto.getPassword()%><br>
        メールアドレス:<%= dto.getMail()%><br>
        住所:<%= dto.getAddress()%><br>
        総購入金額:<%= dto.getTotal()%>円<br>
        最終更新日時:<%= dto.getNewDate()%><br><br>
        <a href="myhistory">購入履歴</a><br>        
        <a href="myupdate">登録情報更新</a><br>
        <a href="mydelete">登録情報削除</a><br>        
        <a href="login">ログアウト</a><br>
        <a href="./Top.jsp">トップへ</a><br>
    </body>
</html>

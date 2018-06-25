<%-- 
    Document   : Myupdateresult
    Created on : 2018/06/22, 17:42:51
    Author     : ryo
--%>
<%@page import="main.UserDataDTO"%>
<%
    UserDataDTO dto = (UserDataDTO) request.getAttribute("login");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Myupdateresult</title>
    </head>
    <body>
        <h1>更新結果</h1><br>
        名前:<%= dto.getName()%><br>
        パスワード:<%= dto.getPassword()%><br>
        メールアドレス:<%= dto.getMail()%><br>
        住所:<%= dto.getAddress()%><br>
        総購入金額:<%= dto.getTotal()%><br>
        最終更新日時:<%= dto.getNewDate()%><br><br>
        以上の内容で更新しました<br>
        <a href="myhistory">購入履歴</a><br>        
        <a href="myupdate">登録情報更新</a><br>
        <a href="mydelete">登録情報削除</a><br>        
        <a href="login">ログアウト</a><br>
        <a href="./Top.jsp">トップへ</a><br>
    </body>
</html>

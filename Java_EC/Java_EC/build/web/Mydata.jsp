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
        <link href="./Css.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/earlyaccess/mplus1p.css" rel="stylesheet">
        <title>Mydata</title>
    </head>
    <body>
        <header>
            <a class="bland" href="./Top.jsp">かごゆめ</a>
            <ul id="nav">
                <li class="nav"><a href="login">ログアウト</a></li>
                <li class="nav"><a href="myhistory">購入履歴</a></li>
                <li class="nav"><a href="myupdate">登録情報更新</a></li>
                <li class="nav"><a href="mydelete">登録情報削除</a></li>
            </ul>
        </header>

        <h1>あなたの登録情報です</h1>

        <ol>
            <li id="myData">
                <p>名前:<%= dto.getName()%></p>
                <p>パスワード:<%= dto.getPassword()%></p>
                <p>メールアドレス:<%= dto.getMail()%></p>
                <p>住所:<%= dto.getAddress()%></p>
                <p>総購入金額:<%= dto.getTotal()%>円</p>
                <p>最終更新日時:<%= dto.getNewDate()%></p>
            </li>
        </ol>

    </body>
</html>

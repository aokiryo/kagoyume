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
<%@page errorPage="Error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./Css.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/earlyaccess/mplus1p.css" rel="stylesheet">
        <title>Registrationcomplete</title>
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

        <h1>登録結果</h1>
        <div id="registrationcomplete">
            <p>名前:<%= ud.getName()%></p>
            <p>パスワード:<%= ud.getPassword()%></p>
            <p>メールアドレス:<%= ud.getMail()%></p>
            <p>住所:<%= ud.getAddress()%></p>
        </div>
        
        <p id="systemmessage">以上の内容で登録しました。</p>
        
    </body>
</html>

<%-- 
    Document   : login
    Created on : 2018/05/28, 16:46:18
    Author     : ryo
--%>
<%
    HttpSession s = request.getSession();
    System.out.print(request.getAttribute("logoutFlag"));
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="Error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./Css.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/earlyaccess/mplus1p.css" rel="stylesheet">
        <title>loginPage</title>
    </head>
    <body>
        <header>
            <a class="bland" href="./Top.jsp">かごゆめ</a>
            <ul id="nav">
                <li class="nav"><a href="registration">新規会員登録</a></li>
            </ul>
        </header>

        <%if (request.getAttribute("logoutFlag") != null && request.getAttribute("logoutFlag").equals(true)) {%>
        <h4 id="login">ログアウトしました</h4>
        <h4>またのご利用をお待ちしております</h4>
        <%} else {%>
        <h4 id="login">ユーザー名とパスワードを入力してください</h4>
        <form class="login" action="loginresult" method="POST">
            ユーザー名:<input type="text" name="name"><br>
            パスワード:<input type="text" name="password"><br>
            <input type="hidden" name="access"  value="<%= request.getAttribute("access")%>">
            <input type="submit" name="btnSubmit" value="ログイン"><br>
        </form>
        <%}%>
    </body>
</html>

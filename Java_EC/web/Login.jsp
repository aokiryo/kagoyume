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
        <title>loginPage</title>
    </head>
    <body>
        <%if (request.getAttribute("logoutFlag") != null && request.getAttribute("logoutFlag").equals(true)) {%>
        ログアウトしました<br>
        またのご利用をお待ちしております<br>
        <%} else {%>
        ユーザー名とパスワードを入力してください<br>
        <form action="loginresult" method="POST">
            ユーザー名:<input type="text" name="name"><br>
            パスワード:<input type="text" name="password"><br>
            <input type="hidden" name="access"  value="<%= request.getAttribute("access")%>">
            <input type="submit" name="btnSubmit" value="ログイン"><br>
        </form>
        <a href="registration">新規会員登録</a><br>
        <%}%>
        <a href="./Top.jsp">トップへ戻る</a>
    </body>
</html>

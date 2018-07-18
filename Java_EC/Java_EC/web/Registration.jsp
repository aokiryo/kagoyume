<%-- 
    Document   : Registration
    Created on : 2018/05/28, 16:47:40
    Author     : ryo
--%>
<%@page import="main.UserData"%>
<%
    HttpSession s = request.getSession();
    UserData ud = null;
    boolean reinput = false;
    if (request.getParameter("mode") != null && request.getParameter("mode").equals("REINPUT")) {
        reinput = true;
        ud = (UserData) s.getAttribute("ud");
    }
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="Error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./Css.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/earlyaccess/mplus1p.css" rel="stylesheet">
        <title>Registration</title>
    </head>
    <body>
        <header>
            <a class="bland" href="./Top.jsp">かごゆめ</a>
        </header>

        <ol>
        <form action="registrationconfirm" method="POST">
            <li id="registration">
                <p>名前:
                <input type="text" name="name" value="<% if(reinput){out.print(ud.getName());}%>"></p>

                <p>パスワード:
                <input type="text" name="password" value="<% if(reinput){out.print(ud.getPassword());}%>"></p>

                <p>メールアドレス:
                <input type="text" name="mail" value="<% if(reinput){out.print(ud.getMail());}%>"></p>

                <p>住所:
                <textarea name="address" rows=10 cols=50 style="resize:none" wrap="hard"><% if(reinput){out.print(ud.getAddress());}%></textarea></p>
        </ol>
        
            <input type="hidden" name="ac"  value="<%= s.getAttribute("ac")%>">
            <p class="registration"><input type="submit" name="btnSubmit" value="確認画面へ"></p>
        </form>
    </body>
</html>

<%-- 
    Document   : Registrationconfirm
    Created on : 2018/05/28, 16:48:28
    Author     : ryo
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="main.UserData"%>
<%
    HttpSession s = request.getSession();
    UserData ud = (UserData) s.getAttribute("ud");
    ArrayList<String> chkList = ud.chkproperties();
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="Error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./Css.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/earlyaccess/mplus1p.css" rel="stylesheet">
        <title>Registrationconfirm</title>
    </head>
    <body>
        <header>
            <a class="bland" href="./Top.jsp">かごゆめ</a>
            <ul id="nav">
                <li class="nav"><a href="login">ログアウト</a></li>
                <li class="nav"><a href="mydata">マイデータ</a></li>
            </ul>
        </header>

        <% if (chkList.size() == 0) {%>

        <h1>登録確認</h1>

        <div id="registrationconfirm">
            <p>名前:<%= ud.getName()%></p>
            <p>パスワード:<%= ud.getPassword()%></p>
            <p>メールアドレス:<%= ud.getMail()%></p>
            <p>住所:<%= ud.getAddress()%></p>
        </div>

        <p id="systemmessage">上記の内容で登録します。よろしいですか？</p>

        <ol>
            <li class="registrationconfirm">
                <form action="registrationcomplete" method="POST">
                    <input type="hidden" name="ac"  value="<%= s.getAttribute("ac")%>">
                    <p><input type="submit" name="yes" value="はい">
                </form>

                <form action="registration" method="POST">
                    <input type="hidden" name="ac"  value="<%= s.getAttribute("ac")%>">
                    <input type="hidden" name="mode" value="REINPUT">
                    <p><input type="submit" name="no" value="いいえ">
                </form>
            </li>
        </ol>

        <% } else { %>
        <h1>入力が不完全です</h1>
        <%
            String output = "";
            for (String val : chkList) {
                if (val.equals("name")) {
                    output += "名前";
                }
                if (val.equals("password")) {
                    output += "パスワード";
                }
                if (val.equals("mail")) {
                    output += "メールアドレス";
                }
                if (val.equals("address")) {
                    output += "住所";
                }
                output += "が未記入です<br>";
            }
        %>
        <div id =erro>
            <p><%= output%>
        </div>
        <% }%>
    </form>
</body>
</html>

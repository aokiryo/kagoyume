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
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registrationconfirm</title>
    </head>
    <body>
        <% if (chkList.size() == 0) {%>
        <h1>登録確認</h1>
        名前:<%= ud.getName()%><br>
        パスワード:<%= ud.getPassword()%><br>
        メールアドレス:<%= ud.getMail()%><br>
        住所:<%= ud.getAddress()%><br>
        上記の内容で登録します。よろしいですか？
        <form action="registrationcomplete" method="POST">
            <input type="hidden" name="ac"  value="<%= s.getAttribute("ac")%>">
            <input type="submit" name="yes" value="はい">
        </form>
        <form action="registration" method="POST">
            <input type="hidden" name="ac"  value="<%= s.getAttribute("ac")%>">
            <input type="hidden" name="mode" value="REINPUT">
            <input type="submit" name="no" value="いいえ">
        </form>
        <% } else { %>
        <h1>入力が不完全です</h1>
        <%        String output = "";
            for (String val : chkList) {
                if (val.equals("name")) {
                    output += "名前";
                }
                if (val.equals("year")) {
                    output += "年";
                }
                if (val.equals("month")) {
                    output += "月";
                }
                if (val.equals("day")) {
                    output += "日";
                }
                if (val.equals("type")) {
                    output += "種別";
                }
                if (val.equals("tell")) {
                    output += "電話番号";
                }
                if (val.equals("comment")) {
                    output += "自己紹介";
                }
                output += "が未記入です<br>";
            }
        %>
        <%= output%>
        <% }%>
        </form>
    </body>
</html>

<%-- 
    Document   : Item
    Created on : 2018/05/28, 16:52:01
    Author     : ryo
--%>
<%@page import="main.ItemData"%>
<%@page import="main.UserData"%>
<%
    HttpSession s = request.getSession();
    ItemData id = (ItemData) s.getAttribute("id");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="Error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./Css.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/earlyaccess/mplus1p.css" rel="stylesheet">
        <title>Item</title>
    </head>
    <body>
        <header>
            <a class="bland" href="./Top.jsp">かごゆめ</a>
            <ul id="nav">
                <%if (s.getAttribute("login") != null) {%>
                <li class="nav"><a href="login">ログアウト</a></li>
                <li class="nav"><a href="cart">カート</a></li>
                <li class="nav"><fa href="mydata">マイデータ</a></li>
                    <%} else {%>
                    <li class="nav"><a href="cart">カート</a></li>
                    <li class="nav"><a href="login">ログイン</a></li>
                        <%}%>
            </ul>
        </header>

        <div id="item">
            <p><img src="<%= id.getImage()%>" title="商品画像"></p>
            <p><%= id.getName()%>:<%= id.getPrice()%>円<br><br>
                商品概要:<%= id.getAbout()%><br><br>
                評価:<%= id.getRate()%>/5</p>
            <form class="add" action="add" method="POST">
                <input type="submit" name="btnSubmit" value="この商品をカートに追加">
            </form>
        </div>

    </body>
</html>

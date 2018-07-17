<%-- 
    Document   : Buyconfirm
    Created on : 2018/05/28, 16:54:33
    Author     : ryo
--%>
<%@page import="java.util.ArrayList"%>
<%@page import="main.ItemData"%>
<%
    HttpSession s = request.getSession();
    ArrayList<ItemData> ids = (ArrayList<ItemData>) s.getAttribute("ids");
    int sum = 0;

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="Error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="./Css.css" rel="stylesheet">
        <link href="https://fonts.googleapis.com/earlyaccess/mplus1p.css" rel="stylesheet">
        <title>Buyconfirm</title>
    </head>
    <body>
        <header>
            <a class="bland" href="./Top.jsp">かごゆめ</a>
            <ul id="nav">
                <%if (s.getAttribute("login") != null) {%>
                <li class="nav"><a href="login">ログアウト</a></li>
                <li class="nav"><a href="mydata">マイデータ</a></li>
                    <%} else {%>
                    <li class="nav"><a href="login">ログイン</a></li>
                        <%}%>
            </ul>
        </header>
            
        <ol>
            <%for (int i = 0; i < ids.size(); i++) {%>
            <li id="buyConfirm">
                <p><img src="<%= ids.get(i).getImage()%>" title="商品画像"></p>
                <p><%= ids.get(i).getName()%>:
                <%= ids.get(i).getPrice()%>円</p>
                <%}%>
        </ol>
        <%for (int i = 0; i < ids.size(); i++) {%>
        <%sum += ids.get(i).getPrice();%>
        <%}%>
        <h5>合計:<%= sum%>円</h5>

        <h4>購入方法を選択のうえ、購入確定ボタンを押してください</h4>

        <form action="buycomplete" method="POST">
            <input type="radio" name="type" value="0" checked="checked">:通常配送<br>
            <input type="radio" name="type" value="1">:お急ぎ<br>
            <input type="hidden" name="sum" value="<%=sum%>">
            <input type="submit" name="btnSubmit" value="上記の内容で購入する"><br>
        </form><br>

        <form action="cart" method="POST">
            <input type="submit" name="btnSubmit" value="カートに戻る"><br>
        </form><br>

    </body>
</html>

<%-- 
    Document   : Myhistory
    Created on : 2018/06/22, 16:45:38
    Author     : ryo
--%>
<%@page import="main.ItemData"%>
<%@page import="java.util.ArrayList"%>
<%
    HttpSession s = request.getSession();
    ArrayList<ItemData> results = (ArrayList<ItemData>) s.getAttribute("history");
    ItemData id = null;
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Myhistory</title>
    </head>
    <body>
        <ol>
            <%for (int i = 0; i < results.size(); i++) {%>
            <li><img src="<%= results.get(i).getImage()%>" title="商品画像"><br>
                <%= results.get(i).getName()%>
                <%}%>
        </ol>
        <a href="./Top.jsp">トップへ</a><br>
        <a href="login">ログアウト</a><br>
    </body>
</html>

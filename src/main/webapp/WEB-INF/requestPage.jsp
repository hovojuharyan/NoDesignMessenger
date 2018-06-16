<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hovo
  Date: 6/16/18
  Time: 5:01 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Requests</title>
</head>
<body>
Requests To Me:<br>
<ul>
<c:forEach items="${requestsToMe}" var="reqto">
    <img src="/image?fileName=${reqto.source.picUrl}" width="200">
     <a href="/user/singlUserPage/${reqto.source.id}"><h3><li>${reqto.source.name} ${reqto.source.surname}</li></h3></a>
    <a href="/user/confirmRequest?id=${reqto.source.id}">Confirm--</a><a href="/user/deleteRequest?id=${reqto.source.id}">--Reject</a><br>
</c:forEach>
</ul>
Requests from me:<br>
<ul>
    <c:forEach items="${requestsFromMe}" var="reqFrom">
        <img src="/image?fileName=${reqFrom.destination.picUrl}" width="200"><br>
        <a href="/user/singlUserPage/${reqFrom.destination.id}"><h3><li>${reqFrom.destination.name} ${reqFrom.destination.surname}</li></h3></a>
        <a href="/user/deleteRequest?id=${reqFrom.destination.id}">Get Back Your Request</a><br><br>
    </c:forEach>
</ul>
</body>
</html>

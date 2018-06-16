<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hovo
  Date: 6/16/18
  Time: 1:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>All Users</title>
</head>
<body>
All Users:<br>
<c:forEach items="${allUsers}" var="user">
    <img src="/image?fileName=${user.picUrl}" width="150">
    <a href="/user/singlUserPage/${user.id}"><h3><li>${user.name} ${user.surname}</li></h3></a>
    <a href="/user/messageSingl/${user.id}">Get Messenger</a>---
    <a href="/user/sendRequest?id=${user.id}">Send Request</a><br>
</c:forEach>
</body>
</html>

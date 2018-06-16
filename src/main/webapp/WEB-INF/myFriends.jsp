<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hovo
  Date: 6/16/18
  Time: 6:09 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My Friends</title>
</head>
<body>
My friends:<br>
<ul>
    <c:forEach items="${myFriends}" var="friend">
        <img src="/image?fileName=${friend.friend.picUrl}">
        <a href="/user/singlUserPage/${friend.friend.id}"><h3><li>${friend.friend.name} ${friend.friend.surname}</li></h3></a>
        <a href="/user/deleteFriend?id=${friend.friend.id}">---Delete from friends list---</a><br>
    </c:forEach>
</ul>
</body>
</html>

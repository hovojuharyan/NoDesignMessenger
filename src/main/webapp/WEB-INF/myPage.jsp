<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: hovo
  Date: 6/16/18
  Time: 8:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${myself.name} ${myself.surname}</title>
</head>
<body>
Hi ${myself.name} ${myself.surname}<br>
<img src="/image?fileName=${myself.picUrl}" width="200">
<li><h3>${myself.name} ${myself.surname}</h3></li>
<a href="/user/friendsPage">---My Friends---</a>
<a href="/user/allUsersPage">---All Users---</a>
<a href="/user/requestPage">---My Requests---</a>
<a href="/logout">---Logout---</a>
<br>
<br>
My Friends:<br>
<ul>
    <c:forEach items="${myFriends}" var="friend">
        <img src="/image?fileName=${friend.friend.picUrl}" width="100">
        <a href="/user/singlUserPage/${friend.friend.id}"><li><h4>${friend.friend.name} ${friend.friend.surname}</h4></li></a>
        <a href="/user/messageSingl/${friend.friend.id}">Get Messenger</a>---
        <%--<a href="/user/deleteFriend?id=${friend.friend.id}">Delet from friend List</a>--%>
    </c:forEach>
</ul>
</body>
</html>

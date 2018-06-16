<%--
  Created by IntelliJ IDEA.
  User: hovo
  Date: 6/16/18
  Time: 8:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${thisUser.name} ${thisUser.surname}</title>
</head>
<body>
Hi ${thisUser.name} ${thisUser.surname}<br>
<img src="/image?fileName=${thisUser.picUrl}">
<li><h3>${thisUser.name} ${thisUser.surname}</h3></li>
<a href="/user/myPage">---My Page---</a>
<a href="/user/allUsersPage">---All Users---</a>
<a href="/logout">---Logout---</a>
</body>
</html>

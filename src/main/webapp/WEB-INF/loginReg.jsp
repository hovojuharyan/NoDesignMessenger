<%@ taglib prefix="spring" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: hovo
  Date: 6/16/18
  Time: 7:22 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login And Registration</title>
</head>
<body>
Login here:<br>
<form action="/loginReg" method="post" name="loginForm" >
    <input type="text" name="email">
    <input type="password" name="password">
    <input type="submit" value="LOGIN">
</form>
<br>
<br>
Registration here:<br>
<spring:form action="/Registration" method="post" modelAttribute="user" enctype="multipart/form-data">
    <spring:input path="name" title="name"/>
    <spring:input path="surname" title="surname"/>
    <spring:input path="email" title="email"/>
    <spring:input path="password" title="password"/>
    <input type="file" name="picture">
    <input type="submit" value="OK">
</spring:form>

</body>
</html>

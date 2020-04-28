<%--
  Created by IntelliJ IDEA.
  User: chels
  Date: 4/27/2020
  Time: 8:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <h1>Login Page</h1>
    <h2>Please enter your credentials:</h2>
    <form action="LoginCheck.jsp" method="post">
        <br/>Username:<input type="text" name="username">
        <br/>Password:<input type="password" name="password">
        <br/><input type="submit" value="Submit">
    </form>
</body>
</html>

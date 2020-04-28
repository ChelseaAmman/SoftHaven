<%--
  Created by IntelliJ IDEA.
  User: chels
  Date: 4/27/2020
  Time: 8:39 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Logout</title>
</head>
<body>
    <%
        session.removeAttribute("username");
        session.removeAttribute("password");
        session.invalidate();
    %>
    <h1>Logout successful!</h1>
</body>
</html>

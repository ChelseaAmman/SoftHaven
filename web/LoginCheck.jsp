<%--
  Created by IntelliJ IDEA.
  User: chels
  Date: 4/27/2020
  Time: 8:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>LoginCheck</title>
</head>
<body>
    <%
        String username=request.getParameter("username");
        String password=request.getParameter("password");

        if ((username.equals("user") && password.equals("pa$$"))){
            session.setAttribute("username", username);
            response.sendRedirect("index.jsp");
        }else{
            response.sendRedirect("Error.jsp");
        }
    %>
</body>
</html>

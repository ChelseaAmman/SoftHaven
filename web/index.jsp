<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>SoftHaven</title>
</head>
<body>
<div>
    <h2>Welcome to PortCall</h2>
</div>
<a href="${pageContext.request.contextPath}/shipAgent.jsp">Ship Agent</a>
<a href="${pageContext.request.contextPath}/shipMaster.jsp">Ship Master</a>
<a href="${pageContext.request.contextPath}/Customs.jsp">Customs Agent</a>
<a href="${pageContext.request.contextPath}/Logout.jsp">Logout</a>
</body>
</html>
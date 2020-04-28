
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ship Agent</title>
</head>
<body>
<h2> Submit Berthing Record Form </h2>
<form class="form" method="post" action="${pageContext.request.contextPath}/submitBR">
    <div>
        <label for="imoNum">IMO Number:</label>
        <input type="number" name="sNape" id="imoNum" value="@{imoNum}" />
    </div>
    <div>
        <label for="quay">Quay:</label>
        <input type="text" name="quay" id="quay" value="@{quay}" />
    </div>
    <div>
        <label for="bNum">Berth Number:</label>
        <input type="number" name="bNum" id="bNum" value="@{bNum}" />
    </div>
    <div>
        <label for="eta">Agent Info:</label>
        <input type="date" name="eta" id="eta" value="@{eta}" />
    </div>
    <div>
        <label for="ata">Arriving From:</label>
        <input type="date" name="ata" id="ata" value="@{ata}" />
    </div>
    <div>
        <label for="etd">ETA:</label>
        <input type="date" name="etd" id="etd" value="@{etd}" />
    </div>
    <div>
        <label for="atd">Berth Number:</label>
        <input type="date" name="atd" id="atd" value="@{atd}" />
    </div>
    <div>
        <label for="status">Status:</label>
        <input type="number" name="status" id="status" value="@{status}" />
    </div>
</form>
</div>
<a href="${pageContext.request.contextPath}/shipAgent.jsp">Ship Agent</a>
<a href="${pageContext.request.contextPath}/shipMaster.jsp">Ship Master</a>
<a href="${pageContext.request.contextPath}/Customs.jsp">Customs Agent</a>
<a href="${pageContext.request.contextPath}/Logout.jsp">Logout</a>
</body>
</body>
</html>

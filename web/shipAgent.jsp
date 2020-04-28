
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
        <input type="number" name="sNape" id="imoNum" required />
    </div>
    <div>
        <label for="quay">Quay:</label>
        <input type="text" name="quay" id="quay" required />
    </div>
    <div>
        <label for="bNum">Berth Number:</label>
        <input type="number" name="bNum" id="bNum" required />
    </div>
    <div>
        <label for="eta">ETA:</label>
        <input type="date" name="eta" id="eta" required />
    </div>
    <div>
        <label for="ata">ATA:</label>
        <input type="date" name="ata" id="ata" required />
    </div>
    <div>
        <label for="etd">ETD:</label>
        <input type="date" name="etd" id="etd" required />
    </div>
    <div>
        <label for="atd">ATD:</label>
        <input type="date" name="atd" id="atd" required />
    </div>
    <div>
        <label for="status">Status:</label>
        <input type="number" name="status" id="status" required />
    </div>
    <div>
        <a href="${pageContext.request.contextPath}/">Back</a> or <button type="submit" name="submit">Submit</button>
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

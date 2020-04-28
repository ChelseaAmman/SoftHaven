
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Ship Master</title>
</head>
<body>
<h2> Submit Pre Arrival Form </h2>
<form class="form" method="post" action="${pageContext.request.contextPath}/submitPaf">
    <div>
        <label for="sName">Ship Name:</label>
        <input type="text" name="sNape" id="sName" value="@{sName}" />
    </div>
    <div>
        <label for="callSign">Call Sign:</label>
        <input type="text" name="callSign" id="callSign" value="@{callSign}" />
    </div>
    <div>
        <label for="imoNum">IMO Number:</label>
        <input type="number" name="imoNum" id="imoNum" value="@{imoNum}" />
    </div>
    <div>
        <label for="agentInfo">Agent Info:</label>
        <input type="text" name="agentInfo" id="agentInfo" value="@{agentInfo}" />
    </div>
    <div>
        <label for="aForm">Arriving From:</label>
        <input type="text" name="aForm" id="aForm" value="@{aForm}" />
    </div>
    <div>
        <label for="eta">ETA:</label>
        <input type="date" name="eta" id="eta" value="@{eta}" />
    </div>
    <div>
        <label for="bNum">Berth Number:</label>
        <input type="number" name="bNum" id="bNum" value="@{bNum}" />
    </div>
    <div>
        <label for="nextPort">Next Port:</label>
        <input type="text" name="nextPort" id="nextPort"  value="@{nextPort}" />
    </div>
    <div>
        <label for="etd">ETD:</label>
        <input type="date" name="etd" id="etd"  value="@{etd}" />
    </div>
    <div>
        <label for="disCargoD">Discharging Cargo Description:</label>
        <input type="text" name="disCargoD" id="disCargoD"  value="@{disCargoD}" />
    </div>
    <div>
        <label for="disCargoA">Discharging Cargo Amount:</label>
        <input type="number" name="disCargoA" id="disCargoA"  value="@{disCargoA}" />
    </div>
    <div>
        <label for="loadCargoD">Loading Cargo Description:</label>
        <input type="text" name="loadCargoD" id="loadCargoD"  value="@{loadCargoD}" />
    </div>
    <div>
        <label for="loadCargoA">Loading Cargo Amount:</label>
        <input type="number" name="loadCargoA" id="loadCargoA"  value="@{loadCargoA}" />
    </div>
    <div>
        <label for="nPassArr">Number of Passengers on Arrival:</label>
        <input type="number" name="nPassArr" id="nPassArr"  value="@{nPassArr}" />
    </div>
    <div>
        <label for="nPassDep">Number of Passengers on Departure:</label>
        <input type="number" name="nPassDep" id="nPassDep"  value="@{nPassDep}" />
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

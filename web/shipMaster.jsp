
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
        <input type="text" name="sNape" id="sName" required />
    </div>
    <div>
        <label for="callSign">Call Sign:</label>
        <input type="text" name="callSign" id="callSign" required />
    </div>
    <div>
        <label for="imoNum">IMO Number:</label>
        <input type="number" name="imoNum" id="imoNum" required />
    </div>
    <div>
        <label for="agentInfo">Agent Info:</label>
        <input type="text" name="agentInfo" id="agentInfo" required />
    </div>
    <div>
        <label for="aForm">Arriving From:</label>
        <input type="text" name="aForm" id="aForm"required />
    </div>
    <div>
        <label for="eta">ETA:</label>
        <input type="date" name="eta" id="eta" required />
    </div>
    <div>
        <label for="bNum">Berth Number:</label>
        <input type="number" name="bNum" id="bNum" required />
    </div>
    <div>
        <label for="nextPort">Next Port:</label>
        <input type="text" name="nextPort" id="nextPort" required />
    </div>
    <div>
        <label for="etd">ETD:</label>
        <input type="date" name="etd" id="etd" required />
    </div>
    <div>
        <label for="disCargoD">Discharging Cargo Description:</label>
        <input type="text" name="disCargoD" id="disCargoD" required />
    </div>
    <div>
        <label for="disCargoA">Discharging Cargo Amount:</label>
        <input type="number" name="disCargoA" id="disCargoA" required />
    </div>
    <div>
        <label for="loadCargoD">Loading Cargo Description:</label>
        <input type="text" name="loadCargoD" id="loadCargoD" required />
    </div>
    <div>
        <label for="loadCargoA">Loading Cargo Amount:</label>
        <input type="number" name="loadCargoA" id="loadCargoA" required />
    </div>
    <div>
        <label for="nPassArr">Number of Passengers on Arrival:</label>
        <input type="number" name="nPassArr" id="nPassArr" required />
    </div>
    <div>
        <label for="nPassDep">Number of Passengers on Departure:</label>
        <input type="number" name="nPassDep" id="nPassDep" required />
    </div>
    <div>
        <a href="${pageContext.request.contextPath}/">Back</a> or <button type="submit" name="submit">Submit</button>
    </div>
</form>
</body>
</html>

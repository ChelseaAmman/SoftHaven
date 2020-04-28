<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customs</title>
</head>
<body>
    <h2> Pre Arrival Forms Queue </h2>
<table>
    <c:forEach var="paf" items="@{pafs}">
        <tr>
            <td>Ship Name: ${paf.sName}</td>
            <td>Call Sign: ${paf.callSign}</td>
            <td>IMO Number: ${paf.imoNum}</td>
            <td>Agent Information: ${paf.agentInfo}</td>
            <td>Arriving From: ${paf.aForm}</td>
            <td>ETA: ${paf.eta}</td>
            <td>Berth Number: ${paf.bNUm}</td>
            <td>Next Port: ${paf.nextPort}</td>
            <td>ETD: ${paf.etd}</td>
            <td>Discharge Cargo Description: ${paf.disCargoD}</td>
            <td>Discharge Cargo Amount: ${paf.disCargoA}</td>
            <td>Loading Cargo Description: ${paf.loadCargoD}</td>
            <td>Loading Cargo Amount: ${paf.loadCargoA}</td>
            <td>Agent Information: ${paf.agentInfo}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>

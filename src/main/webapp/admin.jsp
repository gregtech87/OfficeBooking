<%@ page contentType="text/html; charset=iso-8859-1" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tieto Booking</title>
    <link rel="icon" href="image/favicon.ico">
    <link rel="stylesheet" href="styleSheets/style.css" type="text/css">
    <link rel="stylesheet" href="styleSheets/admin.css" type="text/css">
    <script defer> const permanentSeats = ${permanentSeatListFromServer};</script>
    <script defer> const error = ${credentialsErrorMessage};</script>
    <script defer src="scriptFiles/admin.js"></script>

</head>
<body class="body" onload="buildTable(); selectedRowToInput();">
<%--    Comment out this line if you want to use dev-tools.--%>
<%--<script disable-devtool-auto src='https://cdn.jsdelivr.net/npm/disable-devtool'></script>--%>

<script>
    // Display error if booking with unauthorised symbols occurred.
    let a = error;
    if(a !== "ok"){
        alert("Fel uppgifter eller så matchar inte dom nya lösenorden.\nÄndring av admin uppgifer inte genomförd.");
    }
</script>

<div class="banner">
    <%--        Logo       --%>
    <img alt="image1" src="image/tietoevryLogoWhite.png" width="250" class="logo">
</div>
<img alt="image3" src="image/background.jpg" class="background">
<img alt="image2" src="image/kontoret2.png" width="60%" class="miniMap">

<div class="container">
    <div class="dataTable">
        <table id="myDataTable" class="table-sortable">
            <thead>
            <tr>
                <th>tableId</th>
                <th>name</th>
                <th>latestEdit</th>
                <th>permanentPlace</th>
            </tr>
            </thead>
            <tbody id="myDataTableBody">
            </tbody>
        </table>
    </div>

    <div class="tab tab-2">
        Bordsnummer :<input type="number" name="age" id="newTableID">
        Namn :<input type="text" name="fname" id="fullName">
        <button onclick="addHtmlTableRow()">Lägg till</button>
        <button onclick="editHtmlTableSelectedRow()">ändra</button>
        <button onclick="removeSelectedHtmlTableRow()">Ta bort</button>
        <button onclick="tableToJson()" class="saveButton">Spara ändringar</button>
    </div>
</div>

<form>
    <button type="submit" class="admin2-button">Byt lösenord</button>
</form>

<div id="login2-overlay" class="overlay2" style="display: none;">
    <div class="login-box2">
        <h2>Byte av admin namn och lösenord</h2>
        <form id="admin2-login-form" action="changeAdminPassword" method="post">
            <input type="text" name="oldUsername" id="admin2-username" placeholder="Befintligt användarnamn" required>
            <input type="text" name="newUsername" id="admin2-newUsername" placeholder="Nytt användarnamn" required>
            <input type="password" name="oldPassword" id="admin2-password" placeholder="Gammalt lösenord" required>
            <input type="password" name="newPassword" id="admin2-newPassword" placeholder="Nytt lösenord" required>
            <input type="password" name="newPassword2" id="admin2-newPassword2" placeholder="Nytt lösenord" required>
            <div class="button-container2">
                <button type="submit">uppdatera</button>
                <button type="button" id="cancel2-button">Avbryt</button>
            </div>
        </form>
    </div>
</div>

<form action="index.jsp" method="get" class="backButton">
    <button type="submit" class="backButton">Tillbaka</button>
</form>
<form action="5w5iUnWfFs$*yTQ'44n_vn+P7')'+C*Tquo_._X9" method="get">
    <button type="submit"  class="errorButton">Error logg</button>
</form>

<form id="autoForm" action="eZ$!sypN2qAYZCyFyJkFybh_K$Vnf-prk)v)X9Ay" method="post">
    <input type="hidden" name="permTableToJson" id="permTable">
</form>
</body>
</html>
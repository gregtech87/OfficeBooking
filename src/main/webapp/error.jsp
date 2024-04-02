<%@ page contentType="text/html; charset=iso-8859-1" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tieto Booking</title>
    <link rel="icon" href="image/favicon.ico">
    <link rel="stylesheet" href="styleSheets/style.css" type="text/css">
    <link rel="stylesheet" href="styleSheets/error.css" type="text/css">
    <script defer> const errorTables = ${errorTableListFromServer};</script>
    <script defer> const errorRooms = ${errorRoomListFromServer};</script>
    <script defer src="scriptFiles/error.js"></script>
</head>
<body class="body" onload="buildTable();">
<%--    Comment out this line if you want to use dev-tools.--%>
<%--<script disable-devtool-auto src='https://cdn.jsdelivr.net/npm/disable-devtool'></script>--%>

<div class="banner">
    <%--        Logo       --%>
    <img alt="image1" src="image/tietoevryLogoWhite.png" width="250" class="logo">
</div>
<img alt="image3" src="image/background.jpg" class="background">

<div class="container">
    <div class="dataTable">
        <table id="myDataTable" class="table-sortable">
            <thead>
            <tr>
                <th>tableId</th>
                <th>name</th>
                <th>dateOfBooking</th>
                <th>beforeLunch</th>
                <th>afterLunch</th>
                <th>fullDay</th>
                <th>latestEdit</th>
                <th>permanentPlace</th>
            </tr>
            </thead>
            <tbody id="myDataTableBody">
            </tbody>
        </table>
    </div>
</div>
<div class="container2">
    <div class="dataTable2">
        <table id="myDataTable2" class="table-sortable">
            <thead>
            <tr>
                <th>roomId</th>
                <th>name</th>
                <th>dateOfBooking</th>
                <th>startTime</th>
                <th>endTime</th>
                <th>fullDay</th>
                <th>latestEdit</th>
                <th>permanentBooked</th>
            </tr>
            </thead>
            <tbody id="myDataTableBody2">
            </tbody>
        </table>
    </div>
</div>

<form action="eZ$!sypN2qAYZCyFyJkFybh_K$Vnf-prk)v)X9Ay" method="get" class="backButton">
    <button type="submit">Tillbaka</button>
</form>
</body>
</html>
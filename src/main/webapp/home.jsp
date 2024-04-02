<%@ page contentType="text/html; charset=iso-8859-1" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Tieto Booking</title>
    <link rel="icon" href="image/favicon.ico">
    <link rel="stylesheet" href="styleSheets/style.css" type="text/css">
    <script> const errorBooking = ${errorBooking};</script>
    <script> const errorLogin = ${errorLogin};</script>
    <script> const tables = ${tableListFromServer};</script>
    <script> const rooms = ${roomListFromServer};</script>
    <script> const permanentSeats = ${permanentSeatListFromServer};</script>
    <script defer src="scriptFiles/bookingHandlerTables.js"></script>
    <script defer src="scriptFiles/reNameLater.js"></script>
    <script defer src="scriptFiles/checkboxHandler.js"></script>
    <script defer src="scriptFiles/bookingHandlerRooms.js"></script>
</head>
<body class="body" onload="setButtonColors()">
<%--    Comment out this line if you want to use dev-tools.--%>
<%--<script disable-devtool-auto src='https://cdn.jsdelivr.net/npm/disable-devtool'></script>--%>

<script>
    // Display error if booking with unauthorised symbols occurred.
    let a = errorBooking;
    let b = errorLogin;
    console.log(a);
    console.log(b)
    if(a !== "ok"){
        alert("Ogiltiga tecken användes vid bokning.\nBokning inte genomförd.");
    }
    if (b !== "ok"){
        alert("Fel användarnamn eller lösenord!");
    }
</script>

<div class="banner">
    <%--        Logo       --%>
    <img alt="image1" src="image/tietoevryLogoWhite.png" width="250" class="logo">
</div>

<div class="dropdown" id="dropdownDiv">
    <div class="dropdown-toggle" id="toggleDropdown"><img src="image/MenuIcon.svg" class="menuIcon"></div>
    <div class="dropdown-menu" id="dropdownMenu">
        <form>
            <button type="submit" class="admin-button">Admin</button>
        </form>
        <button class="help-button" onclick="toggleInstructions()">Hjälp</button>
    </div>
</div>
<div id="popupHelpContainer">
    <div id="popupHelpBox">
        <span id="closeHelpButton" onclick="toggleInstructions()">&times;</span>
        <strong>Boka Bord:</strong>
        <ol>
            <li>
                För att boka ett bord, klicka på det önskade bordet. En popup-ruta visas där du kan ange ditt namn och välja datum för bokningen.
            </li>
            <li>
                Efter att du har angett ditt namn och valt datum, titta nedanför för att se bokningsinformationen. Om det står ett namn på "Heldag", "Förmiddag" eller "Eftermiddag" indikerar det att någon har redan bokat den tidsperioden. Om någon av rutorna är borta, betyder det att den tiden redan är bokad. Om alla rutorna är borta är det fullbokat för dagen.
            </li>
            <li>
                När du har bekräftat tillgängligheten och väljer en ledig tid, klicka på "Boka" för att slutföra bokningen.
            </li>
            <p>
                Observera att borden är markerade med olika färger. Grå bord är fasta platser, ljusblå bord är lediga och laxrosa bord indikerar att bordet är fullbokat för hela dagen. Om bordet har en laxrosa kant är det endast halva dagen bokad, och du kan boka den andra halvan.
            </p>
        </ol>
        <strong>Boka Rum:</strong>
        <ol>
            <li>
                För att boka ett rum, klicka på det önskade rummet. En popup-ruta visas där du kan ange ditt namn och välja datum för bokningen.
            </li>
            <li>
                Efter att du har angett ditt namn och valt datum, titta längre ned på sidan för att se en lista över alla bokningar för rummet under den valda dagen.
            </li>
            <li>
                Under datumet finns två tidrutor: "Starttid" och "Sluttid". Ange den önskade tiden för bokningen. Starttiden kan vara tidigast 08:00 och sluttiden kan vara senast 17:00. Observera att du inte kan boka över någon annans tid, och sluttiden måste vara senare än starttiden.
            </li>
            <li>
                Om du vill boka hela dagen, markera kryssrutan för "Heldag". Tiden ställs automatiskt in på 08:00-17:00 och ingen annan kan boka rummet för dagen.
            </li>
            <li>
                När du har bekräftat tillgängligheten och valt önskad tid, klicka på "Boka" för att slutföra bokningen.
            </li>
            <li>
                Rummen är märkta med tre olika färger: orange, grön, mörkblå och ljusblå. Som matchar mot fönstrens färger till rummen.
            </li>
        </ol>
    </div>
</div>

<div id="login-overlay" class="overlay" style="display: none;">
    <div class="login-box">
        <h2>Admin Login</h2>
        <form id="admin-login-form" action="login" method="post">
            <input type="text" name="username" id="admin-username" placeholder="Användarnamn" required>
            <input type="password" name="password" id="admin-password" placeholder="Lösenord" required>
            <div class="button-container">
                <button type="submit">Logga in</button>
                <button type="button" id="cancel-button">Avbryt</button>
            </div>
        </form>
    </div>
</div>


<img alt="image3" src="image/background.jpg" class="background" >

<%--       Layout      --%>
<div id="layout" class="layout">

    <%--        Table popup form      --%>
    <div id="booking-popup" class="popup" style="display: none" >
        <h2 id="popup-Heading">Bokning av&lt;br&gt;Kontorsplats</h2>
        <form id="tableBooking-popup" onsubmit="return handleData()" action="DataFromHtmlToServer" method="post" >
            <input type="hidden" name="bookedTable" id="tableID"  >
            <input type="text" name="nameTable" id="name" placeholder="Namn" required style="width: 88%;">
            <label for="dateInput" id="dateLabel" style="font-size: 100%;">Datum:</label>
            <input type="date" name="dateInputTable" id="dateInput" required oninput="dateFromForm(this.value)">
            <br id="brID">
            <br>
            <label style="font-size: 100%; padding-left: 64px;">Bokningar </label>
            <br>
            <label id="wholeDayText" style="font-size: 100%; padding-left: 34px;"></label>
            <label id="wholeDayTable" style="font-size: 100%;"></label>
            <br>
            <label id="beforeLunchText" style="font-size: 100%; padding-left: 10px;"></label>
            <label id="beforeLunchTable" style="font-size: 100%;"></label>
            <br>
            <label id="afterLunchText" style="font-size: 100%;"></label>
            <label id="afterLunchTable" style="font-size: 100%;"></label>
            <br><br id="brID2">
            <label id="beforeLunchLabel" style="font-size: 100%; padding: 10px; padding-inline-end: 28px">Förmiddag
                <input type="checkbox" class="checkoption" id="beforeLunchBox" value="on" onclick="checkedOnClick(this)" name="beforeLunchInputTable">
            </label>
            <label id="wholeDayLabel" style="font-size: 100%;">Heldag
                <input type="checkbox" class="checkoption" id="wholeDayBox" value="on" onclick="checkedOnClick(this)" name="wholeDayInputTable">
            </label>
            <br>
            <label id="afterLunchLabel" style="font-size: 100%;">Eftermiddag
                <input type="checkbox" class="checkoption" id="afterLunchBox" value="on" onclick="checkedOnClick(this)" name="afterLunchInputTable">
            </label>
            <br>
            <label style="visibility:hidden; color:red; " id="chk_option_error">Var god välj en tidpunkt.</label>
            <button type="submit" id="submitButtonTable" class="booking">Boka</button>
            <button type="reset" class="cancel" onclick="cancelTablePopup()">Avbryt</button>
        </form>
    </div>

    <%--        Room popup form     --%>
    <div id="roomBooking-popup" class="roomPopup" style="display: none">
        <h2 id="roomPopup-Heading">Bokning av rum </h2>
        <form id="roomBooking-form" action="DataFromHtmlToServer" method="post">
            <p id="validationMessage" style="display: none; color: red;">Det finns redan en bokning innanför den tiden.</p>
            <input type="text" name="nameRoom" id="name2" placeholder="Namn" required style="width: 88%;">
            <label for="dateInput2" id="dateLabel2" style="font-size: 100%;">Datum:</label>
            <input type="date" name="dateInputRoom" id="dateInput2" required oninput="dateFromFormRoom(this.value)"><br><br id="roomBrID">
            <label for="startTimeInput2" id="startTimeID2" style="font-size: 100%;">Starttid:</label>
            <label for="endTimeInput2" id="endTimeID2" style="font-size: 100%;">Sluttid:</label><br>
            <input type="hidden" name="bookedRoom" id="roomID">
            <input type="time" step="900" min="08:00" max="17:00" name="startTimeInputRoom" id="startTimeInput2" onchange="roomStartTimeToForm(this.value); checkBookingTime(startTime,endTime)" required>
            <input type="time" step="900" min="08:00" max="17:00" name="endTimeInputRoom" id="endTimeInput2" onchange="roomEndTimeToForm(this.value); checkBookingTime(startTime,endTime)" required>
            <label class="container2" id="wholeDayLabel2" style="font-size: 100%;">Heldag
                <input type="checkbox" onchange="changeTime2(this)" name="wholeDayInputRoom">
                <span class="checkmark"></span>
            </label>
            <br id="roomBrID2">
            <label style="font-size: 100%; padding-left: 64px;">Bokningar </label>
            <br>
            <ol id="bookingList" style="font-size: 100%; height: 315px; overflow-y: auto;">
            </ol>
            <br>
            <br>
            <button type="submit" class="booking2" id="bookButton">Boka</button>
            <button type="reset" class="cancel2" onclick="cancelRoomPopup()">Avbryt</button>
        </form>
    </div>


    <%--        Map       --%>
    <img alt="image2" src="image/kontoret.png" width="68%" class="map">

    <%--        Iterate all tablebuttons       --%>
    <%for (int i = 1; i <= 61; i++) { %>
    <input type="button" class="tableButton" id="id<%= i %>" name="<%= i %>" onClick="tableBooking(this.name)">
    <% } %>

    <%--      Room buttons       --%>
    <input type="button" class="roomButtonTop" id="roomId1" name="Telesto" onclick="roomBooking(this.name)">
    <input type="button" class="roomButtonTop" id="roomId2" name="Europa" onclick="roomBooking(this.name)">
    <input type="button" class="roomButtonMiddle" id="roomId3" name="Titan" onclick="roomBooking(this.name)">
    <input type="button" class="roomButtonMiddle" id="roomId4" name="Triton" onclick="roomBooking(this.name)">
    <input type="button" class="roomButtonMiddleBottom" id="roomId5" name="Sedna" onclick="roomBooking(this.name)">
    <input type="button" class="roomButtonBottom" id="roomId6" name="Pluto" onclick="roomBooking(this.name)">
    <input type="button" class="roomButtonBottom" id="roomId7" name="Eris" onclick="roomBooking(this.name)">
    <input type="button" class="roomButtonBottom" id="roomId8" name="Io" onclick="roomBooking(this.name)">

    <div id="mapTextLayout">
        <label class="mapText" id="toilet1">WC</label>
        <label class="mapText" id="toilet2">WC</label>
        <label class="mapText" id="kapprum">Kapprum</label>
        <label class="mapText" id="reception1">Reception</label>
        <label class="mapText" id="reception2">Reception</label>
        <label class="mapText" id="skrivarrum">Kopiatorrum</label>
        <label class="mapText" id="tystarum">Tysta Rum</label>
        <label class="mapText" id="closet">Städ</label>
        <label class="mapText" id="trapphus">Trapphus</label>
        <label class="mapText" id="kitchen">Kök</label>
        <label class="mapText" id="uteplats">Uteplats</label>
    </div>
    <div id="explanationBanner">
        <div id="showFreeTableLook"></div>
        <label id="freeTableLabel">Ledigt Bord</label>
        <div id="showHalfBookedTableLook"></div>
        <label id="halfBookedLabel">Halvdag bokad</label>
        <div id="showPermanentTableLook"></div>
        <label id="permanentBookingLabel">Permanent bokning</label>
        <div id="showBookedTableLook"></div>
        <label id="wholeDayShowLabel">Heldag bokad</label>
    </div>

</div>
</body>
</html>
function tableBooking(tableNames) {
    displayTablePopupInsteadOfRoom();

    // Get all office table buttons
    const tableButtons = document.querySelectorAll('.tableButton');

    // Get the booking popup
    const bookingPopup = document.getElementById('booking-popup');

    // Show the booking popup on page load
    bookingPopup.style.display = 'block';

    // Initialize the tableNames variable
    let currentTable = tableNames;
    document.getElementById("tableID").value = tableNames;
    tableIdToForm(currentTable);
    scanTableForBookings(currentTable, null);
    const popupHeading = document.querySelector("#popup-Heading");
    popupHeading.innerHTML = `Bokning av<br>Kontorsplats ${currentTable}`;

    // Loop through all table buttons and add a click event listener
    tableButtons.forEach(button => {
        button.addEventListener('click', function (event) {
            currentTable = event.target.name;

            // Show the booking popup
            bookingPopup.style.display = 'block';

            const popupHeading = document.querySelector("#popup-Heading");
            popupHeading.innerHTML = `Bokning av<br>Kontorsplats ${currentTable}`;
        });
    });
}

function cancelTablePopup() {
    const bookingPopup = document.getElementById('booking-popup');
    // Hide the booking popup
    bookingPopup.style.display = 'none';
}

function roomBooking(roomNames) {
    displayRoomPopupInsteadOfTable();

    // Get all office room buttons
    const roomButtons = document.querySelectorAll('.roomButtonTop,.roomButtonMiddle,.roomButtonMiddleBottom,.roomButtonBottom');

    // Get the booking popup
    const roomBookingPopup = document.getElementById('roomBooking-popup');

    // Show the booking popup on page load
    roomBookingPopup.style.display = 'block';

    // Initialize the roomNames variable
    let currentRoom = roomNames;
    document.getElementById("roomID").value = roomNames;
    roomIdToForm(currentRoom);
    scanRoomForBookings(null, null, currentRoom, null);
    const roomPopupHeading = document.querySelector("#roomPopup-Heading");
    roomPopupHeading.innerText = `Bokning av rum ${currentRoom}`;

    // Loop through all room buttons and add a click event listener
    roomButtons.forEach(button => {
        button.addEventListener('click', function (event) {
            currentRoom = event.target.name;

            // Show the booking popup
            roomBookingPopup.style.display = 'block';

            const popupHeading = document.querySelector("#roomPopup-Heading");
            roomPopupHeading.innerText = `Bokning av rum ${currentRoom}`;
        });
    });

    // Get the validation elements
    const validationMessage = document.getElementById("validationMessage");
    const bookButton = document.getElementById("bookButton");

    const startTimeInput = document.getElementById('startTimeInput2');
    const endTimeInput = document.getElementById('endTimeInput2');
    let startTime = ""; // Declare startTime variable
    let endTime = ""; // Declare endTime variable

    startTimeInput.addEventListener('input', function () {
        startTime = this.value;
        checkBookingTime();
    });

    endTimeInput.addEventListener('input', function () {
        endTime = this.value;
        checkBookingTime();
    });
}

function cancelRoomPopup() {
    const roomBookingPopup = document.getElementById('roomBooking-popup');
    // Hide the booking popup
    roomBookingPopup.style.display = 'none';
}

function changeTime2(checkbox) {
    const startTimeInput = document.getElementById('startTimeInput2');
    const endTimeInput = document.getElementById('endTimeInput2');
    if (checkbox.checked) {
        startTimeInput.value = '08:00';
        endTimeInput.value = '17:00';
    } else {
        startTimeInput.value = '';
        endTimeInput.value = '';
    }
}

function displayTablePopupInsteadOfRoom() {
    const roomBookingPopup = document.getElementById('roomBooking-popup');
    if (roomBookingPopup.style.display === 'block') {
        roomBookingPopup.style.display = 'none';
    }
    const sideBar = document.getElementById('dropdownMenu');
    if (sideBar.style.display == 'block') {
        sideBar.style.display = 'none';
        document.getElementById("dropdownDiv").style.width = "0%";
    }
}

function displayRoomPopupInsteadOfTable() {
    const bookingPopup = document.getElementById('booking-popup');
    if (bookingPopup.style.display === 'block') {
        bookingPopup.style.display = 'none';
    }
    const sideBar = document.getElementById('dropdownMenu');
    if (sideBar.style.display == 'block') {
        sideBar.style.display = 'none';
        document.getElementById("dropdownDiv").style.width = "0%";
    }
}

let dropdownMenu = document.getElementById("dropdownMenu");
dropdownMenu.style.display = "none";
function toggleDropdown() {
    if (dropdownMenu.style.display === "none") {
        dropdownMenu.style.display = "block";
        const bookingPopup = document.getElementById('booking-popup');
        bookingPopup.style.display = 'none';
        const roomBookingPopup = document.getElementById('roomBooking-popup');
        roomBookingPopup.style.display = 'none';
        document.getElementById("dropdownDiv").style.width = "10%";
    } else {
        dropdownMenu.style.display = "none";
        document.getElementById("dropdownDiv").style.width = "0%";
    }
}

document.getElementById("toggleDropdown").addEventListener("click", toggleDropdown);

// Visa inloggningsrutan när "Admin" -knappen klickas
document.querySelector(".admin-button").addEventListener("click", function(e) {
    e.preventDefault();
    document.getElementById("login-overlay").style.display = "flex";
});

// Hantera avbryt-knappen
document.getElementById("cancel-button").addEventListener("click", function(e) {
    e.preventDefault();
    document.getElementById("login-overlay").style.display = "none";
});

function toggleInstructions() {
    var popupHelpContainer = document.getElementById('popupHelpContainer');

    // Om popup-rutan är synlig, dölj den
    if (popupHelpContainer.style.display === 'block') {
        popupHelpContainer.style.display = 'none';
    } else {
        // Visa popup-rutan
        popupHelpContainer.style.display = 'block';
    }
}

// Lägg till händelselyssnare för att stänga popup-rutan när användaren klickar utanför den
window.addEventListener('click', function(event) {
    var popupHelpContainer = document.getElementById('popupHelpContainer');
    if (event.target === popupHelpContainer) {
        popupHelpContainer.style.display = 'none';
    }
});

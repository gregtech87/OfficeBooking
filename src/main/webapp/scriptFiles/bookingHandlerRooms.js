let roomArray = rooms;

console.log(roomArray);

let room = "";
let startTime = "";
let endTime = "";

function scanRoomForBookings(incomingStartTime, incomingEndTime, currentRoom, incomingDate) {
    const validationMessage = document.getElementById("validationMessage");
    const bookButton = document.getElementById("bookButton");
    validationMessage.style.display = "none";

    // If no date is selected, use today's date
    const date = incomingDate || new Date().toISOString().slice(0, 10);

    if (checkBookingOverlap(incomingStartTime, incomingEndTime, currentRoom, date)) {
        validationMessage.style.display = "block";
        // Disable the book button when the time is already booked
        bookButton.disabled = true;
        return;
    }

    const bookingList = document.getElementById("bookingList");
    bookingList.innerHTML = searchRoomData(incomingStartTime, incomingEndTime, currentRoom, date);

    // Enable the book button when the time is available
    bookButton.disabled = false;
}

function checkBookingOverlap(startTime, endTime, currentRoom, date) {
    const bookings = roomArray.filter(
        r => r.dateOfBooking === date && r.roomId === currentRoom
    );

    const overlappingBooking = bookings.find(booking => {
        return (
            (startTime >= booking.startTime && startTime < booking.endTime) ||
            (endTime > booking.startTime && endTime <= booking.endTime) ||
            (startTime <= booking.startTime && endTime >= booking.endTime) ||
            (startTime === booking.startTime && endTime === booking.endTime)
        );
    });

    return overlappingBooking !== undefined;
}

function searchRoomData(startTime, endTime, currentRoom, date) {
    const bookings = roomArray.filter(
        r => r.dateOfBooking === date && r.roomId === currentRoom
    );

    const sortedBookings = bookings.sort((a, b) =>
        a.startTime.localeCompare(b.startTime)
    );

    const bookingList = sortedBookings.map(
        booking => `${booking.name} ${booking.startTime}-${booking.endTime}`
    );

    return bookingList.map(booking => `<li>${booking}</li>`).join('');
}

function dateFromFormRoom(date) {
    console.log(date);
    scanRoomForBookings(startTime, endTime, room, date);
}

function roomStartTimeToForm(currentStartTime) {
    startTime = currentStartTime;
    console.log("startTime: " + currentStartTime);
}

function roomEndTimeToForm(currentEndTime) {
    endTime = currentEndTime;
    console.log("endTime: " + currentEndTime);
}

function roomIdToForm(currentRoom) {
    room = currentRoom;
    console.log("hidden: " + room);
}

function changeTime2(checkbox) {
    const startTimeInput = document.getElementById('startTimeInput2');
    const endTimeInput = document.getElementById('endTimeInput2');

    if (checkbox.checked) {
        startTimeInput.value = '08:00';
        endTimeInput.value = '17:00';
        checkBookingTime('08:00', '17:00');
    } else {
        startTimeInput.value = '';
        endTimeInput.value = '';
        checkBookingTime(startTime, endTime);
    }
}

function checkBookingTime(startTime, endTime) {
    const currentDate = new Date().toISOString().slice(0, 10);
    const dateInput = document.getElementById('dateInput2');
    const validationMessage = document.getElementById("validationMessage");
    const bookButton = document.getElementById("bookButton");
    const wholeDayCheckbox = document.querySelector('input[name="wholeDayInputRoom"]');
    const currentRoom = room;

    const date = dateInput.value || currentDate;

    validationMessage.style.display = "none";

    if (date < currentDate) {
        console.log("Du kan inte boka en tid efter dagens datum");
        validationMessage.innerText = "Du kan inte boka en tid efter dagens datum";
        validationMessage.style.display = "block";
        bookButton.disabled = true;
        return;
    }

    if (wholeDayCheckbox.checked) {
        const wholeDayBookingExists = checkWholeDayBookingExists(currentRoom, date);
        if (wholeDayBookingExists) {
            console.log("Det finns redan en heldagsbokning för den valda dagen");
            validationMessage.innerText = "Det finns redan en heldagsbokning för den valda dagen";
            validationMessage.style.display = "block";
            bookButton.disabled = true;
            return;
        }

        const bookingWithinWholeDayExists = checkBookingWithinWholeDayExists(currentRoom, date);
        if (bookingWithinWholeDayExists) {
            console.log("Det finns redan en bokning innanför den tiden");
            validationMessage.innerText = "Det finns redan en bokning innanför den tiden";
            validationMessage.style.display = "block";
            bookButton.disabled = true;
            return;
        }
    } else {
        if (startTime && endTime) {
            if (endTime <= startTime) {
                console.log("Sluttiden kan inte vara satt före eller samtidigt som starttiden");
                validationMessage.innerText = "Sluttiden kan inte vara satt före eller samtidigt som starttiden";
                validationMessage.style.display = "block";
                bookButton.disabled = true;
                return;
            }

            if (checkBookingOverlap(startTime, endTime, currentRoom, date)) {
                console.log("Det finns redan en bokning innanför den tiden");
                validationMessage.innerText = "Det finns redan en bokning innanför den tiden";
                validationMessage.style.display = "block";
                bookButton.disabled = true;
                return;
            }
        }
    }

    validationMessage.style.display = "none";
    bookButton.disabled = false;

    scanRoomForBookings(startTime, endTime, currentRoom, date);
}

function checkWholeDayBookingExists(currentRoom, date) {
    const bookings = roomArray.filter(
        r => r.dateOfBooking === date && r.roomId === currentRoom
    );

    const wholeDayBooking = bookings.find(booking => {
        return booking.startTime === "08:00" && booking.endTime === "17:00";
    });

    return wholeDayBooking !== undefined;
}

function checkBookingWithinWholeDayExists(currentRoom, date) {
    const bookings = roomArray.filter(
        r => r.dateOfBooking === date && r.roomId === currentRoom
    );

    const bookingWithinWholeDay = bookings.find(booking => {
        return (
            (booking.startTime >= "08:00" && booking.endTime <= "17:00") ||
            (booking.startTime < "08:00" && booking.endTime > "08:00") ||
            (booking.startTime < "17:00" && booking.endTime > "17:00")
        );
    });

    return bookingWithinWholeDay !== undefined;
}

// Add a submit event listener to the booking form
document.getElementById('roomBooking-form').addEventListener('submit', event => {
    const validationMessage = document.getElementById("validationMessage");

    // Prevent form submission if the time is already booked
    if (validationMessage.style.display === "block") {
        event.preventDefault();
        // Add any additional handling for preventing form submission
    }
});

// Lägg till en händelselyssnare för ändringar i datumfältet
const dateInput2 = document.getElementById('dateInput2');
dateInput2.addEventListener('change', function() {
    checkBookingTime(startTime, endTime);
});

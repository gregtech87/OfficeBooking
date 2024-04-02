let tableArray = tables;
let permanentArray = permanentSeats;
let currentDateString = new Date().toLocaleString().slice(0, 10);
let bl;
let al;
console.log(tableArray);
console.log(permanentArray);


function scanTableForBookings(currentTable, incomingDate) {

    bl = 0
    al = 0

    if (incomingDate === null) {
        incomingDate = currentDateString;
    }
    checkboxController("clearBoxes")
    document.getElementById("beforeLunchTable").innerHTML = searchTableData("beforeLunch", currentTable, incomingDate);
    document.getElementById("afterLunchTable").innerHTML = searchTableData("afterLunch", currentTable, incomingDate);
    document.getElementById("wholeDayTable").innerHTML = searchTableData("wholeDay", currentTable, incomingDate);

    console.log(bl)
    console.log(al)
    if (bl === 1 && al === 1) {
        checkboxController("beforeAndAfterLunch")
    }
}

function searchTableData(timeOfDay, currentTable, date) {
    // sets name on popup for related booking.
    let name = "";
    document.getElementById("beforeLunchText").innerHTML = "Förmiddag: ";
    document.getElementById("afterLunchText").innerHTML = "Eftermiddag: ";
    document.getElementById("wholeDayText").innerHTML = "Heldag: ";

    if (timeOfDay === "beforeLunch") {
        tableArray.forEach((t) => {
            if (t.dateOfBooking === date && parseInt(currentTable) === t.tableId && t.permanentPlace !== "on" && t.beforeLunch === "on") {
                name = t.name;
                bl = checkboxController(timeOfDay)
            }
        })
    }

    if (timeOfDay === "afterLunch") {
        tableArray.forEach((t) => {
            if (t.dateOfBooking === date && parseInt(currentTable) === t.tableId && t.permanentPlace !== "on" && t.afterLunch === "on") {
                name = t.name;
                al = checkboxController(timeOfDay)
            }
        })
    }

    if (timeOfDay === "wholeDay") {
        permanentArray.forEach((p) => {
            if (parseInt(currentTable) === p.tableId && p.permanentPlace === "on") {
                name = p.name;
                checkboxController("permanent")
                document.getElementById("beforeLunchText").innerHTML = "";
                document.getElementById("afterLunchText").innerHTML = "";
                document.getElementById("wholeDayText").innerHTML = "Fast Plats: ";
            } else {
                tableArray.forEach((t) => {
                    if (t.dateOfBooking === date && parseInt(currentTable) === t.tableId && t.fullDay === "on") {
                        name = t.name;
                        checkboxController(timeOfDay)
                    }
                })
            }
        })
    }
    return name;
}

function setButtonColors() {

    // Get all office table buttons
    const tableButtons = document.querySelectorAll('.tableButton');

    tableButtons.forEach(button => {
        permanentArray.forEach((p) => {
            if (parseInt(button.name) === p.tableId && p.permanentPlace === "on") {
                button.style.opacity = 0.3;
            }
        })
        // a and b represent halfdays, two halfs make a whole.
        let a = 0;
        let b = 0;
        tableArray.forEach((n) => {
            if (n.dateOfBooking === currentDateString) {
                if (parseInt(button.name) === n.tableId && n.afterLunch === "on") {
                    button.style.border = "3px solid #eacbbb";
                    button.style.borderRadius = "15%";
                    a = 1;
                }
                if (parseInt(button.name) === n.tableId && n.beforeLunch === "on") {
                    button.style.border = "3px solid #eacbbb";
                    button.style.borderRadius = "15%";
                    b = 1;
                }
                if (a === 1 && b === 1 || parseInt(button.name) === n.tableId && n.fullDay === "on") {
                    button.style.backgroundColor = '#eacbbb';
                }
            }
        })
    });
}

function adjustdate(monthDayValue) {
    // Makes date string work with string from json.
    let numberString = "";
    if (monthDayValue < 10) {
        numberString = "0" + monthDayValue;
        return numberString;
    } else {
        return monthDayValue;
    }
}

function dateFromForm(date) {
    // Checks booking on this specific date
    console.log(date)
    scanTableForBookings(table, date);
}

let table = "";

function tableIdToForm(currentTable) {
    // Fetches table name for use in "dateFromForm()"
    table = currentTable;
}
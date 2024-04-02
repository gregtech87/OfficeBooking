let perm = permanentSeats;
console.log(perm)
let table = document.getElementById('myDataTableBody');
let tableElement;
let headerIndex;
let currentIsAscending;
let rIndex;
let dataTable = document.getElementById("myDataTable");

function buildTable() {
    // Build table and sort by tableId.
    perm.forEach((p) => {
        let id = parseInt(p.tableId);
        if (id < 10) {
            id = "0" + id.toString();
        }
        let row = `<tr>
                            <td>${id}</td>
							<td>${p.name}</td>
							<td>${p.latestEdit}</td>
							<td>${p.permanentPlace}</td>
					  </tr>`
        table.innerHTML += row
    })
    sortTableByColumn(document.getElementById("myDataTable"), 0, true)
}

// Add event listener for header.
document.querySelectorAll(".table-sortable th").forEach(headerCell => {
    headerCell.addEventListener("click", () => {
        tableElement = headerCell.parentElement.parentElement.parentElement;
        headerIndex = Array.prototype.indexOf.call(headerCell.parentElement.children, headerCell);
        currentIsAscending = headerCell.classList.contains("th-sort-asc");
        sortTableByColumn(tableElement, headerIndex, !currentIsAscending);
    });
});

function sortTableByColumn(table, column, asc = true) {
    const dirModifier = asc ? 1 : -1;
    const tBody = table.tBodies[0];
    const rows = Array.from(tBody.querySelectorAll("tr"));

    // Sort each row.
    const sortedRows = rows.sort((a, b) => {
        const aColText = a.querySelector(`td:nth-child(${column + 1})`).textContent.trim();
        const bColText = b.querySelector(`td:nth-child(${column + 1})`).textContent.trim();
        return aColText > bColText ? (1 * dirModifier) : (-1 * dirModifier);
    });
    // Remove all existing TRs from the table.
    while (tBody.firstChild) {
        tBody.removeChild(tBody.firstChild);
    }
    // Re-add the newly sorted rows.
    tBody.append(...sortedRows);
    // Remember how the column is currently sorted.
    table.querySelectorAll("th").forEach(th => th.classList.remove("th-sort-asc", "th-sort-desc"));
    table.querySelector(`th:nth-child(${column + 1})`).classList.toggle("th-sort-asc", asc);
    table.querySelector(`th:nth-child(${column + 1})`).classList.toggle("th-sort-desc", !asc);
}

function tableToJson() {
    let i;
    let table = document.getElementById("myDataTable")
    let data = [];
    // First row needs to be headers.
    let headers = [];
    for (i = 0; i < table.rows[0].cells.length; i++) {
        headers[i] = table.rows[0].cells[i].innerHTML.replace(/ /gi, '');
    }
    // Go through cell.
    for (i = 1; i < table.rows.length; i++) {
        let tableRow = table.rows[i];
        let rowData = {};
        for (let j = 0; j < tableRow.cells.length; j++) {

            rowData[headers[j]] = tableRow.cells[j].innerHTML;
        }
        data.push(rowData);
        console.log(data)
    }
    // Set json string in form and auto-submit to servlet.
    document.getElementById("permTable").value = JSON.stringify(data);
    console.log(document.getElementById("permTable"))
    document.getElementById("autoForm").submit();
}

function checkEmptyInput() {
    // Check input if empty.
    let isEmpty = false,
        table = document.getElementById("newTableID").value,
        name = document.getElementById("fullName").value;

    if (table === "" || table <= 0 || table > 61) {
        alert("Godkända alternativ: 1-61.");
        isEmpty = true;
    } else if (name === "") {
        alert("Kan inte vara utan ett namn.");
        isEmpty = true;
    }
    return isEmpty;
}

// add Row
function addHtmlTableRow() {
    // Get the table by id.
    // Create a new row and cells.
    // Get value from input text.
    // Set the values into row cell's.
    if (!checkEmptyInput()) {
        let newRow = dataTable.insertRow(dataTable.length),
            cell1 = newRow.insertCell(0),
            cell2 = newRow.insertCell(1),
            cell3 = newRow.insertCell(2),
            cell4 = newRow.insertCell(3),
            newTableID = parseInt(document.getElementById("newTableID").value),
            fullName = document.getElementById("fullName").value,
            id;
        if (newTableID < 10) {
            id = "0" + newTableID.toString();
        } else {
            id = newTableID
        }
        cell1.innerHTML = id;
        cell2.innerHTML = fullName;
        cell3.innerHTML = new Date().toLocaleString().slice(0, 10) + ": " + new Date().toLocaleString().slice(11, 19);
        cell4.innerHTML = "on";
        // Call the function to set the event to the new row and sort by tableId.
        selectedRowToInput();
        sortTableByColumn(document.getElementById("myDataTable"), 0, true)
    }
}


function selectedRowToInput() {
// Display selected row data into input text.
    for (let i = 1; i < dataTable.rows.length; i++) {
        dataTable.rows[i].onclick = function () {
            rIndex = this.rowIndex;
            document.getElementById("newTableID").value = this.cells[0].innerHTML;
            document.getElementById("fullName").value = this.cells[1].innerHTML;
        };
    }
}


function editHtmlTableSelectedRow() {
    // Edit selected row and sort by tableId.
    let newTableID = document.getElementById("newTableID").value,
        name = document.getElementById("fullName").value,
        id;
    if (newTableID.toString().length >= 2) {
        id = newTableID.toString().slice(0, 2)
    } else if (newTableID < 10) {
        id = "0" + newTableID.toString();
    } else {
        id = newTableID
    }
    if (!checkEmptyInput()) {
        dataTable.rows[rIndex].cells[0].innerHTML = id;
        dataTable.rows[rIndex].cells[1].innerHTML = name;
        dataTable.rows[rIndex].cells[2].innerHTML = new Date().toLocaleString().slice(0, 10) + ": " + new Date().toLocaleString().slice(11, 19);
    }
    sortTableByColumn(document.getElementById("myDataTable"), 0, true);
}

function removeSelectedHtmlTableRow() {
    if (!checkEmptyInput()) {

        // Remove selected row.
        dataTable.deleteRow(rIndex);
    } else {
        alert("Välj ett alternativ från listan först.")
    }
    // clear input text
    document.getElementById("newTableID").value = "";
    document.getElementById("fullName").value = "";
}

// Visa inloggningsrutan när "Admin" -knappen klickas
document.querySelector(".admin2-button").addEventListener("click", function(e) {
    e.preventDefault();
    document.getElementById("login2-overlay").style.display = "flex";
});

// Hantera avbryt-knappen
document.getElementById("cancel2-button").addEventListener("click", function(e) {
    e.preventDefault();
    document.getElementById("login2-overlay").style.display = "none";
});

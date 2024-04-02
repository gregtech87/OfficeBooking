let tables = errorTables;
let rooms = errorRooms;
console.log(tables)
console.log(rooms)
let table = document.getElementById('myDataTableBody');
let room = document.getElementById('myDataTableBody2');
let tableElement;
let headerIndex;
let currentIsAscending;
// let rIndex;
// let dataTable = document.getElementById("myDataTable");

function buildTable() {
    // Build table and sort by tableId.
    tables.forEach((t) => {
        let row = `<tr>
                            <td>${t.tableId}</td>
							<td>${t.name}</td>
							<td>${t.dateOfBooking}</td>
							<td>${t.beforeLunch}</td>
							<td>${t.afterLunch}</td>
							<td>${t.fullDay}</td>
							<td>${t.latestEdit}</td>
							<td>${t.permanentPlace}</td>
					  </tr>`
        table.innerHTML += row
    })
    sortTableByColumn(document.getElementById("myDataTable"), 6, false)

    rooms.forEach((r) => {
        let row = `<tr>
                            <td>${r.roomId}</td>
							<td>${r.name}</td>
							<td>${r.dateOfBooking}</td>
							<td>${r.startTime}</td>
							<td>${r.endTime}</td>
							<td>${r.fullDay}</td>
							<td>${r.latestEdit}</td>
							<td>${r.permanentBooked}</td>
					  </tr>`
        room.innerHTML += row
    })
    sortTableByColumn(document.getElementById("myDataTable2"), 6, false)
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
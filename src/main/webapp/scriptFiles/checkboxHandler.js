function checkboxController(timeOfDay) {
    // Checkbox settings

    switch (timeOfDay) {
        case "beforeLunch":
            document.getElementById("beforeLunchBox").disabled = true;
            document.getElementById("beforeLunchBox").style.visibility = 'hidden';
            document.getElementById("beforeLunchLabel").style.visibility = 'hidden';
            document.getElementById("wholeDayBox").style.visibility = 'hidden';
            document.getElementById("wholeDayLabel").style.visibility = 'hidden';
            return 1;

        case "afterLunch":
            document.getElementById("afterLunchBox").disabled = true;
            document.getElementById("afterLunchBox").style.visibility = 'hidden';
            document.getElementById("afterLunchLabel").style.visibility = 'hidden';
            document.getElementById("wholeDayBox").style.visibility = 'hidden';
            document.getElementById("wholeDayLabel").style.visibility = 'hidden';
            return 1;

        case "beforeAndAfterLunch":
            document.getElementById("wholeDayBox").style.visibility = 'hidden';
            document.getElementById("wholeDayLabel").style.visibility = 'hidden';
            document.getElementById("submitButtonTable").disabled = true;
            document.getElementById("submitButtonTable").style.visibility = 'hidden';
            break;

        case "wholeDay":
            document.getElementById("wholeDayBox").style.visibility = 'hidden';
            document.getElementById("wholeDayLabel").style.visibility = 'hidden';
            document.getElementById("beforeLunchBox").style.visibility = 'hidden';
            document.getElementById("beforeLunchLabel").style.visibility = 'hidden';
            document.getElementById("afterLunchBox").style.visibility = 'hidden';
            document.getElementById("afterLunchLabel").style.visibility = 'hidden';
            document.getElementById("submitButtonTable").disabled = true;
            document.getElementById("submitButtonTable").style.visibility = 'hidden';
            break;
        case "permanent":
            document.getElementById("wholeDayBox").style.visibility = 'hidden';
            document.getElementById("wholeDayLabel").style.visibility = 'hidden';
            document.getElementById("beforeLunchBox").style.visibility = 'hidden';
            document.getElementById("beforeLunchLabel").style.visibility = 'hidden';
            document.getElementById("afterLunchBox").style.visibility = 'hidden';
            document.getElementById("afterLunchLabel").style.visibility = 'hidden';
            document.getElementById("submitButtonTable").disabled = true;
            document.getElementById("submitButtonTable").style.visibility = 'hidden';
            break;
        default:
            document.getElementById("beforeLunchBox").disabled = false;
            document.getElementById("afterLunchBox").disabled = false;
            document.getElementById("wholeDayBox").disabled = false;
            document.getElementById("submitButtonTable").disabled = false;
            document.getElementById("submitButtonTable").style.visibility = 'visible';
            document.getElementById("wholeDayBox").style.visibility = 'visible';
            document.getElementById("wholeDayLabel").style.visibility = 'visible';
            document.getElementById("beforeLunchBox").style.visibility = 'visible';
            document.getElementById("beforeLunchLabel").style.visibility = 'visible';
            document.getElementById("afterLunchBox").style.visibility = 'visible';
            document.getElementById("afterLunchLabel").style.visibility = 'visible';
            break;
    }
}

function checkedOnClick(element) {
    // Only one checkbox at the time
    let checkboxesList = document.getElementsByClassName("checkoption");
    for (let i = 0; i < checkboxesList.length; i++) {
        checkboxesList.item(i).checked = false; // Uncheck all checkboxes
    }
    element.checked = true; // Checked clicked checkbox
}

function handleData() {
    // Makes sure at least one checkbox is checked.
    let form_data = new FormData(document.getElementById("tableBooking-popup"));
    if (!form_data.has("beforeLunchInputTable") && !form_data.has("afterLunchInputTable") && !form_data.has("wholeDayInputTable")) {
        document.getElementById("chk_option_error").style.visibility = "visible";
        return false;
    } else {
        document.getElementById("chk_option_error").style.visibility = "hidden";
        return true;
    }
}
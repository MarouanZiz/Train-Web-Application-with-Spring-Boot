function checkDropDown() {
    var a = document.getElementById("station_depart");
    var b = document.getElementById("station_arrivee");

    if (a.options[a.selectedIndex].value == b.options[b.selectedIndex].value) {
        alert("I am an alert box!");
        return false;
    }
    else {
        return confirm('Do you really want to submit the form?');;
    }
}
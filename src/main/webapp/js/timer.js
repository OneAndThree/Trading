function getDate() {
    var today = new Date();
    document.querySelector(".date").innerHTML = today.toUTCString().slice(0,-3);
}

// add a zero in front of numbers<10
function checkTime(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

//timer
setInterval(getDate, 1000);
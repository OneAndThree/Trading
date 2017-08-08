function startTime() {
    var today = new Date();
    var y = today.getFullYear();
    var M = checkTime(today.getMonth() + 1);
    var d = checkTime(today.getDate());
    var h = checkTime(today.getHours());
    var m = checkTime(today.getMinutes());
    var s = checkTime(today.getSeconds());

    $(".time").html(y + "-" + M + "-" + d + " " + h + ":" + m + ":" + s);
}

// add a zero in front of numbers<10
function checkTime(i) {
    if (i < 10) {
        i = "0" + i;
    }
    return i;
}

//timer
setInterval(startTime, 1000);
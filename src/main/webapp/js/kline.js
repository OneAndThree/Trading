function getHistoricalData(symbol, period) {
    var result;
    $.ajax({
        type: 'post',
        url: '/historyData',
        data: {
            symbol: symbol,
            period: period
        },
        async: false,
        success: function (data) {
            result = data;
        },
        error: function () {
            console.error("error");
        }
    });
    return result;
}
function getKLineData(value) {
    // console.log(value);
    var closeArr = [];
    if(value.length !== 0){
        closeArr = value.map(function (item) {
            return item[0];
        });
    }
    return closeArr;
}
function formatTime(timestamp) {
    var date = new Date();
    date.setTime(timestamp * 1000);
    var y = date.getUTCFullYear();
    var M = checkTime(date.getUTCMonth() + 1);
    var d = checkTime(date.getUTCDate());
    var h = checkTime(date.getUTCHours());
    var m = checkTime(date.getUTCMinutes());
    return y + "/" + M + "/" + d + " " + h + ":" + m;
}
function getKChartData(data) {
    var categoryData = [];
    var values = [];
    if(data && data.result){
        var result = JSON.parse(data.result);
        var timestamp = result[0].timestamp;
        var open = result[0].indicators.quote[0].open;
        var close = result[0].indicators.quote[0].close;
        var lowest = result[0].indicators.quote[0].low;
        var highest = result[0].indicators.quote[0].high;
        for (var i = 0; i < timestamp.length; i++) {
            var rowData = [open[i], close[i], lowest[i], highest[i]];
            values.push(rowData);
            categoryData.push(formatTime(timestamp[i]));
        }
    }
    return {
        categoryData: categoryData,
        values: values
    };
}
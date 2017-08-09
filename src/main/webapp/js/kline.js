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
            result = splitHistoricalData(data);
        },
        error: function () {
            console.error("error");
        }
    });
    return result;
}

function getCloseData(value) {
    var closeArr = value.map(function (item) {
        return item[0];
    })
    return closeArr;
}

function splitHistoricalData(data) {
    var data = JSON.parse(data.result);
    var categoryData = [];
    var values = [];
    if(data !== null){
        var timestamp = data[0].timestamp;
        var open = data[0].indicators.quote[0].open;
        var close = data[0].indicators.quote[0].close;
        var lowest = data[0].indicators.quote[0].low;
        var highest = data[0].indicators.quote[0].high;
        for (var i = 0; i < timestamp.length; i++) {
            var rowData = [open[i], close[i], lowest[i], highest[i]];
            values.push(rowData);
            var date = new Date();
            date.setTime(timestamp[i] * 1000);
            categoryData.push(date.toUTCString());
        }
    }
    return {
        categoryData: categoryData,
        values: values
    };
}
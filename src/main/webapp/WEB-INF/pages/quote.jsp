<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Trading Platform</title>
    <link href="../../lib/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="../../css/common/portfolio.css" rel="stylesheet">
</head>
<body>

<header id="nav">
    <jsp:include page="/nav" flush="true"/>
</header>
<section class="jumbotron quote-jumbotron">
    <div class="container">
        <div class="row">
            <div class="col-md-2">
                <h1 class="text-center">${symbol}</h1>
            </div>
            <div class="col-md-2">
                <h1 data-bind="text:previous_close" style="font-size: 44px;"></h1>
            </div>
            <div class="col-md-8">
                <table class="table quote-table">
                    <tr>
                        <th>Previous Close</th>
                        <td data-bind="text:previous_close"></td>
                        <th>Open</th>
                        <td data-bind="text:open"></td>
                        <th>Bid</th>
                        <td data-bind="text:bid"></td>
                        <th>Ask</th>
                        <td data-bind="text:ask"></td>
                    </tr>
                    <tr>
                        <th>gmtoffset</th>
                        <td data-bind="text:gmtoffset"></td>
                        <th>Volume</th>
                        <td data-bind="text:volume"></td>
                        <th>scale</th>
                        <td data-bind="text:scale"></td>
                        <th>instrumentType</th>
                        <td data-bind="text:instrumentType"></td>
                    </tr>
                </table>
            </div>
        </div>
    </div>
</section>
<article id="main" class="container quote-board">
    <div class="row">
        <section class="col-md-8" id="k-line" style="height: 500px; padding: 20px;"></section>
        <section class="col-md-2" id="order-book-bid">
            <table class="table">
                <thead>
                <th>Qty</th>
                <th>Bid</th>
                </thead>
                <tbody data-bind="foreach: orderbookBid().orderList">
                <tr>
                    <td data-bind="text:quantity"></td>
                    <td data-bind="text:price"></td>
                </tr>
                </tbody>
            </table>
        </section>
        <section class="col-md-2" id="order-book-ask">
            <table class="table">
                <thead>
                <th>Ask</th>
                <th>Qty</th>
                </thead>
                <tbody data-bind="foreach: orderbookAsk().orderList">
                <tr>
                    <td data-bind="text: price"></td>
                    <td data-bind="text: quantity"></td>
                </tr>
                </tbody>
            </table>
        </section>
    </div>
</article>


<!-- 3rd party -->
<script src="../../lib/jquery/jquery.js"></script>
<script src="../../lib/bootstrap/js/bootstrap.js"></script>
<script src="../../lib/echarts/echarts.js"></script>
<script src="../../lib/knockout/knockout.js"></script>
<script src="../../lib/sockjs/sockjs.js"></script>
<script src="../../lib/stomp/lib/stomp.min.js"></script>

<script src="../../js/kline.js"></script>
<script src="../../js/portfolio.js"></script>
<script type="text/javascript">
    (function () {
        var daily_data = getHistoricalData('${symbol}', '1d');
        var week_data = getHistoricalData('${symbol}', '5d');
        var month_data = getHistoricalData('${symbol}', '1mo');
        var daily_SplitData = getKChartData(daily_data);
        var week_SplitData = getKChartData(week_data);
        var month_SplitData = getKChartData(month_data);

        var quote = new QuoteModal();
        quote.splitData(week_data);
        ko.applyBindings(quote);

        function getBidListData(symbol) {
            $.ajax({
                type: 'post',
                url: '/orderBookBid',
                data: {
                    symbol: symbol
                },
                success: function (data) {
                    quote.setOrderBookBid(data);
                },
                error: function () {
                    console.error("getOrderList: error");
                }
            });
        }

        function getAskListData(symbol) {
            $.ajax({
                type: 'post',
                url: '/orderBookAsk',
                data: {
                    symbol: symbol
                },
                success: function (data) {
                    console.log(data);
                    quote.setOrderBookAsk(data);
                },
                error: function () {
                    console.error("getOrderList: error");
                }
            });
        }

        getBidListData("${symbol}");
        getAskListData("${symbol}");

        var option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross'
                }
            },
            legend: {
                data: ["1D", "5D", "1M"],
                selectedMode: 'single'
            },
            grid: {
                left: '10%',
                right: '10%',
                bottom: '15%'
            },
            xAxis: {
                type: 'category',
                data: daily_SplitData.categoryData,
                scale: true,
                boundaryGap: false,
                axisLine: {onZero: false},
                splitLine: {show: false},
                splitNumber: 20,
                min: 'dataMin',
                max: 'dataMax'
            },
            yAxis: {
                scale: true,
                splitArea: {
                    show: true
                }
            },
            dataZoom: [
                {
                    type: 'inside',
                    start: 0,
                    end: 100
                },
                {
                    show: true,
                    type: 'slider',
                    y: '90%',
                    start: 0,
                    end: 100
                }
            ],
            series: [
                {
                    name: '1D',
                    type: 'line',
                    data: getKLineData(daily_SplitData.values),
                    smooth: true,
                    lineStyle: {
                        normal: {opacity: 0.5}
                    }
                },
                {
                    name: '1D',
                    type: 'candlestick',
                    data: daily_SplitData.values,
                    markPoint: {
                        label: {
                            normal: {
                                formatter: function (param) {
                                    return param != null ? Math.round(param.value) : '';
                                }
                            }
                        },
                        data: [
                            {
                                name: 'highest value',
                                type: 'max',
                                valueDim: 'highest'
                            },
                            {
                                name: 'lowest value',
                                type: 'min',
                                valueDim: 'lowest'
                            },
                            {
                                name: 'average value on close',
                                type: 'average',
                                valueDim: 'close'
                            }
                        ],
                        tooltip: {
                            formatter: function (param) {
                                return param.name + '<br>' + (param.data.coord || '');
                            }
                        }
                    },
                    markLine: {
                        symbol: ['none', 'none'],
                        data: [
                            [
                                {
                                    name: 'from lowest to highest',
                                    type: 'min',
                                    valueDim: 'lowest',
                                    symbol: 'circle',
                                    symbolSize: 10,
                                    label: {
                                        normal: {show: false},
                                        emphasis: {show: false}
                                    }
                                },
                                {
                                    type: 'max',
                                    valueDim: 'highest',
                                    symbol: 'circle',
                                    symbolSize: 10,
                                    label: {
                                        normal: {show: false},
                                        emphasis: {show: false}
                                    }
                                }
                            ],
                            {
                                name: 'min line on close',
                                type: 'min',
                                valueDim: 'close'
                            },
                            {
                                name: 'max line on close',
                                type: 'max',
                                valueDim: 'close'
                            }
                        ]
                    }
                },
                {
                    name: '5D',
                    type: 'candlestick',
                    data: week_SplitData.values,
                    markPoint: {
                        label: {
                            normal: {
                                formatter: function (param) {
                                    return param != null ? Math.round(param.value) : '';
                                }
                            }
                        },
                        data: [
                            {
                                name: 'highest value',
                                type: 'max',
                                valueDim: 'highest'
                            },
                            {
                                name: 'lowest value',
                                type: 'min',
                                valueDim: 'lowest'
                            },
                            {
                                name: 'average value on close',
                                type: 'average',
                                valueDim: 'close'
                            }
                        ],
                        tooltip: {
                            formatter: function (param) {
                                return param.name + '<br>' + (param.data.coord || '');
                            }
                        }
                    },
                    markLine: {
                        symbol: ['none', 'none'],
                        data: [
                            [
                                {
                                    name: 'from lowest to highest',
                                    type: 'min',
                                    valueDim: 'lowest',
                                    symbol: 'circle',
                                    symbolSize: 10,
                                    label: {
                                        normal: {show: false},
                                        emphasis: {show: false}
                                    }
                                },
                                {
                                    type: 'max',
                                    valueDim: 'highest',
                                    symbol: 'circle',
                                    symbolSize: 10,
                                    label: {
                                        normal: {show: false},
                                        emphasis: {show: false}
                                    }
                                }
                            ],
                            {
                                name: 'min line on close',
                                type: 'min',
                                valueDim: 'close'
                            },
                            {
                                name: 'max line on close',
                                type: 'max',
                                valueDim: 'close'
                            }
                        ]
                    }
                },
                {
                    name: '5D',
                    type: 'line',
                    data: getKLineData(week_SplitData.values),
                    smooth: true,
                    lineStyle: {
                        normal: {opacity: 0.5}
                    }
                },
                {
                    name: '1M',
                    type: 'line',
                    data: getKLineData(month_SplitData.values),
                    smooth: true,
                    lineStyle: {
                        normal: {opacity: 0.5}
                    }
                },
            ]
        };
        var kLineDom = document.getElementById("k-line");
        var kLineChart = echarts.init(kLineDom);
        kLineChart.setOption(option);


        kLineChart.on('legendselectchanged', function (param) {
            option.xAxis.data = changeXAxis(param.name);
            kLineChart.setOption(option);
//            console.log(changeXAxis(param.name));
        });

        function changeXAxis(legend) {
            switch (legend) {
                case '1D':
                    return daily_SplitData.categoryData;
                case '5D':
                    return week_SplitData.categoryData;
                case '1M':
                    return month_SplitData.categoryData;
            }
        }

        /*var stompClient = getSocketClient('/tradeOrderDetail');
        var appModel = new MyPositionsModel(stompClient);
        ko.applyBindings(appModel);
        appModel.connect();*/
//        192.168.43.124
    })();
</script>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Trading Platform</title>
    <link href="../../lib/bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<header id="nav"></header>
<article id="main" class="container">
    <div class="row">
        <h4 class="col-md-3" data-bind="text: company">${symbol}</h4>
    </div>
    <div class="row">
        <section class="col-md-8" id="k-line" style="height: 500px;"></section>
        <section class="col-md-2" id="order-book">
            <table class="table">
                <caption class="text-center h4">Order Book</caption>
                <tbody>
                <tr>
                    <td>Ask</td>
                    <td>8.89</td>
                    <td>221</td>
                </tr>
                <tr>
                    <td>Ask</td>
                    <td>8.88</td>
                    <td>421</td>
                </tr>
                <tr>
                    <td>Ask</td>
                    <td>8.83</td>
                    <td>121</td>
                </tr>
                <tr>
                    <td>Bid</td>
                    <td>8.79</td>
                    <td>431</td>
                </tr>
                <tr>
                    <td>Bid</td>
                    <td>8.76</td>
                    <td>21</td>
                </tr>
                <tr>
                    <td>Bid</td>
                    <td>8.74</td>
                    <td>121</td>
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
<script src="../../lib/sockjs/sockjs.js"></script>
<script src="../../lib/stomp/lib/stomp.min.js"></script>

<script src="../../js/kline.js"></script>
<script type="text/javascript">
    (function () {
        $("#nav").load("/nav");
        var option = {
            title: {
                text: '${symbol}',
                left: 0
            },
            tooltip: {
                trigger: 'axis',
                axisPointer: {
                    type: 'cross'
                }
            },
            legend: {
                data: ["1D", "5D", "1M"]
            },
            grid: {
                left: '10%',
                right: '10%',
                bottom: '15%'
            },
            xAxis: {
                type: 'category',
                data: getHistoricalData("${symbol}","5d").categoryData,
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
                    start: 50,
                    end: 100
                },
                {
                    show: true,
                    type: 'slider',
                    y: '90%',
                    start: 50,
                    end: 100
                }
            ],
            series: [
                {
                    name: '1D',
                    type: 'candlestick',
                    data: getHistoricalData("${symbol}","1d").values,
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
                    data: getHistoricalData("${symbol}","5d").values,
                    smooth: true,
                    lineStyle: {
                        normal: {opacity: 0.5}
                    }
                },
                {
                    name: '1M',
                    type: 'line',
                    data: getHistoricalData("${symbol}","1mo").values,
                    smooth: true,
                    lineStyle: {
                        normal: {opacity: 0.5}
                    }
                },
            ]
        };
        var kLineChart = new KLineChart("k-line", option);
        kLineChart.init();



        /*var stompClient = getSocketClient('/trade');
        var appModel = new ApplicationModel(stompClient);
        ko.applyBindings(appModel);
        appModel.connect();*/
    })();
</script>

</body>
</html>

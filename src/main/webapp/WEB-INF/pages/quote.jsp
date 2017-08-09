<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Trading Platform</title>
    <link href="../../lib/bootstrap/css/bootstrap.css" rel="stylesheet">
</head>
<body>

<header id="nav">
    <jsp:include page="/nav" flush="true"/>
</header>
<article id="main" class="container">
    <div class="row">
        <h4 class="col-md-3" data-bind="text: company">${symbol}</h4>
    </div>
    <div class="row">
        <section class="col-md-8" id="k-line" style="height: 500px;"></section>
        <section class="col-md-2" id="order-book">
            <table class="table">
                <caption class="text-center h4">Order Book</caption>
                <thead>
                    <th>Qty</th>
                    <th>Bid</th>
                    <th>Ask</th>
                    <th>Qty</th>
                </thead>
                <tbody>
                <tr>
                    <td>17</td>
                    <td>53.55</td>
                    <td>54.53</td>
                    <td>13</td>
                </tr>
                <tr>
                    <td>13</td>
                    <td>55.16</td>
                    <td>52.94</td>
                    <td>21</td>
                </tr>
                <tr>
                    <td>11</td>
                    <td>56.81</td>
                    <td></td>
                    <td></td>
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
                data: ["1K", "5D", "1M"]
            },
            grid: {
                left: '10%',
                right: '10%',
                bottom: '15%'
            },
            xAxis: {
                type: 'category',
                data: getHistoricalData("${symbol}","1mo").categoryData,
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
                    name: '1K',
                    type: 'candlestick',
                    data: getHistoricalData("${symbol}","1mo").values,
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
                    data: getCloseData(getHistoricalData("${symbol}","5d").values),
                    smooth: true,
                    lineStyle: {
                        normal: {opacity: 0.5}
                    }
                },
                {
                    name: '1M',
                    type: 'line',
                    data: getCloseData(getHistoricalData("${symbol}","1mo").values),
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
        kLineChart.on('legendselectchanged', function (params) {
            console.log(params);
        })


        /*var stompClient = getSocketClient('/tradeOrderDetail');
        var appModel = new ApplicationModel(stompClient);
        ko.applyBindings(appModel);
        appModel.connect();*/
//        192.168.43.124
    })();
</script>

</body>
</html>

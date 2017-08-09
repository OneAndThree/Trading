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
<article id="main" class="container">
    <div class="row">
        <div class="col-md-2">
            <h1 class="text-center">${symbol}</h1>
        </div>
        <div class="col-md-2 price_details">
            <span class="h1" data-bind="text:previous_close"></span>
            <ul class="list-unstyled visible-lg-inline-block">
                <li data-bind="text:change">-6.30</li>
                <li data-bind="text:change_percent">-0.19%</li>
            </ul>
        </div>
        <div class="col-md-8">
            <table class="table">
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
    <div class="row">
        <section class="col-md-10" id="k-line" style="height: 500px;"></section>
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
<script src="../../lib/knockout/knockout.js"></script>
<script src="../../lib/sockjs/sockjs.js"></script>
<script src="../../lib/stomp/lib/stomp.min.js"></script>

<script src="../../js/kline.js"></script>
<script type="text/javascript">
    (function () {
        var daily_data = getHistoricalData('${symbol}', '1d');
        var week_data = getHistoricalData('${symbol}', '5d');
        var month_data = getHistoricalData('${symbol}', '1mo');
        var QuoteModal = function (data) {
            var self = this;
            self.previous_close = ko.observable();
            self.open = ko.observable();
            self.close = ko.observable();
            self.bid = ko.observable();
            self.ask = ko.observable();
            self.volume = ko.observable();
            self.lowest = ko.observable();
            self.highest = ko.observable();
            self.scale = ko.observable();
            self.gmtoffset = ko.observable();
            self.instrumentType = ko.observable();
            self.change = ko.observable();
            self.change_percent = ko.observable();

            self.splitData = function () {
                var result = JSON.parse(data.result);
                var quote = result[0];
                self.previous_close(quote.meta.previousClose);
                self.gmtoffset(quote.meta.gmtoffset);
                self.scale(quote.meta.scale);
                self.instrumentType(quote.meta.instrumentType);
                self.highest(quote.indicators.quote[0].high.pop());
                self.lowest(quote.indicators.quote[0].low.pop());
                self.open(quote.indicators.quote[0].open.pop());
                self.close(quote.indicators.quote[0].close.pop());
                self.volume(quote.indicators.quote[0].volume.pop());

                console.log(quote);
            }
        };
        var quote = new QuoteModal(week_data);
        ko.applyBindings(quote);
        quote.splitData();

        var option = {
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
                data: getKChartData(month_data).categoryData,
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
                    name: '1K',
                    type: 'candlestick',
                    data: getKChartData(daily_data).values,
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
                    data: getKLineData(getKChartData(week_data).values),
                    smooth: true,
                    lineStyle: {
                        normal: {opacity: 0.5}
                    }
                },
                {
                    name: '1M',
                    type: 'line',
                    data: getKLineData(getKChartData(month_data).values),
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

        /*var stompClient = getSocketClient('/tradeOrderDetail');
        var appModel = new ApplicationModel(stompClient);
        ko.applyBindings(appModel);
        appModel.connect();*/
//        192.168.43.124
    })();
</script>

</body>
</html>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Stock Trading Portfolio</title>
    <meta http-equiv="Cache-Control" content="no-store, no-cache, must-revalidate, max-age=0">
    <link href="../../lib/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="../../css/common/portfolio.css" rel="stylesheet">
    <!--[if lt IE 9]>
    <script src="../../lib/html5shiv/dist/html5shiv.js"></script>
    <![endif]-->
</head>
<body>
<header id="nav">
    <jsp:include page="/nav" flush="true"/>
</header>
<div class="container">
    <div id="main-content">
        <table class="table text-center">
            <thead>
            <tr>
                <td colspan="7"><h3 class="text-left">My Portfolio</h3></td>
            </tr>
            <tr class="table-header">
                <td>Symbol</td>
                <td class="number">Price</td>
                <td class="number">Change%</td>
                <td></td>
                <td class="number">Shares</td>
                <td class="number">Value</td>
                <td></td>
            </tr>
            </thead>
            <tbody data-bind="foreach: portfolio().rows">
            <tr>
                <td><a href="#" data-bind="text: company, click: $root.trade().showDetails"></a></td>
                <td data-bind="text: formattedPrice" class="number"></td>
                <td data-bind="text: change, style: {color: change() < 0 ? 'red' : 'green'}" class="number"></td>
                <td data-bind="html: arrow" class="icon"></td>
                <td data-bind="text: shares" class="number"></td>
                <td data-bind="text: formattedValue" class="number"></td>
                <td class="trade-buttons row">
                    <div class="col-md-offset-2 col-md-4"><button class="btn btn-default" data-bind="click: $root.trade().showBuy">Buy</button></div>
                    <div class="col-md-4"><button class="btn btn-default" data-bind="click: $root.trade().showSell">Sell</button></div>
                </td>
            </tr>
            </tbody>
            <tfoot>
            <tr>
                <td colspan="4">Total</td>
                <td data-bind="text: portfolio().totalShares" class="number"></td>
                <td data-bind="text: portfolio().totalValue" class="number"></td>
                <td></td>
            </tr>
            </tfoot>
            <tbody></tbody>
        </table>
        <div class="alert alert-warning">
            <h5>Notifications</h5>
            <ul data-bind="foreach: notifications">
                <li data-bind="text: notification"></li>
            </ul>
        </div>

        <jsp:include page="/modal" flush="true"/>

    </div>
</div>

<!-- 3rd party -->
<script src="../../lib/jquery/jquery.js"></script>
<script src="../../lib/bootstrap/js/bootstrap.js"></script>
<script src="../../lib/knockout/knockout.js"></script>
<script src="../../lib/sockjs/sockjs.js"></script>
<script src="../../lib/stomp/lib/stomp.min.js"></script>

<!-- application -->
<script src="../../js/websocket.js"></script>
<script src="../../js/portfolio.js"></script>
<script type="text/javascript">
    (function () {
        var client = new WebsocketClient("/portfolio");
        var appModel = new MyPositionsModel(client);
        ko.applyBindings(appModel);

        appModel.connect();
        appModel.pushNotification("TradeOrderDetail results take a 2-3 second simulated delay. Notifications will appear.");
    })();
</script>

</body>
</html>

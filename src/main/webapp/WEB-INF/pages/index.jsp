<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Market Data</title>
    <link href="../../lib/bootstrap/css/bootstrap.css" rel="stylesheet">
    <link href="../../css/common/portfolio.css" rel="stylesheet">
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
                <td colspan="5"><h3 class="text-left">Stock Monitor</h3></td>
            </tr>
            <tr class="table-header">
                <td>Symbol</td>
                <td>Price</td>
                <td>Change%</td>
                <td></td>
                <td></td>
            </tr>
            </thead>
            <tbody data-bind="foreach: portfolio().rows">
            <tr>
                <td><a href="#" data-bind="text: company, click: $root.trade().showDetails"></a></td>
                <td data-bind="text: formattedPrice"></td>
                <td data-bind="text: change, style: {color: change() < 0 ? 'red' : 'green'}"></td>
                <td data-bind="html: arrow" class="icon"></td>
                <td class="trade-buttons">
                    <div class="col-md-offset-2 col-md-4"><button class="btn btn-default" data-bind="click: $root.trade().showBuy">Buy</button></div>
                    <div class="col-md-4"><button class="btn btn-default" data-bind="click: $root.trade().showSell">Sell</button></div>
                </td>
            </tr>

            </tbody>
        </table>
    </div>
    <jsp:include page="/modal" flush="true"/>
</div>

<script src="../../lib/jquery/jquery.js"></script>
<script src="../../lib/bootstrap/js/bootstrap.js"></script>
<script src="../../lib/knockout/knockout.js"></script>
<script src="../../lib/sockjs/sockjs.js"></script>
<script src="../../lib/stomp/lib/stomp.min.js"></script>

<!-- application -->
<script src="../../js/websocket.js"></script>
<script src="../../js/portfolio.js"></script>

<script>
    (function () {
        var client = new WebsocketClient("/portfolio");
        var appModel = new ApplicationModel(client);
        ko.applyBindings(appModel);

        appModel.connect();
        appModel.pushNotification("Trade results take a 2-3 second simulated delay. Notifications will appear.");
    })();
</script>
</body>
</html>

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
        <div id="heading" class="masthead">
            <div class="pull-right">
                <span class="text-info" data-bind="text: username"><em></em></span>&nbsp;
                <button data-bind="click: logout" class="btn"><i class="glyphicon glyphicon-off"></i></button>
            </div>
            <h3 class="text-muted">Stock Trading Portfolio</h3>
        </div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Symbol</th>
                <th>Ticker</th>
                <th class="number">Price</th>
                <th class="number">Change</th>
                <th>%</th>
                <th></th>
            </tr>
            </thead>
            <tbody data-bind="foreach: portfolio().rows">
            <tr>
                <td><a href="#" data-bind="text: company, click: $root.trade().showDetails"></a></td>
                <td data-bind="text: ticker"></td>
                <td data-bind="text: formattedPrice" class="number"></td>
                <td data-bind="text: change, style: {color: change() < 0 ? 'red' : 'green'}" class="number"></td>
                <td data-bind="html: arrow" class="icon"></td>
                <td class="trade-buttons">
                    <button class="btn btn-primary" data-bind="click: $root.trade().showBuy">Buy</button>
                    <button class="btn btn-primary" data-bind="click: $root.trade().showSell">Sell</button>
                </td>
            </tr>
            </tbody>
            <tbody>
            <tr>
                <td><a href="/quote?symbol=APA">APA</a></td>
                <td>Ticker</td>
                <td class="number">$45.22</td>
                <td class="number">0.4</td>
                <td class="icon"></td>
                <td class="trade-buttons">
                    <button class="btn btn-primary" data-bind="click: $root.trade().showBuy">Buy</button>
                    <button class="btn btn-primary" data-bind="click: $root.trade().showSell">Sell</button>
                </td>
            </tr>
            </tbody>
        </table>
        <div class="alert alert-warning">
            <h5>Notifications</h5>
            <ul data-bind="foreach: notifications">
                <li data-bind="text: notification"></li>
            </ul>
        </div>
    </div>
    <div id="trade-dialog" class="modal fade" role="dialog" aria-labelledby="myModalLabel" tabindex="-1">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-body">
                    <form class="form-horizontal" data-bind="submit: trade().executeTrade">
                        <fieldset>
                            <legend>
                                <span data-bind="text: trade().action"></span>
                                <span data-bind="text: trade().currentRow().company"></span> Stock
                            </legend>
                        </fieldset>
                        <div class="form-group" data-bind="css: {error: trade().error()}">
                            <label class="col-sm-2 control-label" for="inputShares">Shares</label>
                            <div class="col-sm-10">
                                <input id="inputShares" class="form-control" type="text"
                                       data-bind="value: trade().sharesToTrade">
                                <span class="help-block" data-bind="text: trade().error">Please enter</span>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <div class="checkbox">
                                    <label class="checkbox"><input type="checkbox"
                                                                   data-bind="checked: trade().suppressValidation">
                                        Suppress client-side validation</label>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">
                                <button class="btn" data-dismiss="modal">Close</button>
                                <button class="btn btn-primary" type="submit">
                                    <span data-bind="text: trade().action"></span>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
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
        $("#nav").load("/nav");

        var client = new WebsocketClient("/portfolio");
        var appModel = new ApplicationModel(client);
        ko.applyBindings(appModel);

        appModel.connect();
        appModel.pushNotification("Trade results take a 2-3 second simulated delay. Notifications will appear.");
    })();
</script>
</body>
</html>

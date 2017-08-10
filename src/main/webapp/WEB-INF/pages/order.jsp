<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <title>My Order</title>
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
            <h3 class="text-muted">Order List</h3>
        </div>
        <table class="table table-striped">
            <caption>Order List</caption>
            <thead>
            <tr>
                <th>Symbol</th>
                <th class="number">Price</th>
                <th class="number">Quantity</th>
                <th>Side</th>
                <th>Date</th>
            </tr>
            </thead>
            <tbody>

            </tbody>
        </table>
    </div>
</div>

<script src="../../lib/jquery/jquery.js"></script>
<script src="../../lib/bootstrap/js/bootstrap.js"></script>
<script src="../../lib/knockout/knockout.js"></script>

<script>
    var orderList = function () {
        var self = this;
        self.symbol = ko.observable();
        self.price = ko.observable();
        self.quantity = ko.observable();
        self.side = ko.observable();
        self.date = ko.observable();

    }
</script>

</body>
</html>

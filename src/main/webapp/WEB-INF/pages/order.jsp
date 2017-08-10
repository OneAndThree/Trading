<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
                <span class="text-info"><em><sec:authorize access="isAuthenticated()"><sec:authentication property='principal.username'/></sec:authorize></em></span>&nbsp;
                <button class="btn"><i class="glyphicon glyphicon-off"></i></button>
            </div>
            <h3 class="text-muted">Order List</h3>
        </div>
        <table class="table table-striped">
            <thead>
            <tr>
                <th>Symbol</th>
                <th>Price</th>
                <th>Quantity</th>
                <th>Side</th>
                <th>Date</th>
            </tr>
            </thead>
            <tbody data-bind="foreach: orderList()">
                <td data-bind="text:symbol"></td>
                <td data-bind="text:price"></td>
                <td data-bind="text:quantity"></td>
                <td data-bind="text:side"></td>
                <td data-bind="text:date"></td>
                <td data-bind="text:side"></td>
            </tbody>
        </table>
    </div>
</div>

<script src="../../lib/jquery/jquery.js"></script>
<script src="../../lib/bootstrap/js/bootstrap.js"></script>
<script src="../../lib/knockout/knockout.js"></script>

<script>
    function OrderListModel() {
        var self = this;
        self.orderList = ko.observableArray();

        self.setOrderList = function (orderList) {
            self.orderList(orderList);
        }
    }
    function OrderRow() {
        var self = this;
        self.symbol = ko.observable();
        self.price = ko.observable();
        self.quantity = ko.observable();
        self.side = ko.observable();
        self.date = ko.observable();
    }
    var orderList = new OrderListModel();
    ko.applyBindings(orderList);
    
    function getOrderList() {
       /* $.ajax({
            type: 'post',
            //todo? getOrderListUrl
            url: '/',
            success: function (data) {
                console.group("getOrderList");
                console.log(data);
                orderList.setOrderList(data);
                console.groupEnd("getOrderList");
            },
            error: function () {
                console.error("getOrderList: error");
            }
        });*/
    }
</script>

</body>
</html>

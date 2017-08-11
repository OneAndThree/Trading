<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="nav-header">
            <a class="navbar-brand" href="/index">ONE&THREE</a>
        </div>

        <div class="nav-collapse">
            <ul class="nav navbar-nav">
                <li><a href="/index">Market Data</a></li>
                <li><a href="/portfolio">My Portfolio</a></li>
                <li><a href="/order">My Order</a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="/index"><i class="glyphicon glyphicon-user"></i></a></li>
                <li><a href="/logout"><i class="glyphicon glyphicon-off"></i></a></li>
            </ul>
        </div>
    </div>
</nav>
<section class="jumbotron nav-jumbotron img-responsive">
    <div class="container transparent">
        <h2>Welcome,
            <sec:authorize access="isAuthenticated()"><sec:authentication property='principal.username'/></sec:authorize>!
            <span class="pull-right date"></span>
        </h2>
        <div class="row badge-link">
            <div class="col-md-1">
                <a href="#">Alerts<span class="badge">0</span></a></li>
            </div>
            <div class="col-md-2">
                <a href="#">Reports<span class="badge">0</span></a></li>
            </div>
        </div>
    </div>
</section>
<script src="/js/timer.js"></script>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <title>Sign in &middot; Stock Trading Portfolio Sign in</title>
  <link href="../../lib/bootstrap/css/bootstrap.css" rel="stylesheet">
  <link href="../../css/common/login.css" rel="stylesheet">
</head>
<body>

  <div class="container">
    <div data-bind="visible: showErrorMessage" class="alert alert-error">
      Invalid username/password.
    </div>
    <form class="form-signin" method="post" action="/checkLogin">
      <h3 class="text-muted form-signin-heading">Please Sign In</h3>
      <input type="text" class="form-control" placeholder="User name" name="username">
      <input type="password" class="form-control" placeholder="Password" name="password">
      <button class="btn btn-primary col-md-offset-4" type="submit">Sign In</button>
    </form>
  </div><!-- /container -->

  <script src="../../lib/knockout/knockout.js"></script>

  <script type="text/javascript">
    ko.applyBindings({
      showErrorMessage : ko.computed(function() {
        var query = window.location.search;
        return query ? (query.indexOf('error') != -1) : false;
      })
    });
  </script>

</body>
</html>

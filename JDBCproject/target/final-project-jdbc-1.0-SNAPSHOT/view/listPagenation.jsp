<%--
  Created by IntelliJ IDEA.
  User: home
  Date: 31.10.2019
  Time: 19:32
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <title>Pagenation</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>

<body class="m-3">

<h1>Show users</h1>

<form action="${pageContext.request.contextPath}/ListEntities">

    <input type="hidden" name="currentPage" value="1">

    <div class="form-group col-md-4">

        <label for="records">Select Users per page:</label>

        <select class="form-control" id="records" name="recordsPerPage">
            <option value="5">5</option>
            <option value="10" selected>10</option>
            <option value="15">15</option>
        </select>

    </div>

    <button type="submit" class="btn btn-primary">Submit</button>

</form>

<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js" ></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js" ></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js" ></script>

</body>
</html>

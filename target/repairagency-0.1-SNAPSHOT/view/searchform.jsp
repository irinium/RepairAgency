<%--
  Created by IntelliJ IDEA.
  User: home
  Date: 05.11.2019
  Time: 22:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
    <link href="../css/bootstrap.min.css" rel="stylesheet" type="text/css" id="bootstrap-css">
    <script src="../js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <h2>Employees</h2>
    <!--Search Form -->
    <form action="/employee" method="get" id="seachEmployeeForm" role="form" >
        <input type="hidden" id="searchAction" name="searchAction" value="searchByName"/>
        <div class="form-group col-xs-5">
            <input type="text" name="employeeName" id="employeeName" class="form-control" required="true"
                   placeholder="Type the Name or Last Name of the employee"/>
        </div>
        <button type="submit" class="btn btn-info">
            <span class="glyphicon glyphicon-search"></span> Search
        </button>
        <br></br>
        <br></br>
    </form>
</div>

</body>
</html>

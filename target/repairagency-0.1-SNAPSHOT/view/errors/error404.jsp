<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="param.locale">
<head>
    <title>404</title>
    <link rel="stylesheet" href="/view/css/404style.css">
</head>
<body class="bg-purple">

<div class="stars">
    <div class="custom-navbar">
        <div class="brand-logo">
            <img src="http://salehriaz.com/404Page/img/logo.svg" width="80px">
        </div>
    </div>
    <div class="central-body">
        <img class="image-404" src="http://salehriaz.com/404Page/img/404.svg" width="300px">
        <a href="/view/home.jsp" class="btn-go-home" target="_blank">${navbar.home}</a>
    </div>
    <div class="objects">
        <img class="object_rocket" src="http://salehriaz.com/404Page/img/rocket.svg" width="40px">
        <div class="earth-moon">
            <img class="object_earth" src="http://salehriaz.com/404Page/img/earth.svg" width="100px">
            <img class="object_moon" src="http://salehriaz.com/404Page/img/moon.svg" width="80px">
        </div>
        <div class="box_astronaut">
            <img class="object_astronaut" src="http://salehriaz.com/404Page/img/astronaut.svg" width="140px">
        </div>
    </div>
    <div class="glowing_stars">
        <div class="star"></div>
        <div class="star"></div>
        <div class="star"></div>
        <div class="star"></div>
        <div class="star"></div>
    </div>
</div>
<div>
    Request from ${pageContext.errorData.requestURI} is failed
    <br/>
    Servlet name:${pageContext.errorData.servletName}
    <br/>
    Status code: ${pageContext.errorData.statusCode}
    <br/>
    Exception: ${pageContext.exception}
    <br/>
    Message from exception: ${pageContext.exception.message}
</div>
</body>
</html>

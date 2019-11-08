<%--
  Created by IntelliJ IDEA.
  User: home
  Date: 06.11.2019
  Time: 0:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<html lang="param.locale">
<head>
    <title>Login</title>
    <link rel="stylesheet" href="css/login_style.css">
    <style>
        <%@ include file="/view/css/login_style.css" %>
    </style>
    <div class="locale">
        <form class="setlocale">
            <label for="locale"></label><select id="locale" name="locale" onchange="submit()">
            <option value="en" ${locale == 'en' ? 'selected' : ''}>English</option>
            <option value="ru" ${locale == 'ru' ? 'selected' : ''}>Русский</option>
        </select>
        </form>
    </div>
</head>
<body>
<div class="login-page">
    <div class="form">
        <form class="register-form" method="post" action="${pageContext.request.contextPath}/command">
            <input class="hidden" name="command" value="register">
            <input name="email" type="text" <fmt:message key="placeholder.enter.email" var="em"/> placeholder="${em}"/>
            <input name="password" type="password" pattern="^\S{8,}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must have at least 8 characters' : ''); if(this.checkValidity()) form.password_two.pattern = this.value;" <fmt:message key="placeholder.enter.password" var="pas"/> placeholder="${pas}" required/>
            <input name="password_two" type="password" pattern="^\S{8,}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Please enter the same Password as above' : '');" <fmt:message key="placeholder.enter.password2" var="pas2"/> placeholder="${pas2}" required/>
            <input name="name" type="text" <fmt:message key="placeholder.enter.name" var="name"/> placeholder="${name}"/>
            <input name="surname" type="text" <fmt:message key="placeholder.enter.surname" var="surname"/> placeholder="${surname}"/>
            <input name="phone" type="text" <fmt:message key="placeholder.enter.phone" var="phone"/> placeholder="${phone}"/>
            <button <fmt:message key="button.registration" var="register"/>>${register}</button>
            <p class="message" <fmt:message key="message.login" var="mes"/>
                    <fmt:message key="button.login" var="log"/>>${mes}<a href="#">${log}</a>
            </p>
        </form>

        <form class="login-form" method="post" action="${pageContext.request.contextPath}/command">
            <input class="hidden" name="command" value="login">
            <input type="text" name="email" <fmt:message key="placeholder.enter.email" var="em"/> placeholder="${em}"/>
            <input type="password" name="password" <fmt:message key="placeholder.enter.password" var="pas"/> placeholder="${pas}"/>
            <button <fmt:message key="button.login" var="login"/>>${login}</button>
            <p class="message" <fmt:message key="message.register" var="mesreg"/>
                    <fmt:message key="button.register" var="register"/>>${mesreg}<a href="#">${register}</a>
            </p>
        </form>
    </div>
</div>

<script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
<script>
    $('.message a').click(function () {
        $('form').animate({height: "toggle", opacity: "toggle"}, "slow");
    });
</script>

</body>
</html>

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
    <link rel="stylesheet" href="css/style.css">
    <style>
        <%@ include file="/view/css/style.css" %>
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
        <form class="register-form" method="post" action="command">
            <input class="hidden" name="command" value="register">
            <input id = email name="email" type="text" <fmt:message key="placeholder.enter.email" var="em"/> placeholder="${em}"/>
            <input id="password" name="password" type="password" pattern="^\S{8,}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must have at least 8 characters' : ''); if(this.checkValidity()) form.password_two.pattern = this.value;" <fmt:message key="placeholder.enter.password" var="pas"/> placeholder="${pas}" required/>
            <input id="password_two" name="password_two" type="password" pattern="^\S{8,}$" onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Please enter the same Password as above' : '');" <fmt:message key="placeholder.enter.password2" var="pas2"/> placeholder="${pas2}" required/>
            <input id= name name="name" type="text" <fmt:message key="placeholder.enter.name" var="name"/> placeholder="${name}"/>
            <input id = surname name="surname" type="text" <fmt:message key="placeholder.enter.surname" var="surname"/> placeholder="${surname}"/>
            <input id = phone name="phone" type="text" <fmt:message key="placeholder.enter.phone" var="phone"/> placeholder="${phone}"/>
            <input id = house name="house" type="text" <fmt:message key="placeholder.enter.house" var="house"/> placeholder="${house}"/>
            <input id = street name="street" type="text" <fmt:message key="placeholder.enter.street" var="str"/> placeholder="${str}"/>
            <input id = city name="city" type="text" <fmt:message key="placeholder.enter.city" var="ci"/> placeholder="${ci}"/>
            <input id = code name="code" type="text" <fmt:message key="placeholder.enter.code" var="code"/> placeholder="${code}"/>
            <button <fmt:message key="button.registration" var="register"/>>${register}</button>
            <p class="message" <fmt:message key="message.login" var="mes"/>
                    <fmt:message key="button.login" var="log"/>>${mes}<a href="#">${log}</a>
            </p>
        </form>

        <form class="login-form" method="post" action="command">
            <input class="hidden" name="command" value="login">
            <input type="text" <fmt:message key="placeholder.enter.email" var="em"/> placeholder="${em}"/>
            <input type="password" <fmt:message key="placeholder.enter.password" var="pas"/> placeholder="${pas}"/>
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

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<html lang="param.locale">

<head>
    <title>Manager</title>
    <link rel="stylesheet" href="/view/css/customerHome_style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
            integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
            crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
            integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
            crossorigin="anonymous"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">

</head>

<body>
<section id="nav-bar">
    <nav class="navbar navbar-expand-lg navbar-light">
        <a class="navbar-brand" href="#"> <img src="/view/css/images/logo.png"></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <i class="fa fa-bars" aria-hidden="true"></i>
        </button>

        <div class="locale">
            <form class="setlocale">
                <label for="locale"></label><select id="locale" name="locale" onchange="submit()">
                <option value="en" ${locale == 'en' ? 'selected' : ''}>English</option>
                <option value="ru" ${locale == 'ru' ? 'selected' : ''}>Русский</option>
            </select>
            </form>
        </div>

        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ml-auto">
                <li class="nav-item">
                    <a class="nav-link" href="#banner" <fmt:message key="manager.toolbar.addmaster"
                                                                    var="add"/>> ${add} </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="command?command=userList&currentPage=1&recordsPerPage=5" <fmt:message
                            key="manager.toolbar.allUsers" var="users"/>> ${users}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="command?command=orderList&currentPage=1&recordsPerPage=5" <fmt:message
                            key="manager.toolbar.allOrders"
                            var="orders"/>> ${orders}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#account" <fmt:message key="customer.mydata"
                                                                     var="account"/>>${account}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/view/home.jsp" <fmt:message key="button.logout"
                                                                           var="logout"/>>${logout}</a>
                </li>
            </ul>
        </div>
    </nav>
</section>
<!-------------banner section--------------->
<section id="banner">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <form class="appl-form" method="post" action="${pageContext.request.contextPath}/command">
                    <input class="hidden" name="command" value="registerMaster">
                    <label for="banner" <fmt:message key="manager.title.register" var="reg"/>>${reg}</label>
                    <div class="form-group">
                        <input class="form-control" name="master_email" maxlength="64" type="email"
                               pattern="^[a-zA-Z0-9_.]{1,49}+@[a-zA-Z]{2,10}+?\.[a-zA-Z]{2,3}$"
                               onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Email must content letters, @, ., 2 or 3 characters in the end. Example: email111@gmail.com' : '');"
                                <fmt:message key="placeholder.enter.email" var="em"/> placeholder="${em}"/>
                        <input class="form-control" name="password" type="password" pattern="^\S{8,}$"
                               onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Must have at least 8 characters' : ''); if(this.checkValidity()) form.password_two.pattern = this.value;"
                                <fmt:message key="placeholder.enter.password" var="pas"/> placeholder="${pas}"
                               required/>
                        <input class="form-control" name="password_two" type="password" pattern="^\S{8,}$"
                               onchange="this.setCustomValidity(this.validity.patternMismatch ? 'Please enter the same Password as above' : '');"
                                <fmt:message key="placeholder.enter.password2" var="pas2"/> placeholder="${pas2}"
                               required/>
                        <input class="form-control" name="master_name" type="text" pattern="[A-Za-zА-Яа-я]+"
                               onchange="this.setCustomValidity(this.validity.patternMismatch ? 'letters only, no  punctuation or special characters' : '');"
                                <fmt:message key="placeholder.enter.name" var="name"/> placeholder="${name}"/>
                        <input class="form-control" name="surname" type="text" pattern="[A-Za-zА-Яа-я]+"
                               onchange="this.setCustomValidity(this.validity.patternMismatch ? 'letters only, no  punctuation or special characters' : '');"
                                <fmt:message key="placeholder.enter.surname" var="surname"/> placeholder="${surname}"/>
                        <input class="form-control" name="phone" type="tel" minlength="10" maxlength="10"
                               pattern="[0-9]{10}"
                               onchange="this.setCustomValidity(this.validity.patternMismatch ? 'format 0932352222' : '');"
                                <fmt:message key="placeholder.enter.phone" var="phone"/> placeholder="${phone}"/>
                    </div>
                    <button class="btn btn-primary" <fmt:message key="button.registration"
                                                                 var="register"/>>${register}</button>
                </form>
            </div>
            <div class="master col-md-6 text-center">
                <img src="/view/css/images/master2.png" class="img-fluid">
            </div>
        </div>
    </div>
    <img src="/view/css/images/footer.png" class="bottom-img">
</section>

<!------------------My account---------------->
<section id="account">
    <div class="container">
        <h1 class="title text-center" <fmt:message key="customer.myaccount.title" var="account"/>>${account}</h1>
        <table class="table table-striped table-bordered table-blue">
            <thead class="thead-dark">
            <tr>
                <th scope="row"><fmt:message key="label.name"/></th>
                <th scope="row"><fmt:message key="label.surname"/></th>
                <th scope="row"><fmt:message key="label.email"/></th>
                <th scope="row"><fmt:message key="label.phone"/></th>
            </tr>
            </thead>
            <tbody light>
            <c:catch var="order">
                <tr>
                    <td>${order.getName()}</td>
                    <td>${order.getSurname()}</td>
                    <td>${order.getEmail()}</td>
                    <td>${order.getPhone()}</td>
                </tr>
            </c:catch>
            </tbody>
        </table>
    </div>
</section>

<!-------------------Social media section------------->
<section id="social-media">
    <div class="container text-center" <fmt:message key="social.title" var="socialtitle"/>>
        <p>${socialtitle}</p>
        <div class="social-icons">
            <a href="https://uk-ua.facebook.com"><img src="/view/css/images/fasebook.png"></a>
            <a href="https://www.instagram.com/?hl=ru"><img src="/view/css/images/insta.png"></a>
            <a href="https://www.linkedin.com"><img src="/view/css/images/linkedin.png"></a>
            <a href="https://www.viber.com/ru/"><img src="/view/css/images/viber.png"></a>
        </div>
    </div>

</section>

<!--------------------FOOTER------------------->
<section id="footer">
    <img src="" class="footer img">
    <div class="container">
        <div class="row">
            <div class="col-md-4 footer-box">
                <img src="/view/css/images/logo.png">
                <p>More than 250,000 happy customers</p>
            </div>
            <div class="col-md-4 footer-box" <fmt:message key="contact.title" var="contactus"/>
                    <fmt:message key="contact.address" var="contactaddress"/>>
                <p><b>${contactus}</b></p>
                <p><i class="fa fa-map-marker"></i> ${contactaddress}</p>
                <p><i class="fa fa-phone"></i> +380989841075</p>
                <p><i class="fa fa-envelope-o"></i> repair_agency@gmail.com</p>
            </div>
            <div class="col-md-4 footer-box" <fmt:message key="subscribe.title" var="subscribetitle"/>>
                <p><b>${subscribetitle}</b></p>
                <input type="email" class="form-control" placeholder="Email">
                <button type="button" class="btn btn-primary" <fmt:message key="button.subscribe"
                                                                           var="butsubscr"/>>${butsubscr}</button>
            </div>
        </div>
    </div>
    <div>
        <hr>
        <p class="copyright">@Made by Iryna Shvets 2019</p>
    </div>
</section>
<!----------Smooth Scroll------------>
<script src="/view/js/smooth-scroll.js"></script>
<script>
    var scroll = new SmoothScroll('a[href*="#"]');
</script>
</body>

</html>
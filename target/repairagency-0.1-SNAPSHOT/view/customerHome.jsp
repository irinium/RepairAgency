<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<html lang="param.locale">

<head>
    <title>Customer</title>
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
                    <a class="nav-link" href="#banner" <fmt:message key="navbar.add.application"
                                                                    var="addApp"/>> ${addApp} </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="customer?command=userOrderList&currentPage=1&recordsPerPage=5" <fmt:message
                            key="navbar.my.applications" var="myApp"/>> ${myApp}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#feedback" <fmt:message key="navbar.leave.feedback"
                                                                      var="leaveFeebk"/>> ${leaveFeebk}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#myaccount" <fmt:message key="customer.mydata"
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
<!-------------Application section--------------->
<section id="banner">
    <div class="container">
        <div class="row">
            <div class="col-md-6">
                <form class="appl-form" method="post" action="${pageContext.request.contextPath}/customer">
                    <input class="hidden" name="command" value="makeOrder">
                    <div class="form-group">
                        <label for="type" <fmt:message key="customer.select.type.title" var="type"/>>${type}</label>
                        <select class="form-control" id="type" name="type"
                                <fmt:message key="services.major" var="major"/>
                                <fmt:message key="services.minor" var="minor"/>
                                <fmt:message key="services.climatic" var="climatic"/>>
                            <option value="MAJOR_HOUSEHOLD_APPLIANCE">${major}</option>
                            <option value="MINOR_HOUSEHOLD_APPLIANCE">${minor}</option>
                            <option value="CLIMATIC_APPLIANCE">${climatic}</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <label for="manufacturer" <fmt:message key="customer.select.manufacturer.title"
                                                               var="manufacturer"/>>${manufacturer}</label>
                        <select class="form-control" id="manufacturer" name="manufacturer">
                            <option value="LG">LG</option>
                            <option value="SAMSUNG">SAMSUNG</option>
                            <option value="BOSCH">BOSCH</option>
                            <option value="BRAUN">BRAUN</option>
                            <option value="NOVATECK">NOVATECK</option>
                            <option value="COOPER_HUNTER">COOPER_HUNTER</option>
                            <option value="TIBERIS">TIBERIS</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <input class="form-control" name="appliance_name" type="text" <fmt:message
                                key="customer.select.appl.name"
                                var="name"/> placeholder="${name}"/>
                        <input class="form-control" name="model" type="text" <fmt:message
                                key="customer.select.appl.model" var="model"/>
                               placeholder="${model}"/>
                        <input class="form-control" name="disrepair" type="text" <fmt:message
                                key="customer.select.appl.disrepair"
                                var="disrepair"/> placeholder="${disrepair}"/>
                        <input class="form-control" name="title" type="text" <fmt:message
                                key="customer.select.appl.title" var="title"/>
                               placeholder="${title}"/>
                    </div>
                    <button class="btn btn-primary" <fmt:message key="button.application"
                                                                 var="application"/>>${application}</button>
                </form>
            </div>
            <div class="master col-md-6 text-center">
                <img src="/view/css/images/broken.jpg" class="img-fluid">
            </div>
        </div>
    </div>
    <img src="/view/css/images/footer.png" class="bottom-img">
</section>

<!-------------------Feedback------------------->

<section id="feedback">
    <div class="container">
        <h1 class="title text-center" <fmt:message key="feedbk.title" var="feedbackTitle"/>>${feedbackTitle}</h1>
        <div class="row">
            <div class="col-md-6 about-us">
                <form method="post" action="${pageContext.request.contextPath}/customer">
                    <input class="hidden" name="command" value="feedback">
                    <div class="form-group">
                    <textarea class="form-control" maxlength="255" minlength="5" <fmt:message
                            key="navbar.leave.feedback" var="feedbk"/> placeholder="${feedbk}"
                              name="feedback" rows="8" cols="50"></textarea>
                    </div>
                    <button class="btn btn-primary" <fmt:message key="navbar.leave.feedback"
                                                                 var="feed"/>>${feed}</button>
                </form>
            </div>
            <div class="col-md-6">
                <img src="/view/css/images/master2.png" class="img-fluid">
            </div>
        </div>
    </div>
</section>

<!------------------My account---------------->
<section id="myaccount">
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
                <c:catch var="user">
                    <tr>
                        <td>${user.getName()}</td>
                        <td>${user.getSurname()}</td>
                        <td>${user.getEmail()}</td>
                        <td>${user.getPhone()}</td>
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
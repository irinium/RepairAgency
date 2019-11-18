
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<html lang="param.locale">
<head>
    <title>Home</title>
    <link rel="stylesheet" href="/view/css/home_style.css">
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
                    <a class="nav-link" href="#top" <fmt:message key="navbar.home" var="addApp"/>> ${addApp} </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#about-us" <fmt:message key="navbar.aboutus" var="myApp"/>> ${myApp}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#services" <fmt:message key="navbar.services" var="leaveFeebk"/>> ${leaveFeebk}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#testimonials" <fmt:message key="navbar.testimonials" var="account"/>>${account}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#footer" <fmt:message key="navbar.contact" var="logout"/>>${logout}</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="/view/login_register.jsp" <fmt:message key="button.login" var="login"/>>${login}</a>
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
                <p class="promo-title" <fmt:message key="promo.title" var="promotit"/> <fmt:message key="promo.message" var="promomes"/>>${promotit}</p>
                <p> ${promomes}</p>
                <a href="/view/css/images/video_2019-11-10_17-05-18.mp4"><img src="/view/css/images/play.png" class="play-btn" <fmt:message key="promo.video" var="promovid"/>>${promovid}</a>
            </div>
            <div class="master col-md-6 text-center">
                <img src="/view/css/images/master5.png" class="img-fluid">
            </div>
        </div>
    </div>
    <%-- <img src="/view/css/images/footer.png" class="bottom-img">--%>
</section>

<!---------------Services section------------------>
<section id="services">
    <form action="/view/login_register.jsp">
    <div class="container text-center">
        <h1 class="title" <fmt:message key="services.title" var="servtitle"/>>${servtitle}</h1>
        <div class="row text-center">
            <div class="col-md-4 services">
                <img src="/view/css/images/dishwasher.png" class="service-img" <fmt:message key="services.major" var="major"/> <fmt:message key="services.major.text" var="majortext"/>>
                <h4>${major}</h4>
                <p>${majortext}</p>
            </div>
            <div class="col-md-4 services">
                <img src="/view/css/images/minor.png" class="service-img" <fmt:message key="services.minor" var="minor"/> <fmt:message key="services.minor.text" var="minortext"/>>
                <h4>${minor}</h4>
                <p>${minortext}</p>
            </div>
            <div class="col-md-4 services">
                <img src="/view/css/images/climatic.jpg" class="service-img" <fmt:message key="services.climatic" var="climatic"/> <fmt:message key="services.climatic.text" var="climatictext"/>>
                <h4>${climatic}</h4>
                <p>${climatictext}</p>
            </div>
        </div>
        <button type="submit" class="btn btn-primary" <fmt:message key="button.application" var="application"/>>${application}</button>
    </div>
    </form>
</section>

<!-------------------About us------------------->

<section id="about-us">
    <div class="container">
        <h1 class="title text-center" <fmt:message key="aboutus.title" var="abouttitle"/>>${abouttitle}</h1>
        <div class="row">
            <div class="col-md-6 about-us">
                <p class="about-title" <fmt:message key="aboutus.title2" var="abouttotle2"/>
                        <fmt:message key="about.message1" var="message1"/>
                        <fmt:message key="about.message2" var="message2"/>
                        <fmt:message key="about.message3" var="message3"/>
                        <fmt:message key="about.message4" var="message4"/>
                        <fmt:message key="about.message5" var="message5"/>
                        <fmt:message key="about.message6" var="message6"/>
                        <fmt:message key="about.message7" var="message7"/>>${abouttotle2}</p>
                <ul>
                    <li> ${message1}</li>
                    <li> ${message2}</li>
                    <li> ${message3}</li>
                    <li> ${message4}</li>
                    <li> ${message5}</li>
                    <li> ${message6}</li>
                    <li> ${message7}</li>
                </ul>
            </div>
            <div class="col-md-6">
                <img src="/view/css/images/master2.png" class="img-fluid">
            </div>
        </div>
    </div>
</section>

<!------------------Testimonials---------------->
<section id="testimonials">
    <div class="container">
        <h1 class="title text-center" <fmt:message key="testimonials.title" var="testimontitle"/>>${testimontitle}</h1>
        <div class="row offset-1">
            <div class="col-md-5 testimonials">
                <p>"This Repair Agency was quick, affordable, and extremely convenient! Roman was my tech and he was
                    great. Would 100% use Agency again!"</p>
                <img src="/view/css/images/roshan.jpg">
                <p class="user-details">
                    <b>Roshan Izmailov</b><br>
                </p>
            </div>
            <div class="col-md-5 testimonials">
                <p>"I'm impressed at the efficiency of this company. Alex was a real MVP, super professional and
                    friendly."</p>
                <img src="/view/css/images/sam.jpg">
                <p class="user-details">
                    <b>Ivan Cazimirov</b><br>
                </p>
            </div>
        </div>
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
        <a href="https://www.viber.com/ru"><img src="/view/css/images/viber.png"></a>
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
                <button type="button" class="btn btn-primary" <fmt:message key="button.subscribe" var="butsubscr"/>>${butsubscr}</button>
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

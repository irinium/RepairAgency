<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html lang="param.locale">

<title>Error Page</title>
<head>
    <link rel="stylesheet" href="/view/css/runtimeError_style.css">
</head>
<body>
<div class="titanic"></div>

<h1 data-txt="5⬡⬡" aria-label="Internal Server Error">5<span data-overlay="🤦‍♀️">⬡</span><span data-overlay="🤦‍♂️">⬡</span></h1>
<p><h3>It's broken, but it's not your fault.
    Stay calm and don't freak out!</h3></p>

Request from ${pageContext.errorData.requestURI} is failed
<br/>
Servlet name:${pageContext.errorData.servletName}
<br/>
Status code: ${pageContext.errorData.statusCode}
<br/>
Exception: ${pageContext.exception}
<br/>
Message from exception: ${pageContext.exception.message}
</body>
</html>
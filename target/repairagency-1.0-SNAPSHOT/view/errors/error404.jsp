<%--
  Created by IntelliJ IDEA.
  User: home
  Date: 18.11.2019
  Time: 5:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>404</title>
<body background="/view/css/images/404-error.jpg">
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

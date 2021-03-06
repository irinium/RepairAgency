<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.locale}"/>
<fmt:setBundle basename="messages"/>

<html lang="param.locale">
<head>
    <title>Orders</title>
    <link rel="stylesheet" href="/css/lists_style.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

</head>
<body>
<div class="locale">
    <form class="setlocale">
        <label for="locale"></label><select id="locale" name="locale" onchange="submit()">
        <option value="en" ${locale == 'en' ? 'selected' : ''}>English</option>
        <option value="ru" ${locale == 'ru' ? 'selected' : ''}>Русский</option>
    </select>
    </form>
</div>

<input type="hidden" name="currentPage" value="1">

<div class="container">
    <table class="table text-center table-bordered table-blue">
        <thead class="thead-dark">
        <tr>
            <th scope="col">Id</th>
            <th scope="col"><fmt:message key="customer.select.appl.name"/></th>
            <th scope="col"><fmt:message key="customer.select.manufacturer.title"/></th>
            <th scope="col"><fmt:message key="customer.select.appl.model"/></th>
            <th scope="col"><fmt:message key="customer.select.appl.disrepair"/></th>
            <th scope="col"><fmt:message key="customer.select.appl.master"/></th>
            <th scope="col"><fmt:message key="customer.allorders.state"/></th>
            <th scope="col"><fmt:message key="customer.select.appl.title"/></th>
            <th scope="col"><fmt:message key="customer.allorders.price"/></th>
            <th scope="col"><fmt:message key="customer.allorders.status"/></th>
            <th scope="col"><fmt:message key="button.edit"/></th>
        </tr>
        </thead>
        <tbody light>
        <c:forEach items="${orders}" var="user">
        <form class="register-form" method="post" action="${pageContext.request.contextPath}/manager">
            <input class="hidden" name="command" value="updateOrder">
            <input type="hidden" name="orderId" value="${user.getId()}">
            <tr>
                <td>${user.getId()}</td>
                <td>${user.getAppliance().getName()}</td>
                <td>${user.getAppliance().getManufacturer()}</td>
                <td>${user.getAppliance().getModel()}</td>
                <td>${user.getAppliance().getDisrepair()}</td>
                <td>${user.getMaster()}</td>
                <td>${user.getState()==false? "not accepted":"accepted"}
                    <select id="state" name="state">
                        <option value="false" <fmt:message key="select.state1" var="notacc"/>>${notacc}</option>
                        <option value="true" <fmt:message key="select.state2" var="accep"/>>${accep}</option>
                    </select>
                </td>
                <td>
                    ${user.getTitle()}
                    <textarea name="comments"></textarea>
                </td>
                <td>${user.getPrice()}
                    <input type="number" placeholder="0.00" name="price" min="0" value="0" step=".01">
                </td>
                <td>${user.getStatus()==false? "archived":"active"}
                <td>
                    <button class="btn btn-primary" value="button" <fmt:message key="button.edit"
                                                                 var="edit"/>>${edit}</button>
                </td>
            </tr>
        </form>
        </c:forEach>
        </tbody>
    </table>
    <a href="managerHome.jsp">
        <button class="btn btn-primary"  <fmt:message key="navbar.home" var="mesreg"/>>${mesreg}</button>
    </a>
</div>

<nav aria-label="Navigation for orders">
    <ul class="pagination">
        <c:if test="${currentPage != 1}">
            <li class="page-item"><a class="page-link"
                                     href="manager?command=orderList&currentPage=${currentPage-1}&recordsPerPage=${recordsPerPage}">Previous</a>
            </li>
        </c:if>

        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <li class="page-item active"><a class="page-link">
                            ${i} <span class="sr-only">(current)</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item"><a class="page-link"
                                             href="manager?command=orderList&currentPage=${i}&recordsPerPage=${recordsPerPage}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li class="page-item"><a class="page-link"
                                     href="manager?command=orderList&currentPage=${currentPage+1}&recordsPerPage=${recordsPerPage}">Next</a>
            </li>
        </c:if>
    </ul>
</nav>

</body>
</html>
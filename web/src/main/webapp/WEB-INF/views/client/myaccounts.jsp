<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><s:message code="page.client.accounts"/></title>
</head>
<body>
<h2><s:message code="page.client.accounts"/></h2>
<h3>${paysuccess}</h3>
<div style="width: 1100px;">
    <div style="float: left; width: 220px;">
        <br>
        <li><a href="${pageContext.request.contextPath}/client/mybookings"><s:message
                code="page.client.bookings"/></a></li>
        <li><a href="${pageContext.request.contextPath}/client/unpaidaccounts"><s:message
                code="page.client.accounts.unpaid"/></a></li>
        <li><a href="${pageContext.request.contextPath}/client/myaccounts"><s:message
                code="page.client.accounts"/></a></li>
        <li><a href="${pageContext.request.contextPath}/client/order"><s:message
                code="page.client.to.booking"/></a></li>
        <br>
        <li><a href="${pageContext.request.contextPath}/logout"><s:message code="page.logout"/></a></li>
    </div>
    <c:choose>
        <c:when test="${not empty bookingByUser}">
            <div style="float: right; width: 880px">
                <table border="1">
                    <thead align="center">
                    <tr>
                        <th><s:message code="page.table.check-in"/></th>
                        <th><s:message code="page.table.check-out"/></th>
                        <th><s:message code="page.table.place"/></th>
                        <th><s:message code="page.table.category"/></th>
                        <th><s:message code="page.table.room.id"/></th>
                        <th><s:message code="page.table.account.id"/></th>
                        <th><s:message code="page.table.status"/></th>
                        <th><s:message code="page.table.summa"/></th>
                    </tr>
                    </thead>
                    <tbody align="center">
                    <c:forEach items="${bookingByUser}" var="booking">
                        <tr>
                            <td><c:out value="${booking.startDate}"/></td>
                            <td><c:out value="${booking.endDate}"/></td>
                            <td><c:out value="${booking.place}"/></td>
                            <td><c:out value="${booking.category}"/></td>
                            <td><c:out value="${booking.roomId}"/></td>
                            <td><c:out value="${booking.accountId}"/></td>
                            <td><c:out value="${booking.status}"/></td>
                            <td><c:forEach items="${accountById}" var="account">
                                <c:if test="${account.accountId==booking.accountId}">${account.sum}$</c:if>
                            </c:forEach></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:when>
        <c:otherwise>
            <h3>Нет счетов</h3>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>
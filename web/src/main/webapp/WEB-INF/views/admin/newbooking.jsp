<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><s:message code="page.admin.new.bookings"/></title>
</head>
<body>
<h2><s:message code="page.admin.new.bookings"/></h2>
<div style="width: 1100px;">
    <div style="float: left; width: 200px;">
        <br>
        <li><a href="${pageContext.request.contextPath}/admin/newbooking"><s:message
                code="page.admin.new.bookings"/></a></li>
        <li><a href="${pageContext.request.contextPath}/admin/allbookings"><s:message
                code="page.admin.all.bookings"/></a></li>
        <li><a href="${pageContext.request.contextPath}/admin/allaccounts"><s:message
                code="page.admin.all.accounts"/></a></li>
        <li><a href="${pageContext.request.contextPath}/admin/allusers"><s:message
                code="page.admin.all.users"/></a></li>
        <li><a href="${pageContext.request.contextPath}/admin/allrooms"><s:message
                code="page.admin.all.rooms"/></a></li>
        <br>
        <li><a href="${pageContext.request.contextPath}/logout"><s:message code="page.logout"/></a></li>
    </div>
    <c:choose>
        <c:when test="${not empty newBooking}">
            <div style="float: right; width: 900px">
                <table border="1">
                    <thead align="center">
                    <tr>
                        <th>№</th>
                        <th><s:message code="page.table.check-in"/></th>
                        <th><s:message code="page.table.check-out"/></th>
                        <th><s:message code="page.table.place"/></th>
                        <th><s:message code="page.table.category"/></th>
                        <th><s:message code="page.table.room.id"/></th>
                        <th><s:message code="page.table.user.id"/></th>
                        <th><s:message code="page.table.account.id"/></th>
                        <th><s:message code="page.table.status"/></th>
                        <th colspan="2"><s:message code="page.table.action"/></th>
                    </tr>
                    </thead>
                    <tbody align="center">
                    <c:forEach items="${newBooking}" var="booking">
                        <tr>
                            <td><c:out value="${booking.bookingId}"/></td>
                            <td><c:out value="${booking.startDate}"/></td>
                            <td><c:out value="${booking.endDate}"/></td>
                            <td><c:out value="${booking.place}"/></td>
                            <td><c:out value="${booking.category}"/></td>
                            <td><c:out value="${booking.roomId}"/></td>
                            <td><c:out value="${booking.userId}"/></td>
                            <td><c:out value="${booking.accountId}"/></td>
                            <td><c:out value="${booking.status}"/></td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/chooseroom?id=${booking.bookingId}"><s:message
                                        code="page.admin.action.bill"/></a>
                            </td>
                            <td>
                                <a href="${pageContext.request.contextPath}/admin/newbooking?id=${booking.bookingId}"><s:message
                                        code="page.admin.action.reject"/></a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </c:when>
        <c:otherwise>
            <h3>Нет новых бронирований номеров</h3>
        </c:otherwise>
    </c:choose>
</div>
</body>
</html>

<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><s:message code="page.admin.all.accounts"/></title>
</head>
<body>
<h2><s:message code="page.admin.all.accounts"/></h2>
<div style="width: 1100px;">
    <div style="float: left; width: 180px;">
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
    <div style="float: right; width: 920px">
        <table border="1">
            <thead align="center">
            <tr>
                <th><s:message code="page.table.check-in"/></th>
                <th><s:message code="page.table.check-out"/></th>
                <th><s:message code="page.table.place"/></th>
                <th><s:message code="page.table.category"/></th>
                <th><s:message code="page.table.room.id"/></th>
                <th><s:message code="page.table.name"/></th>
                <th><s:message code="page.table.last.name"/></th>
                <th><s:message code="page.table.account.id"/></th>
                <th><s:message code="page.table.status"/></th>
                <th><s:message code="page.table.summa"/></th>
            </tr>
            </thead>
            <tbody align="center">
            <c:forEach items="${allBookings}" var="booking">
                <tr>
                    <td><c:out value="${booking.startDate}"/></td>
                    <td><c:out value="${booking.endDate}"/></td>
                    <td><c:out value="${booking.place}"/></td>
                    <td><c:out value="${booking.category}"/></td>
                    <td><c:out value="${booking.roomId}"/></td>
                    <td><c:forEach items="${allUsers}" var="user">
                        <c:if test="${user.userId==booking.userId}">${user.firstName}</c:if>
                    </c:forEach></td>
                    <td><c:forEach items="${allUsers}" var="user">
                        <c:if test="${user.userId==booking.userId}">${user.lastName}</c:if>
                    </c:forEach></td>
                    <td><c:out value="${booking.accountId}"/></td>
                    <td><c:out value="${booking.status}"/></td>
                    <td><c:forEach items="${allAccounts}" var="account">
                        <c:if test="${account.accountId==booking.accountId}">${account.sum}$</c:if>
                    </c:forEach></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
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
<h3><s:message code="page.admin.all.accounts"/></h3>
<br>
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
                <c:if test="${account.accountId==booking.accountId}">${account.summa}$</c:if>
            </c:forEach></td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<table>
    <tr>
        <td>
            <form method="post" action=http://localhost:8080/hotel/admin/newbooking>
                <s:message var="newBookings" code="page.admin.new.bookings"/>
                <input type="submit" value="${newBookings}"/>
            </form>
        </td>
        <td>
            <form method="post" action=http://localhost:8080/hotel/admin/allbookings>
                <s:message var="allBookings" code="page.admin.all.bookings"/>
                <input type="submit" value="${allBookings}"/>
            </form>
        </td>
        <td>
            <form method="post" action=http://localhost:8080/hotel/admin/allusers>
                <s:message var="allUsers" code="page.admin.all.users"/>
                <input type="submit" value="${allUsers}"/>
            </form>
        </td>
        <td>
            <form method="post" action=http://localhost:8080/hotel/admin/allrooms>
                <s:message var="allRooms" code="page.admin.all.rooms"/>
                <input type="submit" value="${allRooms}"/>
            </form>
        </td>
    </tr>
</table>
<br>
<form method="post" action=http://localhost:8080/hotel/logout>
    <s:message var="logout" code="page.logout"/>
    <input type="submit" value="${logout}"/>
</form>
</body>
</html>
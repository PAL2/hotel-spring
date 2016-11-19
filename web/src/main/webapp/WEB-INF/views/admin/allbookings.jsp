<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><s:message code="page.admin.all.bookings"/></title>
</head>
<body>
<h3><s:message code="page.admin.all.bookings"/></h3>
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
    <c:forEach items="${allBooking}" var="booking">
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
                <form method="post" action=http://localhost:8080/hotel/admin/allbookings?id=${booking.bookingId}>
                    <s:message var="delete" code="page.admin.action.delete"/>
                    <input type="submit" value="${delete}"/>
                </form>
            </td>
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
            <form method="post" action=http://localhost:8080/hotel/admin/allaccounts>
                <s:message var="allAccounts" code="page.admin.all.accounts"/>
                <input type="submit" value="${allAccounts}"/>
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
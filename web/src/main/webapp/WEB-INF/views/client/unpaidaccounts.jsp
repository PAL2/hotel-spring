<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>My unpaid accounts</title>
</head>
<body>
<h3>My unpaid accounts</h3>
<table border="1">
    <thead align="center">
    <tr>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Place</th>
        <th>Category</th>
        <th>Room Id</th>
        <th>Name</th>
        <th>Last Name</th>
        <th>Account Id</th>
        <th>Status</th>
        <th>Summa</th>
        <th colspan="1">Action</th>
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
            <td>${user.firstName}</td>
            <td>${user.lastName }</td>
            <td><c:out value="${booking.accountId}"/></td>
            <td><c:out value="${booking.status}"/></td>
            <td><c:forEach items="${accountById}" var="account">
                <c:if test="${account.accountId==booking.accountId}">${account.summa}$</c:if>
            </c:forEach></td>
            <td>
                <form method="post" action="controller">
                    <input type="hidden" name="command" value="pay"/> <input
                        type="hidden" name="booking_id" value="${booking.bookingId}">
                    <input type="submit" value="Pay"/>
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
            <form method="post" action=http://localhost:8080/hotel/client/mybookings>
                <input type="submit" value="My bookings"/>
            </form>
        </td>
        <td>
            <form method="post" action=http://localhost:8080/hotel/client/unpaidaccounts>
                <input type="submit" value="Unpaid accounts"/>
            </form>
        </td>
        <td>
            <form method="post" action=http://localhost:8080/hotel/client/myaccounts>
                <input type="submit" value="My accounts"/>
            </form>
        </td>
        <td>
            <form method="get" action=http://localhost:8080/hotel/client/gotoorder>
                <input type="submit" value="Go to booking"/>
            </form>
        </td>
    </tr>
</table>
<br>
<form method="post" action=http://localhost:8080/hotel/logout>
    <input type="submit" value="Log out"/>
</form>
</body>
</html>
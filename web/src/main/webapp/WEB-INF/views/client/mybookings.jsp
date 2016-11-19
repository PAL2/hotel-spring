<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title><s:message code="page.client.bookings"/></title>
</head>
<body>
	<h3><s:message code="page.client.bookings"/></h3>
	<table border="1">
		<thead align="center">
			<tr>
				<th>№</th>
				<th><s:message code="page.table.check-in"/></th>
				<th><s:message code="page.table.check-out"/></th>
				<th><s:message code="page.table.place"/></th>
				<th><s:message code="page.table.category"/></th>
				<th><s:message code="page.table.room.id"/></th>
				<th><s:message code="page.table.account.id"/></th>
				<th><s:message code="page.table.status"/></th>
			</tr>
		</thead>
		<tbody align="center">
			<c:forEach items="${bookingByUser}" var="booking">
				<tr>
					<td><c:out value="${booking.bookingId}" /></td>
					<td><c:out value="${booking.startDate}" /></td>
					<td><c:out value="${booking.endDate}" /></td>
					<td><c:out value="${booking.place}" /></td>
					<td><c:out value="${booking.category}" /></td>
					<td><c:out value="${booking.roomId}" /></td>
					<td><c:out value="${booking.accountId}" /></td>
					<td><c:out value="${booking.status}" /></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<br>
	<table>
		<tr>
			<td>
				<form method="post" action=http://localhost:8080/hotel/client/mybookings>
					<s:message var="myBookings" code="page.client.bookings"/>
					<input type="submit" value="${myBookings}"/>
				</form>
			</td>
			<td>
				<form method="post" action=http://localhost:8080/hotel/client/unpaidaccounts>
					<s:message var="unpaidAccounts" code="page.client.accounts.unpaid"/>
					<input type="submit" value="${unpaidAccounts}"/>
				</form>
			</td>
			<td>
				<form method="post" action=http://localhost:8080/hotel/client/myaccounts>
					<s:message var="myAccounts" code="page.client.accounts"/>
					<input type="submit" value="${myAccounts}"/>
				</form>
			</td>
			<td>
				<form method="get" action=http://localhost:8080/hotel/client/gotoorder>
					<s:message var="goToBooking" code="page.client.to.booking"/>
					<input type="submit" value="${goToBooking}"/>
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
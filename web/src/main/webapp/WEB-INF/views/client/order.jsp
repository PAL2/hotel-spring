<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Order</title>
</head>
<body>
	<form action="controller" method="post">
		<fieldset>
			<h3>${roomSuccess}</h3>
			<div>
				Number of places: <select name="numberPlaces">
					<option value="1">1</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
				</select>
			</div>
			<div>
				Check-in date:
				<br>
				<input type="date" name="startDate" value="" required />
			</div>

			<div>
				Check-out date
				<br>
				<input	type="date" name="endDate" value="" required />
			</div>
			<br>
			<div>
				Category<select name="category">
					<option value="standard">Standard</option>
					<option value="lux">Lux</option>
				</select>
			</div>
			<h4>${incorrectDate}</h4>
			<div>
				<input type="hidden" name="command" value="order" /> <input
					type="submit" value="Book" />
			</div>
		</fieldset>
	</form>
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
		</tr>
	</table>
	<br>
	<form method="post" action=http://localhost:8080/hotel/logout>
		<input type="submit" value="Log out"/>
	</form>
</body>
</html>
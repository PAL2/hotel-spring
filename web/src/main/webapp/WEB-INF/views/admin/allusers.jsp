<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><s:message code="page.admin.all.users"/></title>
</head>
<body>
<h3><s:message code="page.admin.all.users"/></h3>
<table border="1">
    <thead align="center">
    <tr>
        <th>№</th>
        <th><s:message code="page.table.name"/></th>
        <th><s:message code="page.table.last.name"/></th>
    </tr>
    </thead>
    <tbody align="center">
    <c:forEach items="${allUsers}" var="user">
    <tr>
        <td><c:out value="${user.userId}"/></td>
        <td><c:out value="${user.firstName}"/></td>
        <td><c:out value="${user.lastName}"/></td>
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
            <form method="post" action=http://localhost:8080/hotel/admin/allaccounts>
                <s:message var="allAccounts" code="page.admin.all.accounts"/>
                <input type="submit" value="${allAccounts}"/>
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
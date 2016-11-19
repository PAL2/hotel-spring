<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE HTML>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><s:message code="page.admin.all.rooms"/></title>
</head>
<body>
<h3><s:message code="page.admin.all.rooms"/></h3>
<table border="1">
    <thead align="center">
    <tr>
        <th>№</th>
        <th><s:message code="page.table.category"/></th>
        <th><s:message code="page.table.place"/></th>
        <th><s:message code="page.table.price"/></th>
    </tr>
    </thead>
    <tbody align="center">
    <c:forEach items="${allRooms}" var="room">
    <tr>
        <td><c:out value="${room.roomId}"/></td>
        <td><c:out value="${room.category}"/></td>
        <td><c:out value="${room.place}"/></td>
        <td><c:out value="${room.price}$"/></td>
        </c:forEach>
    </tbody>
</table>

<c:choose>
    <c:when test="${currentPage != 1}">
        <td><a href="http://localhost:8080/hotel/admin/allrooms?currentPage=${currentPage - 1}"><s:message code="page.pagination.previous"/></a></td>
    </c:when>
    <c:otherwise>
        <td></td>
    </c:otherwise>
</c:choose>

<c:forEach begin="1" end="${numberOfPages}" var="i">
    <c:choose>
        <c:when test="${currentPage eq i}">
            <td>${i}</td>
        </c:when>
        <c:otherwise>
            <td><a href="http://localhost:8080/hotel/admin/allrooms?currentPage=${i}">${i}</a></td>
        </c:otherwise>
    </c:choose>
</c:forEach>

<c:choose>
    <c:when test="${currentPage lt numberOfPages}">
        <td><a href="http://localhost:8080/hotel/admin/allrooms?currentPage=${currentPage + 1}"><s:message code="page.pagination.next"/></a></td>
    </c:when>
    <c:otherwise>
        <td></td>
    </c:otherwise>
</c:choose>
<br>
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
            <form method="post" action=http://localhost:8080/hotel/admin/allusers>
                <s:message var="allUsers" code="page.admin.all.users"/>
                <input type="submit" value="${allUsers}"/>
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
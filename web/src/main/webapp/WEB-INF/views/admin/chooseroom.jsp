<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Choose a room</title>
</head>
<body>
<table border="1">
    <thead align="center">
    <tr>
        <th>№</th>
        <td><c:out value="${room.category}"/></td>
        <td><c:out value="${room.place}"/></td>
        <td><c:out value="${room.price}$"/></td>
        <th><s:message code="page.table.action"/></th>
    </tr>
    </thead>
    <tbody align="center">
    <c:forEach items="${availableRooms}" var="room">
        <tr>
            <td><c:out value="${room.roomId}"/></td>
            <td><c:out value="${room.category}"/></td>
            <td><c:out value="${room.place}"/></td>
            <td><c:out value="${room.price}"/></td>
            <td>
                <form method="post" action=http://localhost:8080/hotel/admin/chooseroom?id=${id}&room=${room.roomId}>
                    <s:message var="bill" code="page.admin.action.bill"/>
                    <input type="submit" value="${bill}"/>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<br>
<form method="post" action=http://localhost:8080/hotel/logout>
    <s:message var="logout" code="page.logout"/>
    <input type="submit" value="${logout}"/>
</form>
</body>
</html>
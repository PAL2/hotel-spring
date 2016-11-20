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
<h2><s:message code="page.admin.all.rooms"/></h2>
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
        <br>
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
    </div>
</div>
</body>
</html>
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
<h2><s:message code="page.admin.all.users"/></h2>
<div style="width: 1100px;">
    <div style="float: left; width: 200px;">
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
    <div style="float: right; width: 900px">
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
    </div>
</div>
</body>
</html>
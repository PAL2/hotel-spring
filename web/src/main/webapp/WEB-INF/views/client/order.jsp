<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><s:message code="page.client.order"/></title>
</head>
<body>
<h2><s:message code="page.client.order"/></h2>
<div style="width: 1000px;">
    <div style="float: left; width: 220px;">
        <br>
        <br>
        <li><a href="${pageContext.request.contextPath}/client/mybookings"><s:message
                code="page.client.bookings"/></a></li>
        <li><a href="${pageContext.request.contextPath}/client/unpaidaccounts"><s:message
                code="page.client.accounts.unpaid"/></a></li>
        <li><a href="${pageContext.request.contextPath}/client/myaccounts"><s:message
                code="page.client.accounts"/></a></li>
        <li><a href="${pageContext.request.contextPath}/client/order"><s:message
                code="page.client.to.booking"/></a></li>
        <br>
        <li><a href="${pageContext.request.contextPath}/logout"><s:message code="page.logout"/></a></li>
    </div>
    <div style="float: right; width: 780px">
        <form:form method="post">
            <fieldset>
                <h3>${roomSuccess}</h3>
                <div>
                    <s:message code="page.client.number.places"/>: <select name="numberPlaces">
                    <option value="1">1</option>
                    <option value="2">2</option>
                    <option value="3">3</option>
                    <option value="4">4</option>
                </select>
                </div>
                <br>
                <div>
                    <s:message code="page.table.check-in"/>:
                    <br>
                    <input type="date" name="startDate" value="" required/>
                </div>
                <br>
                <div>
                    <s:message code="page.table.check-out"/>:
                    <br>
                    <input type="date" name="endDate" value="" required/>
                </div>
                <br>
                <div>
                    <s:message code="page.table.category"/>:<select name="category">
                    <option value="standard"><s:message code="page.client.category.standard"/></option>
                    <option value="lux"><s:message code="page.client.category.lux"/></option>
                </select>
                </div>
                <h4>${incorrectDate}</h4>
                <div>
                    <input type="hidden" name="command" value="order"/>
                    <s:message var="book" code="page.client.book"/>
                    <input type="submit" value="${book}"/>
                </div>
            </fieldset>
        </form:form>
    </div>
</div>
</body>
</html>
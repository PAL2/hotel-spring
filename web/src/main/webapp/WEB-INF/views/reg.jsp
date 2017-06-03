<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title><s:message code="page.login.reg"/></title>


</head>
<body>
<form:form modelAttribute="regUser" method="POST">
    <fieldset>
        <div>
            <label for="firstName"><s:message code="page.table.name"/>:</label><br>
            <form:input path="firstName"/>
        </div>
        <div>
            <label for="lastName"><s:message code="page.table.last.name"/>:</label><br>
            <form:input path="lastName"/>
        </div>
        <div>
            <label for="login"><s:message code="page.login.login"/>:</label><br>
            <form:input path="login"/>
        </div>
        <div>
            <label for="password"><s:message code="page.login.password"/>:</label><br>
            <form:password path="password"/>
        </div>
        <div>
            <s:message var="reg" code="page.login.reg"/>
            <input type="submit" value="${reg}"/>
        </div>
    </fieldset>
</form:form>
</body>


</html>
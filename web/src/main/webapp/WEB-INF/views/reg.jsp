<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>Registration</title>


</head>
<body>
<form:form modelAttribute="regUser" method="POST">
    <fieldset>
        <div>
            <label for="firstName">First Name:</label><br>
            <form:input path="firstName"/>
        </div>
        <div>
            <label for="lastName">Last Name:</label><br>
            <form:input path="lastName"/>
        </div>
        <div>
            <label for="login">Login:</label><br>
            <form:input path="login"/>
        </div>
        <div>
            <label for="password">Password:</label><br>
            <form:password path="password"/>
        </div>
        <div>
            <input type="submit" value="Registration"/>
        </div>
    </fieldset>
</form:form>
</body>


</html>
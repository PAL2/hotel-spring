<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Log in</title>
</head>
<body>

<h3>${regSuccess}</h3>

<form:form modelAttribute="newUser" method="POST">
    Login:<br />
    <form:input path="login"/>
    <br/>Password:<br/>
    <form:password path="password"/>
    <br/>
    <a href=http://localhost:8080/hotel/reg>Registration</a>
    <h4>${errorLoginPassMessage}</h4>
    ${wrongAction}
    ${nullPage}
    <input type="submit" value="Log in"/>
</form:form>

</body>
</html>
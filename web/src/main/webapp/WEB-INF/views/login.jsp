<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Log in</title>
</head>
<body>
<div class="header">
    <div style="float: left;">
        <table>
            <tr>
                <td style="padding: 5px;">
                    <a href="?locale=ru">Ru</a>
                </td>
                <td style="padding: 5px;">
                    <a href="?locale=en">En</a>
                </td>
            </tr>
        </table>
    </div>
</div>
<br>

<h3>${regSuccess}</h3>

<form name='loginForm'
      action="<c:url value='/j_spring_security_check' />" method='POST'>

    <table>
        <tr>
            <td><s:message code="page.login"/>:</td>
            <td><input type='text' name='username'></td>
        </tr>
        <tr>
            <td><s:message code="page.login.password"/>:</td>
            <td><input type='password' name='password'/></td>
        </tr>
    </table>

    <s:message var="button" code="page.login"/>
    <input type="submit" value="${button}"/>

    <input type="hidden" name="${_csrf.parameterName}"
           value="${_csrf.token}"/>

</form>
<br>
<a href=http://localhost:8080/hotel/reg><s:message code="page.login.reg"/></a>

</body>
</html>
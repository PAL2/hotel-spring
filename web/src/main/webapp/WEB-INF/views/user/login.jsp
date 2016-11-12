<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<h3>${regSuccess}</h3>
<c:url var="login" value="user/login" />
<sf:form method="post" modelAttribute="user" action="${login}">
    Login:<br/>
    <sf:input path="login" id="login"/>
    <br/>Password:<br/>
    <sf:password path="password" id="password"/>
    <br/>
    <a href=reg.jsp>Registration</a>
    <h4>${errorLoginPassMessage}</h4>
    ${wrongAction}
    ${nullPage}
    <input type="submit" value="Log in"/>

</sf:form>

</body>
</html>
<%--
  Created by IntelliJ IDEA.
  User: Алексей
  Date: 02.10.2016
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<html>
<head>
    <title><s:message code="page.error"/></title>
</head>
<body>
<br/>
${errorDatabase} <br/>
<form method="post" action=http://localhost:8080/hotel/logout>
    <s:message var="logout" code="page.logout"/>
    <input type="submit" value="${logout}"/>
</form>
</body>
</html>
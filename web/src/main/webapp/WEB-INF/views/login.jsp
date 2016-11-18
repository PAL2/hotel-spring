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
    <div style="float: right;">
        <table>
            <tr>
                <td style="padding: 5px;">
                    <a href="?locale=ru">
                        <img src="${pageContext.request.contextPath}/resources/img/locale_ru.png"
                             height="20"
                             width="20"
                             style="display:block;"
                        />
                    </a>
                </td>
                <td style="padding: 5px;">
                    <a href="?locale=en">
                        <img src="${pageContext.request.contextPath}/resources/img/locale_en.png"
                             height="20"
                             width="20"
                             style="display:block;"
                        />
                    </a>
                </td>
            </tr>
        </table>
    </div>
    <%--<br/>
    <a href="${pageContext.request.contextPath}/home">
        <img src="${pageContext.request.contextPath}/assets/img/mini_logo.png"
             alt="logo"
             name="Insert_logo"
             height="90"
             id="Insert_logo"
             style="display:block;"
        />
    </a>
    <br/>--%>
</div>

<h3>${regSuccess}</h3>

<form:form modelAttribute="newUser" method="POST">
    <s:message code="page.login.login"/>:<br />
    <form:input path="login"/>
    <br/><s:message code="page.login.password"/>:<br/>
    <form:password path="password"/>
    <br/>
    <a href=http://localhost:8080/hotel/reg><s:message code="page.login.reg"/></a>
    <h4>${errorLoginPassMessage}</h4>
    ${wrongAction}
    ${nullPage}
    <s:message var="button" code="page.login"/>
    <input type="submit" value="${button}"/>
</form:form>

</body>
</html>
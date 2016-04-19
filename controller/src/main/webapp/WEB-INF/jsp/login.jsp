<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
</head>
<body>

<h1>Sign up</h1>
<c:out value="${message}" />
<c:url var="saveUrl" value="/login?login=${personAttribute.login}&pass=${personAttribute.pass}"/>
<form:form modelAttribute="personAttribute" method="POST" action="${saveUrl}">
    <table>
        <tr>
            <td><form:label path="login">Login:</form:label></td>
            <td><form:input path="login" disabled="false"/></td>
        </tr>

        <tr>
            <td><form:label path="pass">Pass:</form:label></td>
            <td><form:input path="pass" disabled="false"/></td>
        </tr>

    </table>

    <input type="submit" value="Sign in"/>
</form:form>
<c:url var="goToRegistration" value="/registration" />
<a href="${goToRegistration}">go to registration</a>

</body>
</html>

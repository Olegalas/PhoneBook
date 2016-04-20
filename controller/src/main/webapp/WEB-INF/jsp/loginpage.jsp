<%@include file="include.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login</title>
</head>
<body>

<h1>Sign in</h1>
<c:out value="${message}" />
<c:url var="saveUrl" value="/login"/>
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

<a href="/registration">go to registration</a>

</body>
</html>

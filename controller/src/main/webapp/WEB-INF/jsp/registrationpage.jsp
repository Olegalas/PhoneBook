<%@include file="include.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration</title>
</head>
<body>

<h1>Sign up</h1>
<c:out value="${message}" />
<c:url var="saveUrl" value="/registration"/>
<form:form modelAttribute="personAttribute" method="POST" action="${saveUrl}">
    <table>
        <tr>
            <td><form:label path="login">Login:</form:label></td>
            <td><form:input path="login" disabled="false"/></td>
        </tr>

        <tr>
            <td><form:label path="firstName">FirstName:</form:label></td>
            <td><form:input path="firstName" disabled="false"/></td>
        </tr>

        <tr>
            <td><form:label path="email">Email:</form:label></td>
            <td><form:input path="email" disabled="false"/></td>
        </tr>

        <tr>
            <td><form:label path="lastName">LastName:</form:label></td>
            <td><form:input path="lastName" disabled="false"/></td>
        </tr>

        <tr>
            <td><form:label path="mobilePhone">MobilePhone:</form:label></td>
            <td><form:input path="mobilePhone" disabled="false"/></td>
        </tr>

        <tr>
            <td><form:label path="homePhone">HomePhone:</form:label></td>
            <td><form:input path="homePhone" disabled="false"/></td>
        </tr>

        <tr>
            <td><form:label path="pass">Pass:</form:label></td>
            <td><form:input path="pass" disabled="false"/></td>
        </tr>

        <tr>
            <td><form:label path="rePass">RePass:</form:label></td>
            <td><form:input path="rePass" disabled="false"/></td>
        </tr>

    </table>

    <input type="submit" value="Sign up"/>
</form:form>

<a href="/">go back</a>

</body>
</html>

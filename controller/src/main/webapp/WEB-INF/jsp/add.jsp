<%@include file="include.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add new contact</title>
    <style>
        .error {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>

<h1>Create new contact</h1>
<c:out value="${message}"/>
<table>
    <form:form commandName="newContact" method="POST" action="/addcontact?id=${userId}">
        <tr>
            <td><form:label path="login">Enter login:</form:label></td>
            <td><form:input path="login" disabled="false"/></td>
            <td><form:errors path="login" cssClass="error"/></td>
        </tr>

        <tr>
            <td><form:label path="firstName">Enter first name:</form:label></td>
            <td><form:input path="firstName" disabled="false"/></td>
            <td><form:errors path="firstName" cssClass="error"/></td>
        </tr>

        <tr>
            <td><form:label path="lastName">Enter last name:</form:label></td>
            <td><form:input path="lastName" disabled="false"/></td>
            <td><form:errors path="lastName" cssClass="error"/></td>
        </tr>

        <tr>
            <td><form:label path="email">Enter email:</form:label></td>
            <td><form:input path="email" disabled="false"/></td>
            <td><form:errors path="email" cssClass="error"/></td>
        </tr>

        <tr>
            <td><form:label path="mobilePhone">Enter mobilePhone:</form:label></td>
            <td><form:input path="mobilePhone" disabled="false"/></td>
            <td><form:errors path="mobilePhone" cssClass="error"/></td>
        </tr>

        <tr>
            <td><form:label path="homePhone">Enter homePhone:</form:label></td>
            <td><form:input path="homePhone" disabled="false"/></td>
            <td><form:errors path="homePhone" cssClass="error"/></td>
        </tr>

        <tr>
            <td><input type="submit" value="Create"/></td>
        </tr>
    </form:form>
</table>

<c:url var="goBack" value="/home?id=${userId}"/>
<form:form method="POST" action="${goBack}">
    <input type="submit" value="Go Back"/>
</form:form>

</body>
</html>

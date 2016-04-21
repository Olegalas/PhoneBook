<%@include file="include.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add new contact</title>
</head>
<body>

<h1>Create new contact</h1>
<c:url var="saveUrl" value="/addcontact?id=${userId}"/>
<form:form modelAttribute="newContact" method="POST" action="${saveUrl}">
    <table>

        <tr>
            <td><form:label path="login">Enter login:</form:label></td>
            <td><form:input path="login" disabled="false"/></td>
        </tr>

        <tr>
            <td><form:label path="firstName">Enter first name:</form:label></td>
            <td><form:input path="firstName" disabled="false"/></td>
        </tr>

        <tr>
            <td><form:label path="lastName">Enter last name:</form:label></td>
            <td><form:input path="lastName" disabled="false"/></td>
        </tr>

        <tr>
            <td><form:label path="email">Enter email:</form:label></td>
            <td><form:input path="email" disabled="false"/></td>
        </tr>

        <tr>
            <td><form:label path="mobilePhone">Enter mobilePhone:</form:label></td>
            <td><form:input path="mobilePhone" disabled="false"/></td>
        </tr>

        <tr>
            <td><form:label path="homePhone">Enter homePhone:</form:label></td>
            <td><form:input path="homePhone" disabled="false"/></td>
        </tr>

    </table>

    <input type="submit" value="Create"/>
</form:form>

<c:url var="goBack" value="/home?id=${userId}" />
<form:form method="POST" action="${goBack}">
    <input type="submit" value="Go Back"/>
</form:form>

</body>
</html>

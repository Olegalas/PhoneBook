<%@include file="include.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Edit</title>
</head>
<body>


<h1><c:out value="${message}" /></h1>
<c:url var="saveUrl" value="/edituser?id={user.id}&targetId=${targetId}"/>
<form:form modelAttribute="personAttribute" method="POST" action="${saveUrl}">
    <table>
        <tr>
            <td><form:label path="firstName">Enter first name:</form:label></td>
            <td><form:input path="firstName" disabled="false"/></td>
        </tr>

        <tr>
            <td><form:label path="lustName">Enter last name:</form:label></td>
            <td><form:input path="lustName" disabled="false"/></td>
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

        <c:if test="${targetId == user.id}">
        <tr>
            <td><form:label path="oldPass">Enter old pass:</form:label></td>
            <td><form:password path="oldPass" disabled="false"/></td>
        </tr>

        <tr>
            <td><form:label path="newPass">Enter new pass:</form:label></td>
            <td><form:password path="newPass" disabled="false"/></td>
        </tr>

        <tr>
            <td><form:label path="rePass">Enter new pass:</form:label></td>
            <td><form:password path="rePass" disabled="false"/></td>
        </tr>
        </c:if>
    </table>

    <input type="submit" value="Edit"/>
</form:form>
<c:url var="goBack" value="/home?id=${user.id}" />
<a href="${goBack}">go back</a>

</body>
</html>

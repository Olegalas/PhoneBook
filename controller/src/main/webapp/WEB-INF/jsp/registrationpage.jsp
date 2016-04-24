<%@include file="include.jsp" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Registration</title>
    <style>
        .error {
            color: red; font-weight: bold;
        }
    </style>
</head>
<body>

<h1>Sign up</h1>
<c:out value="${message}" />

<table>
    <form:form commandName="personAttribute" method="POST" action="/registration">

        <tr>
            <td><span class="error"><c:out value="${loginMessage}" /></span></td>
        </tr>

        <tr>
            <td><form:label path="login">Login:</form:label></td>
            <td><form:input path="login" disabled="false"/></td>
            <td><form:errors path="login" cssClass="error"/></td>
        </tr>

        <tr>
            <td><form:label path="firstName">FirstName:</form:label></td>
            <td><form:input path="firstName" disabled="false"/></td>
            <td><form:errors path="firstName" cssClass="error"/></td>
        </tr>

        <tr>
            <td><form:label path="lastName">LastName:</form:label></td>
            <td><form:input path="lastName" disabled="false"/></td>
            <td><form:errors path="lastName" cssClass="error"/></td>
        </tr>

        <tr>
            <td><form:label path="email">Email:</form:label></td>
            <td><form:input path="email" disabled="false"/></td>
            <td><form:errors path="email" cssClass="error"/></td>
        </tr>

        <tr>
            <td><form:label path="mobilePhone" title="+38(0__)___-__-__">Mobile telephone +38(0__)___-__-__ :</form:label></td>
            <td><form:input path="mobilePhone" disabled="false" title="+38(0__)___-__-__"/></td>
            <td><form:errors path="mobilePhone" cssClass="error"/></td>
        </tr>

        <tr>
            <td><form:label path="homePhone" title="+38(0__)___-__-__">Home telephone +38(0__)___-__-__ :</form:label></td>
            <td><form:input path="homePhone" disabled="false" title="+38(0__)___-__-__" autocomplete="true" alt="+38(0__)___-__-__"/></td>
            <td><form:errors path="homePhone" cssClass="error"/></td>
        </tr>

        <tr>
            <td><form:label path="pass" >Pass:</form:label></td>
            <td><form:password path="pass" disabled="false"/></td>
            <td><form:errors path="pass" cssClass="error"/></td>
            <td><span class="error"><c:out value="${passMessage}"/></span></td>
        </tr>

        <tr>
            <td><form:label path="rePass">RePass:</form:label></td>
            <td><form:password path="rePass" disabled="false"/></td>
            <td><form:errors path="rePass" cssClass="error"/></td>
        </tr>

        <tr>
            <td> <input type="submit" value="Sign up"/></td>
        </tr>
    </form:form>
</table>

<c:url var="goBack" value="/" />
<form:form method="GET" action="${goBack}">
    <input type="submit" value="Go Back"/>
</form:form>


</body>
</html>

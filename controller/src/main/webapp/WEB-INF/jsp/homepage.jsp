<%@include file="include.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PhoneBook Home</title>
</head>
<body>
    <table>
        <tr>
            <td><span>Login: </span>></td>
            <td><c:out value="${user.login}" /></td>
        </tr>

        <tr>
            <td><span>First Name: </span>></td>
            <td><c:out value="${user.firstName}" /></td>
        </tr>

        <tr>
            <td><span>Last Name: </span>></td>
            <td><c:out value="${user.lastName}" /></td>
        </tr>

        <tr>
            <td><span>Email: </span>></td>
            <td><c:out value="${user.email}" /></td>
        </tr>

        <tr>
            <td><span>Mobile Telephone: </span>></td>
            <td><c:out value="${user.mobilePhone}" /></td>
        </tr>

        <tr>
            <td><span>Home Telephone: </span>></td>
            <td><c:out value="${user.homePhone}" /></td>
        </tr>
    </table>

    <c:url var="editUrl" value="/edit?id=${user.id}&targetId=${user.id}" />
    <form:form method="POST" action="${editUrl}">
        <input type="submit" value="Edit profile"/>
    </form:form>

    <c:url var="showAllUrl" value="/contacts?id=${user.id}" />
    <form:form method="POST" action="${showAllUrl}">
        <input type="submit" value="Show all contacts"/>
    </form:form>

    <c:url var="createContactUrl" value="/add?id=${user.id}" />
    <form:form method="POST" action="${createContactUrl}">
        <input type="submit" value="Create new contact"/>
    </form:form>

</body>
</html>

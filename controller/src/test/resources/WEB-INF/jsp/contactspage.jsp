<%@include file="include.jsp" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>All Contacts</title>
</head>
<body>


<h1>Your contacts</h1>

<c:forEach items="${user.phoneBook}" var="contact">

   <c:url var="editUrl" value="/edit?id=${user.id}&targetId=${contact.id}" />
   <c:url var="deleteUrl" value="/delete?id=${user.id}&targetId=${contact.id}" />
    <table>
        <tr>
            <td><span>Login :  </span>></td>
            <td><c:out value="${contact.login}"/></td>
        </tr>
        <tr>
            <td><span>First Name :  </span>></td>
            <td><c:out value="${contact.firstName}"/></td>
        </tr>
        <tr>
            <td><span>Last Name :  </span>></td>
            <td><c:out value="${contact.lastName}"/></td>
        </tr>
        <tr>
            <td><span>Email :  </span>></td>
            <td><c:out value="${contact.email}"/></td>
        </tr>
        <tr>
            <td><span>Mobile Telephone :  </span>></td>
            <td><c:out value="${contact.mobilePhone}"/></td>
        </tr>
        <tr>
            <td><span>Home Telephone :  </span>></td>
            <td><c:out value="${contact.homePhone}"/></td>
        </tr>
        <tr>
            <form:form method="POST" action="${editUrl}">
                <input type="submit" value="Edit"/>
            </form:form>
        </tr>
        <tr>
            <form:form method="POST" action="${deleteUrl}">
                <input type="submit" value="Delete"/>
            </form:form>
        </tr>
        <tr>
            <td><span>**************************</span></td>
        </tr>
    </table>
</c:forEach>

<c:url var="goBack" value="/home?id=${user.id}" />
<form:form method="POST" action="${goBack}">
    <input type="submit" value="Go Back"/>
</form:form>

</body>
</html>

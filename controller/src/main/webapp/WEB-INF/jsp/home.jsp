<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>PhoneBook Home</title>
</head>
<body>


    <c:out value="${user.login}" />
    <c:out value="${user.firstName}" />
    <c:out value="${user.lastName}" />
    <c:out value="${user.email}" />
    <c:out value="${user.mobilePhone}" />
    <c:out value="${user.homePhone}" />

    <c:forEach items="${user.phoneBook}" var="user">
       <c:url var="editUrl" value="" />
       <c:url var="deleteUrl" value="" />
      <tr>
       <td><c:out value="${user.login}" /></td>
       <td><a href="${editUrl}">Edit</a></td>
       <td><a href="${deleteUrl}">Delete</a></td>
       <td><a href="${addUrl}">Add</a></td>
      </tr>
     </c:forEach>


</body>
</html>

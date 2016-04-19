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

   <c:url var="editUrl" value="/edit?id=${user.id}" />
   <c:url var="showAllUrl" value="/contacts?id=${user.id}" />
   <c:url var="createContactUrl" value="" />
   <tr>
     <td><a href="${editUrl}">Edit profile</a></td>
     <td><a href="${showAllUrl}">Show all contacts</a></td>
     <td><a href="${createContactUrl}">Create new contact</a></td>
   </tr>

</body>
</html>

<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>


<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>All Contacts</title>
</head>
<body>


<h1><c:out value="${message}" /></h1>

<c:forEach items="${user.phoneBook}" var="contact">

   <c:url var="editUrl" value="/edit?id=${user.id}&personal=${contact.id}" />
   <c:url var="deleteUrl" value="/delete?id=${user.id}&personal=${contact.id}" />

  <tr>
   <td><c:out value="${contact.firstName}" /></td>
   <td><c:out value="${contact.lastName}" /></td>
   <td><c:out value="${contact.email}" /></td>
   <td><c:out value="${contact.mobilePhone}" /></td>
   <td><c:out value="${contact.homePhone}" /></td>
   <td><a href="${editUrl}">Edit</a></td>
   <td><a href="${deleteUrl}">Delete</a></td>
  </tr>
 </c:forEach>


<c:url var="goBack" value="/home?id=${user.id}" />
<a href="${goBack}">go back</a>

</body>
</html>
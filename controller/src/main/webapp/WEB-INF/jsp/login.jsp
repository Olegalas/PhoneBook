<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>

<h1>Sign up</h1>
<c:url var="saveUrl" value="/phonebook/main/login?login=${personAttribute.login}&pass=${personAttribute.pass}" />
<form:form modelAttribute="personAttribute" method="POST" action="${saveUrl}">
 <table>
  <tr>
   <td><form:label path="login">Login:</form:label></td>
   <td><form:input path="login" disabled="false"/></td>
  </tr>

  <tr>
   <td><form:label path="pass">Pass:</form:label></td>
   <td><form:input path="pass" disabled="false"/></td>
  </tr>

 </table>

 <input type="submit" value="Sign in" />
</form:form>

</body>
</html>

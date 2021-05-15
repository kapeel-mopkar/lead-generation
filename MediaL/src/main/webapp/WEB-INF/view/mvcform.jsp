<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h2>USER REGISTERATION..!</h2>
	<form:form action="process" modelAttribute="user" method="POST">
		Enter username: <form:input path="username"/><br><br>
		Enter pass: <form:password path="pass"/><br><br>
		Enter Role:
		<form:select path="roles">
			<form:option value="ROLE_ADMIN" label="ROLE_ADMIN"/>
			<form:option value="ROLE_USER" label="ROLE_USER"/>
		</form:select>
		<br><br>
		Enter Country:
		<form:select path="country">
			<form:option value="India" label="India"/>
			<form:option value="NewYork" label="NewYork"/>
			<form:option value="US" label="US"/>
		</form:select>
		<br><br>
		IsEnable:
		<form:select path="enable">
			<form:option value="True" label="True"/>
			<form:option value="False" label="False"/>
		</form:select>
		<br><br>
		<input type="submit" value="Register">
	</form:form>
</body>
</html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h2>Country : ${con }</h2>
	<br><br>
	<table>
	<tr>
	<th>Id</th><th>Country</th><th>Usename</th><th>Role</th><th>Enable</th>
	</tr>
		<c:forEach var="temp" items="${user}">
		<tr>
		<td>${temp.id}</td>
		<td>${temp.country}</td>
		<td>${temp.username}</td>
		<td>${temp.roles}</td>
		<td>${temp.enable}</td>
		</tr>
		</c:forEach>
	
	</table>
</body>
</html>
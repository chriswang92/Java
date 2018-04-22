<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>user login </title>

<style>

	.error {
		color: #ff0000;
	}
</style>

</head>

<body>

	<h2>login </h2>
 
	<form:form method="POST" modelAttribute="user">
		<form:input type="hidden" path="id" id="id"/>
		<table>
			<tr>
				<td><label for="username">Username: </label> </td>
				<td><form:input path="username" id="username"/></td>
		    </tr>
	    
			<tr>
				<td><label for="password">password: </label> </td>
				<td><form:input type="password" path="password" id="password"/></td>
		    </tr>
	
			<tr>
				<td>
					<input type="submit" value="login"/>
				</td>
			</tr>
		</table>
	</form:form>
	<span>${loginFailedMessage}</span>
	<br/>
	<br/>
	Go back to <a href="<c:url value='/' />">home</a>
</body>
</html>
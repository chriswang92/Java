<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration Confirmation Page</title>
</head>
<body>
	message : ${success}
	<br/>current_user : ${current_username}
	<br/>
	<br/>
	<a href="<c:url value='/allBlogs-${current_username}' />">see all blogs wrote by: ${current_username}</a></br>
	Go back to <a href="<c:url value='/list' />">List of All Employees</a></br>
	Go back to <a href="<c:url value='/userlist' />">List of All Users</a>
</body>

</html>
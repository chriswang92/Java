<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>	

<title>home page</title>

<body>
	
	<div class="container">
	<div class="page-header">
	    <h1>Welcome to chris wang's blog</h1>      
	  </div>
	<h2>TOP 5 Employees</h2>	
	<table class="table">
		
	<thead>
      <tr>
        <th>Name</th>
        <th>Joining Date</th>
        <th>Salary</th>
        <th>SSN</th>
      </tr>
    </thead>
    
		<c:forEach items="${topEmployees}" var="topEmployees">
			<tr class="info">
			<td>${topEmployees.name}</td>
			<td>${topEmployees.joiningDate}</td>
			<td>${topEmployees.salary}</td>
			<td>${topEmployees.ssn}</td>
			</tr>
		</c:forEach>
		
	</table>
	<br/>
	<a href="<c:url value='/list' />">all employees</a> </br>
	<a href="<c:url value='/userlist' />">all users</a> </br>
	<a href="<c:url value='/userRegister' />">user register</a> </br>
	<a href="<c:url value='/login' />">user login</a>
</div>
	
</body>
</html>
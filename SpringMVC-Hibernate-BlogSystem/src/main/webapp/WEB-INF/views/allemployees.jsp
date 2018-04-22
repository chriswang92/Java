<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>	
	<title>University Enrollments</title>

	<style>
		tr:first-child{
			font-weight: bold;
			background-color: #C6C9C4;
		}
		.center {
		    margin: auto;
		    width: 60%;
		    border: 3px solid #73AD21;
		    padding: 10px;
		}
	</style>

</head>


<body>
<div class="container">

	<h2>List of Employees</h2>	
	<table class="table">
		
	<thead>
      <tr>
        <th>Name</th>
        <th>Joining Date</th>
        <th>Salary</th>
        <th>SSN</th>
        <th>Operations</th>
      </tr>
    </thead>
    
		<c:forEach items="${employees}" var="employee">
			<tr class="info">
			<td>${employee.name}</td>
			<td>${employee.joiningDate}</td>
			<td>${employee.salary}</td>
			<td><a href="<c:url value='/edit-${employee.ssn}-employee' />">${employee.ssn}</a></td>
			<td><a href="<c:url value='/delete-${employee.ssn}-employee' />">delete</a></td>
			</tr>
		</c:forEach>
		
	</table>
	<br/>
	<a href="<c:url value='/new' />">Add New Employee</a>
	<br/>
	<a href="<c:url value='/' />">Home</a>
</div>
</body>
</html>
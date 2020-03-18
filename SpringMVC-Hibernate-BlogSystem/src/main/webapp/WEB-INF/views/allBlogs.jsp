<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>All blogs Page</title>
</head>
<body>
<div class="container">

	<h2>List of blogs under user: ${current_username}</h2>	
	<table class="table">
		
	<thead>
      <tr>
        <th>id</th>
        <th>title</th>
        <th>content</th>
        <th>section</th>
        <th>Operations</th>
      </tr>
    </thead>
    
		<c:forEach items="${allBlogs}" var="blog">
			<tr class="info">
			<td>${blog.id}</td>
			<td>${blog.title}</td>
			<td>${blog.content}</td>
			<td>${blog.section}</td>
			<td><a href="<c:url value='/delete-${blog.id}-blog' />">Delete</a> 
			<a href="<c:url value='/edit-${blog.id}-blog' />">edit</a></td>
			</tr>
		</c:forEach>
		
	</table>
	<br/>
	<a href="<c:url value='/newBlog-${current_username}' />">Add New Blog</a>
	<br/>
	<a href="<c:url value='/' />">Home</a>
</div>
</body>

</html>
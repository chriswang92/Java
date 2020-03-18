<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Employee Registration Form</title>

<style>

	.error {
		color: #ff0000;
	}
</style>

</head>

<body>

	<h2>new blog Form</h2>
 	${curr_userid }
	<form:form method="POST" modelAttribute="blog">
		<form:input type="hidden" path="id" id="id"/>
		<table>
			<tr>
				<td><label for="title">title: </label> </td>
				<td><form:input path="title" id="title"/></td>
				<td><form:errors path="title" cssClass="error"/></td>
		    </tr>
	    
			<tr>
				<td><label for="content">content: </label> </td>
				<td><form:input path="content" id="content"/></td>
				<td><form:errors path="content" cssClass="error"/></td>
		    </tr>
	
			<tr>
				<td><label for="section">section: </label> </td>
				<td><form:input path="section" id="section"/></td>
				<td><form:errors path="section" cssClass="error"/></td>
		    </tr>
			<tr>
				<td colspan="3">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update"/>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Add"/>
						</c:otherwise>
					</c:choose>
				</td>
			</tr>
		</table>
	</form:form>
	<br/>
	<br/>
	Go back to <a href="<c:url value='/allBlogs' />">List of All Blogs for all users</a>
</body>
</html>
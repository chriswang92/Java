<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>  
<%@page import="java.util.List"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>index</title>
</head>
<body>
	<center>

<%=request.getAttribute("infoString") %>
</center>
<center>
</p>
	<p><a href="Login.jsp">login</a></p></br>
	<p><a href="Register.jsp">注册</a></p>
</center>
</body>
</html>
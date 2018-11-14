<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Iterator"%>  
<%@page import="java.util.List"%> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Welcome</title>
</head>
<body>
<center>
	  
      
       <%  
request.setCharacterEncoding("GBK");  
%>  
<%  
List<String> info=(List<String>)request.getAttribute("info");  
if(info!=null){  
    Iterator<String> iter=info.iterator();  
    while(iter.hasNext()){  
%>  
<h4><%=iter.next()%></h4>  
<%   
}  
}  
%>  
<%=request.getAttribute("infoString") %>

      <form action="LoginOutServlet" method="get">
      <input type=submit value="Log Out">
      </form>
		</br>
      <form action="changePassword.jsp" method="get">
      <input type=submit value="Change Password">
      </form>
      </br>

		<form action="SetBooksInfoServlet" method="get">
      <input type=submit value="see all books">
      </form>
      </br>
      <form action="book.jsp" method="get">
      <input type=submit value="buy books">
      </form>
      </br>
      <%  
List<String> booksInfo=(List<String>)request.getAttribute("booksInfo");  
if(booksInfo!=null){  
    Iterator<String> iter=booksInfo.iterator();  
    while(iter.hasNext()){  
%>  
<h4><%=iter.next()%></h4>  
<%   
}  
}  
%> 
</center>
</body>
</html>
<%@page import="java.util.Iterator"%>  
<%@page import="java.util.List"%>  
<%@ page language="java" contentType="text/html; charset=GBK"  
    pageEncoding="GBK"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">  
<html>  
<head>  
<meta http-equiv="Content-Type" content="text/html; charset=GBK">  
<center><title>MVC��¼ʵ��</title></center>  
</head>  
<body>  
<center><h2>�û���¼����</h2></center>  
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
</center>  
<center>  
<form action="LoginServlet" method="post" onSubmit="return validate(this)">  
�û���:<input type="text" name="name"><br>  
 �� �룺<input type="password" name="password"><br>  
 <input type="submit" value="��¼">  
 <input type="reset" value="����">  
</form>
</center>  
</body>  
</html>  
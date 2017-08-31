<%@page import="java.util.List"%>
<%@page import="service.BookServiceImpl"%>
<%@page import="bean.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书列表的页面</title>
</head>
<body>
<%
    //图书的实体类创建一个对象
    Book book=new Book();
    //图书的业务逻辑层层
    BookServiceImpl service=new BookServiceImpl();
    List<Book> list=service.getAll();
%>
<div style="text-align:left;font-size:36px;">
    <a href="welcome.jsp">Home</a>
</div>
<div style="text-align:right;font-size:36px;">
    <a href="docart.jsp">我的购物车</a>
</div>
<table align="center" width="100%" border="1px">
    <tr>
        <th>id</th>
        <th>Name</th>
        <th>Price</th>
        <th>Author</th>
        <th>Description</th>
    </tr>
    <%
        for(Book b:list){    
    %>
    <tr align="center">
        <td><%=String.valueOf(b.getId()) %></td>
        <td><a href="dobook.jsp?id=<%=b.getId()%>"><%=b.getName() %></a></td>
        <td><%=b.getPrice() %></td>
        <td><%=b.getAuthor() %></td>
        <td><%=b.getDescription() %></td>
    </tr>
    <%} %>
</table>

</body>
</html>
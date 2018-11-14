<%@page import="bean.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>图书详细信息的页面</title>
</head>
<body>
<%
    Book book=(Book)session.getAttribute("book");
%>
<div style="text-align:right;font-size:36px;">

    <a href="docart.jsp">我的购物车</a>
</div>
<table align="center" cellpadding="20" cellspacing="20">
    <tr>
        <td>id</td>
        <td>Name</td>
        <td>Price</td>
        <td>Author</td>
        <td>Description</td>
    </tr>
    <tr>
        <td><%=String.valueOf(book.getId()) %></td>
        <td><%=book.getName() %></td>
        <td><%=book.getPrice() %></td>
        <td><%=book.getAuthor() %></td>
        <td><%=book.getDescription() %>"></td>
    </tr>
    <tr>
        <td colspan="2"></td>
       //<td><a href="cart.jsp">添加到购物车</a></td>
        <td><a href="book.jsp">图书列表</a></td>
        
        <td colspan="2"></td>
    </tr>
</table>
</body>
</html>
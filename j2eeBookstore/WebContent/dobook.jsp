<%@page import="service.BookServiceImpl"%>
<%@page import="service.BookService"%>
<%@page import="dao.proxy.BookDAOProxy"%>
<%@page import="bean.Book"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>处理图书详细信息的页面</title>
</head>
<body>
<b>正在处理</b>
<%
    Book book=new Book();
    String sid = request.getParameter("id");
    Integer id = Integer.parseInt(sid);
    System.out.println(id);
    BookServiceImpl service=new BookServiceImpl();
    BookDAOProxy proxy = new BookDAOProxy();
    Book books=proxy.findBookByID(id);
    System.out.println("--------books==null?: ");
    System.out.println(books==null);
    session.setAttribute("book", books);
    
    response.sendRedirect("detail.jsp");
    System.out.println(((Book)session.getAttribute("book")).getName());
%>
</body>
</html>
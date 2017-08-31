<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="bean.User"%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%ArrayList<User> list = (ArrayList<User>)(request.getAttribute("list")); %>
<html>
  <head>
    <style type = "text/css">
        td{
        
            width:60px;
        }
    </style>
    <script type="text/javascript">
        
        function chickAll(){
        // 全选方法
            var chickobj = document.getElementsByName("num");
            for(var i = 0 ; i<chickobj.length ; i++){
            
                chickobj[i].checked = "checked";
            }
            
        }
        function Nochick(){
        // 反选方法   
            var chickobj = document.getElementsByName("num");
            for(var i = 0 ; i<chickobj.length ; i++){
            
                chickobj[i].checked = !chickobj[i].checked ;
            }
            
        }
    </script>
  </head>
  
  <body>
    <div id = "main">
    <form name="form1" action ="servlet/ServletDeleteUserList" method = "post">
        <table border ="1" align = "center" style="border-collapse:collapse;">
            <tr align="center">
                <td colspan="7">
                    用户信息列表
                </td>
            </tr>
            <tr align="center">
                <td></td>
                <td>用户名</td>
                <td>密码</td>
                <td>省份</td>
                <td>城市</td>
                <td colspan="2">操作</td>
            </tr>
            
            <%for(int i = 0 ; i<list.size();i++) {
            
                User user = list.get(i);%>
                <tr align="center" >
                <td><input type = "checkbox"  value ='<%=user.getUserId() %>' name="num"/></td>
                <td><a href="servlet/ServletViewUser?userId=<%=user.getUserId() %>"><%=user.getUserName()%></a></td>
                <td><%=user.getUserPassword() %></td>
                <td><%=user.getUserProvince() %></td>
                <td><%=user.getUserCity() %></td>
                <td><a href="servlet/ServletUserDelete?userId=<%=user.getUserId() %>">删除</a></td>
                <td><a href="servlet/ServletToUserUpdate?userId=<%=user.getUserId() %>">修改</a></td>
            </tr>
            <% 
            }
            %>
        </table>
    <table align = "center" > 
         <tr>
        <td><input type="button" value ="全选" name="checkall" id = "checkall" onclick="chickAll()"/></td>
        <td><input type ="button" value ="反选" name="nocheck" id= "nocheck" onclick="Nochick()"/></td>
        <td><input type ="submit" value ="批量删除"  /></td>
        </tr>
    </table>
    </form>
    </div>
  </body>
</html>
package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.proxy.UserDAOProxy;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;        
    /** 
     * @see HttpServlet#HttpServlet() 
     */  
    public LoginServlet() {  
        super();  
        // TODO Auto-generated constructor stub  
    }  
  
    /** 
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) 
     */  
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
    String name=request.getParameter("name");  
    String password=request.getParameter("password");  
    //List<String> info=new ArrayList<String>();
    String infoString = null;
    if(name==null||"".equals(name)){ //用户名输入格式问题  
        //info.add("用户名不能为空"); 
        infoString = "用户名不能为空";
        //request.setAttribute("infoString", infoString);
       // System.out.println("用户名不能为空");  
    } else if(password==null||"".equals(password)){//密码输入格式问题  
        infoString = "密码不能为空";  
        //request.setAttribute("infoString", infoString);
        //System.out.println("密码不能为空");  
    }
    if(infoString!=null){
    	request.setAttribute("infoString", infoString);
    	System.out.println(infoString); 
    	request.getRequestDispatcher("Login.jsp").forward(request,response);
    }
    if(name == "admin" && password=="admin"){
		request.getRequestDispatcher("admin.jsp").forward(request, response);
	}else{
    if(infoString == null){  
        User user=new User();  
        user.setName(name);  
        user.setPassword(password);  
        UserDAOProxy userDAOProxy=new UserDAOProxy();  
        try {  
              
            if(userDAOProxy.findLogin(user)){  
            	//登录成功，将用户数据放入到Session中 
            	request.getSession().setAttribute("user", user);
                //info.add("用户登录成功，欢迎"+user.getName()+"光临！");
                infoString = "用户登录成功，欢迎 ["+user.getName()+"] 光临！";
            	request.setAttribute("infoString", infoString);
            	
            		request.getRequestDispatcher("welcome.jsp").forward(request, response);
            	
            		//response.sendRedirect("welcome.jsp");
            	//return;
            }else {  
                //info.add("用户登录失败，错误的用户名和密码"); 
            	infoString = "用户登录失败，错误的用户名和密码";
            	request.setAttribute("infoString", infoString);//保存错误信息  
                request.getRequestDispatcher("Login.jsp").forward(request,response);//跳转  
                  
            }                         
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }
    }  
   
    }  
  
    /** 
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response) 
     */  
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {  
      doGet(request, response);  
    }  
  

}

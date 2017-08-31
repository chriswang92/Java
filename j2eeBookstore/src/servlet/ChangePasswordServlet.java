package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.User;
import dao.proxy.UserDAOProxy;

/**
 * Servlet implementation class ChangePasswordServlet
 */
@WebServlet("/ChangePasswordServlet")
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String newPassword = request.getParameter("newPassword");
		 //userName = request.getParameter("userName");
		User user = (User) request.getSession().getAttribute("user");
		String userName = user.getName();
		UserDAOProxy proxy = new UserDAOProxy();
		try {
			proxy.changePassword(userName, newPassword);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("password changed success");
		String infoString = "用户修改密码成功，["+userName+"] 请重新登录！";
    	request.setAttribute("infoString", infoString);
		request.getRequestDispatcher("Login.jsp").forward(request,
				 response);
		
	}

}

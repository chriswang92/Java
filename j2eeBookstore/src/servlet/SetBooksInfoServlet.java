package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bean.Book;
import dao.BookDAOImpl;
import dao.proxy.BookDAOProxy;

/**
 * Servlet implementation class SetBooksInfoServlet
 */
@WebServlet("/SetBooksInfoServlet")
public class SetBooksInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SetBooksInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//doPost(request, response);
		List<String> booksInfo = new ArrayList<String>();
		BookDAOProxy bookProxy = new BookDAOProxy();
		List<Book> books = null;
		try {
			books = bookProxy.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Book book : books) {
			String bookInfo = book.toString();
			booksInfo.add(bookInfo);
		}
		request.setAttribute("booksInfo", booksInfo);
		request.getRequestDispatcher("welcome.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		List<String> booksInfo = new ArrayList<String>();
		BookDAOProxy bookProxy = new BookDAOProxy();
		List<Book> books = null;
		try {
			books = bookProxy.getAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (Book book : books) {
			String bookInfo = "Book[" + book.getId() + "]: " + book.getName() + ", written by " + book.getAuthor()
					+ ", price: " + book.getPrice() + ", desc: " + book.getDescription();
			booksInfo.add(bookInfo);
		}
		request.setAttribute("booksInfo", booksInfo);
		*/
		doGet(request, response); 
	}

}

package service;

import java.util.List;


import bean.Book;
import dao.BookDAO;
import dao.BookDAOImpl;
import dao.proxy.BookDAOProxy;
import dbc.DatabaseConnection;

public class BookServiceImpl implements BookService {
	BookDAOProxy bookProxy = new BookDAOProxy();
	
	@Override
	public List<Book> getAll() {
		
		try {
			return bookProxy.getAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Book getBook(String name) {
		if(name!=null){
			try {
				return bookProxy.findBook(name);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		return null;
	}

	@Override
	public Book getBookByID(Integer id) {
		if(id!=null){
			try {
				return bookProxy.findBookByID(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} 
		return null;
	}

}

package service;

import java.util.List;

import bean.Book;

public interface BookService {
	public List<Book> getAll();
	
	public Book getBook(String name);
	public Book getBookByID(Integer id);
}

package dao;

import java.util.List;

import bean.Book;
import bean.User;

public interface BookDAO {

public List<Book> getAll() throws Exception;  
public Book findBook(String name) throws Exception;  
public Book findBookByID(Integer id) throws Exception;  
}

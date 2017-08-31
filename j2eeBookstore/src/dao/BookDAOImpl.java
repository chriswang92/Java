package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import bean.Book;
import dbc.DatabaseConnection;

public class BookDAOImpl implements BookDAO {
	private Connection conn = null;// 定义数据库连接对象
	private PreparedStatement pstmt = null;// 定义数据库操作对象
	private ResultSet resultSet = null;

	public BookDAOImpl(Connection conn) { // 设置数据库连接
		this.conn = conn;
	}

	@Override
	public List<Book> getAll() throws Exception {
		List<Book> books = new ArrayList<Book>();
		try{
			String sql = "select id,name,author,price,description from book";
			pstmt = conn.prepareStatement(sql);
			resultSet = pstmt.executeQuery();
			while(resultSet.next()){
				Book b = new Book();
				b.setId(resultSet.getInt("id"));
	            b.setName(resultSet.getString("name"));
	            b.setAuthor(resultSet.getString("author"));
	            b.setPrice(resultSet.getDouble("price"));
	            b.setDescription(resultSet.getString("description"));
	            books.add(b);
			}
		
		return books;
		} catch (Exception e) {
			throw e;
		} finally {
			DatabaseConnection.close(conn, pstmt, resultSet);
		}
	}

	@Override
	public Book findBook(String name) throws Exception {
		Book book = null;
		try {

			String sql = "select * from book where name=?";
			pstmt = conn.prepareStatement(sql);// 实例化操作
			pstmt.setString(1, name);
			resultSet = pstmt.executeQuery();// 取得结果
			if (resultSet.next()) {
				Book b = new Book();
				b.setId(resultSet.getInt("id"));
				b.setName(resultSet.getString("name"));
				b.setAuthor(resultSet.getString("author"));
				b.setPrice(resultSet.getDouble("price"));
				b.setDescription(resultSet.getString("description"));
				return b;
			}
			return null;

		} catch (Exception e) {
			throw e;
		} finally {
			DatabaseConnection.close(conn, pstmt, resultSet);
		}
	}

	@Override
	public Book findBookByID(Integer id) throws Exception {
		try {
			String sql = "select * from book where id=?";
			pstmt = conn.prepareStatement(sql);// 实例化操作
			pstmt.setInt(1, id);
			resultSet = pstmt.executeQuery();// 取得结果
			if (resultSet.next()) {
				Book b = new Book();
				b.setId(resultSet.getInt("id"));
				b.setName(resultSet.getString("name"));
				b.setAuthor(resultSet.getString("author"));
				b.setPrice(resultSet.getDouble("price"));
				b.setDescription(resultSet.getString("description"));
				return b;
			}
			return null;

		} catch (Exception e) {
			throw e;
		} finally {
			DatabaseConnection.close(conn, pstmt, resultSet);
		}
	}

}

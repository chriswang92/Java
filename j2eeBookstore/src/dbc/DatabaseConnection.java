package dbc;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import java.sql.PreparedStatement;
public class DatabaseConnection {
	//定义数据库驱动程序
	private static final String DBDRIVER="com.mysql.jdbc.Driver";
	//数据库连接地址
	private static final String DBURL="jdbc:mysql://localhost:3306/example";//example表示数据库
	private static final String DBUSER="root";
	private static final String DBPASS="19920705";
    private Connection connection=null;
    public DatabaseConnection() throws Exception{
    	try{
    		//数据库操作可能出现异常
    		Class.forName(DBDRIVER);
    		connection=DriverManager.getConnection(DBURL,DBUSER,DBPASS);
    		
    	}catch(Exception exception ){
    		throw exception;
    	} finally {
		}	
    }
    public Connection getConnection(){
    	return connection;
    }
    
    public void close(){
    	if(connection!=null){
    		try {
    			connection.close();			
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    }
    
    public static void close(Connection conn, PreparedStatement ps, ResultSet rs){
    	if(conn!=null){
    		try {
    			conn.close();			
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	if(ps!=null){
    		try {
    			ps.close();			
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	if(rs!=null){
    		try {
    			rs.close();			
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}
    	
    }

}


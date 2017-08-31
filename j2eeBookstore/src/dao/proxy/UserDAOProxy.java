package dao.proxy;

import dao.IUserDAO;  
import dao.UserDAOImpI;  
import dbc.DatabaseConnection;  
import bean.User;  

public class UserDAOProxy implements IUserDAO{  
    private DatabaseConnection dbc=null;//定义数据库连接  
    private IUserDAO dao=null;//定义DAO接口  
    public UserDAOProxy(){  
        try {  
            dbc=new DatabaseConnection();//实例化数据库连接  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        dao=new UserDAOImpI(dbc.getConnection());  
          
    }  
    @Override  
    public boolean findLogin(User user) throws Exception {  
        boolean flag=false;  
        try {  
            flag=dao.findLogin(user);//调用真实主题  
        } catch (Exception e) {  
            throw e;  
        }finally{  
            dbc.close();  
        }  
        return flag;  
    }
	@Override
	public void insertUser(User user) throws Exception {
		try{
			dao.insertUser(user);
		}catch (Exception e) {  
            throw e;  
        }finally{  
            dbc.close();
        }  
		
	}
	@Override
	public void changePassword(String userName, String newPassword) throws Exception {
		try{
			dao.changePassword(userName, newPassword);
		}catch (Exception e) {  
            throw e;  
        }finally{  
            dbc.close();
        }  
	} 
  
}  

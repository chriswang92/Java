package bean;

public class User {  
    private int userid;//对应数据库中userid  
    private String name;//对应数据库中name  
    private String password;//对应数据库中password  
    public int getUserid() {  
        return userid;  
    }  
    public void setUserid(int userid) {  
        this.userid = userid;  
    }  
    public String getName() {  
        return name;  
    }  
    public void setName(String name) {  
        this.name = name;  
    }  
    public String getPassword() {  
        return password;  
    }  
    public void setPassword(String password) {  
        this.password = password;  
    }  
  
}  
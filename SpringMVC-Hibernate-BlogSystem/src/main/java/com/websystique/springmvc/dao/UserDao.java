package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Employee;
import com.websystique.springmvc.model.User;

public interface UserDao {

	User findUserById(int id);

	void saveUser(User user);
	
	void deleteUserByUsername(String username);
	
	List<User> findAllUsers();

	User findUserByUsername(String username);
	
	boolean checkLogin(String username, String password);
}

package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Employee;
import com.websystique.springmvc.model.User;

public interface UserService {

	User findUserById(int id);

	void saveUser(User user);
	
	void updateUser(User user);
	
	void deleteUserByUsername(String username);
	
	List<User> findAllUsers();

	User findUserByUsername(String username);
	
	boolean isUsernameUnique(Integer id, String username);
	
	boolean checkLogin(String username, String password);
}

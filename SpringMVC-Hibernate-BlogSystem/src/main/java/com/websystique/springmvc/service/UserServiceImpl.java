package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.EmployeeDao;
import com.websystique.springmvc.dao.UserDao;
import com.websystique.springmvc.model.Employee;
import com.websystique.springmvc.model.User;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao dao;
	
	public User findUserById(int id) {
		return dao.findUserById(id);
	}

	public void saveUser(User user) {
		dao.saveUser(user);
	}

	/*
	 * Since the method is running with Transaction, No need to call hibernate update explicitly.
	 * Just fetch the entity from db and update it with proper values within transaction.
	 * It will be updated in db once transaction ends. 
	 */
	public void updateUser(User user) {
		User entity = dao.findUserById(user.getId());
		if(entity!=null){
			entity.setUsername(user.getUsername());
			entity.setPassword(user.getPassword());
		}
	}

	public void deleteUserByUsername(String username) {
		dao.deleteUserByUsername(username);
	}
	
	public List<User> findAllUsers() {
		return dao.findAllUsers();
	}

	public User findUserByUsername(String username){
		return dao.findUserByUsername(username);
	}

	public boolean isUsernameUnique(Integer id, String username) {
		User user = dao.findUserById(id);
		return ( user == null || ((id != null) && (user.getId() == id)));
	}

	public boolean checkLogin(String username, String password) {
		
		return dao.checkLogin(username, password);
	}

}

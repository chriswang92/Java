package com.websystique.springmvc.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.LocalDate;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="USER")
public class User {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private int id;

	@Size(min=3, max=45)
	@Column(name = "USERNAME", nullable = false)
	public String username;

	@Size(min=8, max=20)
	@Column(name="PASSWORD",nullable=false)
	private String password;
	
	
	public User(){
		
	}
	
	public User(String username, String password){
		this.username = username;
		this.password=password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}


	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username+" ]";
	}
	
	
	

}

package com.websystique.springmvc.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;


@Entity
@Table(name="BLOG")
public class Blog {

	@Id
	@GeneratedValue
	@Column(name = "BLOG_ID")
	private int blog_id;

	@Size(min=3, max=50)
	@Column(name = "TITLE", nullable = false)
	public String title;

	@Size(min=8, max=500)
	@Column(name="CONTENT",nullable=false)
	private String content;
	
	@Size(min=3, max=30)
	@Column(name = "SECTION", nullable = false)
	public String section;

	@ManyToOne(optional = false)
	@JoinColumn(name="USER_ID")
	private User user;

	public Blog() {
	}

	public Blog(String title, String content, String section) {
		this.title = title;
		this.content = content;
		this.section = section;
	}
	
	public int getId() {
		return blog_id;
	}


	public void setId(int id) {
		this.blog_id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}


	public String getSection() {
		return section;
	}


	public void setSection(String section) {
		this.section = section;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + blog_id;
		return result;
	}


	@Override
	public String toString() {
		return "Blog [blog_id=" + blog_id + ", title=" + title + ", content="
				+ content + ", section=" + section + ", author=" + user +" ]";
	}
	
	
	

}

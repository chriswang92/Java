package com.websystique.springmvc.dao;

import java.util.List;

import com.websystique.springmvc.model.Blog;
import com.websystique.springmvc.model.User;

public interface BlogDao {
	
	Blog findBlogById(int id);

	void saveBlog(Blog blog);
	
	void deleteBlogById(int id);
	
	List<Blog> findAllBlogsUnderUsername(String username);
	
	
}

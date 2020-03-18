package com.websystique.springmvc.service;

import java.util.List;

import com.websystique.springmvc.model.Blog;

public interface BlogService {

	Blog findBlogById(int id);

	void saveBlog(Blog blog);
	
	void deleteBlogById(int id);
	
	List<Blog> findAllBlogsUnderUsername(String username);
	
	Blog updateBlog(Blog blog);
}

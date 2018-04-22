package com.websystique.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.websystique.springmvc.dao.BlogDao;
import com.websystique.springmvc.model.Blog;
import com.websystique.springmvc.model.Employee;

@Service("blogService")
@Transactional
public class BlogServiceImpl implements BlogService {

	@Autowired
	private BlogDao dao;
	
	public Blog findBlogById(int id) {
		return dao.findBlogById(id);
	}

	public void saveBlog(Blog blog) {
		dao.saveBlog(blog);
		
	}

	public void deleteBlogById(int id) {
		dao.deleteBlogById(id);
		
	}

	public List<Blog> findAllBlogsUnderUsername(String username) {
		
		return dao.findAllBlogsUnderUsername(username);
	}

	public Blog updateBlog(Blog blog) {
		Blog entity = dao.findBlogById(blog.getId());
		if(entity!=null){
			entity.setTitle(blog.getTitle());
			entity.setContent(blog.getContent());
			entity.setSection(blog.getSection());
			//if(blog.getUser() != null) entity.setUser(blog.getUser());
		}
		return entity;
	}

}

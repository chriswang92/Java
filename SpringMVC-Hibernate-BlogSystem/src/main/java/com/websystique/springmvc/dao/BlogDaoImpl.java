package com.websystique.springmvc.dao;

import java.util.LinkedList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.sun.swing.internal.plaf.basic.resources.basic;
import com.websystique.springmvc.model.Blog;
import com.websystique.springmvc.model.Employee;
import com.websystique.springmvc.model.User;

@Repository("blogDao")
public class BlogDaoImpl extends AbstractDao<Integer, Blog> implements BlogDao {

	public Blog findBlogById(int id) {
		return getByKey(id);
	}

	public void saveBlog(Blog blog) {
		persist(blog);
		
	}

	public void deleteBlogById(int blog_id) {
		Query query = getSession().createSQLQuery("delete from Blog where blog_id = :blog_id");
		query.setString("blog_id", String.valueOf(blog_id));
		query.executeUpdate();
	}

	@SuppressWarnings("unchecked")
	public List<Blog> findAllBlogsUnderUsername(String username) {
		Criteria criteria = createEntityCriteria();
		List<Blog> allBlogs =  (List<Blog>) criteria.list();
		List<Blog> allBlogsUnderUsername = new LinkedList<Blog>();
		for(Blog blog:allBlogs){
			if(blog.getUser().getUsername().equals(username)){
				allBlogsUnderUsername.add(blog);
			}
		}
		return allBlogsUnderUsername;
	}


}

package com.project.personal_blog.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.personal_blog.entities.Blog;
import com.project.personal_blog.repositories.BlogRepo;

@Service
public class BlogService {

	@Autowired private BlogRepo repo; 
	
	/// Find by Status True - Hibernate
	public List<Blog> findByStatusTrue () {
		return repo.findByStatusTrue();
	}
	
	/// Save Blog 
	public Blog save (Blog blog) {
		return repo.save(blog);
	}
	
	/// Find Blog by Id
	public Blog findById (int id) {
		Optional<Blog> optionalBlog =  repo.findById(id);
		
		if (optionalBlog.isPresent()) {
			return optionalBlog.get();	
		} else {
			return null;
		}
		
	}
	
}

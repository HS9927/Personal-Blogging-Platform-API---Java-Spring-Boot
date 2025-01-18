package com.project.personal_blog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.personal_blog.entities.Blog;
import com.project.personal_blog.repositories.PersonalBlogRepo;

@Service
public class PersonalBlogService {

	@Autowired private PersonalBlogRepo repo; 
	
	
	public List<Blog> findByStatusTrue () {
		return repo.findByStatusTrue();
	}
	
}

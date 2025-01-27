package com.project.personal_blog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.personal_blog.entities.BlogTag;
import com.project.personal_blog.repositories.BlogTagRepo;

import jakarta.transaction.Transactional;

@Service

public class BlogTagService {
	
	@Autowired private BlogTagRepo repo;

	/// Save Blog Tag
	public BlogTag save (BlogTag blogTag) {
		return repo.save(blogTag);
	}
	
	/// Find blog by blog id @ Hibernate
	public List<BlogTag> findByBlogId (int blogId) {
		return repo.findByBlogId(blogId);
	}

	/// - Delete by blog id @ Hibernate
	@Transactional
	public void deleteByBlogId (int blogTagId) {
		repo.deleteByBlogId(blogTagId);
	}
	
	
}

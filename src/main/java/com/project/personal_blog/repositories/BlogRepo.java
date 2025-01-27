package com.project.personal_blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.personal_blog.entities.Blog;
import com.project.personal_blog.projection.tag.TagProjection;

@Repository
public interface BlogRepo extends JpaRepository<Blog, Integer> {
	
	/// List All Article that Status True
	List<Blog> findByStatusTrue();
	
	


}

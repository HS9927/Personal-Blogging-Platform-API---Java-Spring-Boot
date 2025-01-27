package com.project.personal_blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.personal_blog.entities.BlogTag;

import jakarta.transaction.Transactional;

@Repository
public interface BlogTagRepo extends JpaRepository<BlogTag, Integer> {

	/// Find by blog id
	List<BlogTag> findByBlogId(int blogId);

	@Transactional
	void deleteByBlogId(int blogTagId);
	
}

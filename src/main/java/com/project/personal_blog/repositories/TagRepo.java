package com.project.personal_blog.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.personal_blog.entities.Tag;
import com.project.personal_blog.projection.tag.TagProjection;

@Repository
public interface TagRepo extends JpaRepository<Tag, Integer> {
	
	/// Get All Tag
	String queryGetIdAndNameAndSlugAndStatusTrue = "SELECT id, name, slug, status FROM tags WHERE status = true;";
	@Query(value = queryGetIdAndNameAndSlugAndStatusTrue, nativeQuery = true)
	List<TagProjection> getIdAndNameAngSlugAndStatusTrue();
	
	/// Get All Tag
	String queryGetIdAndNameAndSlug = "SELECT id, name, slug, status FROM tags ;";
	@Query(value = queryGetIdAndNameAndSlug, nativeQuery = true)
	List<TagProjection> getIdAndNameAngSlug();
	
	/// Find Tag
	Tag findBySlug(String slug);
	
}

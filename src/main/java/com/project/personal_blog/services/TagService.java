package com.project.personal_blog.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.personal_blog.entities.Tag;
import com.project.personal_blog.projection.tag.TagProjection;
import com.project.personal_blog.repositories.TagRepo;

@Service
public class TagService {
	
	@Autowired private TagRepo repo;

	/// Save Tag	
	public Tag save (Tag tag) {
		Tag savedTag = repo.save(tag);
		return savedTag;
	}
	
	/// Get Id, Name, Slug
	public List<TagProjection> getIdAndNameAngSlugAndStatusTrue () {
		return repo.getIdAndNameAngSlugAndStatusTrue();
	}
	
	/// Get Id, Name, Slug
	public List<TagProjection> getIdAndNameAngSlug () {
		return repo.getIdAndNameAngSlug();
	}
	
	/// Find by Slug
	public Tag findBySlug (String slug) {
		return repo.findBySlug(slug);
	}
	
}

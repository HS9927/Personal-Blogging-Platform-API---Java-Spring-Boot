package com.project.personal_blog.entities;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "blog_tags")
@Setter
@Getter
@ToString
public class BlogTag {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(name = "blog_id", nullable = false) 
	int blogId;
	
	@Column(name = "tag_id", nullable = false) 
	int tagId;
	
	@Column(name = "created_at")
	Timestamp createdAt;
	
	@Column(name = "updated_at", nullable = true)
	Timestamp updatedAt;
}
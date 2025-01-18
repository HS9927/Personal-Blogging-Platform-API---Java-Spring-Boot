package com.project.personal_blog.entities;

import java.sql.Date;
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
@Table(name = "blogs")
@Setter
@Getter
@ToString
public class Blog {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(nullable = true)
	private String name;
	
	@Column(nullable = true)
	private String content;
	
	@Column(name = "publish_date", nullable = true)
	private Date publishDate;
	
	@Column(nullable = true)
	private Boolean status;
	
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "updated_at", nullable = true)
	private Timestamp updatedAt;
	
	@Column(name = "deleted_at", nullable = true)
	private Timestamp deletedAt;
}

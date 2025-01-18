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
@Table(name = "tags")
@Setter
@Getter
@ToString
public class Tag {
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	int id;
	
	@Column(nullable = true, length = 100)
	String name;
	
	@Column(nullable = true, length = 100)
	String slug;
	
	@Column(nullable = true)
	Boolean status;
	
	@Column(name = "created_at")
	Timestamp createdAt;
	
	@Column(name = "updated_at", nullable = true)
	Timestamp updatedAt;
}

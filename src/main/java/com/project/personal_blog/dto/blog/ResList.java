package com.project.personal_blog.dto.blog;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ResList {
	private int id;
	private int no;
	private String name;
	private String publishDate;
	private String status;
}

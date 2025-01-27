package com.project.personal_blog.dto.blog;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class ReqUpdate {
	private int id;
	private String name;
	private String content;
	private String publishDate;
	private String tags;
}

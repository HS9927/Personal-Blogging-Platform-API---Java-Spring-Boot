package com.project.personal_blog.dto.blog;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ReqSave {
	private String name;
	private String content;
	private String publishDate;
	private String tags;
}

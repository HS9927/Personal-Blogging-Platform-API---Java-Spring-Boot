package com.project.personal_blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.personal_blog.utils.ViewUtil;

@Controller
@RequestMapping("/blog")
public class PersonalBlogController {

	@GetMapping({"", "/"})
	public String index () {
		return ViewUtil.personalBlogIndex;
	}
	
	@GetMapping("/form")
	public String form () {
		return ViewUtil.personalBlogForm;
	}
	
}

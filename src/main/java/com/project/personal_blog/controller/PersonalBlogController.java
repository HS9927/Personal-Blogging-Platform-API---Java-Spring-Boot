package com.project.personal_blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/blog")
public class PersonalBlogController {

	@GetMapping({"", "/"})
	public String index () {
		return "index";
	}
	
}

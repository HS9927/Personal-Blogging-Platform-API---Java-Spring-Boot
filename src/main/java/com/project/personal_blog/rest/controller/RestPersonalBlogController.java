package com.project.personal_blog.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.personal_blog.dto.personalBlog.ResList;
import com.project.personal_blog.entities.Blog;
import com.project.personal_blog.services.PersonalBlogService;

@RestController
@RequestMapping("/api-blog")
public class RestPersonalBlogController {
	
	@Autowired private PersonalBlogService service;
	
	@GetMapping("/list")
	public ResponseEntity<List<ResList>> list() {
		/// New Object of ResList
		List<ResList> resList = new ArrayList<>();
		/// Get Article from DB by Status True
		List<Blog> datas = service.findByStatusTrue();
		
		ResList tempResList = new ResList();
		int no = 0;
		for (Blog temp : datas) {
			no++;
			tempResList.setId(temp.getId());
			tempResList.setNo(no);
			tempResList.setName(temp.getName());
			tempResList.setPublishDate(temp.getPublishDate().toString());
			tempResList.setStatus((temp.getStatus()) ? "Active" : "Inactive");
			resList.add(tempResList);
		}
		return new ResponseEntity<>(resList, HttpStatus.OK);
		
	}
	
}

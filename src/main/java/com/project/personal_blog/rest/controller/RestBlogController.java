package com.project.personal_blog.rest.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.personal_blog.dto.blog.ReqSave;
import com.project.personal_blog.dto.blog.ReqUpdate;
import com.project.personal_blog.dto.blog.ResInfo;
import com.project.personal_blog.dto.blog.ResList;
import com.project.personal_blog.entities.Blog;
import com.project.personal_blog.entities.BlogTag;
import com.project.personal_blog.entities.Tag;
import com.project.personal_blog.projection.blog.BlogProjection;
import com.project.personal_blog.projection.tag.TagProjection;
import com.project.personal_blog.services.BlogService;
import com.project.personal_blog.services.BlogTagService;
import com.project.personal_blog.services.TagService;
import com.project.personal_blog.utils.DateFormatUtil;
import com.project.personal_blog.utils.ValidationUtil;

@RestController
@RequestMapping("/api-blog")
public class RestBlogController {
	
	@Autowired private BlogService service;
	@Autowired private TagService tagService;
	@Autowired private BlogTagService blogTagService;
	
	
	@GetMapping("/test")
	public void funTest () {
		
//		List<TagProjection> list = tagService.getIdAndNameAngSlug();
		
		System.out.println("\n --- --- ---");
//		for (TagProjection tag : list) {
//			System.out.println(tag.getName());
//		}
		
		String d = funMakeSlugUtil("Helo so");
		
		System.out.println(d);
		
//		Tag tag = tagService.findBySlug("jame_2");
		
		
		
//		if (tag == null) {
//			System.out.println("NULL");
//		} else {
//			System.out.println(tag.getName());
//		}
		
		
		
		
		List<BlogTag> blogTags = blogTagService.findByBlogId(18);
//		String tags = "";
		if (blogTags != null) {
			for (BlogTag blogTag : blogTags) {
				
				if (blogTags.get(blogTags.size() - 1).getId() == blogTag.getId()) {
//					String tempTag = tags.stream
					System.out.println();
				}
			}	
		}
		
		
	}
	
	/**
	 * Blog List
	 * @return
	 */
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

	
	/**
	 * Blog Save 
	 * @param reqSave
	 * @return
	 */
	@PostMapping("/save")
	public ResponseEntity<String> save (@RequestBody ReqSave reqSave) {
		try {
			
			/// - Validate Field
			/// Validate Name
			if (!ValidationUtil.validateString(reqSave.getName())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Blog Name can't be null");
			}
			
			/// Validate Content
			if (!ValidationUtil.validateString(reqSave.getContent())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Blog Content can't be null");
			}
			
			/// Validate Publish Date
			if (!ValidationUtil.validateDatePattern(reqSave.getPublishDate(), "yyyy-MM-dd")) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Publish Date is wrong format.");
			}
			
			/// Validate Tags
			if (!ValidationUtil.validateString(reqSave.getTags())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Blog Tags can't be null");
			}
			
			/// - Save Blog
			Blog saveBlog = new Blog();
			saveBlog.setName(reqSave.getName());
			saveBlog.setContent(reqSave.getContent());
			saveBlog.setPublishDate(LocalDate.parse(reqSave.getPublishDate()));
			saveBlog.setStatus(true);
			saveBlog.setCreatedAt(LocalDateTime.now());
			saveBlog = service.save(saveBlog);
			
			
			/// - Save Blog Tag			
			String [] arrTags = reqSave.getTags().split(",");
			
			BlogTag saveBlogTag = null;
			for (String tag : arrTags) {
				saveBlogTag = new BlogTag();
				Tag existTag = tagService.findBySlug(funMakeSlugUtil(tag));
				
				if (existTag == null) {
					/// Tag not exist
					/// - Save Tag
					/// Prepare Tag to save
					Tag saveTag = new Tag();
					saveTag.setName(tag.trim());
					saveTag.setSlug(funMakeSlugUtil(tag));
					saveTag.setStatus(true);
					saveTag.setCreatedAt(LocalDateTime.now());
					/// Save Tag
					saveTag = tagService.save(saveTag);
					
					/// - Save Blog Tag
					saveBlogTag.setBlogId(saveBlog.getId());
					saveBlogTag.setTagId(saveTag.getId());
					saveBlogTag.setCreatedAt(LocalDateTime.now());
					blogTagService.save(saveBlogTag);
					
				} else {
					/// Tag exist
					if (existTag.getStatus()) {
						/// Tag Active
						/// - Save Blog Tag
						saveBlogTag.setBlogId(saveBlog.getId());
						saveBlogTag.setTagId(existTag.getId());
						saveBlogTag.setCreatedAt(LocalDateTime.now());
						blogTagService.save(saveBlogTag);
					} else {
						/// Tag Inactive
						/// - Update Tag
						/// Update Tag to Active
						existTag.setStatus(true);
						existTag.setUpdatedAt(LocalDateTime.now());
						
						/// - Save Blog Tag
						saveBlogTag.setBlogId(saveBlog.getId());
						saveBlogTag.setTagId(existTag.getId());
						saveBlogTag.setCreatedAt(LocalDateTime.now());
						blogTagService.save(saveBlogTag);
					}
				}
				
				saveBlogTag = null;
			}

			return ResponseEntity.status(HttpStatus.OK).body("Blog has been saved successfully !");
			
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}
	
	/**
	 * Blog Info
	 * @param id
	 * @return
	 */
	@GetMapping("/info/{id}")
	public ResponseEntity<ResInfo> info (@PathVariable("id") String id) {
		try {
			/// Find blog by id
			Blog blog = service.findById(Integer.parseInt(id));
			/// Prevent is blog not exist
			if (blog == null) {
				return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
			}
			
			/// Prepare Tag Object
			ResInfo data = new ResInfo();
			data.setId(Integer.parseInt(id));
			data.setName(blog.getName());
			data.setContent(blog.getContent());
			data.setPublishDate(blog.getPublishDate().toString());
			data.setStatus((blog.getStatus()) ? "Active" : "Inactive");
			data.setCreatedAt(DateFormatUtil.formatLocalDateTimeToDDMMYYHHMMSS(blog.getCreatedAt()));
			data.setUpdatedAt((blog.getUpdatedAt() == null) ? "N/A" : blog.getUpdatedAt().toString());
			
			List<BlogTag> blogTags = blogTagService.findByBlogId(blog.getId());
			List<TagProjection> tags = tagService.getIdAndNameAngSlugAndStatusTrue();
			String strTags = "";
			if (blogTags != null) {
				for (BlogTag blogTag : blogTags) {
					if (tags != null) {
						for (TagProjection tag : tags) {
							if (tag.getId() == blogTag.getTagId()) {
								strTags += tag.getName();
							}
						}
					}
					
					if (blogTags.get(blogTags.size() - 1).getId() != blogTag.getId()) {
						strTags += ", ";
					}
				}	
			}
			data.setTags(strTags);
			
			return new ResponseEntity<>(data, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Blog Update
	 * @param id
	 * @param reqUpdate
	 * @return
	 */
	@PostMapping("/update/{id}")
	public ResponseEntity<String> update (@PathVariable("id") String id, @RequestBody ReqUpdate	reqUpdate) {
		try {
			
			/// - Validate Field
			/// Validate Name
			if (!ValidationUtil.validateString(reqUpdate.getName())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Blog Name can't be null");
			}
			
			/// Validate Content
			if (!ValidationUtil.validateString(reqUpdate.getContent())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Blog Content can't be null");
			}
			
			/// Validate Publish Date
			if (!ValidationUtil.validateString(reqUpdate.getPublishDate())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Publish Date can't be null");
			} else if (!ValidationUtil.validateDatePattern(reqUpdate.getPublishDate(), "yyyy-MM-dd")) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Publish Date is wrong format.");
			}
			
			/// Validate Tags
			if (!ValidationUtil.validateString(reqUpdate.getTags())) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Blog Tags can't be null");
			}
			
			/// - Update Tag
			Blog updateBlog = service.findById(reqUpdate.getId());
			updateBlog.setName(reqUpdate.getName());
			updateBlog.setContent(reqUpdate.getContent());
			updateBlog.setPublishDate(DateFormatUtil.formatStringLocalDateToLocalDate(reqUpdate.getPublishDate()));
			updateBlog.setUpdatedAt(LocalDateTime.now());
			
			
			/// - Remove Blog Tag by blog id
			blogTagService.deleteByBlogId(reqUpdate.getId());
			
			/// - Save Tag			
			String [] arrTag = reqUpdate.getTags().split(",");
			BlogTag saveBlogTag = null;
			
			for (String tag : arrTag) {
				saveBlogTag = new BlogTag();
				
				Tag existTag = tagService.findBySlug(funMakeSlugUtil(tag));
				
				
				if (existTag != null) {
					/// Exist Tag
					if (existTag.getStatus()) {
						/// Check status of Tag
						/// If status active
						/// - Save Blog Tag
						saveBlogTag.setTagId(existTag.getId());
						saveBlogTag.setCreatedAt(LocalDateTime.now());
						
						
					} else {
						/// If status inactive
						/// - Update Tag
						existTag.setStatus(true);
						existTag.setUpdatedAt(LocalDateTime.now());
						
						/// - Save Blog Tag
						saveBlogTag.setTagId(existTag.getId());
					}
				} else {
					/// - Save Tag
					Tag saveTag = new Tag();
					saveTag.setName(tag.trim());
					saveTag.setSlug(funMakeSlugUtil(tag));
					saveTag.setStatus(true);
					saveTag.setCreatedAt(LocalDateTime.now());
					tagService.save(saveTag);
					
					/// - Save Blog Tag
					saveBlogTag.setTagId(saveTag.getId());
					
				}
				
				saveBlogTag.setBlogId(reqUpdate.getId());
				saveBlogTag.setCreatedAt(LocalDateTime.now());
				blogTagService.save(saveBlogTag);
				
				saveBlogTag = null;
			}
			
			return ResponseEntity.status(HttpStatus.OK).body("Blog has been updated successfully !");
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	/**
	 * Blog Delete
	 * @param id
	 * @return
	 */
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete (@PathVariable("id") String id) {
		try {
			
			if (!ValidationUtil.validateInt(id)) {
				return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
			}
			
			/// - Delete Blog
			Blog blog = service.findById(Integer.parseInt(id));
			blog.setStatus(false);
			service.save(blog);
			
			return ResponseEntity.status(HttpStatus.OK).body("Blog has been deleted successfully !");
			
		} catch (Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	/**
	 * Function Make Slug Util
	 * @param data
	 * @return
	 */
	private String funMakeSlugUtil (String data) {
		return data.toLowerCase().trim().replaceAll(" ", "_");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}

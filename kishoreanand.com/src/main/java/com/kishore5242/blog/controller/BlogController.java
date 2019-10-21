package com.kishore5242.blog.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kishore5242.blog.entity.Blog;
import com.kishore5242.blog.entity.Post;
import com.kishore5242.blog.service.BlogService;
import com.kishore5242.blog.service.PostService;

@Controller
@RequestMapping("/bloguser")
public class BlogController {
	
	private static final Logger logger = LogManager.getLogger(BlogController.class);
	
	@Autowired
	BlogService blogService;
	
	@Autowired
	PostService postService;

	@GetMapping(path = {"/blogs", "/", ""})
	public @ResponseBody List<Blog> getStreams(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		
		List<Blog> blogs = new ArrayList<>();
		
//		Object sessionObj = request.getSession().getAttribute("blogs");
//		if(sessionObj == null) {
//			blogs = blogService.getAllBlogs();
//			request.getSession().setAttribute("blogs", blogs);
//			request.setAttribute("blogs", blogs);
//			
//		} else if (sessionObj instanceof List<?>){
//			blogs = (List<Blog>) sessionObj;
//		}
		
		blogs = blogService.getAllBlogs();
		request.getSession().setAttribute("blogs", blogs);
		request.setAttribute("blogs", blogs);

		return blogs;
	}
	
	@RequestMapping("/blog/{blog_id}")
	public String getPosts(
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable("blog_id") int blog_id) {
		

		Blog blog =  blogService.getBlog(blog_id);
		
		List<Post> posts = postService.getAllPostsByBlog(blog_id);

		request.setAttribute("blog", blog);
		request.setAttribute("posts", posts);
		
		if (posts.size() > 0) {
			request.setAttribute("displaypost", posts.get(0));
			logger.info("Display post is at:" + posts.get(0).getPost_html_path());
		} else {
			logger.info("There is no post to display");
		}
		return "blogview/blogview"; 
	}
	
	
	@RequestMapping("/blog/{blog_id}/{post_id}")
	public String getPosts(
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable("blog_id") int blog_id,
			@PathVariable("post_id") int post_id) {
		

		Blog blog =  blogService.getBlog(blog_id);
		
		List<Post> posts = postService.getAllPostsByBlog(blog_id);

		request.setAttribute("blog", blog);
		request.setAttribute("posts", posts);
		
		Post post =  postService.getPost(post_id);
		request.setAttribute("displaypost", post);
		logger.info("Displya post is at:" + post.getPost_html_path());
		
		return "blogview/blogview"; 
	}
	
}

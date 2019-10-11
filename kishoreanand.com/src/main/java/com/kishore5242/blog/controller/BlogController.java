package com.kishore5242.blog.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.kishore5242.bean.Flashcard;
import com.kishore5242.bean.Stream;
import com.kishore5242.blog.entity.Blog;
import com.kishore5242.blog.entity.Post;
import com.kishore5242.blog.service.BlogService;
import com.kishore5242.blog.service.PostService;
import com.kishore5242.service.StreamService;
import com.kishore5242.util.SecurityUtil;

@Controller
@RequestMapping("/bloguser")
public class BlogController {
	
	@Autowired
	BlogService blogService;
	
	@Autowired
	PostService postService;

	@RequestMapping("/posts")
	public String getPosts(HttpServletRequest request, HttpServletResponse response) {
		
		String blog_id_str = request.getParameter("blog_id");
		int blog_id = 0;
		
		if(blog_id_str != null && !blog_id_str.isEmpty()){
			blog_id = Integer.parseInt(blog_id_str);
		}
		
		Blog blog =  blogService.getBlog(blog_id);
		
		List<Post> posts = postService.getAllPostsByBlog(blog_id);

		request.setAttribute("blog", blog);
		request.setAttribute("posts", posts);
		
		return "blogview/blogview"; 
	}
	
}

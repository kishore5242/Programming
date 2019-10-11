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
import com.kishore5242.bean.Topic;
import com.kishore5242.bean.User;
import com.kishore5242.blog.entity.Blog;
import com.kishore5242.blog.entity.Post;
import com.kishore5242.blog.service.BlogService;
import com.kishore5242.blog.service.PostService;
import com.kishore5242.service.userService;
import com.kishore5242.util.SecurityUtil;

@Controller
@RequestMapping("/blogadmin")
public class BlogAdminController {
	
	@Autowired
	BlogService blogService;
	
	@Autowired
	PostService postService;
	
	@Autowired
	userService userService;
	
	List<Flashcard> allFlashcards = new ArrayList<>();
	
	@RequestMapping("/addBlog")
	public String addBlog(HttpServletRequest request, HttpServletResponse response) {
		
		return "blog/addBlog";
	}
	
	@RequestMapping(value = "/saveBlog", method = RequestMethod.POST)
	public void saveBlog(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		
		String loggedInUser = SecurityUtil.getLoggedInUserName(authentication);
		
		String redirectTo = "/";
		
		String blogName = request.getParameter("blogName");
		
		Blog blog = new Blog();
		blog.setBlog_name(blogName);
		blog.setUsername(loggedInUser);

		blogService.createBlog(blog);

		refreshBlogs(request, authentication);
		
		response.sendRedirect(request.getContextPath() + redirectTo);
	}
	
	@RequestMapping(value = "/editBlog")
	public String editBlog(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String blog_id_str = request.getParameter("blog_id");
		int blog_id = 555;
		try {
			blog_id = Integer.parseInt(blog_id_str);
			
		} catch (NumberFormatException e) {
			return "error/error";
		}
						
		Blog blog = blogService.getBlog(blog_id);

		request.setAttribute("blog", blog);
		
		return "blog/editBlog";
	}
	
	
	@RequestMapping(path = {"/blogs", "/", ""})
	public String getStreams(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

		//String loggedInUser = SecurityUtil.getLoggedInUserName(authentication);

		List<Blog> blogs = blogService.getAllBlogs();
		
		request.setAttribute("blogs", blogs);
		
		return "blog/blogs";
	}
	
	
	@RequestMapping(value = "/updateBlog", method = RequestMethod.POST)
	public void updateBlog(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		
		String blog_id_str = request.getParameter("blog_id");
		String blog_name = request.getParameter("blog_name");
		
		String redirectTo = "/blogadmin";

		int blog_id = 556;

		if(null != blog_id_str && !blog_id_str.isEmpty()) {
			blog_id = Integer.parseInt(blog_id_str);
		}

		Blog blog = new Blog();
		blog.setBlog_id(blog_id);
		blog.setBlog_name(blog_name);
		//blog.setUsername(username);
		
		blogService.updateBlog(blog);
		
		refreshBlogs(request, authentication);
		
		response.sendRedirect(request.getContextPath() + redirectTo);
	}
	
	@RequestMapping(value = "/deleteBlog", method = RequestMethod.POST)
	public void deleteBlog(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		
		String blog_id_str = request.getParameter("blog_id");
		String redirectTo = "/streams";
		
		Integer blog_id = 556;

		if(null != blog_id_str && !blog_id_str.isEmpty()) {
			blog_id = Integer.parseInt(blog_id_str);
		}
		
		blogService.deleteBlog(blog_id);
		
		refreshBlogs(request, authentication);
		
		response.sendRedirect(request.getContextPath() + redirectTo);
	}

	private void refreshBlogs(HttpServletRequest request, Authentication authentication){
		
		//String loggedInUser = SecurityUtil.getLoggedInUserName(authentication);
		
		List<Blog> blogs = blogService.getAllBlogs();
		
		request.getSession().setAttribute("blogs", blogs);
	}	
	
	
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
		
		return "blog/posts";
	}
	
	@RequestMapping("/addPost")
	public String addPost(HttpServletRequest request, HttpServletResponse response) {
		
		request.setAttribute("blog_id", request.getParameter("blog_id"));
		
		return "blog/addPost";
	}
	
	@RequestMapping(value = "/savePost", method = RequestMethod.POST)
	public void savePost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String postName = request.getParameter("postName");
		String author = request.getParameter("author");
		String blog_id_str = request.getParameter("blog_id");

		String redirectTo = "/blogadmin/posts?blog_id="+blog_id_str;
		
		int blog_id = Integer.parseInt(blog_id_str);
		
		User user = userService.findUserByUsername(SecurityUtil.getLoggedInUserName());

		Post post = new Post();
		post.setPost_name(postName);
		post.setPost_author(user.getDisplayName());
		post.setPost_html_path("some path");
		post.setUsername(SecurityUtil.getLoggedInUserName());
		
		postService.createPost(post, blog_id);

		response.sendRedirect(request.getContextPath() + redirectTo);
	}
	
	@RequestMapping(value = "/editPost")
	public String editPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

		String post_id_str = request.getParameter("post_id");
		int post_id = 555;
		try {
			post_id = Integer.parseInt(post_id_str);
			
		} catch (NumberFormatException e) {
			return "error/error";
		}
						
		Post post = postService.getPost(post_id);

		request.setAttribute("post", post);
		
		return "blog/editPost";
	}
	
	@RequestMapping(value = "/updatePost", method = RequestMethod.POST)
	public void updatePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String blog_id_str = request.getParameter("blog_id");
		
		String redirectTo = "/blogadmin/posts?blog_id="+blog_id_str;
		
		String post_id_str = request.getParameter("post_id");
		String post_name = request.getParameter("post_name");
		String post_author = request.getParameter("author");

		int blog_id = 0;
		int post_id =0;
		

		if(null != blog_id_str && !blog_id_str.isEmpty()) {
			blog_id = Integer.parseInt(blog_id_str);
		}
		if(null != post_id_str && !post_id_str.isEmpty()) {
			post_id = Integer.parseInt(post_id_str);
		}
		
		Post post = new Post();
		post.setPost_id(post_id);
		post.setPost_name(post_name);
		post.setPost_author(post_author);
		post.setUsername(SecurityUtil.getLoggedInUserName());
		post.setPost_html_path("updated");

		postService.updatePost(post, blog_id);

		response.sendRedirect(request.getContextPath() + redirectTo);
	}
	
	@RequestMapping(value = "/deletePost", method = RequestMethod.POST)
	public void deletePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String post_id_str = request.getParameter("post_id");
		Integer post_id = Integer.parseInt(post_id_str);
		
		String blog_id_str = request.getParameter("blog_id");
		String redirectTo = "/blogadmin/posts?blog_id="+blog_id_str;
		
		postService.deletePost(post_id);
		
		response.sendRedirect(request.getContextPath() + redirectTo);
	}
	
}

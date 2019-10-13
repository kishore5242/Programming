package com.kishore5242.blog.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.kishore5242.bean.User;
import com.kishore5242.blog.entity.Blog;
import com.kishore5242.blog.entity.Post;
import com.kishore5242.blog.service.BlogService;
import com.kishore5242.blog.service.PostService;
import com.kishore5242.service.userService;
import com.kishore5242.util.DateUtil;
import com.kishore5242.util.SecurityUtil;

@Controller
@RequestMapping("/blogadmin")
public class BlogAdminController {
	
	private static final Logger logger = LogManager.getLogger(BlogAdminController.class);
	
	@Autowired
	BlogService blogService;
	
	@Autowired
	PostService postService;
	
	@Autowired
	userService userService;
	
	//Save the uploaded file to this folder
	@Value("${files.upload.loc}")
    private String UPLOADED_FOLDER;
	
	@RequestMapping("/addBlog")
	public String addBlog(HttpServletRequest request, HttpServletResponse response) {
		
		return "blog/addBlog";
	}
	
	@RequestMapping(value = "/saveBlog", method = RequestMethod.POST)
	public void saveBlog(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		
		String loggedInUser = SecurityUtil.getLoggedInUserName(authentication);
		
		String redirectTo = "/blogadmin";
		
		String blogName = request.getParameter("blogName");
		
		Blog blog = new Blog();
		blog.setBlog_name(blogName);
		blog.setUsername(loggedInUser);

		blogService.createBlog(blog);

		refreshBlogs(request);
		
		response.sendRedirect(request.getContextPath() + redirectTo);
	}
	
	@RequestMapping(value = "/editBlog/{blog_id}")
	public String editBlog(
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable("blog_id") int blog_id) throws IOException {
						
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
		
		refreshBlogs(request);
		
		response.sendRedirect(request.getContextPath() + redirectTo);
	}
	
	@RequestMapping(value = "/deleteBlog", method = RequestMethod.POST)
	public void deleteBlog(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		
		String blog_id_str = request.getParameter("blog_id");
		String redirectTo = "/blogadmin";
		
		Integer blog_id = 556;

		if(null != blog_id_str && !blog_id_str.isEmpty()) {
			blog_id = Integer.parseInt(blog_id_str);
		}
		
		blogService.deleteBlog(blog_id);
		
		refreshBlogs(request);
		
		response.sendRedirect(request.getContextPath() + redirectTo);
	}

	private void refreshBlogs(HttpServletRequest request){
		
		//String loggedInUser = SecurityUtil.getLoggedInUserName(authentication);
		
		List<Blog> blogs = blogService.getAllBlogs();
		
		request.getSession().setAttribute("blogs", blogs);
	}	
	
	
	@RequestMapping("/posts/{blog_id}")
	public String getPosts(
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable("blog_id") int blog_id) {
		
		Blog blog =  blogService.getBlog(blog_id);
		
		List<Post> posts = postService.getAllPostsByBlog(blog_id);

		request.setAttribute("blog", blog);
		request.setAttribute("posts", posts);
		
		return "blog/posts";
	}
	
	@RequestMapping("/addPost/{blog_id}")
	public String addPost(
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable("blog_id") int blog_id) {
		
		request.setAttribute("blog_id", blog_id); 
		
		refreshBlogs(request);
		
		return "blog/addPost";
	}
	
	@RequestMapping(value = "/savePost", method = RequestMethod.POST)
	public void savePost(HttpServletRequest request, HttpServletResponse response, @RequestParam("file") MultipartFile file) throws IOException {

		String postName = request.getParameter("post_name");
		String blog_id_str = request.getParameter("blog_id");
		String position_str = request.getParameter("position");
		
		int blog_id = Integer.parseInt(blog_id_str);
		int position = Integer.parseInt(position_str);
		
		User user = userService.findUserByUsername(SecurityUtil.getLoggedInUserName());
		
		Post post = new Post();
		post.setPost_name(postName);
		post.setPost_author(user.getDisplayName());
		post.setUsername(SecurityUtil.getLoggedInUserName());
		post.setPosition(position);
		post.setPost_modified(DateUtil.getCurrentDate("yyyy/MM/dd HH:mm"));
		
		postService.uploadPost(blog_id, post, file);
		
		refreshBlogs(request);
		
		response.sendRedirect(request.getContextPath() + "/blogadmin/posts/"+blog_id_str);
		
	}
	
	@RequestMapping(value = "/editPost/{post_id}")
	public String editPost(
			HttpServletRequest request, 
			HttpServletResponse response,
			@PathVariable("post_id") int post_id) throws IOException {
			
		Post post = postService.getPost(post_id);

		request.setAttribute("post", post);
		
		refreshBlogs(request);
		
		return "blog/editPost";
	}
	
	@RequestMapping(value = "/updatePost", method = RequestMethod.POST)
	public void updatePost(HttpServletRequest request, HttpServletResponse response, @RequestParam("htmlfile") MultipartFile htmlfile) throws IOException {
		
		String post_id_str = request.getParameter("post_id");
		String post_name = request.getParameter("post_name");
		String blog_id_str = request.getParameter("blog_id");
		String position_str = request.getParameter("position");

//		int blog_id = 0;
		int post_id =0;
		int position =500;
		

//		if(null != blog_id_str && !blog_id_str.isEmpty()) {
//			blog_id = Integer.parseInt(blog_id_str);
//		}
		if(null != post_id_str && !post_id_str.isEmpty()) {
			post_id = Integer.parseInt(post_id_str);
		}
		if(null != position_str && !position_str.isEmpty()) {
			position = Integer.parseInt(position_str);
		}
		
		User user = userService.findUserByUsername(SecurityUtil.getLoggedInUserName());
		
		Post post = new Post();
		post.setPost_id(post_id);
		post.setPost_name(post_name);
		post.setPost_author(user.getDisplayName());
		post.setUsername(SecurityUtil.getLoggedInUserName());
		post.setPosition(position);
		post.setPost_modified(DateUtil.getCurrentDate("yyyy/MM/dd HH:mm"));
		
		postService.updatePost(post, htmlfile);
		
		refreshBlogs(request);

		response.sendRedirect(request.getContextPath() + "/blogadmin/posts/"+blog_id_str);
	}
	
	@RequestMapping(value = "/deletePost", method = RequestMethod.POST)
	public void deletePost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String post_id_str = request.getParameter("post_id");
		Integer post_id = Integer.parseInt(post_id_str);
		String blog_id_str = request.getParameter("blog_id");
		Integer blog_id = Integer.parseInt(blog_id_str);
		
		postService.deletePost(blog_id, post_id);
		
		refreshBlogs(request);
		
		response.sendRedirect(request.getContextPath() + "/blogadmin/posts/"+blog_id_str);
	}
	
}

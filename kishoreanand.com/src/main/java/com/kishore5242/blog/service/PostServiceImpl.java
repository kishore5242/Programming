package com.kishore5242.blog.service;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.kishore5242.blog.dao.PostDao;
import com.kishore5242.blog.entity.Post;
import com.kishore5242.dao.UserDao;
import com.kishore5242.util.FileUtil;

@Service
@Transactional
public class PostServiceImpl implements PostService {
	
	private static final Logger logger = LogManager.getLogger(PostService.class);
	
	@Autowired
	PostDao postDao;
	
	@Autowired
	UserDao userDao;
	
	@Value("${files.upload.loc}")
    private String UPLOADED_FOLDER;

	@Override
	public List<Post> getAllPosts() {
		List<Post> allPosts = postDao.getAllPosts();
		//Collections.sort(allPosts);
		return allPosts;
	}
	
	@Override
	public List<Post> getAllPostsByBlog(Integer blog_id) {
		List<Post> allPosts = postDao.getAllPostsByBlog(blog_id);
		Collections.sort(allPosts);
		return allPosts;
	}

	@Override
	public void removePost(Integer post_id) {
		postDao.removePost(post_id);
	}

	@Override
	public Post getPost(Integer post_id) {
		return postDao.getPost(post_id);
	}
	

	@Override
	public void deletePost(Integer blog_id, Integer post_id) {
		
		String postDir = UPLOADED_FOLDER + blog_id+"/"+post_id+"/";
		
		FileUtil.deleteFolder(postDir);
		
		postDao.deletePost(post_id);
		
	}

	@Override
	public void uploadPost(Integer blog_id, Post post,  MultipartFile file) throws IOException {
				
		// create post // get post id
		Integer post_id = postDao.addPost(post, blog_id);
		
		// upload zip file
    	logger.info("File upload request for blog_id: "+blog_id);
		
    	String filePath = blog_id+"/"+post_id+"/"+file.getOriginalFilename();
		String fullRootPath = UPLOADED_FOLDER + filePath;
		String postDir = UPLOADED_FOLDER + blog_id+"/"+post_id+"/";
		
		FileUtil.clearFolder(postDir);
        FileUtil.writeMultipartFile(file, fullRootPath);
		
        // update post with zip file name
		//post.setPost_zip_path(filePath);
        
        
		// extract zip file
		//FileUtil.unzip(fullRootPath, postDir);
		// get html file name
		//String html_file = FileUtil.getHtmlFile(postDir);
		// update post with html file name
		//String reqUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();
		//FileUtil.modifyHtmlFile(UPLOADED_FOLDER +blog_id+"/"+post_id+"/"+html_file, reqUrl+"/uploaded/"+blog_id+"/"+post_id+"/");
		
		//post.setPost_html_path(blog_id+"/"+post_id+"/"+html_file);
		post.setPost_html_path(blog_id+"/"+post_id+"/"+file.getOriginalFilename());
		
		// update
		post.setPost_id(post_id);
		postDao.updatePost(post, blog_id);
		
	}
	
	@Override
	public void updatePost(Post post,  MultipartFile file) throws IOException {
		
		Post p = getPost(post.getPost_id());
		p.setPost_id(post.getPost_id());
		p.setPost_name(post.getPost_name());
		p.setPost_author(post.getPost_author());
		p.setUsername(post.getUsername());
		p.setPosition(post.getPosition());
		p.setPost_modified(post.getPost_modified());
		
		Integer blog_id = p.getBlog().getBlog_id();
		
		if(!file.isEmpty()) {
			// upload file
	    	logger.info("File upload request for blog_id: "+blog_id);
			
	    	String filePath = blog_id+"/"+post.getPost_id()+"/"+file.getOriginalFilename();
			String fullRootPath = UPLOADED_FOLDER + filePath;
			String postDir = UPLOADED_FOLDER + blog_id+"/"+post.getPost_id()+"/";
			
			FileUtil.clearFolder(postDir);
	        FileUtil.writeMultipartFile(file, fullRootPath);

			p.setPost_html_path(blog_id+"/"+post.getPost_id()+"/"+file.getOriginalFilename());
		}

		// update
		postDao.updatePost(p, blog_id);
		
	}
	
}

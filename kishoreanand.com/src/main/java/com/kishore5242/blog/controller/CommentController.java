package com.kishore5242.blog.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kishore5242.blog.entity.Comment;
import com.kishore5242.blog.service.CommentService;
import com.kishore5242.blog.service.PostService;


@RestController
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@Autowired
	PostService postService;
	
	@PostMapping(value = "/comments")
	public @ResponseBody Comment postComment(@RequestBody Comment comment) {
		comment.setUpdated(System.currentTimeMillis());
		comment.setPost(postService.getPost(comment.getPostid()));
		return commentService.save(comment);
	}
	
	@GetMapping(value = "/comments")
	public @ResponseBody List<Comment> getAllComment() {
		
		List<Comment> comments = commentService.findAll();

		
		Collections.sort(comments, new Comparator<Comment>() {
			@Override
			public int compare(Comment o1, Comment o2) {
				return (int) (o1.getUpdated()-o2.getUpdated());
			}
		});
		
		for(int i=0; i<comments.size(); i++) {
			Comment parent = comments.get(i);
			int below = 1;
			for(int j=i+1; j<comments.size(); j++) {
				if(comments.get(j).getParent_id() == parent.getId()) {
					Comment child = comments.get(j);
					child.setIndent(parent.getIndent() + 1);
					// move child below parent - below times
					comments.remove(child);
					comments.add(i+below, child);
					below++;
				}
			}
		}
		
		return comments;
	}
	
	@GetMapping(value = "/comments/{id}")
	public @ResponseBody Comment getComment(@PathVariable Integer id) throws Exception {
		Comment comment = commentService.findById(id);
		return comment;
	}
	
	@PostMapping(value = "/comments/{id}")
	public @ResponseBody Comment updateComment(@RequestBody Comment comment, @PathVariable Integer id) throws Exception {
		comment.setId(id);
		comment.setUpdated(System.currentTimeMillis());
		return commentService.save(comment);
	}

}

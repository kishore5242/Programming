package com.kishore5242.blog.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "post")
public class Post {

	@Id
	@Column(name = "post_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer post_id;
	
	@Column(name = "post_name")
	private String post_name;
	
	@Column(name = "post_author")
	private String post_author;
	
	@Column(name = "post_html_path")
	private String post_html_path;

	@Column(name = "username")
	private String username;
	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "blog_id", nullable = false)
	private Blog blog;
	
	public Integer getPost_id() {
		return post_id;
	}

	public void setPost_id(Integer post_id) {
		this.post_id = post_id;
	}

	public String getPost_name() {
		return post_name;
	}

	public void setPost_name(String post_name) {
		this.post_name = post_name;
	}

	public String getPost_author() {
		return post_author;
	}

	public void setPost_author(String post_author) {
		this.post_author = post_author;
	}

	public String getPost_html_path() {
		return post_html_path;
	}

	public void setPost_html_path(String post_html_path) {
		this.post_html_path = post_html_path;
	}

	public Blog getBlog() {
		return blog;
	}

	public void setBlog(Blog blog) {
		this.blog = blog;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
		
}

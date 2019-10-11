package com.kishore5242.blog.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "blog")
public class Blog {
	
	@Id
	@Column(name = "blog_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer blog_id;
	
	@Column(name = "blog_name")
	private String blog_name;
	
	@Column(name = "username")
	private String username;
	
	@OneToMany(cascade = CascadeType.REMOVE,
			fetch = FetchType.LAZY,
			mappedBy = "blog")
	private List<Post> posts;

	
	public Integer getBlog_id() {
		return blog_id;
	}

	public void setBlog_id(Integer blog_id) {
		this.blog_id = blog_id;
	}

	public String getBlog_name() {
		return blog_name;
	}

	public void setBlog_name(String blog_name) {
		this.blog_name = blog_name;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}

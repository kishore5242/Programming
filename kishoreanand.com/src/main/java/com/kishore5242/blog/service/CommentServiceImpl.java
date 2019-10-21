package com.kishore5242.blog.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kishore5242.blog.dao.CommentDao;
import com.kishore5242.blog.entity.Comment;

@Service
@Transactional
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDao commentDao;
	
	@Override
	public Comment save(Comment comment) {
		return commentDao.save(comment);
	}

	@Override
	public List<Comment> findAll() {
		return commentDao.findAll();
	}

	@Override
	public Comment findById(Integer id) {
		return commentDao.findById(id);
	}

}

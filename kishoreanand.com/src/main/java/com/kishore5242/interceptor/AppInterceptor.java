package com.kishore5242.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.kishore5242.blog.entity.Blog;
import com.kishore5242.blog.service.BlogService;

@Component
public class AppInterceptor implements HandlerInterceptor {
	
	@Autowired
	BlogService blogService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		List<Blog> blogs = new ArrayList<>();
		
		Object sessionObj = request.getSession().getAttribute("blogs");
		
		if(sessionObj != null && sessionObj instanceof List<?>) {
			blogs = (List<Blog>) sessionObj;
		}
		
		if(sessionObj == null || blogs.isEmpty()) {
			blogs = blogService.getAllBlogs();
			request.getSession().setAttribute("blogs", blogs);
			request.setAttribute("blogs", blogs);
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {}

}
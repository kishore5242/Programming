package com.kishore5242.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kishore5242.bean.Flashcard;
import com.kishore5242.bean.Stream;
import com.kishore5242.service.StreamService;
import com.kishore5242.util.SecurityUtil;

@Controller
public class AppStreamController {
	
	@Autowired
	StreamService streamService;
	
	List<Flashcard> allFlashcards = new ArrayList<>();
	
	@RequestMapping("/addStream")
	public String addStream(HttpServletRequest request, HttpServletResponse response) {
		
		return "admin/addStream";
	}
	
	@RequestMapping(value = "/saveStream", method = RequestMethod.POST)
	public void saveStream(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		
		String loggedInUser = SecurityUtil.getLoggedInUserName(authentication);
		
		String redirectTo = "/streams";
		
		String streamName = request.getParameter("streamName");
		
		Stream stream = new Stream();
		stream.setStream_name(streamName);
		stream.setUsername(loggedInUser);

		streamService.createStream(stream);

		refreshStreams(request, authentication);
		
		response.sendRedirect(request.getContextPath() + redirectTo);
	}
	
	@RequestMapping(value = "/editStream")
	public String editStream(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		String stream_id_str = request.getParameter("stream_id");
		int stream_id = 555;
		try {
			stream_id = Integer.parseInt(stream_id_str);
			
		} catch (NumberFormatException e) {
			return "error/error";
		}
						
		Stream stream = streamService.getStream(stream_id);

		request.setAttribute("stream", stream);
		
		return "admin/editStream";
	}
	
	
	@RequestMapping("/streams")
	public String getStreams(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {

		String loggedInUser = SecurityUtil.getLoggedInUserName(authentication);

		List<Stream> streams = streamService.getAllStreamsByUser(loggedInUser);
		
		request.setAttribute("streams", streams);
		
		return "streams/streams";
	}
	
	@GetMapping("/streamList")
	public @ResponseBody List<Stream> getStreamList(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		
		List<Stream> streams = new ArrayList<>();
		
//		Object sessionObj = request.getSession().getAttribute("streams");
//		if(sessionObj == null) {
//			String loggedInUser = SecurityUtil.getLoggedInUserName(authentication);
//			streams = streamService.getAllStreamsByUser(loggedInUser);
//			
//			request.getSession().setAttribute("streams", streams);
//			request.setAttribute("streams", streams);
//		} else if (sessionObj instanceof List<?>){
//			streams = (List<Stream>) sessionObj;
//		}

		String loggedInUser = SecurityUtil.getLoggedInUserName(authentication);
		streams = streamService.getAllStreamsByUser(loggedInUser);
		
		request.getSession().setAttribute("streams", streams);
		request.setAttribute("streams", streams);
		

		return streams;
	}
	
	
	@RequestMapping(value = "/updateStream", method = RequestMethod.POST)
	public void updateStream(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		
		String stream_id_str = request.getParameter("stream_id");
		String stream_name = request.getParameter("stream_name");
		
		String redirectTo = "/streams";

		int stream_id = 556;

		if(null != stream_id_str && !stream_id_str.isEmpty()) {
			stream_id = Integer.parseInt(stream_id_str);
		}

		Stream stream = new Stream();
		stream.setStream_id(stream_id);
		stream.setStream_name(stream_name);
		
		streamService.updateStream(stream);
		
		refreshStreams(request, authentication);
		
		response.sendRedirect(request.getContextPath() + redirectTo);
	}
	
	@RequestMapping(value = "/deleteStream", method = RequestMethod.POST)
	public void deleteStream(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
		
		String stream_id_str = request.getParameter("stream_id");
		String redirectTo = "/streams";
		
		Integer stream_id = 556;

		if(null != stream_id_str && !stream_id_str.isEmpty()) {
			stream_id = Integer.parseInt(stream_id_str);
		}
		
		streamService.deleteStream(stream_id);
		
		refreshStreams(request, authentication);
		
		response.sendRedirect(request.getContextPath() + redirectTo);
	}

	private void refreshStreams(HttpServletRequest request, Authentication authentication){
		
		String loggedInUser = SecurityUtil.getLoggedInUserName(authentication);
		
		List<Stream> streams = streamService.getAllStreamsByUser(loggedInUser);
		
		request.getSession().setAttribute("streams", streams);
	}

}

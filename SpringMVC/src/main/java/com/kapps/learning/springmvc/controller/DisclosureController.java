package com.kapps.learning.springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kapps.learning.springmvc.model.Slide;

@Controller
@RequestMapping("/ftnt")
public class DisclosureController {
	
	@RequestMapping(value="{xinId}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody 
	Slide getSlideDetails(@PathVariable int xinId){
		
		Slide slide = new Slide();
		slide.setXinId(xinId);
		slide.setName("SlideOne.pptx");
		
		return slide;
	}

}

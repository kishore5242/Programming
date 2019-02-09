package com.kishore5242.photorg.driver;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.kishore5242.photorg.config.SpringConfig;
import com.kishore5242.photorg.service.FileService;

public class Driver {

	public static void main(String[] args) {
		
		ApplicationContext ctx = new AnnotationConfigApplicationContext(SpringConfig.class);
		FileService fileService = ctx.getBean(FileService.class);
		
		fileService.listAllImagesInDirectory("D:/project/files");
		
	}

}

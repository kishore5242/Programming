package com.kishore5242.photorg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import com.kishore5242.photorg.bean.RunDetails;
import com.kishore5242.photorg.service.FileService;

@Configuration
public class SpringConfig {

	@Bean
	public FileService fileService() {
		return new FileService();
	}

	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer pspc = new PropertySourcesPlaceholderConfigurer();
		Resource[] resources = new ClassPathResource[] { new ClassPathResource("resources/application.properties") };
		pspc.setLocations(resources);
		pspc.setIgnoreUnresolvablePlaceholders(true);
		return pspc;
	}
	
	@Bean
	public RunDetails runDetails() {
		return new RunDetails();
	}
}

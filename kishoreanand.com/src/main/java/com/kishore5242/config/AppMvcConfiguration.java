package com.kishore5242.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.kishore5242.interceptor.AppInterceptor;

@Configuration
public class AppMvcConfiguration extends WebMvcConfigurerAdapter {

	private static final String[] CLASSPATH_RESOURCE_LOCATIONS = {
			"classpath:/META-INF/resources/", "classpath:/resources/",
			"classpath:/static/", "classpath:/public/" };
	
	@Value("${files.upload.loc}")
	private String uploadedFiles;
	
	@Autowired
	AppInterceptor appInterceptor;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		if (!registry.hasMappingForPattern("/webjars/**")) {
			registry.addResourceHandler("/webjars/**").addResourceLocations(
					"classpath:/META-INF/resources/webjars/");
		}
		if (!registry.hasMappingForPattern("/**")) {
			registry.addResourceHandler("/**").addResourceLocations(
					CLASSPATH_RESOURCE_LOCATIONS);
		}
		if (uploadedFiles != null) {
			registry.addResourceHandler("/uploaded/**").addResourceLocations("file:" + uploadedFiles);
		}
	}

	   @Override
	   public void addInterceptors(InterceptorRegistry registry) {
	      registry.addInterceptor(appInterceptor);
	   }
	
}
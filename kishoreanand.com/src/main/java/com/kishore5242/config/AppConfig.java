package com.kishore5242.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class AppConfig {

	@Bean
    public MessageSource messageSource() {
    	ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasenames("mymessages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
	
}
package com.niit.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableWebMvc
@ComponentScan("com.niit")
public class webResolver {

	@Bean
	public InternalResourceViewResolver getViewResolver()
	{
		InternalResourceViewResolver viewResolver= new InternalResourceViewResolver();
		viewResolver.setPrefix("WEB-INF/");
		viewResolver.setSuffix(".html");
		return viewResolver;
	}
	@Bean(name="multipartResolver")
	public CommonsMultipartResolver getMultiPartResolver()
	{
		CommonsMultipartResolver multiPartResolver = new CommonsMultipartResolver();
		multiPartResolver.setMaxUploadSize(10000000);
		return multiPartResolver;
	}

}

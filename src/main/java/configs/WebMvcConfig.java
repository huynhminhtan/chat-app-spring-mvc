package main.java.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//import stdio.components.UploadHelper;

import javax.servlet.ServletContext;
import java.util.List;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="main.java.controllers")
public class WebMvcConfig implements WebMvcConfigurer {
	

	@Bean(name="multipartResolver")
	public CommonsMultipartResolver multipartResolver() {
	    CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
	    multipartResolver.setMaxUploadSize(10 * 1024 * 1024);
	    return multipartResolver;
	}
	
	@Autowired
	ServletContext servletContext;
	
}
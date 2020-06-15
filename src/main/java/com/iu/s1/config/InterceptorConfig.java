package com.iu.s1.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.iu.s1.interceptor.QnaInterceptor;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer{

	@Autowired
	private QnaInterceptor qnaInterceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		
		registry.addInterceptor(qnaInterceptor)
		.addPathPatterns("/qna/*")
		.excludePathPatterns("/qna/qnaList");
		
	}

	
	
}

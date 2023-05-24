package com.ssafy.enjoytrip.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.ssafy.enjoytrip.intercepter.JwtInterceptor;


@Configuration
@MapperScan({"com.ssafy.enjoytrip.**.mapper"})
public class WebMvcConfigurator implements WebMvcConfigurer{
	 
	@Autowired
	private JwtInterceptor jwtInterceptor;
	
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*");
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor).addPathPatterns("/board/*", "/user/info", "/user/editUser", "/user/delete");
	}
}

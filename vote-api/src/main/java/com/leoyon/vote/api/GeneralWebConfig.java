package com.leoyon.vote.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class GeneralWebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new AllowCrossDomainHeaderInterceptor()).addPathPatterns("/**");
		registry.addInterceptor(new TokenHandlerInterceptor())
			.addPathPatterns("/**")
			.excludePathPatterns("/login")
			.excludePathPatterns("/register")
			.excludePathPatterns("/verify-code/**")
			.excludePathPatterns("/doc/**")
			;
		super.addInterceptors(registry);
	}
	
}

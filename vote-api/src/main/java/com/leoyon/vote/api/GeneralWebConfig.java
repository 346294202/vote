package com.leoyon.vote.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
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
	
	@Autowired
	private ApplicationContext context;
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		
		Map<String, HandlerMethodArgumentResolver> map = context.getBeansOfType(HandlerMethodArgumentResolver.class);
		
		for(HandlerMethodArgumentResolver i:map.values()) {
			if(i.getClass().getPackage().getName().startsWith("com.leoyon.vote"))
				argumentResolvers.add(i);
		}
		
		super.addArgumentResolvers(argumentResolvers);
	}
}

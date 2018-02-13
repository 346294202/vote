package com.leoyon.vote;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.leoyon.vote.user.FindSysUserRequest;
import com.leoyon.vote.user.FindUserRequest;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {
	
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(new FindSysUserRequest.ArgumentResolver());
		argumentResolvers.add(new FindUserRequest.ArgumentResolver());
		super.addArgumentResolvers(argumentResolvers);
	}

}

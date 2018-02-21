package com.leoyon.vote.user;

import java.util.Date;


import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.leoyon.vote.util.Parses;

@Component
public class FindUserHouseRequestArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {			
		return parameter.getParameterType().equals(FindUserHouseRequest.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		return new FindUserHouseRequest(
				webRequest.getParameter("realName"),
				webRequest.getParameter("mobile"),
				Parses.parse(webRequest.getParameter("dateCreateStart"), Date.class, null),
				Parses.parse(webRequest.getParameter("dateCreateEnd"), Date.class, null),
				Parses.parse(webRequest.getParameter("ownerStatus"), Integer.class, null),
				Parses.parse(webRequest.getParameter("page"), Integer.class, 0),
				Parses.parse(webRequest.getParameter("psize"), Integer.class, 20)
				);
	}
	
}
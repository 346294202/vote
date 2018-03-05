package com.leoyon.vote.business;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.leoyon.vote.util.Parses;

@Component
public class FindBizRequestArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {			
		return parameter.getParameterType().equals(FindBizRequest.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		return new FindBizRequest(
				Parses.parse(webRequest.getParameter("type"), Integer.class, 1),
				Parses.parse(webRequest.getParameter("lng"), Double.class, null),
				Parses.parse(webRequest.getParameter("lat"), Double.class, null),
				Parses.parse(webRequest.getParameter("page"), Integer.class, 0),
				Parses.parse(webRequest.getParameter("psize"), Integer.class, 20)				
				);
	}
	
}
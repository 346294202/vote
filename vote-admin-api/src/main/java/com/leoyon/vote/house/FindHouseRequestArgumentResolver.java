package com.leoyon.vote.house;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.leoyon.vote.util.Parses;

@Component
public class FindHouseRequestArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {			
		return parameter.getParameterType().equals(FindHouseRequest.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		return new FindHouseRequest(
				Parses.parse(webRequest.getParameter("areaId"), Long.class, null),
				Parses.parse(webRequest.getParameter("building"), Integer.class, null),
				Parses.parse(webRequest.getParameter("unit"), Integer.class, null),
				Parses.parse(webRequest.getParameter("number"), Integer.class, null),
				Parses.parse(webRequest.getParameter("page"), Integer.class, 0),
				Parses.parse(webRequest.getParameter("psize"), Integer.class, 20),
				Parses.parse(webRequest.getParameter("houseType"), Integer.class, null),
				Parses.parse(webRequest.getParameter("houseStatus"), Integer.class, null),
				webRequest.getParameter("ownerName"),
				webRequest.getParameter("ownerMobile")
				);
	}
	
}

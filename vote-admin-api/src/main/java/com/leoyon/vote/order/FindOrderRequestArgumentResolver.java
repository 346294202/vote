package com.leoyon.vote.order;

import java.util.Date;

import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.leoyon.vote.util.Parses;

@Component
public class FindOrderRequestArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {			
		return parameter.getParameterType().equals(FindOrderRequest.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		
		return new FindOrderRequest(
				Parses.parse(webRequest.getParameter("type"), Integer.class, null),
				Parses.parse(webRequest.getParameter("createTimeStart"), Date.class, null),
				Parses.parse(webRequest.getParameter("createTimeEnd"), Date.class, null),
				Parses.parse(webRequest.getParameter("orderStatus"), Integer.class, null),
				webRequest.getParameter("userRealName"),
				webRequest.getParameter("userMobile"),
				Parses.parse(webRequest.getParameter("areaId"), Long.class, null),
				Parses.parse(webRequest.getParameter("houseBuilding"), Integer.class, null),
				Parses.parse(webRequest.getParameter("houseUnit"), Integer.class, null),
				Parses.parse(webRequest.getParameter("houseNumber"), Integer.class, null),				
				Parses.parse(webRequest.getParameter("page"), Integer.class, 0),
				Parses.parse(webRequest.getParameter("psize"), Integer.class, 20)
				);
	}
	
}
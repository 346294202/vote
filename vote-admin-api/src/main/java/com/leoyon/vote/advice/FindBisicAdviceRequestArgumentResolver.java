package com.leoyon.vote.advice;


import com.leoyon.vote.advertisement.FindeBizAdvertisementRequest;
import com.leoyon.vote.util.Parses;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class FindBisicAdviceRequestArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {			
		return parameter.getParameterType().equals(FindBaiscAdviceRequest.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		return new FindBaiscAdviceRequest(
				Parses.parse(webRequest.getParameter("areaId"), Integer.class, null),
				Parses.parse(webRequest.getParameter("building"), Integer.class, null),
				Parses.parse(webRequest.getParameter("unit"), Integer.class, null),
				Parses.parse(webRequest.getParameter("number"), Integer.class, null),
				webRequest.getParameter("startTime"),
				webRequest.getParameter("endTime"),
				webRequest.getParameter("p"),
				Parses.parse(webRequest.getParameter("page"), Integer.class, 0),
				Parses.parse(webRequest.getParameter("psize"), Integer.class, 20)
				);
	}
	
}
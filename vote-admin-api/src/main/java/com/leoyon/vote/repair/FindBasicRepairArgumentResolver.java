package com.leoyon.vote.repair;


import com.leoyon.vote.advice.FindBaiscAdviceRequest;
import com.leoyon.vote.util.Parses;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class FindBasicRepairArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {			
		return parameter.getParameterType().equals(FindBasicRepairRequest.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		return new FindBasicRepairRequest(
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
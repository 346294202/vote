package com.leoyon.vote.dynamics;


import com.leoyon.vote.dictionary.FindSysDictionaryRequest;
import com.leoyon.vote.util.Parses;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class FindReleaseRequestArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {			
		return parameter.getParameterType().equals(FindReleaseRequest.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		return new FindReleaseRequest(
				webRequest.getParameter("startTime"),
				webRequest.getParameter("endTime"),
				Parses.parse(webRequest.getParameter("pageNum"), Integer.class, 0),
				Parses.parse(webRequest.getParameter("pageSize"), Integer.class, 20)
				);
	}
	
}
package com.leoyon.vote.dictionary;


import com.leoyon.vote.util.Parses;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Component
public class FindSysDictionaryRequestArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {			
		return parameter.getParameterType().equals(FindSysDictionaryRequest.class);
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		System.out.println(webRequest.getParameter("pageNum"));
		System.out.println(webRequest.getParameter("pageSize"));
		return new FindSysDictionaryRequest(
				webRequest.getParameter("q"),
				Parses.parse(webRequest.getParameter("categoryName"), Integer.class,1),
				Parses.parse(webRequest.getParameter("pageNum"), Integer.class, 0),
				Parses.parse(webRequest.getParameter("pageSize"), Integer.class, 20)
				);
	}
	
}
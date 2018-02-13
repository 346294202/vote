package com.leoyon.vote.user;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.leoyon.vote.util.Parses;

public class FindUserRequest {
	
	public static class ArgumentResolver implements HandlerMethodArgumentResolver {

		@Override
		public boolean supportsParameter(MethodParameter parameter) {			
			return parameter.getParameterType().equals(FindUserRequest.class);
		}

		@Override
		public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
				NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
			
			return new FindUserRequest(
					webRequest.getParameter("q"),
					Parses.parse(webRequest.getParameter("page"), Integer.class, 0),
					Parses.parse(webRequest.getParameter("psize"), Integer.class, 20),
					Parses.parse(webRequest.getParameter("active"), Integer.class, null)
					);
		}
		
	}

	private String q;
	private Integer page;
	private Integer psize;
	private Integer active;
	
	public FindUserRequest(String q, Integer page, Integer psize, Integer active) {
		super();
		this.q = q;
		this.page = page;
		this.psize = psize;
		this.active = active;
	}

	public Integer getActive() {
		return active;
	}

	public void setActive(Integer active) {
		this.active = active;
	}

	public String getQ() {
		return q;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getPsize() {
		return psize;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setPsize(Integer psize) {
		this.psize = psize;
	}
	
	
}

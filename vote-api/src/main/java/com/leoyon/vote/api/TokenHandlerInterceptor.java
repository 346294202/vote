package com.leoyon.vote.api;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

public class TokenHandlerInterceptor extends HandlerInterceptorAdapter {	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String token = request.getHeader(Token.TOKEN_NAME);
		if(StringUtils.isBlank(token)) {
			throw new ResponseException(Error.TOKEN_EXCEPT);
		}
		
		request.setAttribute(Token.TOKEN_NAME, token);
		
		return super.preHandle(request, response, handler);
	}

	
}

package com.leoyon.vote;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

public class GeneralController {

	@Autowired
	protected HttpServletRequest request;

	public String getCookie(String name) {
		for(Cookie c:request.getCookies()) {
			if(c.getName().equals(name)) {
				return c.getValue();
			}
		}
		return null;
	}

}
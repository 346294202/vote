package com.leoyon.vote;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.leoyon.vote.Constants;
import com.leoyon.vote.api.Error;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.api.Token;
import com.leoyon.vote.user.SysUserService;
import com.leoyon.vote.user.SysUser;

public abstract class AuthenticationController {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private SysUserService userService;

	public AuthenticationController() {
		super();
	}

	public SysUser getLogin(boolean silence) throws ResponseException {
		Token token = new Token((String) request.getAttribute(Constants.TOKEN_NAME), 0);
		
		if(token.isExpired()) {
			if(silence)
				return null;
			throw new ResponseException(Error.TOKEN_EXCEPT);
		}
		
		Long id = Long.parseLong(token.getValue());
		
		SysUser user = userService.get(id);
		
		if(user == null) {
			if(silence)
				return null;
			throw new ResponseException(Error.TOKEN_EXCEPT);
		}
		
		return user;
	}

}
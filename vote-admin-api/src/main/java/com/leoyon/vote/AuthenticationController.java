package com.leoyon.vote;

import org.springframework.beans.factory.annotation.Autowired;

import com.leoyon.vote.api.Error;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.api.Token;
import com.leoyon.vote.user.SysUserService;
import com.leoyon.vote.user.SysUser;

public abstract class AuthenticationController extends GeneralController {

	@Autowired
	protected SysUserService userService;

	public AuthenticationController() {
		super();
	}

	public SysUser getLogin(boolean silence) throws ResponseException {
		Token token = (Token) request.getAttribute(Token.TOKEN_NAME);
		
		String value = token.getValue();
		
		if(value == null) {
			throw new ResponseException(Error.TOKEN_EXCEPT);
		}
		
		Long id = Long.parseLong(value);
		
		SysUser user = userService.get(id);
		
		if(user == null) {
			if(silence)
				return null;
			throw new ResponseException(Error.TOKEN_EXCEPT);
		}
		
		return user;
	}

}
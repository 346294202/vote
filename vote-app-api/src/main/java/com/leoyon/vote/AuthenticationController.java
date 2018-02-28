package com.leoyon.vote;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;

import com.leoyon.vote.api.Error;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.api.Token;
import com.leoyon.vote.user.User;
import com.leoyon.vote.user.UserService;

public abstract class AuthenticationController {

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	protected UserService userService;

	public AuthenticationController() {
		super();
	}

	public User getLogin(boolean silence) throws ResponseException {
		Token token = (Token) request.getAttribute(Token.TOKEN_NAME);
		
		String value = token.getValue();
		
		if(value == null) {
			throw new ResponseException(Error.TOKEN_EXCEPT);
		}
		
		Long id = Long.parseLong(value);
		
		User user = userService.get(id);
		
		if(user == null) {
			if(silence)
				return null;
			throw new ResponseException(Error.TOKEN_EXCEPT);
		}
		
		return user;
	}

}
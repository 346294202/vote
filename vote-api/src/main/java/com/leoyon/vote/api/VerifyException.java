package com.leoyon.vote.api;

public class VerifyException extends ResponseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3985608736400444080L;

	public VerifyException() {
		super(Error.VARIFY_FAILED);
	}

}

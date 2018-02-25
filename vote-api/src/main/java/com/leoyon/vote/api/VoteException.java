package com.leoyon.vote.api;

public class VoteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2393499955140103591L;

	public VoteException(String message, Throwable cause) {
		super(message, cause);
	}

	public VoteException(String message) {
		super(message);
	}

}

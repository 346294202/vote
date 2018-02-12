package com.leoyon.vote.api;

import org.apache.commons.lang3.RandomStringUtils;

public class Token {
	
	private String token;
	private Integer expir;
	public final static String TOKEN_NAME = "VOTE-ACCESS-TOKEN";
	
	public Token(String token, Integer expir) {
		super();
		this.token = token;
		this.expir = expir;
	}

	public String getToken() {
		return token;
	}
	
	public long getExpir() {
		return expir;
	}

	public static Token build(Object value, Integer expir) {
		//TODO 前期调试结束后对token进行编码
		String token = String.format("VT-%s-%d-%s", value, 1000*expir+System.currentTimeMillis(), RandomStringUtils.randomAlphabetic(16));
		return new Token(token, expir);
	}

	public boolean isExpired() {
		//TODO 前期调试结束后对token进行编码
		String[] parts = token.split("[-]");
		if(parts.length != 4) {
			return false;
		}
		Long expiredMS = Long.parseLong(parts[2]); 
		return expiredMS < System.currentTimeMillis();
	}

	public String getValue() {
		String[] parts = token.split("[-]");
		if(parts.length != 4) {
			return null;
		}
		return parts[1];
	}
	

}

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

	/**
	 * 
	 * @param value
	 * @param expir 过期时间，秒
	 * @return
	 */
	public static Token build(Object value, Integer expir) {
		//TODO 前期调试结束后对token进行编码
		Long currentMS = System.currentTimeMillis();
		Long expiredMS =  1000*expir+currentMS;
//		System.out.println((1000*expir)+"+"+currentMS+"="+expiredMS);
		String token = String.format("VT-%s-%d-%s", value, expiredMS, RandomStringUtils.randomAlphabetic(16));
		return new Token(token, expir);
	}

	public boolean isExpired() {
		//TODO 前期调试结束后对token进行编码
		String[] parts = token.split("[-]");
		if(parts.length != 4) {
			return false;
		}
		Long expiredMS = Long.parseLong(parts[2]);
		Long currentMS = System.currentTimeMillis();
//		System.out.println(expiredMS+"-"+currentMS+"="+(expiredMS-currentMS));
		return expiredMS < currentMS;
	}

	public String getValue() {
		String[] parts = token.split("[-]");
		if(parts.length != 4) {
			return null;
		}
		return parts[1];
	}
	

}

package com.leoyon.vote.user;

import javax.xml.bind.DatatypeConverter;

import org.apache.commons.codec.digest.DigestUtils;

import com.leoyon.vote.util.Wet;

public class VerifyCode {
	public static final String KEY_NAME = "vote-verify-code-key";
	private static final String SALT = "sdakjdhb76odbasd6foKJHBUYBHK;kjnlLkjnlkhbLKJxz7czxjcnboWA7DSD^&8769*^%*&^";
	
	private String key;
	private String code;
	
	public String getKey() {
		return key;
	}
	
	public String getCode() {
		return code;
	}
	
	@Override
	public String toString() {
		return "VerifyCode [key=" + key + ", code=" + code + "]";
	}
	
	private VerifyCode(String key, String code) {
		super();
		this.key = key;
		this.code = code;
	}

	public static VerifyCode encode(String code, int expirms) {
		String s = encrypt(code);
		if(expirms>0) {
			s += "-" + (expirms+System.currentTimeMillis());
		}
		s = Wet.encode(s);
		return new VerifyCode(s, code);
	}

	private static String encrypt(String code) {
		return DatatypeConverter
				.printHexBinary(DigestUtils.md5((SALT+code.toUpperCase()).getBytes()));
	}

	public static boolean verify(String key, String code) {
		String s = Wet.decode(key);
		String[] ss = s.split("[-]");
		if(ss.length > 1) {
			Long expir = Long.valueOf(ss[1]);
			if(expir < System.currentTimeMillis()) {
				return false;
			}
		}
		return encrypt(code).equals(ss[0]);
	}
	
}
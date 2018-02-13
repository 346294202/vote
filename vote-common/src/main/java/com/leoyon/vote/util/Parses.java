package com.leoyon.vote.util;

import org.apache.commons.lang3.StringUtils;

public class Parses {

	@SuppressWarnings("unchecked")
	public static <T> T parse(String s, Class<T> clazz, T defValue) {
		if(StringUtils.isBlank(s))
			return defValue;		
		if(clazz.equals(Integer.class))
			return (T) Integer.valueOf(s);
		if(clazz.equals(Long.class))
			return (T) Long.valueOf(s);
		if(clazz.equals(Double.class))
			return (T) Double.valueOf(s);
		if(clazz.equals(Boolean.class))
			return (T) Boolean.valueOf(s);
		return null;
	}

}

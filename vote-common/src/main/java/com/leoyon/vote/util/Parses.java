package com.leoyon.vote.util;

import java.util.List;
import java.util.Vector;

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

	public static <T> List<T> parseList(String s, Class<T> clazz, String seprator) throws Exception {
		String[] ss = s.split("["+seprator+"]");
		Vector<T> ret = new Vector<>();
		for(String i:ss) {
			T value = parse(i, clazz, null);
			if(value == null) {
				throw new Exception("错误的格式，"+s);
			}
			ret.add(value);
		}
		return ret;
	}

}

package com.leoyon.vote.util;

import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

/**
 * 各种字符串格式验证方法
 * @author wj
 *
 */
public class Checks {
	
	/**
	 * 
	 * @Title: checkEmail 
	 * @Description: 验证Email
	 * @param email
	 * @return 验证成功返回true，验证失败返回false
	 * @return: boolean
	 */
	public static boolean checkEmail(String email) {
		return Pattern.matches(Regexs.REGEX_EMAIL, email);
	}
	
	/**
	 * 
	 * @Title: checkIdCard 
	 * @Description: 验证身份证号码
	 * @param idCard
	 * @return
	 * @return: boolean
	 */
	public static boolean checkIdCard(String idCard) {
		return Pattern.matches(Regexs.REGEX_ID_CARD18,idCard); 
	} 
	
	/**
	 * 
	 * @Title: checkMobile 
	 * @Description: 验证手机号码（支持国际格式，+86135xxxx...（中国内地），+00852137xxxx...（中国香港））
	 * @param mobile
	 * @return 验证成功返回true，验证失败返回false
	 * @return: boolean
	 */
	public static boolean checkMobile(String mobile) {
		if(StringUtils.isBlank(mobile))
			return false;
		return Pattern.matches(Regexs.REGEX_MOBILE_SIMPLE, mobile);
	}
	
	/**
	 * 
	 * @Title: checkDigit 
	 * @Description: 验证整数（正整数和负整数）
	 * @param digit 一位或多位0-9之间的整数
	 * @return 验证成功返回true，验证失败返回false
	 * @return: boolean
	 */
	public static boolean checkDigit(String digit) { 
		String regex = "^-?\\d+$";
		return Pattern.matches(regex,digit);
	}
	
	/**
	 * 
	 * @Title: checkDecimals 
	 * @Description: 验证整数和浮点数（正负整数和正负浮点数）
	 * @param decimals 一位或多位0-9之间的浮点数，如：1.23，233.30
	 * @return 验证成功返回true，验证失败返回false
	 * @return: boolean
	 */
	public static boolean checkDecimals(String decimals) {
		String regex = "^(-?\\d+)(\\.\\d+)?$";
		return Pattern.matches(regex,decimals);
	}
}

package com.leoyon.vote.api;

import java.util.Arrays;
import java.util.Optional;

/**
 * 返回码
 * @author WangJiang
 *
 */
public enum Error {
	UNKNOWN_EXCEPT(10000, "未知异常，请联系平台"),
	TOKEN_EXCEPT(1000, "无效的Token"),
	USER_LOGIN_FAIL_NOT_EXSISTED(1001, "登录失败，账号不存在"),
	USER_LOGIN_FAIL(1002, "登录失败，手机或密码不匹配"),
	USER_REGISTER_FAIL_DUPLICATED(1003, "注册失败，账号重复"),
	USER_REGISTER_FAIL_INVALID_CODE(1004, "注册失败，验证码错误"),	
	HOUSE_BIND_WORNG_OWNER(2000, "业主错误"),
	;
	
	private int value;
	private String label;
	
	public int getValue() {
		return value;
	}

	public String getLabel() {
		return label;
	}

	Error(int value,String label){
        this.value = value;
        this.label = label;
    }
	
    public static Error getByVal(int value){
        Optional<Error> optional =  Arrays.stream(Error.values()).filter(st -> st.value == value)
                .findFirst();
        return  optional.isPresent()?optional.get():null;
    }
}

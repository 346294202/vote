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
	VALID_EXCEPT(100000, "数据验证失败"),
	TOKEN_EXCEPT(1000, "无效的Token"),
	INVALID_PARAM(10001, "错误的参数"),
	MISSING_PARAM(10002, "没有提供参数"),
	USER_LOGIN_FAIL_NOT_EXSISTED(1001, "登录失败，账号不存在"),
	USER_LOGIN_FAIL(1002, "登录失败，账号或密码不匹配"),
	USER_REGISTER_FAIL_DUPLICATED(1003, "注册失败，账号重复"),
	VARIFY_FAILED(1004, "验证码错误"),
	HOUSE_BIND_WORNG_OWNER(2000, "业主错误"),
	USER_UNCOMPLATE_INFO(1010, "没有完善实名信息"),
	USER_BIND_NO_HOUSE(1011, "没有关联房屋"),
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

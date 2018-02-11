package com.leoyon.vote.api;

import java.io.Serializable;
import java.util.HashMap;

import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.util.StringUtils;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_EMPTY)
public class JsonResponse implements Serializable {


    /**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = -7941354869234545831L;

	public static final int SUCCESS_CODE=1;
    public static final String SUCCESS_MSG="success";
    public static final String SUCCESS_MSG_4_CHINESE="操作成功";

    private int code;
    private String error;
    private Object data;

    public int getCode() {
		return code;
	}

	public String getError() {
		return error;
	}

	public Object getData() {
		return data;
	}

	public JsonResponse(int code , String msg, Object data){
        this.code = code;
        this.error = msg;
        if(data != null){
            this.data = data;
        }else{
            this.data = new HashMap<String, Object>();
        }
    }

    public static JsonResponse RespSuccess(Object data){
        return new JsonResponse(SUCCESS_CODE,SUCCESS_MSG,data);
    }

    public static JsonResponse RespSuccess(){
        return new JsonResponse(SUCCESS_CODE,SUCCESS_MSG,null);
    }
    
    public static JsonResponse RespSuccess(String msg){
        return new JsonResponse(SUCCESS_CODE, StringUtils.isEmpty(msg)?SUCCESS_MSG_4_CHINESE:msg,null);
    }

    public static JsonResponse RespFail(int code, String msg){
        return  new JsonResponse(code,msg,null);
    }

	public static JsonResponse RespFail(Error code) {
		return RespFail(code.getValue(), code.getLabel());
	}

	@Override
	public String toString() {
		return "JsonResponse [code=" + code + ", error=" + error + ", data=" + data + "]";
	}

    
}

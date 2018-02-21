package com.leoyon.doc;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;

public class MethodParam {

	private String name;
	private String desc;
	private Class<?> type;
	private boolean required;
	private Integer maxLength;
	private Integer minLength;
	private String defaultValue;
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(name+":");
		if(!StringUtils.isBlank(desc)) {
			sb.append(desc+",");
		}
		if(type != null) {
			sb.append(getTypeName() + ",");
		}
		sb.append((required ? "必须" : "可选")+",");
		if(maxLength != null) {
			sb.append("最大"+maxLength+",");
		}
		if(minLength != null) {
			sb.append("最小"+minLength + ",");
		}
		if(defaultValue != null) {
			sb.append("缺省"+defaultValue + ",");
		}
		
		return sb.toString();
	}
	
	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getDefaultValue() {
		return defaultValue;
	}
	public void setDefaultValue(String defaultValue) {
		this.defaultValue = defaultValue;
	}
	public String getDefaultValueName() {
		return !StringUtils.isBlank(defaultValue) ? "缺省"+defaultValue : "";
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Class<?> getType() {
		return type;
	}
	
	public String getTypeName() {
		if(type.equals(Long.class) || type.equals(Integer.class) || type.equals(Short.class))
			return "整数";
		if(type.equals(Float.class) || type.equals(Double.class))
			return "浮点数";
		if(type.equals(Boolean.class))
			return "布尔";
		if(type.equals(Date.class))
			return "日期";
		if(type.equals(String.class))
			return "字符串";
		return type.getSimpleName();
	}

	public void setType(Class<?> type) {
		this.type = type;
	}
	public boolean isRequired() {
		return required;
	}
	public void setRequired(boolean required) {
		this.required = required;
	}
	public String getRequiredName() {
		return isRequired() ? "必须" : "可选";
	}
	public Integer getMaxLength() {
		return maxLength;
	}
	public void setMaxLength(Integer maxLength) {
		this.maxLength = maxLength;
	}
	public String getMaxLengthName() {
		return maxLength != null ? "最大"+maxLength : "";
	}
	public Integer getMinLength() {
		return minLength;
	}
	public void setMinLength(Integer minLength) {
		this.minLength = minLength;
	}
	public String getMinLengthName() {
		return minLength != null ? "最小"+maxLength : "";
	}
}

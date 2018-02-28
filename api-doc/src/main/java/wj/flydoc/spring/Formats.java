package wj.flydoc.spring;

import java.lang.reflect.Parameter;
import java.util.Date;

class Formats {

	public static String format(Class<?> type) {
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

	public static String formatRequired(Boolean required) {
		return required ? "必须" : "可选";
	}

	public static String formatDefVal(String defVal) {
		return "缺省"+defVal;
	}

	public static String formatReturn(Class<?> type) {
		StringObjectWriter writer = new HtmlJsonObjectWriter();
		ObjectWalker walker = new ReadablePropertiesObjectWalker(type);
		try {
			walker.accept(0, writer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return writer.toString();
	}

	public static String formatParameter(Parameter rawParam) {
		StringObjectWriter writer = new HtmlJsonObjectWriter();
		ObjectWalker walker = new WritablePropertiesObjectWalker(rawParam.getType());
		try {
			walker.accept(0, writer);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return writer.toString();
	}

}

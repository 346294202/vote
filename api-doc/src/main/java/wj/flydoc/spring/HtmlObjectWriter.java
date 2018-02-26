package wj.flydoc.spring;

import java.util.Vector;

import org.apache.commons.lang3.StringUtils;

public class HtmlObjectWriter implements StringObjectWriter {

	StringBuilder sb = new StringBuilder();
	
	@Override
	public String toString() {
		return sb.toString();
	}

	@Override
	public void beginObject(int tabs) {
	}

	@Override
	public void endObject(int tabs) {
	}

	@Override
	public void writeValue(int tabs, Class<?> type, String desc, Boolean required, String defVal) {
		Vector<String> parts = new Vector<>();
		if(type != null)
			parts.add(Formats.format(type));
		if(!StringUtils.isBlank(desc))
			parts.add(desc);
		if(required != null)
			parts.add(Formats.formatRequired(required));
		if(!StringUtils.isBlank(defVal))
			parts.add(Formats.formatDefVal(defVal));		
		sb.append(String.join(",", parts));	
	}

	@Override
	public void beginField(int tabs, String name) {
		sb.append(name+":");
	}

	@Override
	public void endField(int tabs) {
		sb.append("<br/>");
	}

	@Override
	public void beginList(int tabs) {
	}

	@Override
	public void endList(int tabs) {
		
	}

}

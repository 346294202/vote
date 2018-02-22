package com.leoyon.doc;

import java.util.Collection;

public class JsonMethodParam extends AbstractMethodParam {

	private Collection<AbstractMethodParam> params;

	public Collection<AbstractMethodParam> getParams() {
		return params;
	}

	public void setParams(Collection<AbstractMethodParam> params) {
		this.params = params;
	}
	
	@Override
	public String getInfo() {
		StringBuilder sb = new StringBuilder();
		sb.append("{<br/>");
		params.forEach(i -> {
			sb.append("&nbsp;&nbsp;"+i.getName()+":"+i.getInfo()+"<br/>");
		});
		sb.append("}");
		return sb.toString();
	}

	@Override
	public String toString() {
		return getInfo();
	}
	
	
	
}

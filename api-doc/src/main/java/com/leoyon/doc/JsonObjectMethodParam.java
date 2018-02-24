package com.leoyon.doc;

import java.util.Collection;

public class JsonObjectMethodParam extends AbstractMethodParam {

	private Collection<AbstractMethodParam> params;

	public Collection<AbstractMethodParam> getParams() {
		return params;
	}

	public void setParams(Collection<AbstractMethodParam> params) {
		this.params = params;
	}
	
	@Override
	public String getInfo(String tab) {
		StringBuilder sb = new StringBuilder();
		if(!isValueType())
			sb.append("{<br/>");
		params.forEach(i -> {
			sb.append(tab+"&nbsp;"+i.getName()+":"+i.getInfo(tab+"&nbsp;")+"<br/>");
		});
		if(!isValueType())
			sb.append(tab+"}");
		return sb.toString();
	}

	@Override
	public String toString() {
		return getInfo("");
	}
	
	
	
}


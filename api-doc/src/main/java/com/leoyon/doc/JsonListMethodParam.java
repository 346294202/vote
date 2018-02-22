package com.leoyon.doc;

public class JsonListMethodParam extends AbstractMethodParam {
	
	private AbstractMethodParam member;

	public JsonListMethodParam(AbstractMethodParam member) {
		super();
		this.member = member;
	}

	@Override
	public String getInfo(String tab) {
		StringBuilder sb = new StringBuilder();
		sb.append("[<br/>");
		sb.append(tab+"&nbsp;"+member.getInfo(tab+"&nbsp;")+",...<br/>");
		sb.append(tab+"]");
		return sb.toString();
	}

	@Override
	public String toString() {
		return getInfo("");
	}
}

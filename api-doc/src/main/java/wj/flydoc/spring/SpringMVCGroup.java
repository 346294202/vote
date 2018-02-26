package wj.flydoc.spring;

import java.util.List;

import wj.flydoc.ApiGroup;
import wj.flydoc.ApiMethod;

public class SpringMVCGroup implements ApiGroup {

	private String name;
	private List<ApiMethod> methods;
	
	public SpringMVCGroup(String name, List<ApiMethod> methods) {
		this.name = name;
		this.methods = methods;
	}

	@Override
	public Iterable<ApiMethod> getMethods() {
		return methods;
	}

	@Override
	public String getName() {
		return name;
	}

}

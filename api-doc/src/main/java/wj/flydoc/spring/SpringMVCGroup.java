package wj.flydoc.spring;

import java.util.List;

import wj.flydoc.ApiGroup;
import wj.flydoc.ApiMethod;

public class SpringMVCGroup implements ApiGroup {

	private List<ApiMethod> methods;
	
	public SpringMVCGroup(List<ApiMethod> methods) {

		this.methods = methods;
	}

	@Override
	public Iterable<ApiMethod> getMethods() {
		return methods;
	}

}

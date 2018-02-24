package wj.flydoc.spring;

import java.lang.reflect.Method;

import org.springframework.web.bind.annotation.DeleteMapping;

public class SpringMVCMethodPatchMapping extends SpringMVCMethod {
	
	private DeleteMapping annotation;

	public SpringMVCMethodPatchMapping(Method m, String rootPath, DeleteMapping annotation) {
		super(m, rootPath);
		this.annotation = annotation;
	}
	

	@Override
	public String getName() {
		return annotation.name();
	}

	@Override
	public String getMethod() {
		return "PATCH";
	}

	@Override
	protected String getMethodPath() {
		if(annotation.path().length > 0)
			return annotation.path()[0];
		if(annotation.value().length > 0)
			return annotation.value()[0];
		return super.getMethodPath();
	}
}

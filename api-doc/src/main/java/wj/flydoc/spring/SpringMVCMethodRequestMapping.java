package wj.flydoc.spring;

import java.lang.reflect.Method;

import org.springframework.web.bind.annotation.RequestMapping;

public class SpringMVCMethodRequestMapping extends SpringMVCMethod {
	
	private RequestMapping annotation;

	public SpringMVCMethodRequestMapping(Method m, String rootPath, RequestMapping annotation) {
		super(m, rootPath);
		this.annotation = annotation;
	}


	@Override
	public String getName() {
		return annotation.name();
	}

	@Override
	public String getMethod() {
		if(annotation.method().length > 0)
			return annotation.method()[0].toString();
		return "GET";
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

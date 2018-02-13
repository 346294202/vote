package com.leoyon.doc;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DocController {
	
	public class MethodItem {
		private String name;
		private String path;
		private String method;
		private String[] params;
		public String getName() {
			return name;
		}
		public String getPath() {
			return path;
		}
		public String getMethod() {
			return method;
		}
		public String[] getParams() {
			return params;
		}
		public MethodItem(String name, String path, String method, String[] params) {
			super();
			this.name = name;
			this.path = path;
			this.method = method;
			this.params = params;
		}
		
	}

	@Autowired
	private ApplicationContext app;
	
	@GetMapping("/doc")
	public String showDocs(Model model) throws IOException {
		Map<String, Object> map = app.getBeansWithAnnotation(RestController.class);
		
		Vector<MethodItem> methods = new Vector<>();
		
		for(Object o:map.values()) {
			for(Method m:o.getClass().getMethods()) {
				
				String name = null;
				String path = null;
				String requestMethod = null;
				String[] params = {};
				
				for(Annotation a:m.getAnnotations()) {					
					if(GetMapping.class.isInstance(a)) {			
						GetMapping mapping = (GetMapping) a;
						name = (mapping.name());
						path = (mapping.value()[0]);
						requestMethod = ("GET");
					} else if(PostMapping.class.isInstance(a)) {
						PostMapping mapping = (PostMapping) a;
						name = (mapping.name());
						path = (mapping.value()[0]);
						requestMethod = ("POST");
					} else if(DeleteMapping.class.isInstance(a)) {
						DeleteMapping mapping = (DeleteMapping) a;
						name = (mapping.name());
						path = (mapping.value()[0]);
						requestMethod = ("DELETE");
					} else if(ApiDocAnnotation.class.isInstance(a)) {
						params = ((ApiDocAnnotation)a).params();
					}
				}
				
				if(name != null) {
					methods.add(new MethodItem(name, path, requestMethod, params));
				}
			}
		}
		
		model.addAttribute("methods", methods);
		
		return "doc-main";
	}

}

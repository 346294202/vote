package com.leoyon.vote.doc;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class DocController {

	@Autowired
	private ApplicationContext app;
	
	@GetMapping("/doc")
	public String showDocs(Model model) throws IOException {
		
		model.addAttribute("xxx", "111");
		
//		resp.setCharacterEncoding("UTF-8");
//		
//		Map<String, Object> map = app.getBeansWithAnnotation(RestController.class);
//		
//		for(Object o:map.values()) {
////			resp.getWriter().println(o);
//			for(Method m:o.getClass().getMethods()) {
////				resp.getWriter().println(m);
//				
//				for(Annotation a:m.getAnnotations()) {
//					
//					if(GetMapping.class.isInstance(a)) {
//						GetMapping mapping = (GetMapping) a;
//						resp.getWriter().println(mapping.name());
//						resp.getWriter().println(Arrays.toString(mapping.value()));
//						resp.getWriter().println();
//					} else if(PostMapping.class.isInstance(a)) {
//						PostMapping mapping = (PostMapping) a;
//						resp.getWriter().println(mapping.name());
//						resp.getWriter().println(Arrays.toString(mapping.value()));
//						resp.getWriter().println();
//					}
////					
//				}
//			}
//		}
		
		return "doc-main";
	}
}

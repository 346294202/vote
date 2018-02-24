package com.leoyon.doc;

import java.io.IOException;
import java.util.Map;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import wj.flydoc.spring.SpringMVCDocument;

@Controller
public class DocController {
	
	@Autowired
	private ApplicationContext app;
	
	@GetMapping("/doc")
	public String showDocs(Model model) throws IOException {
		Map<String, Object> map = app.getBeansWithAnnotation(RestController.class);
		
		model.addAttribute("methods", new SpringMVCDocument(new Vector<>(map.values())).methods());
//		model.addAttribute("methods", MethodItem.generate(map.values()));
		return "doc-main";
	}

}

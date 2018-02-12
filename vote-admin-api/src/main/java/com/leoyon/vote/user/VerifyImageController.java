package com.leoyon.vote.user;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class VerifyImageController {
	
	@Autowired
	private VerifyService verifyService;
	
	@GetMapping("/verify-code")
	public void generateVerifyImage(
			HttpServletResponse resp) throws IOException {
		
		VerifyCode v = verifyService.generate();
		
		Cookie id = new Cookie(VerifyCode.KEY_NAME, v.getKey());
		resp.addCookie(id);
		resp.setContentType("application/jpeg");
		VerifyImages.build(v.getCode(), 75, 24, resp.getOutputStream());				
		resp.flushBuffer();
	}
}

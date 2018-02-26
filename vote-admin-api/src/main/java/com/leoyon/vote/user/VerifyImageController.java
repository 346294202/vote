package com.leoyon.vote.user;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.util.M;

@RestController("图形验证码")
public class VerifyImageController {
	
	@Autowired
	private VerifyService verifyService;
	
	@GetMapping(value="/verify-code/b64", name="获得图片验证码,no token")
	public JsonResponse generateVerifyImageBase64() throws IOException {
		VerifyCode v = verifyService.generate();		
		ByteArrayOutputStream out = new ByteArrayOutputStream(2048);
		VerifyImages.build(v.getCode(), 75, 24, out);				
		String b64 = Base64.getEncoder().encodeToString(out.toByteArray());
		return JsonResponse.sucess(M.map()
				.put("key", v.getKey())
				.put("imgb64", b64)
				.build());
	}
	
	//采用cookie会带来跨域问题，搞复杂了
//	@GetMapping("/verify-code")
//	public void generateVerifyImage(
//			HttpServletResponse resp) throws IOException {
//		
//		VerifyCode v = verifyService.generate();
//		
//		Cookie id = new Cookie(VerifyCode.KEY_NAME, v.getKey());
////		id.setPath("/verify-code");
////		id.setHttpOnly(true);
//		resp.addCookie(id);
//		resp.setContentType("application/jpeg");
//		VerifyImages.build(v.getCode(), 75, 24, resp.getOutputStream());				
//		resp.flushBuffer();
//	}
}

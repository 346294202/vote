package test.com.leoyon.vote;

import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.JsonResponse;

@RestController
@Scope("prototype")
public class TokenTestAuthenticationController extends AuthenticationController {

	@GetMapping("/test/query")
	public JsonResponse query() {
		return JsonResponse.sucess();
	}
	
	@PostMapping("/test/add")
	public JsonResponse add() {
		return JsonResponse.sucess();
	}
}

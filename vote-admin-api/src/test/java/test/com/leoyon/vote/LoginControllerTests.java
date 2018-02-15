package test.com.leoyon.vote;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.user.VerifyCode;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class LoginControllerTests extends BaseWebTests {

	@Before
	public void setUp() throws Exception {
		super.setUp();
		setToken(defUID);
	}
	
	@Test
	public void getVerifyCode() {
		
		JsonResponse r = restTemplate.getForObject("/verify-code/b64", JsonResponse.class);
		assertSucess(r);
		
	}
	
	@Test
	public void login() {
		
		VerifyCode vc = VerifyCode.encode(RandomStringUtils.randomAlphanumeric(4), 600);
		
		
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
		data.add("username", username);
		data.add("password", password);
		data.add("key", vc.getKey());
		data.add("code", vc.getCode());
		
		JsonResponse r = restTemplate.postForObject("/login", data, JsonResponse.class);
		assertSucess(r);
	}
}

package test.com.leoyon.vote;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leoyon.vote.api.JsonResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class AuthenticationControllerTests extends BaseWebTests {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
	}
	
	@Test
	public void noToken() {
		JsonResponse r = restTemplate.getForObject("/test/query", JsonResponse.class);
		assertEquals(com.leoyon.vote.api.Error.TOKEN_EXCEPT.getValue(), r.getCode());
	}
	
	@Test
	public void validToken() throws Exception {
		setToken(defUID);
		JsonResponse r = restTemplate.getForObject("/test/query", JsonResponse.class);
		assertSucess(r);
	}
	
	@Test
	public void expiredToken() throws Exception {
		setToken(defUID, 1);
		Thread.sleep(1005);
		
		JsonResponse r = restTemplate.getForObject("/test/query", JsonResponse.class);
		assertEquals(com.leoyon.vote.api.Error.TOKEN_EXCEPT.getValue(), r.getCode());
	}
}

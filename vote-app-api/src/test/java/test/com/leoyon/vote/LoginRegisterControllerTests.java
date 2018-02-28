package test.com.leoyon.vote;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leoyon.vote.api.Error;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.user.AppUserErrors;
import com.leoyon.vote.user.LoginRequest;
import com.leoyon.vote.user.RegisterRequest;
import com.leoyon.vote.util.M;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class LoginRegisterControllerTests extends BaseWebTests {
		
	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		dbUtil.clear(new String[] {"basic_user", "mobile_verify_code"});
	}
	
	@Test
	public void sendVerifyCodeWrongNumber() {
		JsonResponse r = restTemplate.postForObject("/verify-code/15157185", null, JsonResponse.class);
		assertEquals(Error.VALID_EXCEPT.getValue(), r.getCode());
	}
	
	@Test
	@Ignore("不作为自动测试")
	public void sendVerifyCode() {
		JsonResponse r = restTemplate.postForObject("/verify-code/15158087185", null, JsonResponse.class);
		assertSucess(r);
	}

	@Test
	public void register() throws Exception {
		String code = "12345678";
		dbUtil.insert("mobile_verify_code", M.map()
				.put("mobile", defMobile)
				.put("code", code)
				.put("expire", new Date(System.currentTimeMillis()+1000))
				.build());
		RegisterRequest rqst = new RegisterRequest();
		rqst.setMobile(defMobile);
		rqst.setPassword(defPassword);
		rqst.setCode(code);
		
		JsonResponse r = restTemplate.postForObject("/register", rqst, JsonResponse.class);
		assertSucess(r);
		
		assertFalse(dbUtil.select("select * from basic_user").isEmpty());
	}
	
	@Test
	public void registerCheckParams() {
		RegisterRequest rqst = new RegisterRequest();
		JsonResponse r = restTemplate.postForObject("/register", rqst, JsonResponse.class);
		assertEquals(Error.VALID_EXCEPT.getValue(), r.getCode());
	}
	
	@Test
	public void loginCheckParams() {
		LoginRequest rqst = new LoginRequest();
		JsonResponse r = restTemplate.postForObject("/login", rqst, JsonResponse.class);
		assertEquals(Error.VALID_EXCEPT.getValue(), r.getCode());
		
		rqst = new LoginRequest();
		rqst.setMobile("1738");
		r = restTemplate.postForObject("/login", rqst, JsonResponse.class);
		assertEquals(Error.VALID_EXCEPT.getValue(), r.getCode());
		
	}
	
	@Test
	public void registerNoCode() {
		String code = "12345678";
		RegisterRequest rqst = new RegisterRequest();
		rqst.setMobile(defMobile);
		rqst.setPassword(defPassword);
		rqst.setCode(code);
		
		JsonResponse r = restTemplate.postForObject("/register", rqst, JsonResponse.class);	
		assertEquals(Error.VARIFY_FAILED.getValue(), r.getCode());
	}
	
	@Test
	public void registerExpired() throws Exception {
		String code = "12345678";
		dbUtil.insert("mobile_verify_code", M.map()
				.put("mobile", defMobile)
				.put("code", code)
				.put("expire", new Date(System.currentTimeMillis()-1000))
				.build());
		RegisterRequest rqst = new RegisterRequest();
		rqst.setMobile(defMobile);
		rqst.setPassword(defPassword);
		rqst.setCode(code);
		
		JsonResponse r = restTemplate.postForObject("/register", rqst, JsonResponse.class);	
		assertEquals(Error.VARIFY_FAILED.getValue(), r.getCode());
	}
	
	@Test
	public void registerDuplicated() throws Exception {
		String code = "12345678";
		addDefUserDb();
		dbUtil.insert("mobile_verify_code", M.map()
				.put("mobile", defMobile)
				.put("code", code)
				.put("expire", new Date(System.currentTimeMillis()+1000))
				.build());
		RegisterRequest rqst = new RegisterRequest();
		rqst.setMobile(defMobile);
		rqst.setPassword(defPassword);
		rqst.setCode(code);
		
		JsonResponse r = restTemplate.postForObject("/register", rqst, JsonResponse.class);
		assertEquals(Error.USER_REGISTER_FAIL_DUPLICATED.getValue(), r.getCode());
	}
	
	@Test
	public void loginNoUser() {
		LoginRequest rqst = new LoginRequest();
		rqst.setMobile(defMobile);
		rqst.setPassword(defPassword);
		
		JsonResponse r = restTemplate.postForObject("/login", rqst, JsonResponse.class);
		assertEquals(Error.USER_LOGIN_FAIL_NOT_EXSISTED.getValue(), r.getCode());

	}
	
	@Test
	public void login() throws Exception {
		addDefUserDb();
		LoginRequest rqst = new LoginRequest();
		rqst.setMobile(defMobile);
		rqst.setPassword(defPassword);
		
		JsonResponse r = restTemplate.postForObject("/login", rqst, JsonResponse.class);
		assertSucess(r);
		
		assertNotNull(r.getItem("token"));
		assertNotNull(r.getItem("expir"));
	}
	
	@Test
	public void loginWrongPassword() throws Exception {
		addDefUserDb();
		LoginRequest rqst = new LoginRequest();
		rqst.setMobile(defMobile);
		rqst.setPassword(defPassword+"xxx");
		
		JsonResponse r = restTemplate.postForObject("/login", rqst, JsonResponse.class);
		assertEquals(Error.USER_LOGIN_FAIL.getValue(), r.getCode());
	}
	
	@Autowired
	private com.leoyon.vote.user.dao.MobileVerifyDao mobileVerifyDao;
	
	@Test
	public void addCode() throws Exception {
		
		String code = "12345678";
		mobileVerifyDao.add(defMobile, code, new Date(System.currentTimeMillis() + 1000));
		
		List<Map<String, Object>> results = dbUtil.select("select * from mobile_verify_code where mobile='"+defMobile+"'");
		
		assertFalse(results.isEmpty());
		assertEquals(code, results.get(0).get("code"));
		
		code = "87654321";
		mobileVerifyDao.add(defMobile, code, new Date(System.currentTimeMillis() + 1000));
		results = dbUtil.select("select * from mobile_verify_code where mobile='"+defMobile+"'");		
		assertFalse(results.isEmpty());
		assertEquals(code, results.get(0).get("code"));

	}
	
}

package test.com.leoyon.vote;

import java.io.IOException;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;

import com.leoyon.vote.Passwords;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.api.Token;
import com.leoyon.vote.util.M;

public class BaseWebTests extends BaseDbTests {

	@Autowired
	protected TestRestTemplate restTemplate;
	
	protected int tokenExpirSeconds = 1;
	
	String username = "15158087185";
	String password = "123456";

	public BaseWebTests() {
		super();
	}
	
	protected void setToken(Long uid) throws Exception {
		setToken(uid, tokenExpirSeconds);
	}

	protected void setToken(Long uid, int expie) throws Exception {
		String salt = "sdasdasd";
		
		dbUtil.insert("sys_user", new M<String,Object>()
				.put("id", uid)
				.put("username", username)
				.put("password", Passwords.encode(password, salt))
				.put("salt", salt)
				.put("real_name", defUname)
				.build());
		final String tokenValue = Token.build(uid+"", expie).getToken();
		restTemplate.getRestTemplate().setInterceptors(Collections.singletonList(new ClientHttpRequestInterceptor() {
			
			@Override
			public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution)
					throws IOException {
				request.getHeaders().add(Token.TOKEN_NAME, tokenValue);
				return execution.execute(request, body);
			}
		}));
	}

	@Before
	public void setUp() throws Exception {		
		dbUtil.clear("sys_user");		
		restTemplate.getRestTemplate().setInterceptors(null);
	}

	protected void assertSucess(JsonResponse r) {
		Assert.assertEquals(1,  r.getCode());
	}

}
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
import com.leoyon.vote.dao.DbUtil;
import com.leoyon.vote.util.M;

public class BaseWebTests {

	@Autowired
	protected DbUtil dbUtil;
	
	@Autowired
	protected TestRestTemplate restTemplate;
	
	protected int tokenExpirSeconds = 1;
	
	public long defUid = 1L;
	public String defMobile = "15158087185";
	public String defPassword = "123456";
	public String defSalt = "sdasdasd";
	
	public void setToken(Long uid) throws Exception {
		setToken(uid, tokenExpirSeconds);
	}

	public void setToken(Long uid, int expie) throws Exception {
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
		dbUtil.clear("basic_user");		
		restTemplate.getRestTemplate().setInterceptors(null);
	}

	public void addDefUserDb() throws Exception {
		dbUtil.insert("basic_user", new M<String,Object>()
				.put("id", defUid)
				.put("mobile", defMobile)
				.put("password", Passwords.encode(defPassword, defSalt))
				.put("salt", defSalt)
				.build());
	}

	public void assertSucess(JsonResponse r) {
		Assert.assertEquals(1,  r.getCode());
	}

}
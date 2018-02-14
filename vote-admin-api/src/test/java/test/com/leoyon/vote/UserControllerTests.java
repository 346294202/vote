package test.com.leoyon.vote;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.user.User;
import com.leoyon.vote.util.M;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class UserControllerTests extends BaseWebTests {
	
	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		dbUtil.clear(new String[]{"vote_user"});
		setToken(defUID);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void find() throws Exception {
		
		List<Map<String, Object>> list = Arrays.asList(
				M.map()
				.put("mobile", "123")
				.put("password", "111")
				.put("salt", "aaa")
				.put("active", 1)
				.build(),
				M.map()
				.put("mobile", "234")
				.put("password", "111")
				.put("salt", "aaa")
				.put("active", 1)
				.build(),
				M.map()
				.put("mobile", "345")
				.put("password", "111")
				.put("salt", "aaa")
				.put("active", 1)
				.build(),
				M.map()
				.put("mobile", "456")
				.put("password", "111")
				.put("salt", "aaa")
				.put("active", 1)
				.build(),
				M.map()
				.put("mobile", "567")
				.put("password", "111")
				.put("salt", "aaa")
				.put("active", 0)
				.build(),
				M.map()
				.put("mobile", "678")
				.put("password", "111")
				.put("salt", "aaa")
				.put("active", 1)
				.build()
				);
		
		dbUtil.insert("vote_user", list);
		
		JsonResponse r = restTemplate.getForObject("/user", JsonResponse.class);
		assertSucess(r);
		
		List<Object> items = (List<Object>) r.getItem("items");
		Assert.assertEquals(list.size(), items.size());
		
		r = restTemplate.getForObject("/user?q={q}", JsonResponse.class, M.map().put("q", "56").build());
		items = (List<Object>) r.getItem("items");
		Assert.assertEquals(2, items.size());
		r = restTemplate.getForObject("/user?q={q}&active={active}"
				, JsonResponse.class
				, M.map()
				.put("q", "56")
				.put("active", 1)
				.build());
		items = (List<Object>) r.getItem("items");
		Assert.assertEquals(1, items.size());
	}
	
	@Test
	public void update() throws Exception {
		dbUtil.insert("vote_user", M.map()
				.put("id", 1L)
				.put("mobile", "123")
				.put("password", "111")
				.put("salt", "aaa")
				.put("active", 1)
				.build());
		
		User user = new User();
		user.setActive(false);
		
		JsonResponse r = restTemplate.postForObject("/user/1", user, JsonResponse.class);
		assertSucess(r);
		
		List<Map<String, Object>> list = dbUtil.select("select * from vote_user where id=1");
		Assert.assertEquals(0, (int) list.get(0).get("active"));
	}
}

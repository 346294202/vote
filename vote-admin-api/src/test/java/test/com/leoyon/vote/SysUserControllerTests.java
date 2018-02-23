package test.com.leoyon.vote;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.leoyon.vote.Passwords;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.util.M;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class SysUserControllerTests extends BaseWebTests {
		
	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();		
		setToken(defUID);
		dbUtil.clear("sys_role");
		dbUtil.clear("sys_user_role");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void find() throws Exception {		
		
		List<Map<String, Object>> list = buildSysUsers();
		
		dbUtil.insert("sys_user", list);
		
		JsonResponse r = restTemplate.getForObject("/sys/user", JsonResponse.class);
		assertSucess(r);
		
		List<Map<String, Object>> users = (List<Map<String, Object>>) ((Map<String, Object>) r.getData()).get("items");		
		Assert.assertEquals(list.size()+1, users.size());
		
		r = restTemplate.getForObject("/sys/user?q={q}", JsonResponse.class, M.map().put("q", "wj").build());
		users = (List<Map<String, Object>>) ((Map<String, Object>) r.getData()).get("items");		
		Assert.assertEquals(3, users.size());
		
		r = restTemplate.getForObject("/sys/user?active={active}", JsonResponse.class, M.map().put("active", 0).build());
		users = (List<Map<String, Object>>) ((Map<String, Object>) r.getData()).get("items");		
		Assert.assertEquals(2, users.size());
		
		dbUtil.insert("sys_role", Arrays.asList(
				M.map()
				.put("id", 1L)
				.put("name", "Menu1")
				.build(),
				M.map()
				.put("id", 2L)
				.put("name", "Menu2")
				.build(),
				M.map()
				.put("id", 3L)
				.put("name", "Menu3")
				.build(),
				M.map()
				.put("id", 4L)
				.put("name", "Menu4")
				.build(),
				M.map()
				.put("id", 5L)
				.put("name", "Menu5")
				.build()
				));
		
		dbUtil.insert("sys_user_role", Arrays.asList(
				M.map()
				.put("user_id", users.get(0).get("id"))
				.put("role_id", 1L)
				.build(),
				M.map()
				.put("user_id", users.get(0).get("id"))
				.put("role_id", 2L)
				.build(),
				M.map()
				.put("user_id", users.get(1).get("id"))
				.put("role_id", 1L)
				.build(),
				M.map()
				.put("user_id", users.get(1).get("id"))
				.put("role_id", 2L)
				.build(),
				M.map()
				.put("user_id", users.get(1).get("id"))
				.put("role_id", 3L)
				.build()
				));
		
		r = restTemplate.getForObject("/sys/user?active={active}", JsonResponse.class, M.map().put("active", 0).build());
		users = (List<Map<String, Object>>) ((Map<String, Object>) r.getData()).get("items");		
		Assert.assertEquals(2, ((List<Map<String, Object>>)users.get(0).get("roles")).size());
		
	}

	@Test
	public void put() throws Exception {
		
		JsonResponse r = restTemplate.postForObject("/sys/user", M.map()
				.put("username", "10wj")
				.put("password", "111")
				.put("active", 0)
				.build(), JsonResponse.class);
		
		assertSucess(r);
		
		List<Map<String,Object>> list = dbUtil.select("select * from sys_user where username='10wj'");
		
		Assert.assertEquals(1, list.size());
		Assert.assertFalse(Boolean.valueOf(list.get(0).get("active")+""));
		Assert.assertTrue(Passwords.match("111", list.get(0).get("password").toString(), list.get(0).get("salt").toString()));
		
		Long id = (Long) list.get(0).get("id");
		
		restTemplate.postForObject("/sys/user/"+id, M.map()
				.put("active", 1)
				.build(), JsonResponse.class);
		list = dbUtil.select("select * from sys_user where username='10wj'");
		Assert.assertFalse(Boolean.valueOf(list.get(0).get("active")+""));
	}
	

	@Test
	public void seUserRoles() throws Exception {
		Long uid = 2L;

		dbUtil.insert("sys_user", M.map()
				.put("id", uid)
				.put("username", "wj")
				.build());		
		
		List<Map<String, Object>> list = Arrays.asList(
				M.map()
				.put("id", 1L)
				.put("name", "经理")
				.put("so", 1)
				.put("remark", "一般")
				.build(),
				M.map()
				.put("id", 2L)
				.put("name", "主管")
				.put("so", 2)
				.put("remark", "一般")
				.build(),
				M.map()
				.put("id", 3L)
				.put("name", "客服")
				.put("so", 3)
				.put("remark", "一般")
				.build()
				);
		
		dbUtil.insert("sys_role", list);
		
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
		data.add("ids", "1,3");
		JsonResponse r = restTemplate.postForObject("/sys/user/1/role",
				data, JsonResponse.class);
		assertSucess(r);		
	}
	
	@Test
	public void queryDelete() throws Exception {
		List<Map<String, Object>> list = Arrays.asList(
				M.map()
				.put("username", "wj")
				.put("password", "111")
				.put("salt", "asas")
				.put("active", 1)
				.put("delete", 1)
				.build(),
				M.map()
				.put("username", "w10j")
				.put("password", "111")
				.put("salt", "asas")
				.put("active", 0)
				.put("delete", 1)
				.build(),
				M.map()
				.put("username", "wj10")
				.put("password", "111")
				.put("salt", "asas")
				.put("active", 1)
				.put("delete", 2)
				.build(),
				M.map()
				.put("username", "10wj")
				.put("password", "111")
				.put("salt", "asas")
				.put("active", 0)
				.put("delete", 2)
				.build()
				);
		
		dbUtil.insert("sys_user", list);
		
		JsonResponse r = restTemplate.getForObject("/sys/user", JsonResponse.class);
		assertSucess(r);
		List<Map<String, Object>> users = (List<Map<String, Object>>) ((Map<String, Object>) r.getData()).get("items");		
		Assert.assertEquals(list.size()+1-2, users.size());

	}
	
}

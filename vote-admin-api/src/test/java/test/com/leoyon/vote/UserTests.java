package test.com.leoyon.vote;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.api.Passwords;
import com.leoyon.vote.user.SysUser;
import com.leoyon.vote.util.MapBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class UserTests extends BaseWebTests {

	@SuppressWarnings("unchecked")
	@Test
	public void find() throws Exception {
		
		setToken(1L);
		
		List<Map<String, Object>> list = Arrays.asList(
				MapBuilder.map()
				.put("username", "wj")
				.put("password", "111")
				.put("salt", "asas")
				.put("active", 1)
				.build(),
				MapBuilder.map()
				.put("username", "w10j")
				.put("password", "111")
				.put("salt", "asas")
				.put("active", 0)
				.build(),
				MapBuilder.map()
				.put("username", "wj10")
				.put("password", "111")
				.put("salt", "asas")
				.put("active", 1)
				.build(),
				MapBuilder.map()
				.put("username", "10wj")
				.put("password", "111")
				.put("salt", "asas")
				.put("active", 0)
				.build()
				);
		
		dbUtil.insert("sys_user", list);
		
		JsonResponse r = restTemplate.getForObject("/user", JsonResponse.class);
		Assert.assertEquals(1,  r.getCode());
		
		List<SysUser> users = (List<SysUser>) ((Map<String, Object>) r.getData()).get("items");		
		Assert.assertEquals(list.size()+1, users.size());
		
		r = restTemplate.getForObject("/user?q={q}", JsonResponse.class, MapBuilder.map().put("q", "wj").build());
		users = (List<SysUser>) ((Map<String, Object>) r.getData()).get("items");		
		Assert.assertEquals(3, users.size());
		
		r = restTemplate.getForObject("/user?active={active}", JsonResponse.class, MapBuilder.map().put("active", 0).build());
		users = (List<SysUser>) ((Map<String, Object>) r.getData()).get("items");		
		Assert.assertEquals(2, users.size());
	}
	
	@Test
	public void put() throws Exception {
		
		setToken(1L);
		
		JsonResponse r = restTemplate.postForObject("/user", MapBuilder.map()
				.put("username", "10wj")
				.put("password", "111")
				.put("active", 0)
				.build(), JsonResponse.class);
		
		Assert.assertEquals(1,  r.getCode());
		
		List<Map<String,Object>> list = dbUtil.select("select * from sys_user where username='10wj'");
		
		Assert.assertEquals(1, list.size());
		Assert.assertFalse(Boolean.valueOf(list.get(0).get("active")+""));
		Assert.assertTrue(Passwords.match("111", list.get(0).get("password").toString(), list.get(0).get("salt").toString()));
		
		Long id = (Long) list.get(0).get("id");
		
		restTemplate.postForObject("/user/"+id, MapBuilder.map()
				.put("active", 1)
				.build(), JsonResponse.class);
		list = dbUtil.select("select * from sys_user where username='10wj'");
		Assert.assertFalse(Boolean.valueOf(list.get(0).get("active")+""));
	}
	
	
}

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
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.role.SysRole;
import com.leoyon.vote.user.SysUser;
import com.leoyon.vote.util.M;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class SysRoleControllerTests extends BaseWebTests {
	
	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();		
		setToken(1L);
		dbUtil.clear("sys_role");
		dbUtil.clear("sys_role_command");
		dbUtil.clear("sys_command");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void find() throws Exception {		
		
		List<Map<String, Object>> list = Arrays.asList(
				M.map()
				.put("name", "经理")
				.put("so", 1)
				.put("remark", "一般")
				.build(),
				M.map()
				.put("name", "主管")
				.put("so", 2)
				.put("remark", "一般")
				.build(),
				M.map()
				.put("name", "客服")
				.put("so", 3)
				.put("remark", "一般")
				.build()
				);
		
		dbUtil.insert("sys_role", list);
		
		JsonResponse r = restTemplate.getForObject("/sys/role", JsonResponse.class);
		assertSucess(r);
		
		List<SysUser> users = (List<SysUser>) ((Map<String, Object>) r.getData()).get("items");		
		Assert.assertEquals(list.size(), users.size());
		
		r = restTemplate.getForObject("/sys/role?q={q}", JsonResponse.class, M.map().put("q", "主管").build());
		users = (List<SysUser>) ((Map<String, Object>) r.getData()).get("items");		
		Assert.assertEquals(1, users.size());
		
	}

	@Test
	public void put() throws Exception {
		
		SysRole role = new SysRole();
		role.setName("主管");
		
		JsonResponse r = restTemplate.postForObject("/sys/role", role, JsonResponse.class);		
		assertSucess(r);
		
		List<Map<String,Object>> list = dbUtil.select("select * from sys_role");		
		Assert.assertEquals(1, list.size());
		
		Long id = (Long) list.get(0).get("id");
		
		role = new SysRole();
		role.setName("二级主管");
		
		restTemplate.postForObject("/sys/role/"+id, role, JsonResponse.class);
		list = dbUtil.select("select * from sys_role");
		Assert.assertEquals("二级主管", list.get(0).get("name"));
	}
	
	@Test
	public void bindCommands() throws Exception {
		Long rid = 1L;

		dbUtil.insert("sys_role", M.map()
				.put("id", rid)
				.put("name", "经理")
				.put("so", 1)
				.put("remark", "一般")
				.build());		
		
		List<Map<String, Object>> list = Arrays.asList(
				M.map()
				.put("id", 1L)
				.put("parent_id", 0L)
				.put("name", "一级1")
				.put("so", 1)
				.put("url", null)
				.build(),
				M.map()
				.put("id", 2L)
				.put("parent_id", 0L)
				.put("name", "一级2")
				.put("so", 1)
				.put("url", null)
				.build(),
				M.map()
				.put("id", 3L)
				.put("parent_id", 1L)
				.put("name", "二级1")
				.put("so", 1)
				.put("url", "/sys/level1.html")
				.build(),
				M.map()
				.put("id", 4L)
				.put("parent_id", 2L)
				.put("name", "二级2")
				.put("so", 1)
				.put("url", "/sys/level1.html")
				.build()
				);
		
		dbUtil.insert("sys_command", list);
		
		MultiValueMap<String, Object> data = new LinkedMultiValueMap<>();
		data.add("ids", "1,3");
		JsonResponse r = restTemplate.postForObject("/sys/role/1/command",
				data, JsonResponse.class);
		assertSucess(r);
		
		
	}
	
	
}

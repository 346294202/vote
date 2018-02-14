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
import com.leoyon.vote.command.SysCommand;
import com.leoyon.vote.user.SysUser;
import com.leoyon.vote.util.M;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class SysCommandControllerTests extends BaseWebTests {


	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();		
		setToken(1L);
		dbUtil.clear("sys_command");
	}

	@SuppressWarnings("unchecked")
	@Test
	public void find() throws Exception {		
		
		List<Map<String, Object>> list = Arrays.asList(
				M.map()
				.put("parent_id", 0L)
				.put("name", "一级1")
				.put("so", 1)
				.put("url", null)
				.build(),
				M.map()
				.put("parent_id", 0L)
				.put("name", "一级2")
				.put("so", 1)
				.put("url", null)
				.build(),
				M.map()
				.put("parent_id", 1L)
				.put("name", "二级1")
				.put("so", 1)
				.put("url", "/sys/level1.html")
				.build(),
				M.map()
				.put("parent_id", 2L)
				.put("name", "二级2")
				.put("so", 1)
				.put("url", "/sys/level1.html")
				.build()
				);
		
		dbUtil.insert("sys_command", list);
		
		JsonResponse r = restTemplate.getForObject("/sys/command", JsonResponse.class);
		assertSucess(r);
		
		List<SysUser> users = (List<SysUser>) ((Map<String, Object>) r.getData()).get("items");		
		Assert.assertEquals(list.size(), users.size());
		
		r = restTemplate.getForObject("/sys/command?q={q}", JsonResponse.class, M.map().put("q", "二级").build());
		users = (List<SysUser>) ((Map<String, Object>) r.getData()).get("items");		
		Assert.assertEquals(2, users.size());
		
	}

	@Test
	public void put() throws Exception {
		
		SysCommand role = new SysCommand();
		role.setName("主管");
		
		JsonResponse r = restTemplate.postForObject("/sys/command", role, JsonResponse.class);		
		assertSucess(r);
		
		List<Map<String,Object>> list = dbUtil.select("select * from sys_command");		
		Assert.assertEquals(1, list.size());
		
		Long id = (Long) list.get(0).get("id");
		
		role = new SysCommand();
		role.setName("二级主管");
		
		restTemplate.postForObject("/sys/command/"+id, role, JsonResponse.class);
		list = dbUtil.select("select * from sys_command");
		Assert.assertEquals("二级主管", list.get(0).get("name"));
	}
	
}

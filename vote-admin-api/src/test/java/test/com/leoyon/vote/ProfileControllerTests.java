package test.com.leoyon.vote;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.util.M;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class ProfileControllerTests extends BaseWebTests {

	@Before
	public void setUp() throws Exception {
		super.setUp();
		setToken(defUID);
		dbUtil.clear(new String[] {
				"sys_role", "sys_command", "sys_role_command", "sys_user_role"
		});
	}
	
	@Test
	public void getUserMenus() throws Exception {
		dbUtil.insert("sys_role", M.map()
				.put("id", 1L)
				.put("name", "R1")
				.build());
		
		dbUtil.insert("sys_command", buildCommands());
		
		dbUtil.insert("sys_role_command", Arrays.asList(
				M.map()
				.put("role_id", 1L)
				.put("command_id", 1L)
				.build()
				));
		
		dbUtil.insert("sys_user_role", M.map()
				.put("role_id", 1L)
				.put("user_id", 1L)
				.build());
		
		JsonResponse r = restTemplate.getForObject("/profile/menu", JsonResponse.class);
		assertSucess(r);
		
		/*
		1 foo
		|->2 fo10o
		|->3 foo10
		*/
		List<Map<String,Object>> items = (List<Map<String, Object>>) r.getItem("items");
		assertEquals(1, items.size());
		assertEquals("foo", items.get(0).get("name"));
		
		List<Map<String,Object>> menus = (List<Map<String, Object>>) items.get(0).get("menus");
		assertEquals(2, menus.size());
	}
	
	@Test
	public void getUserInfo() {
		JsonResponse r = restTemplate.getForObject("/profile/info", JsonResponse.class);
		assertSucess(r);
	}
}

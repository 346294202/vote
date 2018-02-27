package test.com.leoyon.vote;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.user.ChangePasswordRequest;
import com.leoyon.vote.util.M;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class ProfileControllerTests extends BaseWebTests {

	@Before
	public void setUp() throws Exception {
		super.setUp();
		dbUtil.clear(new String[] {
				"sys_role", "sys_command", "sys_role_command", "sys_user_role"
		});
	}
	
	@Test
	public void getUserMenus() throws Exception {
		setToken(defUID);
		
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
	public void bug_getUserMenus() throws Exception {		
		setToken(2L, 10000);
		dbUtil.insert("sys_role", Arrays.asList(
				M.map().put("id", 1l).build(),
				M.map().put("id", 2l).build(),
				M.map().put("id", 3l).build(),
				M.map().put("id", 4l).build()
				));
		dbUtil.insert("sys_command", M.mapList(
				Arrays.asList("id", "parent_id", "name"), 
				Arrays.asList(4,
						6,
						7,
						8,
						9,
						10,
						11,
						12,
						13,
						14,
						15,
						16,
						17,
						18,
						19,
						20,
						21,
						22,
						23,
						24,
						25,
						26,
						27,
						28,
						29,
						30),
				Arrays.asList(0,
						4,
						4,
						4,
						4,
						0,
						10,
						10,
						10,
						10,
						10,
						10,
						0,
						17,
						17,
						17,
						17,
						17,
						17,
						17,
						17,
						0,
						26,
						26,
						26,
						26),
				Arrays.asList(0,
						4,
						4,
						4,
						4,
						0,
						10,
						10,
						10,
						10,
						10,
						10,
						0,
						17,
						17,
						17,
						17,
						17,
						17,
						17,
						17,
						0,
						26,
						26,
						26,
						26))
				);
		dbUtil.insert("sys_user_role", Arrays.asList(
				M.map()
				.put("user_id", 2l)
				.put("role_id", 2l)
				.build(),
				M.map()
				.put("user_id", 2l)
				.put("role_id", 3l)
				.build(),
				M.map()
				.put("user_id", 2l)
				.put("role_id", 4l)
				.build(),
				M.map()
				.put("user_id", 4l)
				.put("role_id", 3l)
				.build(),
				M.map()
				.put("user_id", 6l)
				.put("role_id", 2l)
				.build(),
				M.map()
				.put("user_id", 6l)
				.put("role_id", 3l)
				.build(),
				M.map()
				.put("user_id", 6l)
				.put("role_id", 4l)
				.build()
				));
		dbUtil.insert("sys_role_command", M.mapList(Arrays.asList("role_id", "command_id"),
				Arrays.asList(2,
						2,
						2,
						2,
						2,
						3,
						3,
						3,
						3,
						3,
						3,
						3,
						3,
						3,
						4,
						4,
						4,
						4,
						4,
						4,
						4,
						4,
						4,
						4,
						4,
						4),
				Arrays.asList(26,
						27,
						28,
						29,
						30,
						17,
						18,
						19,
						20,
						21,
						22,
						23,
						24,
						25,
						4,
						6,
						7,
						8,
						9,
						10,
						11,
						12,
						13,
						14,
						15,
						16)));
		
		JsonResponse r = restTemplate.getForObject("/profile/menu", JsonResponse.class);
		assertSucess(r);
		List<Map<String,Object>> items = (List<Map<String, Object>>) r.getItem("items");
		assertEquals(4, items.size());
	}
	
	@Test
	public void getUserInfo() throws Exception {
		setToken(defUID);
		JsonResponse r = restTemplate.getForObject("/profile/info", JsonResponse.class);
		assertSucess(r);
	}
	
	@Test
	public void changePassword() throws Exception {
		setToken(defUID);
		String newPassword = "newPassword";
		ChangePasswordRequest rqst = new ChangePasswordRequest();
		rqst.setOldPassword(password);
		rqst.setNewPassword(newPassword);
		
		JsonResponse r = restTemplate.postForObject("/profile/password", rqst, JsonResponse.class);
		assertSucess(r);
	}
}

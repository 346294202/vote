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
import com.leoyon.vote.util.MapBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class FrontControllerTests extends BaseWebTests {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		dbUtil.clear(new String[]{"sys_command", "sys_user_command"});
		setToken(defUID);
		dbUtil.insert("sys_command", buildCommands());
	}
	
	@SuppressWarnings({ "unchecked" })
	@Test
	public void front() throws Exception {
		
		dbUtil.insert("sys_user_command", Arrays.asList(
				MapBuilder.map()
				.put("user_id", defUID)
				.put("command_id", 3L)
				.build()
				));
		
		JsonResponse r = restTemplate.getForObject("/front", JsonResponse.class);
		assertSucess(r);
		
		List<Object> menus = (List<Object>) ((Map<String,Object>)r.getData()).get("menus");
		Assert.assertEquals(1, menus.size());
		Assert.assertEquals("foo", ((Map<String,Object>)menus.get(0)).get("name"));
		Assert.assertEquals(1, ((List<Object>)((Map<String,Object>)menus.get(0)).get("menus")).size());
		Assert.assertEquals("foo10", (((Map<String,Object>)((List<Object>)((Map<String,Object>)menus.get(0)).get("menus")).get(0)).get("name")));
	}
}

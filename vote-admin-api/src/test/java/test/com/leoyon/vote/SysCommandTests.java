package test.com.leoyon.vote;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leoyon.vote.command.SysCommand;
import com.leoyon.vote.command.SysCommandService;
import com.leoyon.vote.dao.DbUtil;
import com.leoyon.vote.user.SysUser;
import com.leoyon.vote.user.SysUserService;
import com.leoyon.vote.util.MapBuilder;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .MOCK)
public class SysCommandTests {

	@Autowired
	private DbUtil dbUtil;
	
	@Before
	public void setUp() throws Exception {
		dbUtil.clear(new String[]{
				"sys_user", "sys_command", "sys_user_command"
		});
	}
	
	@Autowired
	private SysCommandService commandService; 
	
	@Autowired
	private SysUserService userService;
	
	@Test
	public void menusByUser() throws Exception {
		
		Long uid = 1L;
		
		dbUtil.insert("sys_user", new MapBuilder<String,Object>()
				.put("id", uid)
				.put("username", "ass")
				.put("password", "ass")
				.put("salt", "asa")
				.build());
		
		dbUtil.insert("sys_command", Arrays.asList(
				new MapBuilder<String,Object>()
				.put("id", 1L)
				.put("parent_id", 0L)
				.put("name", "1")
				.put("url", "1")
				.build(),
				new MapBuilder<String,Object>()
				.put("id", 2L)
				.put("parent_id", 1L)
				.put("name", "1")
				.put("url", "1")
				.build(),
				new MapBuilder<String,Object>()
				.put("id", 3L)
				.put("parent_id", 1L)
				.put("name", "1")
				.put("url", "1")
				.build(),
				new MapBuilder<String,Object>()
				.put("id", 4L)
				.put("parent_id", 0L)
				.put("name", "1")
				.put("url", "1")
				.build()
				));
		
		dbUtil.insert("sys_user_command", Arrays.asList(
				new MapBuilder<String,Object>()
				.put("user_id", uid)
				.put("command_id", 1L)
				.build(),
				new MapBuilder<String,Object>()
				.put("user_id", uid)
				.put("command_id", 2L)
				.build(),
				new MapBuilder<String,Object>()
				.put("user_id", uid)
				.put("command_id", 3L)
				.build(),
				new MapBuilder<String,Object>()
				.put("user_id", uid)
				.put("command_id", 4L)
				.build()
				));
		
		SysUser user = userService.get(uid);
		
		List<SysCommand> commands = commandService.listByUser(user);
		
		Assert.assertEquals(4, commands.size());
		
	}
}

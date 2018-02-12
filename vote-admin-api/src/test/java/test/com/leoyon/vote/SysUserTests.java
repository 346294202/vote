package test.com.leoyon.vote;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.api.Passwords;
import com.leoyon.vote.user.SysUser;
import com.leoyon.vote.user.SysUserService;
import com.leoyon.vote.user.dao.SysUserDao;
import com.leoyon.vote.util.MapBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class SysUserTests extends BaseWebTests {

	@Autowired
	private SysUserDao userDao;
	
	@Test
	public void daoAddUser() throws Exception {
		
		SysUser user = new SysUser();
		user.setUsername("12345678");
		user.setPassword("654321");
		user.setSalt("qazxcv");
		userDao.addUser(user);
		
		Assert.assertNotNull(user.getId());
	}
	
	
	@Autowired
	private SysUserService userService; 
	
	@Test
	public void serviceAddUser() throws Exception {
		
		String username = "15158087185";
		String password = "123456";
		
		SysUser user = new SysUser();
		user.setUsername(username);
		user.setPassword(password);
		
		userService.add(user);
		
		Assert.assertNotNull(user);
		Assert.assertEquals(username, user.getUsername());
		Assert.assertTrue(user.matchPassword(password));
	}
	
	@Test
	public void serviceGetUser() throws Exception {
		String username = "15158087185";
		String password = "123456";
		String salt = "sdasdasd";
		
		dbUtil.insert("sys_user", new MapBuilder<String,Object>()
				.put("username", username)
				.put("password", password)
				.put("salt", salt)
				.build());
		
		SysUser user = userService.get(username);
		Assert.assertNotNull(user);
		Assert.assertEquals(username, user.getUsername());		
	}
	
	@Test
	public void updat() throws Exception {
		
		Long uid = 1L;
		String username = "15158087185";
		String password = "123456";
		String salt = "sdasdasd";
		
		dbUtil.insert("sys_user", new MapBuilder<String,Object>()
				.put("id", uid)
				.put("username", username)
				.put("password", Passwords.encode(password, salt))
				.put("salt", salt)
				.build());
		
		SysUser user = new SysUser();
		user.setId(uid);
		user.setUsername("wj");
		
		userService.update(user);
		
		user = userService.get(uid);
		
		Assert.assertEquals("wj", user.getUsername());
		Assert.assertTrue(user.matchPassword(password));
	}
	
	@Test
	public void front() throws Exception {
		
		setToken(1L);
		
		JsonResponse r = restTemplate.getForObject("/front", JsonResponse.class);
		
		
		
		Assert.assertEquals(1, r.getCode());
	}
}

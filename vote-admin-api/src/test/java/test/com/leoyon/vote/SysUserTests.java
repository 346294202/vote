package test.com.leoyon.vote;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leoyon.vote.api.Passwords;
import com.leoyon.vote.dao.DbUtil;
import com.leoyon.vote.user.SysUser;
import com.leoyon.vote.user.SysUserService;
import com.leoyon.vote.user.dao.SysUserDao;
import com.leoyon.vote.util.MapBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .MOCK)
public class SysUserTests {

	@Autowired
	private SysUserDao userDao;
	
	@Autowired
	private DbUtil dbUtil;
	
	@Before
	public void setUp() throws Exception {
		dbUtil.clear(new String[]{
				"sys_user"
		});
	}
	
	@Test
	public void daoAddUser() throws Exception {
		
		SysUser user = new SysUser();
		user.setUsername("12345678");
		user.setPassword("654321");
		user.setSalt("qazxcv");
		userDao.addUser(user);
		
		Assert.assertNotNull(user.getId());
	}
	
	@Test
	public void testPasswords() throws Exception {
		String value = "asdfg";
		String salt = "wqdszc";
		
		String r = Passwords.encode(value, salt);
		
		Assert.assertEquals(r, Passwords.encode(value, salt));
		
		Assert.assertTrue(Passwords.match(r, value, salt));
	}
	
	@Autowired
	private SysUserService userService; 
	
	@Test
	public void serviceAddUser() throws Exception {
		
		String username = "15158087185";
		String password = "123456";
		
		SysUser user = userService.add(username, password);
		
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
}

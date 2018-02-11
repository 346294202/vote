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
import com.leoyon.vote.user.User;
import com.leoyon.vote.user.UserService;
import com.leoyon.vote.user.dao.UserDao;
import com.leoyon.vote.util.MapBuilder;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .MOCK)
public class UserTests {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private DbUtil dbUtil;
	
	@Before
	public void setUp() throws Exception {
		dbUtil.clear(new String[]{
				"vote_user"
		});
	}
	
	@Test
	public void daoAddUser() throws Exception {
		
		User user = new User();
		user.setMobile("12345678");
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
	private UserService userService; 
	
	@Test
	public void serviceAddUser() throws Exception {
		
		String mobile = "15158087185";
		String password = "123456";
		
		User user = userService.add(mobile, password);
		
		Assert.assertNotNull(user);
		Assert.assertEquals(mobile, user.getMobile());
		Assert.assertTrue(user.matchPassword(password));
	}
	
	@Test
	public void serviceGetUser() throws Exception {
		String mobile = "15158087185";
		String password = "123456";
		String salt = "sdasdasd";
		
		dbUtil.insert("vote_user", new MapBuilder<String,Object>()
				.put("mobile", mobile)
				.put("password", password)
				.put("salt", salt)
				.build());
		
		User user = userService.get(mobile);
		Assert.assertNotNull(user);
		Assert.assertEquals(mobile, user.getMobile());
		
	}
}

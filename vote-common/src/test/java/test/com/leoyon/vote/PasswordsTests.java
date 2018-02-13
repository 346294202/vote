package test.com.leoyon.vote;

import org.junit.Assert;
import org.junit.Test;

import com.leoyon.vote.Passwords;

public class PasswordsTests {

	@Test
	public void testPasswords() throws Exception {
		String value = "123456";
		String salt = "wqdszc";
		
		String r = Passwords.encode(value, salt);
		
		System.out.println(r);
		
		Assert.assertEquals(r, Passwords.encode(value, salt));
		
		Assert.assertTrue(Passwords.match(r, value, salt));
	}
}

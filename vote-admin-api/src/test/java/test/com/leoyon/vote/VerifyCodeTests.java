package test.com.leoyon.vote;

import org.junit.Assert;
import org.junit.Test;

import com.leoyon.vote.user.VerifyCode;

public class VerifyCodeTests {

	@Test
	public void verify() throws InterruptedException {
		
		String code = "gfT6";
		
		VerifyCode vc = VerifyCode.encode(code, 0);
		
		System.out.println(vc);
		
		Assert.assertTrue(VerifyCode.verify(vc.getKey(), code));
		
		vc = VerifyCode.encode(code, 100);
		
		Thread.sleep(105);
		
		Assert.assertFalse(VerifyCode.verify(vc.getKey(), code));
	}
}

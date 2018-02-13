package test.com.leoyon.vote;

import org.junit.Assert;
import org.junit.Test;

import com.leoyon.vote.util.Wet;

public class WetTests {

	@Test
	public void test() {
		String s = "sadkjncjoawienf213sad&8hnKJNLpiu";
		String s1 = Wet.encode(s);
		String s2 = Wet.decode(s1);
		Assert.assertEquals(s, s2);
	}
}

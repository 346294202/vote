package test.com.leoyon.vote;

import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.util.Date;

import org.junit.Test;

import com.leoyon.vote.util.Parses;

public class ParsesTest {

	@Test
	public void parseDate() throws ParseException {
		//http://39.106.97.131:8082/basic/user/house?realName=&mobile=&dateCreateStart=2018-02-01+17%3A27%3A56&dateCreateEnd=2018-02-27+17%3A27%3A58&ownerStatus=&page=0&psize=10
		
		Date d = Parses.parse("2018-02-01 12:00:00", Date.class, null);
		assertNotNull(d);
	}
}

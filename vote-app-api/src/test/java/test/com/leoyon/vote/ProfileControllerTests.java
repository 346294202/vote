package test.com.leoyon.vote;

import java.text.ParseException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.user.UserRealInfo;
import com.leoyon.vote.util.Dates;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class ProfileControllerTests extends BaseWebTests {
		
	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		addDefUserDb();
		setToken(defUid);
	}
	
	@Test
	public void setUserRealInfo() throws ParseException {
		
		UserRealInfo entity = new UserRealInfo();
		entity.setRealName("李元霸");
		entity.setBirthday(Dates.parseTime("1987-02-03 16:00:00"));
		entity.setEmail("lyb@tdynasty.gov");
		entity.setIdNumber("12345678");
		
		JsonResponse r = restTemplate.postForObject("/profile/info/real", entity, JsonResponse.class);
		assertSucess(r);
	}
	
}

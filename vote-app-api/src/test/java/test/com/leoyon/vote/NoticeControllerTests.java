package test.com.leoyon.vote;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.util.M;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class NoticeControllerTests extends BaseWebTests {
		
	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		addDefUserDb();
		setToken(defUid);
		dbUtil.clear("property_notice");
		dbUtil.clear("user_notice");
	}
	
	@Test
	public void list() throws Exception {
		dbUtil.insert("property_notice", M.mapList(Arrays.asList("id", "area_id", "title", "content", "state", "delete", "create_person", "date_create")
				, Arrays.asList(1,2,3,4)
				, Arrays.asList(1,1,1,1)
				, Arrays.asList("通知1","通知2","通知3","通知4")
				, Arrays.asList("通知1","通知2","通知3","通知4")
				, Arrays.asList(1,1,1,2)
				, Arrays.asList(1,2,1,1)
				, Arrays.asList(1,2,1,1)
				, Arrays.asList(new Date(),new Date(),new Date(),new Date())
				));
		
		dbUtil.insert("user_notice", M.mapList(Arrays.asList("user_id","notice_id")
				, Arrays.asList(defUid)
				, Arrays.asList(1)
				));
		
		JsonResponse r = restTemplate.getForObject("/notice", JsonResponse.class);
		assertSucess(r);
		
		List<Map<String, Object>> items = (List<Map<String, Object>>) r.getItem("items");
		assertEquals(2, items.size());
		assertTrue(items.stream().anyMatch(i -> {
			return i.get("id").equals(1) && i.get("readed").equals(true);
		}));
		assertTrue(items.stream().anyMatch(i -> {
			return i.get("id").equals(3) && i.get("readed").equals(false);
		}));
	}
	
	@Test
	public void setreaded() throws Exception {
		dbUtil.insert("property_notice", M.mapList(Arrays.asList("id", "area_id", "title", "content", "state", "delete", "create_person", "date_create")
				, Arrays.asList(1,2,3,4)
				, Arrays.asList(1,1,1,1)
				, Arrays.asList("通知1","通知2","通知3","通知4")
				, Arrays.asList("通知1","通知2","通知3","通知4")
				, Arrays.asList(1,1,1,2)
				, Arrays.asList(1,2,1,1)
				, Arrays.asList(1,2,1,1)
				, Arrays.asList(new Date(),new Date(),new Date(),new Date())
				));
		
		JsonResponse r = restTemplate.postForObject("/notice/1/readed", null, JsonResponse.class);
		assertSucess(r);
		assertFalse(dbUtil.select("select * from user_notice where user_id=1 and notice_id=1").isEmpty());
		r = restTemplate.postForObject("/notice/1/readed", null, JsonResponse.class);
		assertSucess(r);
	}
	
	
}

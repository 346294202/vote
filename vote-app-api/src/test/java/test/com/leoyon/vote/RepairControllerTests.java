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
import com.leoyon.vote.picture.Picture;
import com.leoyon.vote.repair.Repair;
import com.leoyon.vote.util.M;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class RepairControllerTests extends BaseWebTests {
		
	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		addDefUserDb();
		setToken(defUid);
		dbUtil.clear("basic_repair");
		dbUtil.clear("basic_repair_picture");
		dbUtil.clear("basic_picture");
		dbUtil.clear("basic_house");
		dbUtil.clear("basic_area");
	}
	
	@Test
	public void list() throws Exception {
		dbUtil.insert("basic_area", M.mapList(Arrays.asList("id", "name")
				, Arrays.asList(1,2,3)
				, Arrays.asList(1,2,3)
				));
		dbUtil.insert("basic_house", M.mapList(Arrays.asList("id", "area_id", "building", "unit", "number")
				, Arrays.asList(1,2,3)
				, Arrays.asList(1,2,3)
				, Arrays.asList(1,2,3)
				, Arrays.asList(1,2,3)
				, Arrays.asList(1,2,3)				
				));
		dbUtil.insert("basic_picture", M.mapList(Arrays.asList("id", "url")
				, Arrays.asList(1,2,3)
				, Arrays.asList("a.jpg","b.jpg","c.jpg")
				));
		dbUtil.insert("basic_repair", M.mapList(Arrays.asList("id", "user_id", "address", "content", "status", "house_id")
				, Arrays.asList(1,2,3,4,5)
				, Arrays.asList(1,1,3,1,1)
				, Arrays.asList("地址1","地址2","地址3","地址4","地址5")
				, Arrays.asList("内容1","内容2","内容3","内容4","内容5")
				, Arrays.asList(1,1,1,2,2)
				, Arrays.asList(1,1,1,2,2)
				));
		
		dbUtil.insert("basic_repair_picture", M.mapList(Arrays.asList("repair_id", "picture_id")
				, Arrays.asList(1,2,3,4,5)
				, Arrays.asList(1,2,3,1,2)
				));
		
		JsonResponse r = restTemplate.getForObject("/repair", JsonResponse.class);
		assertSucess(r);
		List<Map<String, Object>> items = (List<Map<String, Object>>) r.getItem("items");
		
		assertEquals(4, items.size());
		
		assertTrue(items.stream().anyMatch(i -> {
			return i.get("status").equals(2) && i.get("address").equals("地址4");
		}));
	}
	
	@Test
	public void add() throws Exception {
		dbUtil.insert("basic_picture", M.mapList(Arrays.asList("id", "url")
				, Arrays.asList(1,2,3)
				, Arrays.asList("a.jpg","b.jpg","c.jpg")
				));
		
		Picture pic1 = new Picture();
		pic1.setId(1l);
		Picture pic2 = new Picture();
		pic2.setId(2l);
		
		Repair entity = new Repair();
		entity.setAddress("五大花园12栋2单元103室");
		entity.setContent("厕所堵了");
		entity.setPictures(Arrays.asList(pic1, pic2));
		
		JsonResponse r = restTemplate.postForObject("/repair", entity, JsonResponse.class);
		assertSucess(r);
		
		assertFalse(dbUtil.select("select * from basic_repair where address='五大花园12栋2单元103室'").isEmpty());
		assertEquals(2, dbUtil.select("select * from basic_repair_picture").size());
	}
	
}

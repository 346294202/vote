package test.com.leoyon.vote;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leoyon.vote.address.Address;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.product.PaymentRequest;
import com.leoyon.vote.product.PaymentRequestItem;
import com.leoyon.vote.user.UserHouse;
import com.leoyon.vote.util.M;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class HouseControllerTests extends BaseWebTests {
		
	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		addDefUserDb();
		setToken(defUid);
		dbUtil.clear("basic_area");
		dbUtil.clear("basic_house");
		dbUtil.clear("basic_user_house");
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
		dbUtil.insert("basic_user_house", M.mapList(Arrays.asList("user_id", "house_id", "owner_status", "owner_reason")
				, Arrays.asList(defUid,defUid,defUid)
				, Arrays.asList(1,2,3)
				, Arrays.asList(1,2,3)
				, Arrays.asList("",2,"错误数据")
				));
		
		JsonResponse r = restTemplate.getForObject("/house", JsonResponse.class);
		assertSucess(r);
		
		List<Map<String, Object>> items = (List<Map<String, Object>>) r.getItem("items");
		assertEquals(3, items.size());
		
	}
	
	@Test
	public void setHouse() throws Exception {
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
		
		UserHouse userHouse = new UserHouse();
		userHouse.setUserId(defUid);
		userHouse.setHouseId(1l);
		userHouse.setOwnerType(1);
		
		JsonResponse r = restTemplate.postForObject("/house", userHouse, JsonResponse.class);
		assertSucess(r);
		
		assertFalse(dbUtil.select("select * from basic_user_house where user_id=1 and house_id=1 and owner_type=1").isEmpty());
		
		userHouse.setOwnerType(2);
		 r = restTemplate.postForObject("/house", userHouse, JsonResponse.class);
		assertSucess(r);
		assertTrue(dbUtil.select("select * from basic_user_house where user_id=1 and house_id=1 and owner_type=1").isEmpty());
		assertFalse(dbUtil.select("select * from basic_user_house where user_id=1 and house_id=1 and owner_type=2").isEmpty());

		
	}
	
}

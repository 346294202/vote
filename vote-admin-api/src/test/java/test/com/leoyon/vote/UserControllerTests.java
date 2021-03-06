package test.com.leoyon.vote;

import static org.junit.Assert.assertFalse;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.user.UserHouse;
import com.leoyon.vote.util.M;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class UserControllerTests extends BaseWebTests {
	
	Long u1id = 1L;
	Long u2id = 2L;
	
	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();		
		setToken(defUID, 1000000);
		dbUtil.clear(new String[] {"basic_user", "basic_user_house", "basic_house", "basic_area"});
	}

	public void addUserHouses() throws Exception {
		List<Map<String, Object>> userhouses = Arrays.asList(
				M.map()
				.put("user_id", u1id)
				.put("house_id", 1L)
				.put("owner_status", 1)
				.put("owner_type", 1)
				.put("owner_reason", "")
				.put("owner_update_uid", null)
				.put("owner_update_time", null)
				.build(),
				M.map()
				.put("user_id", u1id)
				.put("house_id", 3L)
				.put("owner_status", 3)
				.put("owner_type", 1)
				.put("owner_reason", "owner_reason")
				.put("owner_update_uid", defUID)
				.put("owner_update_time", "2018-01-02")
				.build(),
				M.map()
				.put("user_id", u2id)
				.put("house_id", 2L)
				.put("owner_status", 2)
				.put("owner_type", 1)
				.put("owner_reason", "")
				.put("owner_update_uid", defUID)
				.put("owner_update_time", "2018-01-01")
				.build()
				);
		dbUtil.insert("basic_user_house", userhouses);
	}

	public void addHouses() throws Exception {
		dbUtil.insert("basic_area", M.map()
				.put("id", 1L)
				.put("name", "area1")
				.build());
		
		List<Map<String, Object>> houses = Arrays.asList(
				M.map()
				.put("id", 1L)
				.put("area_id", 1L)
				.put("building", 1)
				.put("unit", 1)
				.put("number", 1001)
				.put("house_type", 1)
				.put("house_status", 1)
				.put("remark", "待出租")
				.put("owner_name", "house1 owner_name")
				.put("owner_phone", "house1 owner_phone")
				.build(),
				M.map()
				.put("id", 2L)
				.put("area_id", 1L)
				.put("building", 1)
				.put("unit", 1)
				.put("number", 1002)
				.put("house_type", 1)
				.put("house_status", 1)
				.put("remark", "待出租")
				.put("owner_name", "house2 owner_name")
				.put("owner_phone", "house2 owner_phone")
				.build(),
				M.map()
				.put("id", 3L)
				.put("area_id", 1L)
				.put("building", 1)
				.put("unit", 1)
				.put("number", 1003)
				.put("house_type", 1)
				.put("house_status", 1)
				.put("remark", "待出租")
				.put("owner_name", "house3 owner_name")
				.put("owner_phone", "house3 owner_phone")
				.build()
				);
		dbUtil.insert("basic_house", houses);
	}

	public void addUsers() throws Exception {
		List<Map<String, Object>> users = Arrays.asList(
				M.map()
				.put("id", u1id)
				.put("mobile", "15158087185")
				.put("password", "111")
				.put("salt", "111")
				.put("real_name", "王大毛")
				.build(),
				M.map()
				.put("id", u2id)
				.put("mobile", "17381971975")
				.put("password", "111")
				.put("salt", "111")
				.put("real_name", "wj221")
				.build()
				);
		dbUtil.insert("basic_user", users);
	}
	
	@Test
	public void updateHouse() throws Exception {
		addUsers();
		addHouses();
		addUserHouses();
		
		UserHouse uh = new UserHouse();
		uh.setOwnerStatus(3);
		uh.setOwnerReason("照片模糊");
		
		JsonResponse r = restTemplate.postForObject("/basic/user/1/house/1", uh, JsonResponse.class);
		assertSucess(r);
		
		assertFalse(dbUtil.select("select * from basic_user_house where user_id=1 and house_id=1 and owner_status=3 and owner_reason='照片模糊'").isEmpty());
	}
	
	@Test
	@Ignore
	public void bug_findHouseWithDate() throws Exception {
		addUsers();
		addHouses();
		addUserHouses();
		
		JsonResponse r = restTemplate.getForObject("/basic/user/house?realName=&mobile=&dateCreateStart=2018-02-01+17%3A27%3A56&dateCreateEnd=2018-02-27+17%3A27%3A58&ownerStatus=&page=0&psize=10", JsonResponse.class);
		assertSucess(r);
	}

	@Test
	public void findOwner() throws Exception {
		addUsers();

		JsonResponse r = restTemplate.getForObject("/basic/user/house", JsonResponse.class);
		assertSucess(r);
		List<Map<String, Object>> list = (List<Map<String, Object>>) r.getItem("items");
		Assert.assertTrue(list.isEmpty());		

		addHouses();
		addUserHouses();
		
		r = restTemplate.getForObject("/basic/user/house", JsonResponse.class);
		assertSucess(r);
		list = (List<Map<String, Object>>) r.getItem("items");
		Assert.assertEquals(3, list.size());
		
		r = restTemplate.getForObject("/basic/user/house?ownerStatus={ownerStatus}", JsonResponse.class,
				M.map().put("ownerStatus", 2).build());
		assertSucess(r);
		list = (List<Map<String, Object>>) r.getItem("items");
		Assert.assertEquals(1, list.size());
		Assert.assertEquals(defUname, list.get(0).get("ownerUpdateUname"));
		Assert.assertEquals(u2id, Long.valueOf(list.get(0).get("userId")+""));
	}
}

package test.com.leoyon.vote;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leoyon.vote.api.Error;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.house.House;
import com.leoyon.vote.util.M;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class HouseControllerTests extends BaseWebTests {
	
	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		dbUtil.clear("basic_user");
		dbUtil.clear("basic_house");
		dbUtil.clear("basic_user_house");
		setToken(defUID);
	}

	@Test
	public void find() throws Exception {
		
		List<Map<String, Object>> list = Arrays.asList(
				M.map()
				.put("id", 1L)
				.put("area_id", 1L)
				.put("building", 1)
				.put("unit", 1)
				.put("number", 1001)
				.put("house_type", 1)
				.put("house_status", 1)
				.put("remark", "待出租")
				.build(),
				M.map()
				.put("id", 2L)
				.put("area_id", 1L)
				.put("building", 1)
				.put("unit", 2)
				.put("number", 1001)
				.put("house_type", 1)
				.put("house_status", 1)
				.put("remark", "待出租")
				.build(),
				M.map()
				.put("id", 3L)
				.put("area_id", 2L)
				.put("building", 1)
				.put("unit", 1)
				.put("number", 1001)
				.put("house_type", 1)
				.put("house_status", 1)
				.put("remark", "待出租")
				.build(),
				M.map()
				.put("id", 4L)
				.put("area_id", 2L)
				.put("building", 1)
				.put("unit", 2)
				.put("number", 1001)
				.put("house_type", 1)
				.put("house_status", 1)
				.put("remark", "待出租")
				.build(),
				M.map()
				.put("id", 5L)
				.put("area_id", 3L)
				.put("building", 1)
				.put("unit", 1)
				.put("number", 1001)
				.put("house_type", 1)
				.put("house_status", 1)
				.put("remark", "待出租")
				.build()
				);
		
		dbUtil.insert("basic_house", list);
		
		JsonResponse r = restTemplate.getForObject("/basic/house", JsonResponse.class);
		assertSucess(r);
		Assert.assertEquals(list.size(), ((List<?>)r.getMap().get("items")).size());
		
		dbUtil.insert("basic_user", Arrays.asList(
				M.map()
				.put("id", 1L)
				.put("mobile", "15158778921")
				.put("password", "1")
				.put("salt", "1")
				.build(),
				M.map()
				.put("id", 2L)
				.put("mobile", "13920341977")
				.put("password", "1")
				.put("salt", "1")
				.build()
				));
		
		dbUtil.insert("basic_user_house", Arrays.asList(
				M.map()
				.put("user_id", 1L)
				.put("house_id", 1L)
				.build(),
				M.map()
				.put("user_id", 1L)
				.put("house_id", 2L)
				.build(),
				M.map()
				.put("user_id", 2L)
				.put("house_id", 3L)
				.build()
				));
		
		r = restTemplate.getForObject("/basic/house?ownerMobile={ownerMobile}", JsonResponse.class, M.map()
				.put("ownerMobile", "15158778921")
				.build());
		assertSucess(r);
		Assert.assertEquals(2, ((List<?>)r.getMap().get("items")).size());
		
		r = restTemplate.getForObject("/basic/house?ownerMobile=&areaId=", JsonResponse.class);
		assertSucess(r);
		Assert.assertEquals(list.size(), ((List<?>)r.getMap().get("items")).size());
	}
	
	@Test
	public void set() throws Exception {
		House house = new House();
		house.setAreaId(1L);
		house.setBuilding(1);
		house.setUnit(2);
		house.setNumber(3003);
		house.setHouseType(1);
		house.setHouseStatus(1);
		house.setRemark("新增");
		
		JsonResponse r = restTemplate.postForObject("/basic/house", house, JsonResponse.class);
		assertSucess(r);
		
		List<Map<String, Object>> list = dbUtil.select("select * from basic_house");
		Assert.assertEquals("新增", list.get(0).get("remark"));
		Long id = (Long) list.get(0).get("id");
		
		house = new House();
		house.setRemark("修改");
		r = restTemplate.postForObject("/basic/house/"+id, house, JsonResponse.class);
		assertSucess(r);
		
		list = dbUtil.select("select * from basic_house");
		Assert.assertEquals("修改", list.get(0).get("remark"));
	}
	
	@Test
	public void addCheck() throws Exception {

		List<Map<String, Object>> list = Arrays.asList(
				M.map()
				.put("id", 1L)
				.put("area_id", 1L)
				.put("building", 1)
				.put("unit", 1)
				.put("number", 1001)
				.put("house_type", 1)
				.put("house_status", 1)
				.put("remark", "待出租")
				.build()
				);
		
		dbUtil.insert("basic_house", list);
		
		House house = new House();
		house.setAreaId(1L);
		house.setBuilding(1);
		house.setUnit(1);
		house.setNumber(1001);
		house.setHouseType(1);
		house.setHouseStatus(1);
		house.setRemark("新增");
		
		JsonResponse r = restTemplate.postForObject("/basic/house", house, JsonResponse.class);
		assertEquals(Error.UNKNOWN_EXCEPT.getValue(), r.getCode());

		
	}
	
	@Test
	public void checkUpfate() throws Exception {

		List<Map<String, Object>> list = Arrays.asList(
				M.map()
				.put("id", 1L)
				.put("area_id", 1L)
				.put("building", 1)
				.put("unit", 1)
				.put("number", 1001)
				.put("house_type", 1)
				.put("house_status", 1)
				.put("remark", "待出租")
				.build(),
				M.map()
				.put("id", 2L)
				.put("area_id", 1L)
				.put("building", 1)
				.put("unit", 2)
				.put("number", 1001)
				.put("house_type", 1)
				.put("house_status", 1)
				.put("remark", "待出租")
				.build()
				);
		
		dbUtil.insert("basic_house", list);
		
		
		House house = new House();
		house.setAreaId(1L);
		house.setBuilding(1);
		house.setUnit(1);
		house.setNumber(1001);
		house.setHouseType(1);
		house.setHouseStatus(1);
		house.setRemark("新增");
		
		JsonResponse r = restTemplate.postForObject("/basic/house/2", house, JsonResponse.class);
		assertEquals(Error.UNKNOWN_EXCEPT.getValue(), r.getCode());
	}
}

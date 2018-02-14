package test.com.leoyon.vote;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
		dbUtil.clear("vote_house");
		setToken(defUID);
	}

	@Test
	public void find() throws Exception {
		
		List<Map<String, Object>> list = Arrays.asList(
				M.map()
				.put("area_id", 1L)
				.put("building", 1)
				.put("unit", 1)
				.put("number", 1001)
				.put("house_type", 1)
				.put("house_status", 1)
				.put("remark", "待出租")
				.build(),
				M.map()
				.put("area_id", 1L)
				.put("building", 1)
				.put("unit", 2)
				.put("number", 1001)
				.put("house_type", 1)
				.put("house_status", 1)
				.put("remark", "待出租")
				.build(),
				M.map()
				.put("area_id", 2L)
				.put("building", 1)
				.put("unit", 1)
				.put("number", 1001)
				.put("house_type", 1)
				.put("house_status", 1)
				.put("remark", "待出租")
				.build(),
				M.map()
				.put("area_id", 2L)
				.put("building", 1)
				.put("unit", 2)
				.put("number", 1001)
				.put("house_type", 1)
				.put("house_status", 1)
				.put("remark", "待出租")
				.build(),
				M.map()
				.put("area_id", 3L)
				.put("building", 1)
				.put("unit", 1)
				.put("number", 1001)
				.put("house_type", 1)
				.put("house_status", 1)
				.put("remark", "待出租")
				.build()
				);
		
		dbUtil.insert("vote_house", list);
		
		JsonResponse r = restTemplate.getForObject("/house", JsonResponse.class);
		assertSucess(r);
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
		
		JsonResponse r = restTemplate.postForObject("/house", house, JsonResponse.class);
		assertSucess(r);
		
		List<Map<String, Object>> list = dbUtil.select("select * from vote_house");
		Assert.assertEquals("新增", list.get(0).get("remark"));
		Long id = (Long) list.get(0).get("id");
		
		house = new House();
		house.setRemark("修改");
		r = restTemplate.postForObject("/house/"+id, house, JsonResponse.class);
		assertSucess(r);
		
		list = dbUtil.select("select * from vote_house");
		Assert.assertEquals("修改", list.get(0).get("remark"));
	}
}

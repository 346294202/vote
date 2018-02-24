package test.com.leoyon.vote;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.area.Area;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class AreaControllerTests extends BaseWebTests {

	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		setToken(defUID);
		dbUtil.clear("basic_area");
	}
	
	@Test
	public void find() {
		JsonResponse r = restTemplate.getForObject("/basic/area", JsonResponse.class);
		assertSucess(r);
	}
	
	@Test
	public void add() throws Exception {
		
		Area entity = new Area();
		entity.setName("金蓝半岛");
		entity.setAddress("东坡路");
		entity.setDeveloper("frftgrejgbjd");
		entity.setService("frftgrejgbjd");
		entity.setRemark("frftgrejgbjd");
		
		JsonResponse r = restTemplate.postForObject("/basic/area", entity, JsonResponse.class);
		assertSucess(r);
		
		List<Map<String, Object>> list = dbUtil.select("select * from basic_area");
		Long id = (Long) list.get(0).get("id");
		
		entity = new Area();
		entity.setName("天际湾");
		entity.setAddress("东坡路");
		
		r = restTemplate.postForObject("/basic/area/"+id, entity, JsonResponse.class);
		assertSucess(r);
		
		list = dbUtil.select("select * from basic_area");
		Assert.assertEquals(entity.getName(), list.get(0).get("name"));
		Assert.assertEquals(entity.getAddress(), list.get(0).get("address"));
	}
}

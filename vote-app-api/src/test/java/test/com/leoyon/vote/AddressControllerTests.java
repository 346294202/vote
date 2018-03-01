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
import com.leoyon.vote.util.M;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class AddressControllerTests extends BaseWebTests {
		
	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		addDefUserDb();
		setToken(defUid);
		dbUtil.clear("basic_user_address");
	}
	
	@Test
	public void byUser() throws Exception {
		dbUtil.insert("basic_user_address", M.mapList(Arrays.asList("user_id", "longitude", "address", "def", "deleted")
				, Arrays.asList(1,1,1,2,2)
				, Arrays.asList(120,130,111,140,150)
				, Arrays.asList("王府井","春熙路","阿萨", "三圣乡","天府广场")
				, Arrays.asList(0,1,0,1,0)
				, Arrays.asList(1,1,2,1,1)
				));
		
		JsonResponse r = restTemplate.getForObject("/address", JsonResponse.class);
		assertSucess(r);
		List<Map<String, Object>> list = (List<Map<String, Object>>) r.getItem("items");
		assertEquals(2, list.size());
		assertTrue(list.stream().anyMatch(i -> {
			return i.get("address").equals("王府井") && i.get("def").equals(false);
		}));
		assertTrue(list.stream().anyMatch(i -> {
			return i.get("address").equals("春熙路") && i.get("def").equals(true);
		}));
	}
	
	@Test
	public void delete() throws Exception {
		dbUtil.insert("basic_user_address", M.mapList(Arrays.asList("id", "user_id", "longitude", "address", "def", "deleted")
				, Arrays.asList(1,2,3,4,5)
				, Arrays.asList(1,1,1,2,2)
				, Arrays.asList(120,130,111,140,150)
				, Arrays.asList("王府井","春熙路","阿萨", "三圣乡","天府广场")
				, Arrays.asList(0,1,0,1,0)
				, Arrays.asList(1,1,2,1,1)
				));
		
		restTemplate.delete("/address/1");
		
		assertFalse(dbUtil.select("select * from basic_user_address where id=1 and deleted=2;").isEmpty());
	}
	
	@Test
	public void add() throws Exception {
		Address entity = new Address();
		entity.setAddress("春熙路");
		entity.setContacts("李元霸");
		
		JsonResponse r = restTemplate.postForObject("/address", entity, JsonResponse.class);
		assertSucess(r);
		
		assertFalse(dbUtil.select("select * from basic_user_address where user_id=1 and address='春熙路';").isEmpty());
	}
	
	@Test
	public void update() throws Exception {
		dbUtil.insert("basic_user_address", M.mapList(Arrays.asList("id", "user_id", "longitude", "address", "def", "deleted")
				, Arrays.asList(1,2,3,4,5)
				, Arrays.asList(1,1,1,2,2)
				, Arrays.asList(120,130,111,140,150)
				, Arrays.asList("王府井","春熙路","阿萨", "三圣乡","天府广场")
				, Arrays.asList(0,1,0,1,0)
				, Arrays.asList(1,1,2,1,1)
				));
		
		Address entity = new Address();
		entity.setAddress("春熙路");
		entity.setContacts("李元霸");
		entity.setDef(true);
		
		JsonResponse r = restTemplate.postForObject("/address/1", entity, JsonResponse.class);
		assertSucess(r);
		
		assertFalse(dbUtil.select("select * from basic_user_address where id=1 and address='春熙路' and def=1;").isEmpty());
		assertTrue(dbUtil.select("select * from basic_user_address where id=2 and def=1;").isEmpty());
		
	}
	
}

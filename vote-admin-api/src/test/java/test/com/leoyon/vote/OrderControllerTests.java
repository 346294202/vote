package test.com.leoyon.vote;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.user.VerifyCode;
import com.leoyon.vote.util.M;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class OrderControllerTests extends BaseWebTests {

	@Before
	public void setUp() throws Exception {
		super.setUp();
		setToken(defUID);
		dbUtil.clear(new String[]{
				"basic_order", "basic_user", "basic_user_house", "basic_house", "basic_product", "basic_product_spec"
		});
	}
	
	@Test
	public void find() throws Exception {
		
		List<Map<String, Object>> users = Arrays.asList(
				M.map()
				.put("id", 1L)
				.put("mobile", "111")
				.put("password", "111")
				.put("salt", "111")
				.build()
				);
		
		dbUtil.insert("basic_user", users);
		
		List<Map<String, Object>> houses = Arrays.asList(
				M.map()
				.put("area_id", 1L)
				.put("building", 1)
				.put("unit", 1)
				.put("number", 1)
				.build()
				);
		
		dbUtil.insert("basic_house", houses);
		
		List<Map<String, Object>> userHouses = Arrays.asList(
				M.map()
				.put("user_id", 1L)
				.put("house_id", 1L)
				.put("owner_status", 2)
				.build()
				);
		dbUtil.insert("basic_user_house", userHouses);
		
		List<Map<String, Object>> products = Arrays.asList(
				M.map()
				.put("id", 1L)
				.put("type", 1)
				.put("name", "P1")
				.build()
				);
		dbUtil.insert("basic_product", products);
		
		List<Map<String, Object>> productSpecs = Arrays.asList(
				M.map()
				.put("id", 1L)
				.put("product_id", 1L)
				.put("name", "PS1")
				.put("price", 12.5)
				.build()
				);
		dbUtil.insert("basic_product_spec", productSpecs);
		
		List<Map<String, Object>> orders = Arrays.asList(
				M.map()
				.put("id", 1L)
				.put("product_id", 1L)
				.put("product_spec_id", 1L)
				.put("count", 3)
				.put("price", 12.5)
				.put("user_id", 1L)
				.put("address", "地址")
				.build()
				);
		
		dbUtil.insert("basic_order", orders);
		
		JsonResponse r = restTemplate.getForObject("/basic/order", JsonResponse.class);
		assertSucess(r);
		
	}
}

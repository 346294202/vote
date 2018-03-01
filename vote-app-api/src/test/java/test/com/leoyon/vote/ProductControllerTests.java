package test.com.leoyon.vote;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.user.UserRealInfo;
import com.leoyon.vote.util.Dates;
import com.leoyon.vote.util.M;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class ProductControllerTests extends BaseWebTests {
		
	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		addDefUserDb();
		setToken(defUid);
		dbUtil.clear("basic_product");
		dbUtil.clear("basic_product_picture");
		dbUtil.clear("basic_product_spec");
		dbUtil.clear("basic_order");
		dbUtil.clear("basic_picture");
	}
	
	@Test
	public void list() throws Exception {
		
		dbUtil.insert("basic_picture", M.mapList(Arrays.asList("id", "url")
				, Arrays.asList(1,2,3), Arrays.asList("1","2","3")));
		
		dbUtil.insert("basic_product", M.mapList(Arrays.asList("id", "type", "name")
				, Arrays.asList(1,2,3,4)
				, Arrays.asList(1,1,2,2)
				, Arrays.asList("产品1","产品2","产品3","产品4")));
		
		dbUtil.insert("basic_product_picture", M.mapList(Arrays.asList("picture_id", "product_id")
				, Arrays.asList(1,2,3,1,2,3)
				, Arrays.asList(1,2,3,4,1,2)));
		
		dbUtil.insert("basic_product_spec", M.mapList(Arrays.asList("id", "product_id", "name", "price")
				, Arrays.asList(1,2,3,4,5,6,7,8,9,10)
				, Arrays.asList(1,1,1,2,2,2,2,3,3,3)
				, Arrays.asList("规格1","规格2","规格3","规格4","规格5","规格6","规格7","规格8","规格9","规格10")
				, Arrays.asList(11,12,13,14,15,16,17,18,19,20)
				));
		
		JsonResponse r = restTemplate.getForObject("/product/1", JsonResponse.class);
		assertSucess(r);
		
		List<Map<String, Object>> list = (List<Map<String, Object>>) r.getItem("items");
		assertEquals(2, list.size());
		assertTrue(list.stream().flatMap(i -> {
			return ((List<Map<String, Object>>)i.get("specs")).stream();
		}).anyMatch(i -> {
			return i.get("name").equals("规格2");
		}));
		assertTrue(list.stream().flatMap(i -> {
			return ((List<Map<String, Object>>)i.get("pictures")).stream();
		}).anyMatch(i -> {
			return i.get("url").equals("1");
		}));
	}
	
}

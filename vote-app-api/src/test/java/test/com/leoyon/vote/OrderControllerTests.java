package test.com.leoyon.vote;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Ignore;
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
public class OrderControllerTests extends BaseWebTests {
		
	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		addDefUserDb();
		setToken(defUid);
		dbUtil.clear("basic_order");
		dbUtil.clear("basic_product");
		dbUtil.clear("basic_product_spec");
		dbUtil.clear("basic_payment");
	}
	
	@Test
	public void list() throws Exception {
		dbUtil.insert("basic_product", M.mapList(Arrays.asList("id", "type", "name")
				, Arrays.asList(1,2,3,4)
				, Arrays.asList(1,1,1,1)
				, Arrays.asList(1,2,3,4)
				));
		dbUtil.insert("basic_product_spec", M.mapList(Arrays.asList("id", "product_id", "name", "price")
				, Arrays.asList(1,2,3,4,5,6)
				, Arrays.asList(1,2,3,1,2,3)
				, Arrays.asList(1,2,3,4,5,6)
				, Arrays.asList(1,2,3,4,5,6)
				));
		dbUtil.insert("basic_payment", M.mapList(Arrays.asList("id", "channel", "amount", "data")
				, Arrays.asList(1,2,3)
				, Arrays.asList(1,1,2)
				, Arrays.asList(1,1,2)
				, Arrays.asList(1,1,2)
				));
		dbUtil.insert("basic_order", M.mapList(
				Arrays.asList("product_id", "product_spec_id", "user_id", "count", "price", "address", "payment_id")
				, Arrays.asList(1,2,3)
				, Arrays.asList(1,2,3)
				, Arrays.asList(defUid,defUid,defUid)
				, Arrays.asList(1,3,3)
				, Arrays.asList(1,3,3)
				, Arrays.asList(1,3,3)
				, Arrays.asList(1,1,2)
				));
		
		JsonResponse r = restTemplate.getForObject("/order", JsonResponse.class);
		assertSucess(r);
		
	}
}

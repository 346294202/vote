package test.com.leoyon.vote;

import static org.junit.Assert.assertEquals;

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
import com.leoyon.vote.picture.Picture;
import com.leoyon.vote.product.Product;
import com.leoyon.vote.product.ProductSpec;
import com.leoyon.vote.user.VerifyCode;
import com.leoyon.vote.util.M;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class ProductControllerTests extends BaseWebTests {

	@Before
	public void setUp() throws Exception {
		super.setUp();
		setToken(defUID);
		dbUtil.clear(new String[] {
				"basic_picture", "basic_product", "basic_product_picture", "basic_product_spec"
		});
		dbUtil.insert("basic_picture", M.map()
				.put("id", 1L)
				.put("url", "/a/b/c/d.jpg")
				.build());
	}
	
	@Test
	public void find() throws Exception {
		
		dbUtil.insert("basic_product", M.map()
				.put("id", 1L)
				.put("name", "P1")
				.put("type", 1)
				.build());
		
		dbUtil.insert("basic_product_picture", M.map()
				.put("picture_id", 1L)
				.put("product_id", 1L)
				.build());
		
		dbUtil.insert("basic_product_spec", M.map()
				.put("id", 1L)
				.put("product_id", 1L)
				.put("name", "PS1")
				.put("price", 128.65)
				.build());
		
		
		JsonResponse r = restTemplate.getForObject("/basic/product", JsonResponse.class);
		assertSucess(r);
		List<Map<String, Object>> items = (List<Map<String, Object>>) r.getItem("items");
		assertEquals(1, items.size());
		
		List<Map<String, Object>> pictures = (List<Map<String, Object>>) items.get(0).get("pictures");
		assertEquals(1, pictures.size());
		assertEquals("/a/b/c/d.jpg", pictures.get(0).get("url"));
		
		List<Map<String, Object>> specs = (List<Map<String, Object>>) items.get(0).get("specs");
		assertEquals(1, specs.size());
		assertEquals("PS1", specs.get(0).get("name"));
	}
	
	@Test
	public void add() {
		
		Product p = new Product();
		p.setName("P1");
		p.setType(1);
		
		Picture pic = new Picture();
		pic.setId(1L);
		pic.setUrl("/a/b/c/d.jpg");
		
		p.setPictures(Arrays.asList(pic));
		
		ProductSpec spec = new ProductSpec();
		spec.setName("PS1");
		spec.setPrice(128.65);
		p.setSpecs(Arrays.asList(spec));
		
		JsonResponse r = restTemplate.postForObject("/basic/product", p, JsonResponse.class);
		assertSucess(r);
		
	}
	
	@Test
	public void update() throws Exception {
		dbUtil.insert("basic_product", M.map()
				.put("id", 1L)
				.put("name", "P1")
				.put("type", 1)
				.build());
		
		dbUtil.insert("basic_product_picture", M.map()
				.put("picture_id", 1L)
				.put("product_id", 1L)
				.build());
		
		dbUtil.insert("basic_product_spec", M.map()
				.put("id", 1L)
				.put("product_id", 1L)
				.put("name", "PS1")
				.put("price", 128.65)
				.build());
		
		Product p = new Product();
		p.setName("P1");
		p.setType(1);
		
		Picture pic = new Picture();
		pic.setId(1L);
		pic.setUrl("/a/b/c/d.jpg");
		
		p.setPictures(Arrays.asList(pic));
		
		ProductSpec spec = new ProductSpec();
		spec.setId(1L);
		spec.setName("PS2");
		spec.setPrice(133d);
		p.setSpecs(Arrays.asList(spec));
		
		JsonResponse r = restTemplate.postForObject("/basic/product/1", p, JsonResponse.class);
		assertSucess(r);
	}
	
}
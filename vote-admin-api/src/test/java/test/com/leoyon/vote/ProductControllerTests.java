package test.com.leoyon.vote;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.codehaus.jackson.JsonFactory;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.fasterxml.jackson.databind.deser.std.JsonNodeDeserializer;
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
	
	@Test
	@Ignore("检查前端数据为什么异常")
	public void add_debug() throws JsonParseException, IOException {
		ObjectMapper om = new ObjectMapper();
		JsonParser jp = om.getJsonFactory().createJsonParser("{\"name\":\"大肆发放\",\"desc\":\"111\",\"price_desc\":\"222\",\"so\":\"3\",\"remark\":\"\",\"specs\":[{\"name\":\"放大\",\"price\":\"44\",\"remark\":\"法大使馆\",\"so\":\"11\"}]}");
		
		JsonResponse r = restTemplate.postForObject("/basic/product", 
				jp.readValueAs(Product.class), 
				JsonResponse.class);
		assertSucess(r);
	}
	
	@Test
	public void update_debug() throws Exception {
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
		
		ObjectMapper om = new ObjectMapper();
		JsonParser jp = om.getJsonFactory().createJsonParser("{\"type\":1,\"name\":\"fdsaf111111\",\"desc\":\"fdsaf\",\"priceDesc\":\"fsaf\",\"so\":\"12\",\"remark\":\"fdsa\",\"specs\":[{\"id\":3,\"productId\":3,\"name\":\"123fds\",\"price\":31,\"remark\":\"fds\",\"so\":123},{\"id\":4,\"productId\":3,\"name\":\"dsaf\",\"price\":1233,\"remark\":\"ddsfd\",\"so\":3214}],\"pictures\":[]}");
		
		JsonResponse r = restTemplate.postForObject("/basic/product/1", 
				jp.readValueAs(Product.class), 
				JsonResponse.class);
		assertSucess(r);
	}
}

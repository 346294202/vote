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

import com.leoyon.geom.Point;
import com.leoyon.vote.advice.Advice;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.picture.Picture;
import com.leoyon.vote.util.M;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class BusinessControllerTests extends BaseWebTests {
		
	@Before
	@Override
	public void setUp() throws Exception {
		super.setUp();
		addDefUserDb();
		setToken(defUid);
		dbUtil.clear("biz_business");
		dbUtil.clear("biz_business_picture");
		dbUtil.clear("basic_picture");
	}
	
	@Test
	public void list() throws Exception {
		dbUtil.insert("basic_picture", M.mapList(Arrays.asList("id", "url")
				, Arrays.asList(1,2,3)
				, Arrays.asList("a.jpg","b.jpg","c.jpg")
				));
		dbUtil.insert("biz_business", M.mapList(Arrays.asList("id", "business_name", "business_type", "center")
				, Arrays.asList(1,2,3,4,5)
				, Arrays.asList("成都世纪城天堂洲际大饭店","泰合索菲特大饭店","成都城市名人酒店","民航大厦宾馆","花园宾馆(走马街店)")
				, Arrays.asList(1,2,3,4,5)
				, Arrays.asList(
						new Point(104.072145, 30.559748)
						, new Point(104.068789, 30.647298)
						, new Point(104.065454, 30.654155)
						, new Point(104.076558, 30.657973)
						, new Point(104.075057, 30.652671)
						)
				));
		
		dbUtil.insert("biz_business_picture", M.mapList(Arrays.asList("business_id", "picture_id")
				, Arrays.asList(1,2,3,4,5)
				, Arrays.asList(1,2,3,1,2)
				));
		
		JsonResponse r = restTemplate.getForObject("/business?lng={lng}&lat={lat}", JsonResponse.class
				, M.map().put("lng", 104.065454).put("lat", 30.654155).build());
		assertSucess(r);
		List<Map<String, Object>> items = (List<Map<String, Object>>) r.getItem("items");
		
		assertEquals(5, items.size());
		
		assertTrue(items.stream().anyMatch(i -> {
			return i.get("id").equals(3) && (Double)i.get("distance") < 1;
		}));
	}

}

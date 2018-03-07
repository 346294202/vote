package test.com.leoyon.vote;


import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.business.BizBusiness;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Thinkpad on 2018/2/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class BizBusinessControllerTests extends BaseWebTests {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        setToken(defUID);
        dbUtil.clear(new String[] {
                "biz_business"
        });
    }



    @Test
    public void add() throws Exception {
        BizBusiness p=new BizBusiness();
        p.setId(1L);
        p.setBusinessName("123");
        p.setBusinessType(1);
        p.setState(1);
        p.setIeType(2);
        p.setLat("30.67");
        p.setLng("104.06");
            JsonResponse r = restTemplate.postForObject("/biz/business/add",p, JsonResponse.class);
            assertSucess(r);
    }

    @Test
    public void find() throws Exception {
        /*FindSysDictionaryRequest  p=new FindSysDictionaryRequest("dqwd",2,1,2);*/
        JsonResponse r = restTemplate.getForObject("/biz/business/find?ieType=1&page=0&psize=10", JsonResponse.class);
        assertSucess(r);
        /*List<Map<String, Object>> items = (List<Map<String, Object>>) r.getItem("items");*/
        System.out.println(/*items.toString()*/r.getItem("items"));
    }

    @Test
    public void findById() throws Exception {
        /*FindSysDictionaryRequest  p=new FindSysDictionaryRequest("dqwd",2,1,2);*/
        JsonResponse r = restTemplate.getForObject("/biz/business/findById/1", JsonResponse.class);
        assertSucess(r);
        /*List<Map<String, Object>> items = (List<Map<String, Object>>) r.getItem("items");*/
        System.out.println(/*items.toString()*/r.getItem("item"));
    }


    @Test
    public void update() throws Exception {
        BizBusiness p=new BizBusiness();
        p.setBusinessName("111");
        p.setBusinessType(1);
        p.setState(1);
        p.setLat("30.67");
        p.setLng("104.06");
        JsonResponse r = restTemplate.postForObject("/biz/business/update/1",p, JsonResponse.class);
        assertSucess(r);
    }

    @Test
    public void delete() throws Exception {
       restTemplate.delete("/biz/business/delete/1",1L);
    }

}

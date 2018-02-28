package test.com.leoyon.vote;

import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.notice.PropertyNotice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Thinkpad on 2018/2/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class PropertyNoticeControllerTests extends BaseWebTests{

    @Before
    public void setUp() throws Exception {
        super.setUp();
        setToken(defUID);
        //dbUtil.clear("sys_dictionary");
    }

    @Test
    public void add() throws Exception {
        PropertyNotice p = new PropertyNotice();
        p.setTitle("123");
        p.setAreaId(91L);
        p.setState(1);
        p.setContent("123123213213");
        JsonResponse r = restTemplate.postForObject("/property/notice/add", p, JsonResponse.class);
        assertSucess(r);
    }

    @Test
    public void find() throws Exception {
        /*FindSysDictionaryRequest  p=new FindSysDictionaryRequest("dqwd",2,1,2);*/
        JsonResponse r = restTemplate.getForObject("/property/notice/find?page=0&psize=10", JsonResponse.class);
        assertSucess(r);
        /*List<Map<String, Object>> items = (List<Map<String, Object>>) r.getItem("items");*/
        System.out.println(/*items.toString()*/r.getItem("items"));
    }

    @Test
    public void findById() throws Exception {
        /*FindSysDictionaryRequest  p=new FindSysDictionaryRequest("dqwd",2,1,2);*/
        JsonResponse r = restTemplate.getForObject("/property/notice/findById/2", JsonResponse.class);
        assertSucess(r);
        /*List<Map<String, Object>> items = (List<Map<String, Object>>) r.getItem("items");*/
        System.out.println(/*items.toString()*/r.getItem("item"));
    }

    @Test
    public void findAll() throws Exception {
        /*FindSysDictionaryRequest  p=new FindSysDictionaryRequest("dqwd",2,1,2);*/
        JsonResponse r = restTemplate.getForObject("/property/notice/findAll", JsonResponse.class);
        assertSucess(r);
        /*List<Map<String, Object>> items = (List<Map<String, Object>>) r.getItem("items");*/
        System.out.println(/*items.toString()*/r.getItem("items"));
    }

    @Test
    public void update() throws Exception {
        PropertyNotice p = new PropertyNotice();
        p.setTitle("1111");
        p.setAreaId(91L);
        p.setState(2);
        p.setContent("1111");
        JsonResponse r = restTemplate.postForObject("/property/notice/update/1",p, JsonResponse.class);
        assertSucess(r);
    }

    @Test
    public void delete() throws Exception {
        restTemplate.delete("/property/notice/delete/1",1L);
    }

}

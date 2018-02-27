package test.com.leoyon.vote;

import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.dictionary.SysDictionary;
import com.leoyon.vote.dynamics.Release;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Thinkpad on 2018/2/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class ReleaseControllerTests  extends BaseWebTests{

    @Before
    public void setUp() throws Exception {
        super.setUp();
        setToken(defUID);
        //dbUtil.clear("sys_dictionary");
    }

    @Test
    public void add() throws Exception {
        Release p = new Release();
        p.setReleaseTitle("123");
        p.setUrl("321");
        p.setState(1);
        p.setContent("123123213213");
        JsonResponse r = restTemplate.postForObject("/dynamics/release/add", p, JsonResponse.class);
        assertSucess(r);
    }

    @Test
    public void find() throws Exception {
        /*FindSysDictionaryRequest  p=new FindSysDictionaryRequest("dqwd",2,1,2);*/
        JsonResponse r = restTemplate.getForObject("/dynamics/release/find?pageNum=1&pageSize=10", JsonResponse.class);
        assertSucess(r);
        /*List<Map<String, Object>> items = (List<Map<String, Object>>) r.getItem("items");*/
        System.out.println(/*items.toString()*/r.getItem("items"));
    }

    @Test
    public void update() throws Exception {
        Release p = new Release();
        p.setReleaseTitle("111");
        p.setUrl("111");
        p.setState(1);
        p.setContent("111");
        JsonResponse r = restTemplate.postForObject("/dynamics/release/update/1",p, JsonResponse.class);
        assertSucess(r);
    }

    @Test
    public void delete() throws Exception {
        restTemplate.delete("/dynamics/release/delete/1",1L);
    }

}

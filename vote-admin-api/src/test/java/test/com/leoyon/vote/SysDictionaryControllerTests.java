package test.com.leoyon.vote;

import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.dictionary.FindSysDictionaryRequest;
import com.leoyon.vote.dictionary.SysDictionary;
import com.leoyon.vote.util.M;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import java.util.*;

/**
 * Created by Thinkpad on 2018/2/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Application4Test.class},webEnvironment = SpringBootTest.WebEnvironment
        .RANDOM_PORT)
public class SysDictionaryControllerTests extends BaseWebTests {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        setToken(defUID);
        //dbUtil.clear("sys_dictionary");
    }



    @Test
    public void add() throws Exception {
        SysDictionary p=new SysDictionary();
        p.setSo(1);
        p.setDictionaryName("123");
        p.setRemark("321");
        p.setCategoryName(1);
        for (int i=0;i<10;i++){
            JsonResponse r = restTemplate.postForObject("/sys/dictionary/add",p, JsonResponse.class);
            assertSucess(r);
        }


    }

    @Test
    public void find() throws Exception {
        /*FindSysDictionaryRequest  p=new FindSysDictionaryRequest("dqwd",2,1,2);*/
        JsonResponse r = restTemplate.getForObject("/sys/dictionary/find?pageNum=1&pageSize=10", JsonResponse.class);
        assertSucess(r);
        /*List<Map<String, Object>> items = (List<Map<String, Object>>) r.getItem("items");*/
        System.out.println(/*items.toString()*/r.getItem("items"));
    }

    @Test
    public void update() throws Exception {
        SysDictionary p=new SysDictionary();
        p.setId(35L);
        p.setSo(1);
        p.setDictionaryName("111");
        p.setRemark("111");
        p.setCategoryName(1);
        JsonResponse r = restTemplate.postForObject("/sys/dictionary/update/35",p, JsonResponse.class);
        assertSucess(r);
    }

    @Test
    public void delete() throws Exception {
       restTemplate.delete("/sys/dictionary/delete/35",35L);
    }



}

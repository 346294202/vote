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
        dbUtil.clear(new String[] {
                "sys_dictionary"
        });
    }
    @Test
    public void add() throws Exception {
        SysDictionary p=new SysDictionary();
        p.setId(1L);
        p.setSo(1);
        p.setDictionaryName("123");
        p.setCategoryName(1);
        JsonResponse r = restTemplate.postForObject("/sys/dictionary/add",p, JsonResponse.class);
        assertSucess(r);
    }
    @Test
    public void find() throws Exception {
        JsonResponse r = restTemplate.getForObject("/sys/dictionary/find?page=0&psize=10", JsonResponse.class);
        assertSucess(r);
        System.out.println(r.getItem("items"));
    }

    @Test
    public void findByCategoryName() throws Exception {
        JsonResponse r = restTemplate.getForObject("/sys/dictionary/findByCategoryName/1", JsonResponse.class);
        assertSucess(r);
        System.out.println(r.getItem("items"));
    }
    @Test
    public void findById() throws Exception {
        JsonResponse r = restTemplate.getForObject("/sys/dictionary/findById/1", JsonResponse.class);
        assertSucess(r);
        System.out.println(r.getItem("item"));
    }
    @Test
    public void findAll() throws Exception {
        JsonResponse r = restTemplate.getForObject("/sys/dictionary/findAll", JsonResponse.class);
        assertSucess(r);
        System.out.println(r.getItem("items"));
    }
    @Test
    public void update() throws Exception {
        SysDictionary p=new SysDictionary();
        p.setDictionaryName("222");
        p.setRemark("");
        JsonResponse r = restTemplate.postForObject("/sys/dictionary/update/1",p, JsonResponse.class);
        assertSucess(r);
    }
    @Test
    public void delete() throws Exception {
       restTemplate.delete("/sys/dictionary/delete/1",1L);
    }
}

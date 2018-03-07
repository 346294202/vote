package test.com.leoyon.vote;


import com.leoyon.vote.advice.FindReplayRequest;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.util.M;
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
public class BiascAdviceControllerTests extends BaseWebTests {

    @Before
    public void setUp() throws Exception {
        super.setUp();
        setToken(defUID);
        dbUtil.clear(new String[] {
                "basic_advice", "basic_advice_picture", "basic_picture"
        });
        dbUtil.insert("basic_picture", M.map()
                .put("id", 1L)
                .put("url", "/a/b/c/d.jpg")
                .build());
    }

    @Test
    public void find() throws Exception {
        dbUtil.insert("basic_advice", M.map()
                .put("id", 1L)
                .put("user_id", 1L)
                .put("content","123213")
                .build());
        JsonResponse r = restTemplate.getForObject("/baisc/advice/find?startTime=2018-03-06&&page=0&psize=10", JsonResponse.class);
        assertSucess(r);
        System.out.println(r.getItem("items"));
    }


    @Test
    public void findById() throws Exception {
        dbUtil.insert("basic_advice", M.map()
                .put("id", 1L)
                .put("user_id", 1L)
                .put("content","123213")
                .build());
        dbUtil.insert("basic_advice", M.map()
                .put("id", 2L)
                .put("user_id", 1L)
                .put("content","123213")
                .build());
        JsonResponse r = restTemplate.getForObject("/baisc/advice/findById/1", JsonResponse.class);
        assertSucess(r);
        System.out.println(r.getItem("item"));
    }

    @Test
    public void update() throws Exception {
        FindReplayRequest p=new FindReplayRequest();
        p.setId(1L);
        p.setReplay("1");
        p.setStatus(2);
        p.setReplayRemark("2");
        JsonResponse r = restTemplate.postForObject("/baisc/advice/replay",p, JsonResponse.class);
        assertSucess(r);
    }
}

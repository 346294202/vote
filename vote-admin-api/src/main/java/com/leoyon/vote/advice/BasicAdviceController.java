package com.leoyon.vote.advice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.business.FindBizBusinessResponse;
import com.leoyon.vote.dictionary.FindSysDictionaryRequest;
import com.leoyon.vote.dictionary.FindSysDictionaryResponse;
import com.leoyon.vote.dictionary.SysDictionary;
import com.leoyon.vote.dictionary.SysDictionaryService;
import com.leoyon.vote.util.M;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Thinkpad on 2018/3/6.
 */
@RestController("投诉建议处理")
@Scope("prototype")
public class BasicAdviceController extends AuthenticationController {

        @Autowired
        private BasicAdviceService basicAdviceService;

        @GetMapping(value="/baisc/advice/find", name="查询投诉建议")
        public JsonResponse find(FindBaiscAdviceRequest req) {
            PageHelper.startPage((req.getPage()+1),req.getPsize());
            List<Advice> list =basicAdviceService.find(req);
            PageInfo<Advice> appsPageInfo = new PageInfo<Advice>(list);
            return JsonResponse.sucess(new M<>()
                    .put("items", appsPageInfo.getList())
                    .put("count",appsPageInfo.getTotal())
                    .build());
        }

        @GetMapping(value="/baisc/advice/findById/{id}", name="通过id查询投诉建议")
        public JsonResponse findById(@PathVariable(value="id") Long id) {
            Advice entity = new Advice();
            entity.setId(id);
            Advice appsPageInfo =basicAdviceService.findById(entity) ;
            return JsonResponse.sucess(new M<>()
                    .put("item", appsPageInfo)
                    .build());
        }


    @PostMapping(value="/baisc/advice/replay", name="回复处理")
    public JsonResponse replay(@RequestBody FindReplayRequest req ) throws Exception {
        basicAdviceService.replay(req);
        return JsonResponse.sucess();
    }
}

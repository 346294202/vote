package com.leoyon.vote.repair;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.advice.*;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.util.M;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Thinkpad on 2018/3/6.
 */
@RestController("报事报修管理")
@Scope("prototype")
public class BasicRepairController extends AuthenticationController {

    @Autowired
    private BasicRepairService basicRepairService;

    @GetMapping(value="/baisc/repair/find", name="查询报事报修")
    public JsonResponse find(FindBasicRepairRequest req) {
        PageHelper.startPage((req.getPage()+1),req.getPsize());
        List<Repair> list =basicRepairService.find(req);
        PageInfo<Repair> appsPageInfo = new PageInfo<Repair>(list);
        return JsonResponse.sucess(new M<>()
                .put("items", appsPageInfo.getList())
                .put("count",appsPageInfo.getTotal())
                .build());
    }

    @GetMapping(value="/baisc/repair/findById/{id}", name="通过id查询投诉建议")
    public JsonResponse findById(@PathVariable(value="id") Long id) {
        Repair entity = new Repair();
        entity.setId(id);
        Repair appsPageInfo =basicRepairService.findById(entity) ;
        return JsonResponse.sucess(new M<>()
                .put("item", appsPageInfo)
                .build());
    }


    @PostMapping(value="/baisc/repair/replay", name="回复处理")
    public JsonResponse replay(@RequestBody com.leoyon.vote.repair.FindReplayRequest req) throws Exception {
        basicRepairService.replay(req);
        return JsonResponse.sucess();
    }
}

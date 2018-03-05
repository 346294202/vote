package com.leoyon.vote.advertisement;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.dynamics.FindReleaseRequest;
import com.leoyon.vote.dynamics.FindReleaseResponse;
import com.leoyon.vote.dynamics.Release;
import com.leoyon.vote.dynamics.ReleaseService;
import com.leoyon.vote.util.M;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Thinkpad on 2018/2/27.
 */
@RestController("广告位管理")
@Scope("prototype")
public class BizAdvertisementController extends AuthenticationController {

    @Autowired
    private BizAdvertisementService bizAdvertisementService;

    @GetMapping(value="/biz/advertisement/find", name="查询广告位")
    public JsonResponse find(FindeBizAdvertisementRequest req) {
        PageHelper.startPage((req.getPage()+1),req.getPsize());
        List<FindBizAdvertisementResponse> list =bizAdvertisementService.find(req);
        PageInfo<FindBizAdvertisementResponse> appsPageInfo = new PageInfo<FindBizAdvertisementResponse>(list);
        return JsonResponse.sucess(new M<>()
                .put("items", appsPageInfo.getList())
                .put("count", appsPageInfo.getTotal())
                .build());
    }

    @GetMapping(value="/biz/advertisement/findById/{id}", name="通过id查询广告位")
    public JsonResponse findById(@PathVariable(value="id") Long id) {
        BizAdvertisement release = new BizAdvertisement();
        release.setId(id);
        FindBizAdvertisementResponse findBizAdvertisementResponse = bizAdvertisementService.findById(release);
        return JsonResponse.sucess(new M<>()
                .put("item", findBizAdvertisementResponse)
                .build());
    }

    @GetMapping(value="/biz/advertisement/findAll", name="查询广告位(不分页)")
    public JsonResponse findAll() {
        List<FindBizAdvertisementResponse> list =bizAdvertisementService.findAll();
        return JsonResponse.sucess(new M<>()
                .put("items", list)
                .build());
    }

    @PostMapping(value="/biz/advertisement/add", name="新增广告位")
    public JsonResponse add(@RequestBody BizAdvertisement bizAdvertisement) throws Exception {
        bizAdvertisement.setUpdateUid(getLogin(false).getId());
        bizAdvertisement.setCreatePerson(getLogin(false).getId());
        bizAdvertisement.setDateCreate(new Date());
        bizAdvertisementService.add(bizAdvertisement);
        return JsonResponse.sucess();
    }

    @PostMapping(value="/biz/advertisement/update/{id}", name="修改广告位")
    public JsonResponse update(@RequestBody BizAdvertisement bizAdvertisement,@PathVariable(value="id") Long id)throws Exception {
        bizAdvertisement.setId(id);
        bizAdvertisement.setUpdateUid(getLogin(false).getId());
        bizAdvertisement.setUpdateTime(new Date());
        bizAdvertisementService.update(bizAdvertisement);
        return JsonResponse.sucess();
    }

    @DeleteMapping(value="/biz/advertisement/delete/{id}", name="删除广告位")
    public JsonResponse delete(@PathVariable(value="id") Long id) throws ResponseException {
        BizAdvertisement bizAdvertisement = new BizAdvertisement();
        bizAdvertisement.setUpdateUid(getLogin(false).getId());
        bizAdvertisement.setId(id);
        bizAdvertisementService.delete(bizAdvertisement);
        return JsonResponse.sucess();
    }
}

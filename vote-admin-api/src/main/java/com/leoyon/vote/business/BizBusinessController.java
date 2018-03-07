package com.leoyon.vote.business;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.advertisement.BizAdvertisement;
import com.leoyon.vote.advertisement.FindBizAdvertisementResponse;
import com.leoyon.vote.advertisement.FindeBizAdvertisementRequest;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.util.M;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Thinkpad on 2018/3/5.
 */
@RestController("周边商家信息")
@Scope("prototype")
public class BizBusinessController extends AuthenticationController {

    @Autowired
    private BizBusinessService bizBusinessService;

    @GetMapping(value="/biz/business/find", name="查询周边商家信息或者周边政务信息")
    public JsonResponse find(FindeBizBusinessRequest req) {
        PageHelper.startPage((req.getPage()+1),req.getPsize());
        List<FindBizBusinessResponse> list =bizBusinessService.find(req);
        PageInfo<FindBizBusinessResponse> appsPageInfo = new PageInfo<FindBizBusinessResponse>(list);
        return JsonResponse.sucess(new M<>()
                .put("items", appsPageInfo.getList())
                .put("count", appsPageInfo.getTotal())
                .build());
    }

    @GetMapping(value="/biz/business/findById/{id}", name="通过id查询周边商家信息")
    public JsonResponse findById(@PathVariable(value="id") Long id) {
        BizBusiness bizBusiness = new BizBusiness();
        bizBusiness.setId(id);
        BizBusiness findBizBusinessResponse = bizBusinessService.findById(bizBusiness);
        return JsonResponse.sucess(new M<>()
                .put("item", findBizBusinessResponse)
                .build());
    }


    @PostMapping(value="/biz/business/add", name="新增周边商家信息或者周边政务信息")
    public JsonResponse add(@RequestBody BizBusiness bizBusiness) throws Exception {
        bizBusiness.setUpdateUid(getLogin(false).getId());
        bizBusiness.setDateCreate(new Date());
        bizBusinessService.add(bizBusiness);
        return JsonResponse.sucess();
    }


    @PostMapping(value="/biz/business/update/{id}", name="修改周边商家信息")
    public JsonResponse update(@RequestBody BizBusiness bizBusiness,@PathVariable(value="id") Long id)throws Exception {
        bizBusiness.setId(id);
        bizBusiness.setUpdateUid(getLogin(false).getId());
        bizBusiness.setUpdateTime(new Date());
        bizBusinessService.update(bizBusiness);
        return JsonResponse.sucess();
    }

    @DeleteMapping(value="/biz/business/delete/{id}", name="删除周边商家信息")
    public JsonResponse delete(@PathVariable(value="id") Long id) throws ResponseException {
        BizBusiness bizBusiness = new BizBusiness();
        bizBusiness.setUpdateUid(getLogin(false).getId());
        bizBusiness.setId(id);
        bizBusinessService.delete(bizBusiness);
        return JsonResponse.sucess();
    }
}

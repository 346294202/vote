package com.leoyon.vote.notice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.dictionary.FindSysDictionaryResponse;
import com.leoyon.vote.dictionary.SysDictionary;
import com.leoyon.vote.util.M;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Thinkpad on 2018/2/28.
 */
@RestController("物业公告发布")
@Scope("prototype")
public class PropertyNoticeController extends AuthenticationController {

    @Autowired
    private PropertyNoticeService propertyNoticeService;

    @GetMapping(value="/property/notice/find", name="查询物业公告")
    public JsonResponse find(FindPropertyNoticeRequest req) {
        PageHelper.startPage((req.getPage()+1),req.getPsize());
        List<FindPropertyNoticeResponse> list =propertyNoticeService.find(req);
        PageInfo<FindPropertyNoticeResponse> appsPageInfo = new PageInfo<FindPropertyNoticeResponse>(list);
        return JsonResponse.sucess(new M<>()
                .put("items", appsPageInfo.getList())
                .put("count",appsPageInfo.getTotal())
                .build());
    }

    @GetMapping(value="/property/notice/findById/{id}", name="通过id查询物业公告")
    public JsonResponse findById(@PathVariable(value="id") Long id) {
        PropertyNotice propertyNotice = new PropertyNotice();
        propertyNotice.setId(id);
        FindPropertyNoticeResponse appsPageInfo =propertyNoticeService.findById(propertyNotice) ;
        return JsonResponse.sucess(new M<>()
                .put("item", appsPageInfo)
                .build());
    }

    @GetMapping(value="/property/notice/findAll", name="查询物业公告(不分页)")
    public JsonResponse findAll() {
        List<FindPropertyNoticeResponse> list =propertyNoticeService.findAll();
        return JsonResponse.sucess(new M<>()
                .put("items",list)
                .build());
    }

    @PostMapping(value="/property/notice/add", name="新增物业公告")
    public JsonResponse add(@RequestBody PropertyNotice propertyNotice) throws Exception {
        propertyNotice.setUpdateUid(getLogin(false).getId());
        propertyNotice.setCreatePerson(getLogin(false).getId());
        propertyNotice.setDateCreate(new Date());
        propertyNoticeService.add(propertyNotice);
        return JsonResponse.sucess();
    }

    @PostMapping(value="/property/notice/update/{id}", name="修改物业公告")
    public JsonResponse update(
            @PathVariable(value="id") Long id,
            @RequestBody PropertyNotice propertyNotice
    ) throws Exception {
        propertyNotice.setId(id);
        propertyNotice.setUpdateUid(getLogin(false).getId());
        propertyNotice.setUpdateTime(new Date());
        propertyNoticeService.update(propertyNotice);
        return JsonResponse.sucess();
    }

    @DeleteMapping(value="/property/notice/delete/{id}", name="删除物业公告")
    public JsonResponse delete(
            @PathVariable(value="id") Long id) throws ResponseException {
        PropertyNotice propertyNotice = new PropertyNotice();
        propertyNotice.setUpdateUid(getLogin(false).getId());
        propertyNotice.setId(id);
        propertyNoticeService.delete(propertyNotice);
        return JsonResponse.sucess();
    }
}

package com.leoyon.vote.dynamics;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.dictionary.SysDictionary;
import com.leoyon.vote.util.M;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Thinkpad on 2018/2/27.
 */
@RestController("物业动态发布")
@Scope("prototype")
public class ReleaseController extends AuthenticationController {

    @Autowired
    private ReleaseService releaseService;

    @GetMapping(value="/dynamics/release/find", name="查询物业动态")
    public JsonResponse find(FindReleaseRequest req) {
        PageHelper.startPage((req.getPage()+1),req.getPsize());
        List<FindReleaseResponse> list =releaseService.find(req);
        PageInfo<FindReleaseResponse> appsPageInfo = new PageInfo<FindReleaseResponse>(list);
        return JsonResponse.sucess(new M<>()
                .put("items", appsPageInfo.getList())
                .put("count", appsPageInfo.getTotal())
                .build());
    }

    @GetMapping(value="/dynamics/release/findById/{id}", name="通过id查询物业动态")
    public JsonResponse findById(@PathVariable(value="id") Long id) {
        Release release = new Release();
        release.setId(id);
        FindReleaseResponse findReleaseResponse = releaseService.findById(release);
        return JsonResponse.sucess(new M<>()
                .put("item", findReleaseResponse)
                .build());
    }

    @GetMapping(value="/dynamics/release/findAll", name="查询物业动态(不分页)")
    public JsonResponse findAll() {
        List<FindReleaseResponse> list =releaseService.findAll();
        return JsonResponse.sucess(new M<>()
                .put("items", list)
                .build());
    }

    @PostMapping(value="/dynamics/release/add", name="新增物业动态")
    public JsonResponse add(@RequestBody Release release) throws Exception {
        release.setUpdateUid(getLogin(false).getId());
        release.setCreatePerson(getLogin(false).getId());
        release.setDateCreate(new Date());
        releaseService.add(release);
        return JsonResponse.sucess();
    }

    @PostMapping(value="/dynamics/release/update/{id}", name="修改物业动态")
    public JsonResponse update(@RequestBody Release release,@PathVariable(value="id") Long id)throws Exception {
        release.setId(id);
        release.setUpdateUid(getLogin(false).getId());
        release.setUpdateTime(new Date());
        releaseService.update(release);
        return JsonResponse.sucess();
    }

    @DeleteMapping(value="/dynamics/release/delete/{id}", name="删除物业动态")
    public JsonResponse delete(@PathVariable(value="id") Long id) throws ResponseException {
        Release entity = new Release();
        entity.setUpdateUid(getLogin(false).getId());
        entity.setId(id);
        releaseService.delete(entity);
        return JsonResponse.sucess();
    }
}

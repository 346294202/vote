package com.leoyon.vote.dictionary;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.product.Product;
import com.leoyon.vote.util.M;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * Created by Thinkpad on 2018/2/24.
 */
@RestController("数据字典")
@Scope("prototype")
public class SysDictionaryController extends AuthenticationController {

    @Autowired
    private SysDictionaryService sysDictionaryService;

    @GetMapping(value="/sys/dictionary/find", name="查询数据字典")
    public JsonResponse find(FindSysDictionaryRequest req) {
        PageHelper.startPage((req.getPage()+1),req.getPsize());
        List<FindSysDictionaryResponse> list =sysDictionaryService.find(req);
        PageInfo<FindSysDictionaryResponse> appsPageInfo = new PageInfo<>(list);
        return JsonResponse.sucess(new M<>()
                .put("items", appsPageInfo.getList())
                .put("count",appsPageInfo.getTotal())
                .build());
    }

    @GetMapping(value="/sys/dictionary/findById/{id}", name="通过id查询数据字典")
    public JsonResponse findById(@PathVariable(value="id") Long id) {
        SysDictionary entity = new SysDictionary();
        entity.setId(id);
        FindSysDictionaryResponse appsPageInfo =sysDictionaryService.findById(entity) ;
        return JsonResponse.sucess(new M<>()
                .put("item", appsPageInfo)
                .build());
    }

    @GetMapping(value="/sys/dictionary/findByCategoryName/{categoryName}", name="通过字典类目查询数据字典")
    public JsonResponse findByCategoryName(@PathVariable(value="categoryName") Integer categoryName) {
        SysDictionary entity = new SysDictionary();
        entity.setCategoryName(categoryName);
        List<FindSysDictionaryResponse> appsPageInfo =sysDictionaryService.findByCategoryName(entity) ;
        return JsonResponse.sucess(new M<>()
                .put("items", appsPageInfo)
                .build());
    }

    @GetMapping(value="/sys/dictionary/findAll", name="查询数据字典(不分页)")
    public JsonResponse findAll() {
        List<FindSysDictionaryResponse> list =sysDictionaryService.findAll();
        return JsonResponse.sucess(new M<>()
                .put("items",list)
                .build());
    }

    @PostMapping(value="/sys/dictionary/add", name="新增数据字典")
    public JsonResponse add(@RequestBody SysDictionary sysDictionary) throws Exception {
        sysDictionary.setUpdateUid(getLogin(false).getId());
        sysDictionary.setDateCreate(new Date());
        sysDictionaryService.add(sysDictionary);
        return JsonResponse.sucess();
    }

    @PostMapping(value="/sys/dictionary/update/{id}", name="修改数据字典")
    public JsonResponse update(
            @PathVariable(value="id") Long id,
            @RequestBody SysDictionary entity
    ) throws Exception {
        entity.setId(id);
        entity.setUpdateUid(getLogin(false).getId());
        entity.setUpdateTime(new Date());
        sysDictionaryService.update(entity);
        return JsonResponse.sucess();
    }

    @DeleteMapping(value="/sys/dictionary/delete/{id}", name="删除数据字典")
    public JsonResponse delete(
            @PathVariable(value="id") Long id) throws ResponseException {
        SysDictionary entity = new SysDictionary();
        entity.setUpdateUid(getLogin(false).getId());
        entity.setId(id);
        sysDictionaryService.delete(entity);
        return JsonResponse.sucess();
    }
}

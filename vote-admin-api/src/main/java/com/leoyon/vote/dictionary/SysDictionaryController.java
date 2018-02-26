package com.leoyon.vote.dictionary;


import com.leoyon.vote.AuthenticationController;
import com.leoyon.vote.api.JsonResponse;
import com.leoyon.vote.api.ResponseException;
import com.leoyon.vote.product.Product;
import com.leoyon.vote.util.M;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


/**
 * Created by Thinkpad on 2018/2/24.
 */
@RestController
@Scope("prototype")
public class SysDictionaryController extends AuthenticationController {

    @Autowired
    private SysDictionaryService sysDictionaryService;

    @GetMapping(value="/sys/dictionary/find", name="查询数据字典")
    public JsonResponse find(FindSysDictionaryRequest req) {
        List<FindSysDictionaryResponse> items = sysDictionaryService.find(req);
        return JsonResponse.sucess(new M<>()
                .put("items", items)
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
            @RequestBody SysDictionary entity
    ) throws Exception {
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

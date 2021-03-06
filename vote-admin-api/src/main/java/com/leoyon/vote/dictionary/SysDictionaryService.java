package com.leoyon.vote.dictionary;

import java.util.List;

/**
 * Created by Thinkpad on 2018/2/24.
 */
public interface SysDictionaryService {

    List<FindSysDictionaryResponse> find(FindSysDictionaryRequest rqst);

    FindSysDictionaryResponse findById(SysDictionary dictionary);

    List<FindSysDictionaryResponse> findByCategoryName(SysDictionary dictionary);

    List<FindSysDictionaryResponse> findAll();

    void add(SysDictionary dictionary);

    void update(SysDictionary dictionary);

    void delete(SysDictionary entity);
}

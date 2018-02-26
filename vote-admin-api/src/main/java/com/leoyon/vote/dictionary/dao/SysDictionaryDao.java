package com.leoyon.vote.dictionary.dao;

import com.leoyon.vote.dictionary.FindSysDictionaryRequest;
import com.leoyon.vote.dictionary.FindSysDictionaryResponse;
import com.leoyon.vote.dictionary.SysDictionary;

import java.util.List;

/**
 * Created by Thinkpad on 2018/2/24.
 */
public interface SysDictionaryDao {

    List<FindSysDictionaryResponse> find(FindSysDictionaryRequest rqst);

    void add(SysDictionary dictionary);

    void update(SysDictionary dictionary);

    void delete(SysDictionary entity);
}

package com.leoyon.vote.dynamics.dao;

import com.leoyon.vote.dictionary.FindSysDictionaryRequest;
import com.leoyon.vote.dictionary.FindSysDictionaryResponse;
import com.leoyon.vote.dictionary.SysDictionary;
import com.leoyon.vote.dynamics.FindReleaseRequest;
import com.leoyon.vote.dynamics.FindReleaseResponse;
import com.leoyon.vote.dynamics.Release;

import java.util.List;

/**
 * Created by Thinkpad on 2018/2/26.
 */
public interface ReleaseDao {

    List<FindReleaseResponse> find(FindReleaseRequest rqst);

    FindReleaseResponse findById(Release release);

    List<FindReleaseResponse> findAll();

    void add(Release release);

    void update(Release release);

    void delete(Release release);



}

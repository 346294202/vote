package com.leoyon.vote.dynamics;

import java.util.List;

/**
 * Created by Thinkpad on 2018/2/26.
 */
public interface ReleaseService {

    List<FindReleaseResponse> find(FindReleaseRequest rqst);

    FindReleaseResponse findById(Release release);

    List<FindReleaseResponse> findAll();

    void add(Release release);

    void update(Release release);

    void delete(Release release);
}

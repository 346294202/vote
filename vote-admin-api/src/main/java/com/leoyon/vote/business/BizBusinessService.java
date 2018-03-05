package com.leoyon.vote.business;

import java.util.List;

/**
 * Created by Thinkpad on 2018/3/5.
 */
public interface BizBusinessService {

    List<FindBizBusinessResponse> find(FindeBizBusinessRequest rqst);

    FindBizBusinessResponse findById(BizBusiness bizBusiness);

    List<FindBizBusinessResponse> findAll();

    void add(BizBusiness bizBusiness);

    void update(BizBusiness bizBusiness);

    void delete(BizBusiness bizBusiness);
}

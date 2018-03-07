package com.leoyon.vote.business;

import java.util.List;

/**
 * Created by Thinkpad on 2018/3/5.
 */
public interface BizBusinessService {

    List<FindBizBusinessResponse> find(FindeBizBusinessRequest rqst);


    BizBusiness findById(BizBusiness bizBusiness);


    void add(BizBusiness bizBusiness);

    void update(BizBusiness bizBusiness);

    void delete(BizBusiness bizBusiness);
}

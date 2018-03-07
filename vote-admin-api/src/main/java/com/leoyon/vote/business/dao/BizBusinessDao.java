package com.leoyon.vote.business.dao;

import com.leoyon.vote.business.BizBusiness;
import com.leoyon.vote.business.FindBizBusinessResponse;
import com.leoyon.vote.business.FindeBizBusinessRequest;

import java.util.List;

/**
 * Created by Thinkpad on 2018/3/1.
 */
public interface BizBusinessDao {

    List<FindBizBusinessResponse> find(FindeBizBusinessRequest rqst);

    BizBusiness findById(BizBusiness bizBusiness);

    void addCenter(BizBusiness bizBusiness);

    void add(BizBusiness bizBusiness);

    void update(BizBusiness bizBusiness);

    void delete(BizBusiness bizBusiness);
}

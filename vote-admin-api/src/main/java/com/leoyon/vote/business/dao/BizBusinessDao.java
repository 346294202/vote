package com.leoyon.vote.business.dao;

import com.leoyon.vote.advertisement.BizAdvertisement;
import com.leoyon.vote.advertisement.FindBizAdvertisementResponse;
import com.leoyon.vote.advertisement.FindeBizAdvertisementRequest;
import com.leoyon.vote.business.BizBusiness;
import com.leoyon.vote.business.FindBizBusinessResponse;
import com.leoyon.vote.business.FindeBizBusinessRequest;

import java.util.List;

/**
 * Created by Thinkpad on 2018/3/1.
 */
public interface BizBusinessDao {

    List<FindBizBusinessResponse> find(FindeBizBusinessRequest rqst);

    FindBizBusinessResponse findById(BizBusiness bizBusiness);

    List<FindBizBusinessResponse> findAll();

    void add(BizBusiness bizBusiness);

    void update(BizBusiness bizBusiness);

    void delete(BizBusiness bizBusiness);
}

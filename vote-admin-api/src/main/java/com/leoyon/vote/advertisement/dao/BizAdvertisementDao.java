package com.leoyon.vote.advertisement.dao;

import com.leoyon.vote.advertisement.BizAdvertisement;
import com.leoyon.vote.advertisement.FindBizAdvertisementResponse;
import com.leoyon.vote.advertisement.FindeBizAdvertisementRequest;
import java.util.List;

/**
 * Created by Thinkpad on 2018/3/1.
 */
public interface BizAdvertisementDao {

    List<FindBizAdvertisementResponse> find(FindeBizAdvertisementRequest rqst);

    FindBizAdvertisementResponse findById(BizAdvertisement bizAdvertisement);

    List<FindBizAdvertisementResponse> findAll();

    void add(BizAdvertisement bizAdvertisement);

    void update(BizAdvertisement bizAdvertisement);

    void delete(BizAdvertisement bizAdvertisement);
}

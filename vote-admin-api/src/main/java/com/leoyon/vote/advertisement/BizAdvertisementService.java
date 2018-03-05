package com.leoyon.vote.advertisement;


import java.util.List;

/**
 * Created by Thinkpad on 2018/2/26.
 */
public interface BizAdvertisementService {

    List<FindBizAdvertisementResponse> find(FindeBizAdvertisementRequest rqst);

    FindBizAdvertisementResponse findById(BizAdvertisement bizAdvertisement);

    List<FindBizAdvertisementResponse> findAll();

    void add(BizAdvertisement bizAdvertisement);

    void update(BizAdvertisement bizAdvertisement);

    void delete(BizAdvertisement bizAdvertisement);

}

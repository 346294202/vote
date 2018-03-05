package com.leoyon.vote.advertisement;

import com.leoyon.vote.advertisement.dao.BizAdvertisementDao;
import com.leoyon.vote.dynamics.FindReleaseRequest;
import com.leoyon.vote.dynamics.FindReleaseResponse;
import com.leoyon.vote.dynamics.Release;
import com.leoyon.vote.dynamics.ReleaseService;
import com.leoyon.vote.dynamics.dao.ReleaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Thinkpad on 2018/2/26.
 */
@Service
public class BizAdvertisementServiceImpl implements BizAdvertisementService{

    @Autowired
    private BizAdvertisementDao bizAdvertisementDao;


    @Override
    public List<FindBizAdvertisementResponse> find(FindeBizAdvertisementRequest rqst) {
        return bizAdvertisementDao.find(rqst);
    }

    @Override
    public FindBizAdvertisementResponse findById(BizAdvertisement bizAdvertisement) {
        return bizAdvertisementDao.findById(bizAdvertisement);
    }

    @Override
    public List<FindBizAdvertisementResponse> findAll() {
        return bizAdvertisementDao.findAll();
    }

    @Override
    public void add(BizAdvertisement bizAdvertisement) {
         bizAdvertisementDao.add(bizAdvertisement);
    }

    @Override
    public void update(BizAdvertisement bizAdvertisement) {
        bizAdvertisementDao.update(bizAdvertisement);
    }

    @Override
    public void delete(BizAdvertisement bizAdvertisement) {
        bizAdvertisementDao.delete(bizAdvertisement);
    }


}

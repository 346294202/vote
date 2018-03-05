package com.leoyon.vote.business;

import com.leoyon.vote.business.dao.BizBusinessDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Thinkpad on 2018/3/5.
 */
@Service
public class BizBusinessServiceImpl implements BizBusinessService{

    @Autowired
    private BizBusinessDao bizBusinessDao;

    @Override
    public List<FindBizBusinessResponse> find(FindeBizBusinessRequest rqst) {
        return bizBusinessDao.find(rqst);
    }

    @Override
    public FindBizBusinessResponse findById(BizBusiness bizBusiness) {
        return bizBusinessDao.findById(bizBusiness);
    }

    @Override
    public List<FindBizBusinessResponse> findAll() {
        return bizBusinessDao.findAll();
    }

    @Override
    public void add(BizBusiness bizBusiness) {
        bizBusinessDao.add(bizBusiness);
    }

    @Override
    public void update(BizBusiness bizBusiness) {
        bizBusinessDao.update(bizBusiness);
    }

    @Override
    public void delete(BizBusiness bizBusiness) {
        bizBusinessDao.delete(bizBusiness);
    }
}

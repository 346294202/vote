package com.leoyon.vote.business;

import com.leoyon.vote.business.dao.BizBusinessDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
        List<FindBizBusinessResponse> list= bizBusinessDao.find(rqst);
        return list;
    }



    @Override
    public BizBusiness findById(BizBusiness bizBusiness) {
        return bizBusinessDao.findById(bizBusiness);
    }



    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void add(BizBusiness bizBusiness) {
        bizBusinessDao.add(bizBusiness);
        System.out.println(bizBusiness.getId());
        bizBusinessDao.addCenter(bizBusiness);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
    public void update(BizBusiness bizBusiness) {
        bizBusinessDao.update(bizBusiness);
        bizBusinessDao.addCenter(bizBusiness);
    }

    @Override
    public void delete(BizBusiness bizBusiness) {
        bizBusinessDao.delete(bizBusiness);
    }
}

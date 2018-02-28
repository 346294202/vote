package com.leoyon.vote.notice;


import com.leoyon.vote.notice.dao.PropertyNoticeDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Thinkpad on 2018/2/28.
 */
@Service
public class PropertyNoticeServiceImpl implements PropertyNoticeService {

    @Autowired
    private PropertyNoticeDao propertyNoticeDao;

    @Override
    public List<FindPropertyNoticeResponse> find(FindPropertyNoticeRequest rqst) {
        List<FindPropertyNoticeResponse> ret = propertyNoticeDao.find(rqst);
        return ret;
    }

    @Override
    public FindPropertyNoticeResponse findById(PropertyNotice propertyNotice) {
        return propertyNoticeDao.findById(propertyNotice);
    }

    @Override
    public List<FindPropertyNoticeResponse> findAll() {
        return propertyNoticeDao.findAll();
    }

    @Override
    public void add(PropertyNotice propertyNotice) {
        propertyNoticeDao.add(propertyNotice);
    }

    @Override
    public void update(PropertyNotice propertyNotice) {
        propertyNoticeDao.update(propertyNotice);
    }

    @Override
    public void delete(PropertyNotice propertyNotice) {
        propertyNoticeDao.delete(propertyNotice);
    }
}

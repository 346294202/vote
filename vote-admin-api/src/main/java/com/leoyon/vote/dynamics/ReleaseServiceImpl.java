package com.leoyon.vote.dynamics;

import com.leoyon.vote.dynamics.dao.ReleaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Thinkpad on 2018/2/26.
 */
@Service
public class ReleaseServiceImpl implements ReleaseService{

    @Autowired
    private ReleaseDao releaseDao;


    @Override
    public List<FindReleaseResponse> find(FindReleaseRequest rqst) {
        return releaseDao.find(rqst);
    }

    @Override
    public FindReleaseResponse findById(Release release) {
        return releaseDao.findById(release);
    }

    @Override
    public List<FindReleaseResponse> findAll() {
        return releaseDao.findAll();
    }

    @Override
    public void add(Release release) {
         releaseDao.add(release);
    }

    @Override
    public void update(Release release) {
        releaseDao.update(release);
    }

    @Override
    public void delete(Release release) {
        releaseDao.delete(release);
    }
}

package com.leoyon.vote.advice;

import com.leoyon.vote.advice.dao.BasicAdviceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Thinkpad on 2018/3/6.
 */
@Service
public class BasicAdviceServiceImpl implements  BasicAdviceService{

    @Autowired
    private BasicAdviceDao basicAdviceDao;

    @Override
    public List<Advice> find(FindBaiscAdviceRequest req) {
        List<Advice> ret = basicAdviceDao.find(req);
        ret.forEach(i -> {
            Long adviceId=i.getId();
            i.setPictures(basicAdviceDao.getPictures(adviceId));
        });
        return ret;
    }

    @Override
    public Advice findById(Advice advice) {
        Advice ret = basicAdviceDao.findById(advice);
        Long adviceId=ret.getId();
        ret.setPictures(basicAdviceDao.getPictures(adviceId));
        return ret;
    }

    @Override
    public void replay(FindReplayRequest req) {
        basicAdviceDao.replay(req);
    }
}

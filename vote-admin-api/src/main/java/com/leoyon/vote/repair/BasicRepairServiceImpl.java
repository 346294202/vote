package com.leoyon.vote.repair;

import com.leoyon.vote.advice.*;
import com.leoyon.vote.advice.FindReplayRequest;
import com.leoyon.vote.advice.dao.BasicAdviceDao;
import com.leoyon.vote.repair.dao.BasicRepairDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Thinkpad on 2018/3/6.
 */
@Service
public class BasicRepairServiceImpl implements BasicRepairService{

    @Autowired
    private BasicRepairDao basicRepairDao;

    @Override
    public List<Repair> find(FindBasicRepairRequest req) {
        List<Repair> list= basicRepairDao.find(req);
        list.forEach(i -> {
            Long repairId= i.getId();
            i.setPictures(basicRepairDao.getPictures(repairId));
        });
        return list;
    }

    @Override
    public Repair findById(Repair repair) {
        Repair list= basicRepairDao.findById(repair);
        Long repairId=list.getId();
        list.setPictures(basicRepairDao.getPictures(repairId));
        return list;
    }

    @Override
    public void replay(com.leoyon.vote.repair.FindReplayRequest req) {
        basicRepairDao.replay(req);
    }
}

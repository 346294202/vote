package com.leoyon.vote.repair.dao;


import com.leoyon.vote.advice.FindReplayRequest;
import com.leoyon.vote.picture.Picture;
import com.leoyon.vote.repair.FindBasicRepairRequest;
import com.leoyon.vote.repair.Repair;

import java.util.Collection;
import java.util.List;

/**
 * Created by Thinkpad on 2018/3/6.
 */
public interface BasicRepairDao {

    List<Repair> find(FindBasicRepairRequest req);

    Repair findById(Repair repair);

    Collection<Picture> getPictures(Long repairId);

    void  replay(com.leoyon.vote.repair.FindReplayRequest req);
}

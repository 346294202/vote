package com.leoyon.vote.repair;
import java.util.List;

/**
 * Created by Thinkpad on 2018/3/6.
 */
public interface BasicRepairService {

    List<Repair> find(FindBasicRepairRequest req);

    Repair findById(Repair repair);

    void  replay(com.leoyon.vote.repair.FindReplayRequest req);

}

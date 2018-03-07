package com.leoyon.vote.advice.dao;

import com.leoyon.vote.advice.Advice;
import com.leoyon.vote.advice.FindBaiscAdviceRequest;
import com.leoyon.vote.advice.FindReplayRequest;
import com.leoyon.vote.picture.Picture;
import com.leoyon.vote.repair.FindBasicRepairRequest;

import java.util.Collection;
import java.util.List;

/**
 * Created by Thinkpad on 2018/3/5.
 */
public interface BasicAdviceDao {

    List<Advice> find(FindBaiscAdviceRequest req);

    Advice findById(Advice advice);

    Collection<Picture> getPictures(Long adviceId);

    void  replay(FindReplayRequest req);



}

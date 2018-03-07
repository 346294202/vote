package com.leoyon.vote.advice;
import java.util.List;

/**
 * Created by Thinkpad on 2018/3/6.
 */
public interface BasicAdviceService {

    List<Advice> find(FindBaiscAdviceRequest req);

    Advice findById(Advice advice);

    void  replay(FindReplayRequest req);


}

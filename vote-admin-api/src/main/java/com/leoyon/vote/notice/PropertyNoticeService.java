package com.leoyon.vote.notice;

import java.util.List;

/**
 * Created by Thinkpad on 2018/2/28.
 */
public interface PropertyNoticeService {

    List<FindPropertyNoticeResponse> find(FindPropertyNoticeRequest rqst);

    FindPropertyNoticeResponse findById(PropertyNotice propertyNotice);

    List<FindPropertyNoticeResponse> findAll();

    void add(PropertyNotice propertyNotice);

    void update(PropertyNotice propertyNotice);

    void delete(PropertyNotice propertyNotice);
}

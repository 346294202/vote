package com.leoyon.vote.notice.dao;

import com.leoyon.vote.dynamics.FindReleaseRequest;
import com.leoyon.vote.dynamics.FindReleaseResponse;
import com.leoyon.vote.dynamics.Release;
import com.leoyon.vote.notice.FindPropertyNoticeRequest;
import com.leoyon.vote.notice.FindPropertyNoticeResponse;
import com.leoyon.vote.notice.PropertyNotice;

import java.util.List;

/**
 * Created by Thinkpad on 2018/2/26.
 */
public interface PropertyNoticeDao {

    List<FindPropertyNoticeResponse> find(FindPropertyNoticeRequest rqst);

    FindPropertyNoticeResponse findById(PropertyNotice propertyNotice);

    List<FindPropertyNoticeResponse> findAll();

    void add(PropertyNotice propertyNotice);

    void update(PropertyNotice propertyNotice);

    void delete(PropertyNotice propertyNotice);
}

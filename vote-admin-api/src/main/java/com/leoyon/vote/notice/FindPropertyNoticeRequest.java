package com.leoyon.vote.notice;

import wj.flydoc.ApiParam;
import wj.flydoc.ApiParamCtor;


/**
 * Created by Thinkpad on 2018/2/26.
 */
public class FindPropertyNoticeRequest {

    private String startTime;
    private String endTime;
    private Integer page;
    private Integer psize;

    @ApiParamCtor
    public FindPropertyNoticeRequest(
            @ApiParam(desc="开始时间")
                    String startTime,
            @ApiParam(desc="结束时间")
                    String endTime,
            @ApiParam(desc="页码")
                    Integer page,
            @ApiParam(desc="页大小")
                    Integer psize
    ) {
        super();
        this.startTime = startTime;
        this.endTime = endTime;
        this.page = page;
        this.psize = psize;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getPsize() {
        return psize;
    }

    public void setPsize(Integer psize) {
        this.psize = psize;
    }
}

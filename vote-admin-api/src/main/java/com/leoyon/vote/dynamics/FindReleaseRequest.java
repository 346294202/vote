package com.leoyon.vote.dynamics;

import wj.flydoc.ApiParam;
import wj.flydoc.ApiParamCtor;


/**
 * Created by Thinkpad on 2018/2/26.
 */
public class FindReleaseRequest {

    private String startTime;
    private String endTime;
    private Integer pageNum;
    private Integer pageSize;

    @ApiParamCtor
    public FindReleaseRequest(
            @ApiParam(desc="开始时间")
                    String startTime,
            @ApiParam(desc="结束时间")
                    String endTime,
            @ApiParam(desc="页码")
                    Integer pageNum,
            @ApiParam(desc="页大小")
                    Integer pageSize
    ) {
        super();
        this.startTime = startTime;
        this.endTime = endTime;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
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

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}

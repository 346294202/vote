package com.leoyon.vote.repair;

import wj.flydoc.ApiParam;
import wj.flydoc.ApiParamCtor;


/**
 * Created by Thinkpad on 2018/2/26.
 */
public class FindBasicRepairRequest {

    private Integer areaId;
    private Integer building;
    private Integer unit;
    private Integer number;
    private String startTime;
    private String endTime;
    private String p;
    private Integer page;
    private Integer psize;

    @ApiParamCtor
    public FindBasicRepairRequest(
            @ApiParam(desc="所属小区")
                    Integer areaId,
            @ApiParam(desc="楼号")
                    Integer building,
            @ApiParam(desc="单元号")
                    Integer unit,
            @ApiParam(desc="房号")
                    Integer number,
            @ApiParam(desc="开始时间")
                    String startTime,
            @ApiParam(desc="结束时间")
                    String endTime,
            @ApiParam(desc="关键字查询")
                    String p,
            @ApiParam(desc="页码")
                    Integer page,
            @ApiParam(desc="页大小")
                    Integer psize

    ) {
        super();
        this.areaId=areaId;
        this.building=building;
        this.unit=unit;
        this.number=number;
        this.startTime = startTime;
        this.endTime = endTime;
        this.p=p;
        this.page = page;
        this.psize = psize;
    }

    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }

    public Integer getBuilding() {
        return building;
    }

    public void setBuilding(Integer building) {
        this.building = building;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
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

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
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

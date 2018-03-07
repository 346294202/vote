package com.leoyon.vote.repair;

import com.leoyon.vote.AbstractResource;
import wj.flydoc.ApiParam;

/**
 * Created by Thinkpad on 2018/3/5.
 */
public class BasicRepair extends AbstractResource<BasicRepair> {

    private Integer userId;

    private Integer houseId;

    private String address;

    private String content;

    private Integer status;

    @ApiParam(desc="申报业主")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @ApiParam(desc="房屋id")
    public Integer getHouseId() {
        return houseId;
    }

    public void setHouseId(Integer houseId) {
        this.houseId = houseId;
    }

    @ApiParam(desc="房屋id")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ApiParam(desc="申报内容")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @ApiParam(desc="状态")
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}

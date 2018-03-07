package com.leoyon.vote.advice;

import com.leoyon.vote.AbstractResource;
import com.leoyon.vote.advertisement.BizAdvertisement;
import wj.flydoc.ApiParam;

/**
 * Created by Thinkpad on 2018/3/5.
 */
public class BasicAdvice extends AbstractResource<BizAdvertisement> {

    private long userId;

    private String phone;

    private String content;

    private Integer status;

    private Integer type;

    @ApiParam(desc="申报业主")
    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    @ApiParam(desc="业主")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @ApiParam(desc="内容")
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

    @ApiParam(desc="类型")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}

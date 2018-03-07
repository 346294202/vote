package com.leoyon.vote.business;

import wj.flydoc.ApiParamCtor;

/**
 * Created by Thinkpad on 2018/3/1.
 */
public class FindeBizBusinessRequest {

    private String  businessName;
    private Integer businessType;
    private Integer ieType;
    private Integer page;
    private Integer psize;

    @ApiParamCtor
    public FindeBizBusinessRequest(
                    String businessName,
                    Integer businessType,
                    Integer ieType,
                            Integer page,
                            Integer psize
    ) {
        super();
        this.businessName=businessName;
        this.businessType=businessType;
        this.ieType=ieType;
        this.page = page;
        this.psize = psize;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }

    public Integer getIeType() {
        return ieType;
    }

    public void setIeType(Integer ieType) {
        this.ieType = ieType;
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

package com.leoyon.vote.business;

import wj.flydoc.ApiParam;
import wj.flydoc.ApiParamCtor;

/**
 * Created by Thinkpad on 2018/3/1.
 */
public class FindeBizBusinessRequest {

    private String businessName;
    private Integer businessType;
    private Integer page;
    private Integer psize;

    @ApiParamCtor
    public FindeBizBusinessRequest(
            @ApiParam(desc="商家名称")
                    String businessName,
            @ApiParam(desc="商家类型")
                    Integer businessType,
            @ApiParam(desc="页码")
                    Integer page,
            @ApiParam(desc="页大小")
                    Integer psize
    ) {
        super();
        this.businessName=businessName;
        this.businessType=businessType;
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

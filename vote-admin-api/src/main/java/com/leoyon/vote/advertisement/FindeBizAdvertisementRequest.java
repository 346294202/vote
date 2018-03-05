package com.leoyon.vote.advertisement;

import wj.flydoc.ApiParam;
import wj.flydoc.ApiParamCtor;

/**
 * Created by Thinkpad on 2018/3/1.
 */
public class FindeBizAdvertisementRequest {


    private Integer page;
    private Integer psize;

    @ApiParamCtor
    public FindeBizAdvertisementRequest(
            @ApiParam(desc="页码")
                    Integer page,
            @ApiParam(desc="页大小")
                    Integer psize
    ) {
        super();
        this.page = page;
        this.psize = psize;
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

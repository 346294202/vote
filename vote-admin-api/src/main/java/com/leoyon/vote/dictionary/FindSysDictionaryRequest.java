package com.leoyon.vote.dictionary;

import wj.flydoc.ApiParam;
import wj.flydoc.ApiParamCtor;

/**
 * Created by Thinkpad on 2018/2/24.
 */
public class FindSysDictionaryRequest {

    private String q;
    private Integer categoryName;
    private Integer page;
    private Integer psize;



    @ApiParamCtor
    public FindSysDictionaryRequest(
            @ApiParam(desc="模糊查询")
                    String q,
            @ApiParam(desc="字典类目")
                    Integer categoryName,
            @ApiParam(desc="页码")
                    Integer page,
            @ApiParam(desc="页大小")
                    Integer psize
          ) {
        super();
        this.q = q;
        this.categoryName = categoryName;
        this.page = page;
        this.psize = psize;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public Integer getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(Integer categoryName) {
        this.categoryName = categoryName;
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

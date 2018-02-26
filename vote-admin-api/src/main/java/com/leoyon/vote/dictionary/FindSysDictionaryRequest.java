package com.leoyon.vote.dictionary;

import wj.flydoc.ApiParam;
import wj.flydoc.ApiParamCtor;

/**
 * Created by Thinkpad on 2018/2/24.
 */
public class FindSysDictionaryRequest {

    private String q;
    private Integer categoryName;
    private Integer pageNum;
    private Integer pageSize;



    @ApiParamCtor
    public FindSysDictionaryRequest(
            @ApiParam(desc="模糊查询")
                    String q,
            @ApiParam(desc="字典类目")
                    Integer categoryName,
            @ApiParam(desc="页码")
                    Integer pageNum,
            @ApiParam(desc="页大小")
                    Integer pageSize
          ) {
        super();
        this.q = q;
        this.categoryName = categoryName;
        this.pageNum = pageNum;
        this.pageSize = pageSize;
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

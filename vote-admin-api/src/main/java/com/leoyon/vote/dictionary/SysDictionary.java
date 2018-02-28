package com.leoyon.vote.dictionary;

import com.leoyon.vote.AbstractResource;
import com.leoyon.vote.user.SysUser;

/**
 * Created by Thinkpad on 2018/2/24.
 */
public class SysDictionary extends AbstractResource<SysUser> {

    private Integer categoryName;
    private String dictionaryName;
    private Integer so;
    private String remark;

    public Integer getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(Integer categoryName) {
        this.categoryName = categoryName;
    }

    public String getDictionaryName() {
        return dictionaryName;
    }

    public void setDictionaryName(String dictionaryName) {
        this.dictionaryName = dictionaryName;
    }

    public Integer getSo() {
        return so;
    }

    public void setSo(Integer so) {
        this.so = so;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}

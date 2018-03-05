package com.leoyon.vote.business;

import com.leoyon.vote.AbstractResource;
import wj.flydoc.ApiParam;


/**
 * Created by Thinkpad on 2018/3/5.
 */
public class BizBusiness extends AbstractResource<BizBusiness> {

    private String businessName;

    private Integer businessType;

    private String url;

    private String address;

    private String contactWay;

    private String remark;

    private Integer state;

    private String lng;

    private String lat;

    @ApiParam(desc="商家名称")
    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    @ApiParam(desc="商家类型")
    public Integer getBusinessType() {
        return businessType;
    }

    public void setBusinessType(Integer businessType) {
        this.businessType = businessType;
    }
    @ApiParam(desc="商家首图")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @ApiParam(desc="详细地址")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @ApiParam(desc="联系方式")
    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    @ApiParam(desc="图文描述")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @ApiParam(desc="状态")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @ApiParam(desc="经度")
    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    @ApiParam(desc="纬度")
    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
}

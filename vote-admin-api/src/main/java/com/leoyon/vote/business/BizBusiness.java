package com.leoyon.vote.business;

import com.leoyon.vote.AbstractResource;

/**
 * Created by Thinkpad on 2018/3/5.
 */
public class BizBusiness extends AbstractResource<BizBusiness> {

    private String businessName;

    private Integer businessType;

    private String address;

    private String contactWay;

    private String remark;

    private Integer state;

    private String lng;

    private String lat;

    private Integer ieType;

    private String url;


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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public Integer getIeType() {
        return ieType;
    }

    public void setIeType(Integer ieType) {
        this.ieType = ieType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

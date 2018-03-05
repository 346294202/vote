package com.leoyon.vote.advertisement;

import com.leoyon.vote.AbstractResource;
import wj.flydoc.ApiParam;

/**
 * Created by Thinkpad on 2018/2/28.
 */
public class BizAdvertisement extends AbstractResource<BizAdvertisement> {

    private Integer position;

    private String url;

    private Integer way;

    private Integer dynamicsId;

    private String externalLinks;

    private Integer state;

    private Long createPerson;

    @ApiParam(desc="位置")
    public Integer getPosition() {
        return position;
    }
    public void setPosition(Integer position) {
        this.position = position;
    }
    @ApiParam(desc="广告位图片")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @ApiParam(desc="链接方式")
    public Integer getWay() {
        return way;
    }

    public void setWay(Integer way) {
        this.way = way;
    }
    @ApiParam(desc="物业动态")
    public Integer getDynamicsId() {
        return dynamicsId;
    }

    public void setDynamicsId(Integer dynamicsId) {
        this.dynamicsId = dynamicsId;
    }


    @ApiParam(desc="外部链接")
    public String getExternalLinks() {
        return externalLinks;
    }

    public void setExternalLinks(String externalLinks) {
        this.externalLinks = externalLinks;
    }

    @ApiParam(desc="状态")
    public Integer getState() {
        return state;
    }
    public void setState(Integer state) {
        this.state = state;
    }
    @ApiParam(desc="发布人")
    public Long getCreatePerson() {
        return createPerson;
    }
    public void setCreatePerson(Long createPerson) {
        this.createPerson = createPerson;
    }
}

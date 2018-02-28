package com.leoyon.vote.notice;
import com.leoyon.vote.AbstractResource;
import wj.flydoc.ApiParam;

/**
 * Created by Thinkpad on 2018/2/27.
 */
public class PropertyNotice extends AbstractResource<PropertyNotice> {

    private Long areaId;

    private String  title;

    private String content;

    private Integer state;

    private Long createPerson;

    @ApiParam(desc="发布小区")
    public Long getAreaId() {
        return areaId;
    }
    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }
    @ApiParam(desc="公告标题")
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    @ApiParam(desc="公告内容")
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }
    @ApiParam(desc="公告状态")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Long getCreatePerson() {
        return createPerson;
    }

    public void setCreatePerson(Long createPerson) {
        this.createPerson = createPerson;
    }
}

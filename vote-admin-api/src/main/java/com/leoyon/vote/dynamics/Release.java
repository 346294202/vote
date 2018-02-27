package com.leoyon.vote.dynamics;
import com.leoyon.vote.AbstractResource;
import wj.flydoc.ApiParam;

/**
 * Created by Thinkpad on 2018/2/26.
 */
public class Release extends AbstractResource<Release> {


    private String  releaseTitle;

    private String url;

    private String content;

    private Integer state;

    private Long createPerson;

    @ApiParam(desc="标题")
    public String getReleaseTitle() {
        return releaseTitle;
    }

    public void setReleaseTitle(String releaseTitle) {
        this.releaseTitle = releaseTitle;
    }
    @ApiParam(desc="图片路径")
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
    @ApiParam(desc="内容")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    @ApiParam(desc="状态")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
    @ApiParam(desc="创建人")
    public Long getCreatePerson() {
        return createPerson;
    }
    public void setCreatePerson(Long createPerson) {
        this.createPerson = createPerson;
    }
}

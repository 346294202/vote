package com.leoyon.vote.advice;

import com.leoyon.vote.picture.Picture;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Thinkpad on 2018/3/5.
 */
public class Advice{

    private Long id;

    private Date dateCreate;

    private Integer type;

    private String userName;

    private String phone;

    private String content;

    private Integer status;

    private String replay;

    private String replayRemark;

    private Collection<Picture> pictures;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getReplay() {
        return replay;
    }

    public void setReplay(String replay) {
        this.replay = replay;
    }

    public String getReplayRemark() {
        return replayRemark;
    }

    public void setReplayRemark(String replayRemark) {
        this.replayRemark = replayRemark;
    }

    public Collection<Picture> getPictures() {
        return pictures;
    }

    public void setPictures(Collection<Picture> pictures) {
        this.pictures = pictures;
    }


}

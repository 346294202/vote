package com.leoyon.vote.repair;

import com.leoyon.vote.picture.Picture;

import java.util.Collection;
import java.util.Date;

/**
 * Created by Thinkpad on 2018/3/6.
 */
public class Repair {

    private Long id;

    private Date dateCreate;

    private String  areaName;

    private Integer building;

    private Integer unit;

    private Integer number;

    private String userName;

    private String address;

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

    public String getAreaName() {
        return areaName;
    }
    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
    public Integer getBuilding() {
        return building;
    }

    public void setBuilding(Integer building) {
        this.building = building;
    }

    public Integer getUnit() {
        return unit;
    }

    public void setUnit(Integer unit) {
        this.unit = unit;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

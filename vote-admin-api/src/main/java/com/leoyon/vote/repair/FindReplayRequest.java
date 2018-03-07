package com.leoyon.vote.repair;

/**
 * Created by Thinkpad on 2018/3/6.
 */
public class FindReplayRequest {

    private Long  id;
    private String  replay;
    private Integer status;
    private String  replayRemark;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReplay() {
        return replay;
    }

    public void setReplay(String replay) {
        this.replay = replay;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getReplayRemark() {
        return replayRemark;
    }

    public void setReplayRemark(String replayRemark) {
        this.replayRemark = replayRemark;
    }
}

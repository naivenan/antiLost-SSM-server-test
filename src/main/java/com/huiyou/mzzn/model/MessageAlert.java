package com.huiyou.mzzn.model;

import java.util.Date;

public class MessageAlert {
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 推送人
     */
    private Long pushUserId;

    /**
     * 类型:1走出电子围栏提醒  2：跌倒提醒
     */
    private Integer type;

    /**
     * 状态 0：未提醒  1：已提醒
     */
    private Integer status;

    private Integer isDel;

    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getPushUserId() {
        return pushUserId;
    }

    public void setPushUserId(Long pushUserId) {
        this.pushUserId = pushUserId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
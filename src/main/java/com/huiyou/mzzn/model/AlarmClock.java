package com.huiyou.mzzn.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;

import me.fishlord.common.utils.BaseJsonView;

public class AlarmClock {
	public interface ListView extends BaseJsonView{};
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    private String title;

    /**
     * 类型 1：吃药  2：生日
     */
    private Integer type;

    /**
     * 频率 1：一次  2：每天/每年
     */
    private Integer frequency;

    /**
     * 间隔  每天时有用，N表示间隔N天提醒
     */
    private Integer period;

    /**
     * 时间，多个时间用逗号隔开，生日使用年月
     */
    @JsonView({ListView.class})
    private String times;

    /**
     * 提醒详情
     */
    @JsonView({ListView.class})
    private String detail;

    /**
     * 触发器名称,多个用逗号隔开
     */
    private String triggerNames;

    /**
     * 任务名称,多个用逗号隔开
     */
    private String jobNames;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Integer getPeriod() {
        return period;
    }

    public void setPeriod(Integer period) {
        this.period = period;
    }

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times == null ? null : times.trim();
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public String getTriggerNames() {
        return triggerNames;
    }

    public void setTriggerNames(String triggerNames) {
        this.triggerNames = triggerNames == null ? null : triggerNames.trim();
    }

    public String getJobNames() {
        return jobNames;
    }

    public void setJobNames(String jobNames) {
        this.jobNames = jobNames == null ? null : jobNames.trim();
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
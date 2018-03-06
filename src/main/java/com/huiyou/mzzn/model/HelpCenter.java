package com.huiyou.mzzn.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;

import me.fishlord.common.utils.BaseJsonView;

public class HelpCenter {
	public interface ListView extends BaseJsonView{};
	@JsonView({ListView.class})
    private Long id;
    
    @JsonView({ListView.class})
    private String title;
    
    @JsonView({ListView.class})
    private String summary;
    @JsonView({ListView.class})
    private String content;
    
    private Integer isDel;

    private Date createTime;
    
    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
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

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}
	
}
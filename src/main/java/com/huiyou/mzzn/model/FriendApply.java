package com.huiyou.mzzn.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;

import me.fishlord.common.utils.BaseJsonView;

public class FriendApply {
	
	public interface ListView extends BaseJsonView{};
	
	@JsonView({ListView.class})
    private Long id;

    /**
     * 类型 1:绑定申请 2:好友申请
     */
    @JsonView({ListView.class})
    private Integer type;

    /**
     * 申请人
     */
    private Long applyUserId;
    
    //申请人用户名
    @JsonView({ListView.class})
    private String applyUserName;
    //申请人头像
    @JsonView({ListView.class})
    private String applyUserHeadPic;
    //手机号码
    @JsonView({ListView.class})
    private String mobile;
    /**
     * 好友id
     */
    private Long friendId;

    /**
     * 状态 1:申请中 2:通过 3:拒绝
     */
    @JsonView({ListView.class})
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Long getApplyUserId() {
        return applyUserId;
    }

    public void setApplyUserId(Long applyUserId) {
        this.applyUserId = applyUserId;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
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

	public String getApplyUserName() {
		return applyUserName;
	}

	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}

	public String getApplyUserHeadPic() {
		return applyUserHeadPic;
	}

	public void setApplyUserHeadPic(String applyUserHeadPic) {
		this.applyUserHeadPic = applyUserHeadPic;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
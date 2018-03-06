package com.huiyou.mzzn.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;

import me.fishlord.common.utils.BaseJsonView;

public class Friend {
	
	
	public interface ListView extends BaseJsonView{};
	
	@JsonView({ListView.class})
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 好友id
     */
    @JsonView({ListView.class})
    private Long friendId;
    
    //好友用户名
    @JsonView({ListView.class})
    private String friendUserName;
    //好友头像
    @JsonView({ListView.class})
    private String friendUserHeadPic;

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

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
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

	public String getFriendUserName() {
		return friendUserName;
	}

	public void setFriendUserName(String friendUserName) {
		this.friendUserName = friendUserName;
	}

	public String getFriendUserHeadPic() {
		return friendUserHeadPic;
	}

	public void setFriendUserHeadPic(String friendUserHeadPic) {
		this.friendUserHeadPic = friendUserHeadPic;
	}
}
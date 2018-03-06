package com.huiyou.mzzn.model;

import java.util.Date;
import java.util.List;

import me.fishlord.common.utils.BaseJsonView;

import com.fasterxml.jackson.annotation.JsonView;

public class GroupMember {
	public interface GroupListView extends BaseJsonView{};
	public interface ListView extends BaseJsonView{};
	
    private Long id;

    /**
     * 群id
     */
    @JsonView({GroupListView.class,ListView.class})
    private Long groupId;
    
    //群名称
    @JsonView({GroupListView.class,ListView.class})
    private String groupName;
    
    //群成员头像
    @JsonView({GroupListView.class})
    private List<String>  userHeadPics;

    /**
     * 组员id
     */
    @JsonView({ListView.class})
    private Long memberId;
    
    //组员用户名
    @JsonView({ListView.class})
    private String memberUserName;
    
    //组员头像
    @JsonView({ListView.class})
    private String memberUserHeadPic;

    /**
     * 是否群主
     */
    @JsonView({ListView.class})
    private Integer isOwner;

    /**
     * 在本群的昵称
     */
    @JsonView({ListView.class})
    private String nickName;

    private Integer isDel;
    //是否为 自己
    @JsonView({ListView.class})
    private Integer mySelf;
    
    private Date createTime;

    private Date updateTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public Integer getIsOwner() {
        return isOwner;
    }

    public void setIsOwner(Integer isOwner) {
        this.isOwner = isOwner;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName == null ? null : nickName.trim();
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

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public List<String> getUserHeadPics() {
		return userHeadPics;
	}

	public void setUserHeadPics(List<String> userHeadPics) {
		this.userHeadPics = userHeadPics;
	}

	public String getMemberUserName() {
		return memberUserName;
	}

	public void setMemberUserName(String memberUserName) {
		this.memberUserName = memberUserName;
	}

	public String getMemberUserHeadPic() {
		return memberUserHeadPic;
	}

	public void setMemberUserHeadPic(String memberUserHeadPic) {
		this.memberUserHeadPic = memberUserHeadPic;
	}

	public Integer getMySelf() {
		return mySelf;
	}

	public void setMySelf(Integer mySelf) {
		this.mySelf = mySelf;
	}
	
}
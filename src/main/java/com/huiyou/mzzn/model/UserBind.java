package com.huiyou.mzzn.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;

import me.fishlord.common.utils.BaseJsonView;

public class UserBind {
	public interface ListView extends BaseJsonView{};
	
	@JsonView({ListView.class})
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 绑定的用户id
     */
    private Long bindUserId;
    
    /**
     * 绑定用户当前纬度
     */
    @JsonView({ListView.class})
    private Double nowLatitude;

    /**
     * 绑定用户当前经度
     */
    @JsonView({ListView.class})
    private Double nowLongitude;
    
    //绑定用户头像
    @JsonView({ListView.class})
    private String bindUserHeadPic;
    
    //绑定用户用户名
    @JsonView({ListView.class})
    private String bindUserName;
    //绑定用户手机号
    @JsonView({ListView.class})
    private String mobile;
    
    /**
     * 当前位置
     */
    @JsonView({ListView.class})
    private String address;
    /**
     * 是否设置了电子围栏  1:是 0:否
     */
    @JsonView({ListView.class})
    private Integer isSet;

    /**
     * 纬度
     */
    @JsonView({ListView.class})
    private Double latitude;

    /**
     * 经度
     */
    @JsonView({ListView.class})
    private Double longitude;

    /**
     * 电子围栏半径 米
     */
    @JsonView({ListView.class})
    private Integer railRadius;

    private Integer isDel;

    private Date createTime;

    private Date updateTime;
    
    /**
     *位置状态:0未超出;1已超出 
     */
    @JsonView({ListView.class})
    private Integer status;
    
    /**
     *绑定状态:0单绑;1互绑 
     */
    @JsonView({ListView.class})
    private Integer bind;
    
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

    public Long getBindUserId() {
        return bindUserId;
    }

    public void setBindUserId(Long bindUserId) {
        this.bindUserId = bindUserId;
    }

    public Integer getIsSet() {
        return isSet;
    }

    public void setIsSet(Integer isSet) {
        this.isSet = isSet;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getRailRadius() {
        return railRadius;
    }

    public void setRailRadius(Integer railRadius) {
        this.railRadius = railRadius;
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

	public String getBindUserHeadPic() {
		return bindUserHeadPic;
	}

	public void setBindUserHeadPic(String bindUserHeadPic) {
		this.bindUserHeadPic = bindUserHeadPic;
	}

	public String getBindUserName() {
		return bindUserName;
	}

	public void setBindUserName(String bindUserName) {
		this.bindUserName = bindUserName;
	}

	public Double getNowLatitude() {
		return nowLatitude;
	}

	public void setNowLatitude(Double nowLatitude) {
		this.nowLatitude = nowLatitude;
	}

	public Double getNowLongitude() {
		return nowLongitude;
	}

	public void setNowLongitude(Double nowLongitude) {
		this.nowLongitude = nowLongitude;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getBind() {
		return bind;
	}

	public void setBind(Integer bind) {
		this.bind = bind;
	}
}
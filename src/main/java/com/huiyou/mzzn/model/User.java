package com.huiyou.mzzn.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonView;

import me.fishlord.common.utils.BaseJsonView;

public class User {
	
	public interface DetailView extends BaseJsonView{};
	public interface ListView extends BaseJsonView{};
	
	@JsonView({DetailView.class,ListView.class})
    private Long id;

    /**
     * 头像
     */
    @JsonView({DetailView.class,ListView.class})
    private String headPic;

    /**
     * 类型 1：家属 2：老人
     */
    @JsonView({DetailView.class})
    private Integer type;

    /**
     * 用户名,唯一
     */
    @JsonView({DetailView.class,ListView.class})
    private String username;

    /**
     * 手机号
     */
    @JsonView({DetailView.class,ListView.class})
    private String mobile;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    @JsonView({DetailView.class})
    private String email;

    /**
     * 地区
     */
    @JsonView({DetailView.class})
    private String area;

    /**
     * 生日
     */
    @JsonView({DetailView.class})
    private String birthday;

    /**
     * 字号,默认1
     */
    @JsonView({DetailView.class})
    private String wordSize;

    /**
     * 报警音
     */
    @JsonView({DetailView.class})
    private String alertVoice;

    private Integer isDel;

    private Date createTime;

    private Date updateTime;

    private String weixinToken;

    private String weiboToken;
    
    private String pushToken;

    private String token;
    
    //验证码
    private String verifyCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeadPic() {
        return headPic;
    }

    public void setHeadPic(String headPic) {
        this.headPic = headPic == null ? null : headPic.trim();
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area == null ? null : area.trim();
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday == null ? null : birthday.trim();
    }

    public String getWordSize() {
        return wordSize;
    }

    public void setWordSize(String wordSize) {
        this.wordSize = wordSize == null ? null : wordSize.trim();
    }

    public String getAlertVoice() {
        return alertVoice;
    }

    public void setAlertVoice(String alertVoice) {
        this.alertVoice = alertVoice == null ? null : alertVoice.trim();
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

    public String getWeixinToken() {
        return weixinToken;
    }

    public void setWeixinToken(String weixinToken) {
        this.weixinToken = weixinToken == null ? null : weixinToken.trim();
    }

    public String getWeiboToken() {
        return weiboToken;
    }

    public void setWeiboToken(String weiboToken) {
        this.weiboToken = weiboToken == null ? null : weiboToken.trim();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
    }

	public String getVerifyCode() {
		return verifyCode;
	}

	public void setVerifyCode(String verifyCode) {
		this.verifyCode = verifyCode;
	}

	public String getPushToken() {
		return pushToken;
	}

	public void setPushToken(String pushToken) {
		this.pushToken = pushToken;
	}
}
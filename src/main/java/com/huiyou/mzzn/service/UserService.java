package com.huiyou.mzzn.service;

import java.util.List;

import me.fishlord.common.result.ResultEntity;

import com.huiyou.mzzn.model.User;
import com.huiyou.mzzn.model.UserExample;

public interface UserService{
	int queryUserCount(UserExample example);
	List<User> queryUserList(UserExample example);
	User queryUserById(Long id);
	ResultEntity createUser(User user);
	int updateUser(User user);
	ResultEntity login(String openid,String headimgurl,Integer type,String pushToken);
	ResultEntity bind(User user,String openid,String headimgurl,Integer type);
	ResultEntity doLogin(User user);
	ResultEntity findPassword(User user);
	ResultEntity resetPassword(User user,String oldpassword);
	
	ResultEntity emailFindPassword(User user);
}

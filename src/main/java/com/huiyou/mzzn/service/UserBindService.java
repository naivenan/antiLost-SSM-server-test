package com.huiyou.mzzn.service;

import java.util.List;

import com.huiyou.mzzn.model.UserBind;
import com.huiyou.mzzn.model.UserBindExample;

public interface UserBindService {
	int queryUserBindCount(UserBindExample example);
	List<UserBind> queryUserBindList(UserBindExample example);
	UserBind queryUserBindById(Long id);
	int createUserBind(UserBind userBind);
	int updateUserBind(UserBind userBind);
	int updateUserBindByExample(UserBind userBind,UserBindExample example);
}

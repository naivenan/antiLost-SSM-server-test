package com.huiyou.mzzn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huiyou.mzzn.mapper.UserBindMapper;
import com.huiyou.mzzn.model.UserBind;
import com.huiyou.mzzn.model.UserBindExample;
import com.huiyou.mzzn.service.UserBindService;

@Service
public class UserBindServiceImpl implements UserBindService{

	@Autowired
	private UserBindMapper userBindMapper;
	
	@Override
	public int queryUserBindCount(UserBindExample example) {
		return userBindMapper.countByExample(example);
	}

	@Override
	public List<UserBind> queryUserBindList(UserBindExample example) {
		return userBindMapper.selectByExample(example);
	}

	@Override
	public UserBind queryUserBindById(Long id) {
		return userBindMapper.selectByPrimaryKey(id);
	}

	@Override
	public int createUserBind(UserBind userBind) {
		return userBindMapper.insertSelective(userBind);
	}

	@Override
	public int updateUserBind(UserBind userBind) {
		return userBindMapper.updateByPrimaryKeySelective(userBind);
	}

	@Override
	public int updateUserBindByExample(UserBind userBind,UserBindExample example) {
		return userBindMapper.updateByExampleSelective(userBind, example);
	}
	
	

}

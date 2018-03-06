package com.huiyou.mzzn.mapper;

import com.huiyou.mzzn.model.User;
import com.huiyou.mzzn.model.UserExample;

import java.util.List;

import me.fishlord.common.mybatis.BaseMapper;

import org.apache.ibatis.annotations.Param;

public interface UserMapper extends BaseMapper<User, UserExample, Long> {
	User selectUserByToken(@Param("token") String token);
}
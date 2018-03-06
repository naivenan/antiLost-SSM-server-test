package com.huiyou.mzzn.mapper;

import com.huiyou.mzzn.model.Friend;
import com.huiyou.mzzn.model.FriendExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface FriendMapper extends BaseMapper<Friend, FriendExample, Long> {
}
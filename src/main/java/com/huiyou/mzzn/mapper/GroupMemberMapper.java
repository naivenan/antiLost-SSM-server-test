package com.huiyou.mzzn.mapper;

import com.huiyou.mzzn.model.GroupMember;
import com.huiyou.mzzn.model.GroupMemberExample;

import java.util.List;

import me.fishlord.common.mybatis.BaseMapper;

import org.apache.ibatis.annotations.Param;

public interface GroupMemberMapper extends BaseMapper<GroupMember, GroupMemberExample, Long> {
	int updateIsDel(@Param("groupId")Long groupId,@Param("groupMemberId")Long groupMemberId);
	int updateGroupIsDel(@Param("groupId")Long groupId);
}
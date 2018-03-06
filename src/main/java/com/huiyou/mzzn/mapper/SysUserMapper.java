package com.huiyou.mzzn.mapper;

import com.huiyou.mzzn.model.SysUser;
import com.huiyou.mzzn.model.SysUserExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SysUserMapper extends BaseMapper<SysUser, SysUserExample, Long> {
}
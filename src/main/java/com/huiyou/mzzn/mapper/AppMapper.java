package com.huiyou.mzzn.mapper;

import com.huiyou.mzzn.model.App;
import com.huiyou.mzzn.model.AppExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface AppMapper extends BaseMapper<App, AppExample, Long> {
}
package com.huiyou.mzzn.mapper;

import com.huiyou.mzzn.model.SmsMessage;
import com.huiyou.mzzn.model.SmsMessageExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface SmsMessageMapper extends BaseMapper<SmsMessage, SmsMessageExample, Long> {
}
package com.huiyou.mzzn.mapper;

import com.huiyou.mzzn.model.EmailMessage;
import com.huiyou.mzzn.model.EmailMessageExample;
import java.util.List;
import me.fishlord.common.mybatis.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface EmailMessageMapper extends BaseMapper<EmailMessage, EmailMessageExample, Long> {
}
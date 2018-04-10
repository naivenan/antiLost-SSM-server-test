package com.huiyou.mzzn.service;

import java.util.List;

import me.fishlord.common.result.ResultEntity;

import com.huiyou.mzzn.model.SmsMessage;
import com.huiyou.mzzn.model.SmsMessageExample;
public interface MessageService {

	public ResultEntity sendRegisterMessage(String mobile);
	public ResultEntity sendReplyMessage(String mobile);
	public ResultEntity sendFindMessage(String mobile);
	public ResultEntity sendResetMessage(String mobile,Long userId);
	public ResultEntity sendMobileMessage(String mobile);
	public ResultEntity sendMessageAlert(String mobile, String user, String older, String addr);
	
	int updateSmsMessage(SmsMessage smsMessage);
	List<SmsMessage> querySmsMessage(SmsMessageExample smsMessageExample);
}
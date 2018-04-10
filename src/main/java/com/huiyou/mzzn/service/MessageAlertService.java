package com.huiyou.mzzn.service;

import java.util.List;

import com.huiyou.mzzn.model.MessageAlert;
import com.huiyou.mzzn.model.MessageAlertExample;

public interface MessageAlertService {
	int queryMessageAlertCount(MessageAlertExample example);
	List<MessageAlert> queryMessageAlertList(MessageAlertExample example);
	MessageAlert queryMessageAlertById(Long id);
	int createMessageAlert(MessageAlert messageAlert);
	int updateMessageAlert(MessageAlert messageAlert);
}

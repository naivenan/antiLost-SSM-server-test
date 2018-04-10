package com.huiyou.mzzn.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huiyou.mzzn.mapper.MessageAlertMapper;
import com.huiyou.mzzn.model.MessageAlert;
import com.huiyou.mzzn.model.MessageAlertExample;
import com.huiyou.mzzn.service.MessageAlertService;

@Service
public class MessageAlertServiceImpl implements MessageAlertService {

	@Autowired
	private MessageAlertMapper messageAlertMapper;
	
	@Override
	public int queryMessageAlertCount(MessageAlertExample example) {
		return messageAlertMapper.countByExample(example);
	}

	@Override
	public List<MessageAlert> queryMessageAlertList(MessageAlertExample example) {
		return messageAlertMapper.selectByExample(example);
	}

	@Override
	public MessageAlert queryMessageAlertById(Long id) {
		return messageAlertMapper.selectByPrimaryKey(id);
	}

	@Override
	public int createMessageAlert(MessageAlert messageAlert) {
		return messageAlertMapper.insertSelective(messageAlert);
	}

	@Override
	public int updateMessageAlert(MessageAlert messageAlert) {
		return messageAlertMapper.updateByPrimaryKeySelective(messageAlert);
	}

}

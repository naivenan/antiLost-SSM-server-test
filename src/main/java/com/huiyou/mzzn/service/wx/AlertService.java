package com.huiyou.mzzn.service.wx;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huiyou.mzzn.mapper.MessageAlertMapper;
import com.huiyou.mzzn.mapper.UserMapper;
import com.huiyou.mzzn.model.MessageAlert;
import com.huiyou.mzzn.model.MessageAlertExample;
import com.huiyou.mzzn.model.User;
import com.huiyou.mzzn.pojo.AlertList;

@Service
public class AlertService {
	
	@Autowired
	UserMapper userMapper;
	@Autowired
	MessageAlertMapper messageAlertMapper;
	
	public List<AlertList> list(int bid){
		List<AlertList> r = new ArrayList<>();
		try {
			MessageAlertExample example = new MessageAlertExample();
			example.createCriteria().andPushUserIdEqualTo((long)bid).andStatusEqualTo(2);
			List<MessageAlert> list = messageAlertMapper.selectByExample(example);
			for(MessageAlert mAlert : list){
				User u = userMapper.selectByPrimaryKey(mAlert.getUserId());
				
				AlertList alertlist = new AlertList();
				alertlist.setId(mAlert.getId());
				alertlist.setBid(bid);
				alertlist.setUid(mAlert.getUserId());
				alertlist.setName(u.getUsername());
				alertlist.setImgUrl(u.getHeadPic());
				alertlist.setContent("我摔倒了，快来扶我！");
				
				r.add(alertlist);
				
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return r;
	}
	
	public int cancel(int id){
		int r = 0;
		MessageAlert record = new MessageAlert();
		record.setId((long)id);
		record.setStatus(1);
		try {
			r = messageAlertMapper.updateByPrimaryKeySelective(record);
		} catch (Exception e) {
			System.err.println(e);
		}
		return r;
	}

}

package com.huiyou.mzzn.service.wx;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huiyou.mzzn.mapper.UserBindMapper;
import com.huiyou.mzzn.mapper.UserMapper;
import com.huiyou.mzzn.model.User;
import com.huiyou.mzzn.model.UserBind;
import com.huiyou.mzzn.model.UserBindExample;
import com.huiyou.mzzn.pojo.OlderLocation;

@Service
public class LocationService {
	
	@Autowired
	UserMapper userMapper;
	@Autowired
	UserBindMapper userBindMapper;
	
	public List<OlderLocation> location(int uid){
		List<OlderLocation> list = new ArrayList<>();
		try {
			UserBindExample example = new UserBindExample();
			example.createCriteria().andBindUserIdEqualTo((long)uid);
			List<UserBind> ublist = userBindMapper.selectByExample(example);
			for(UserBind ub : ublist){
				User user = userMapper.selectByPrimaryKey(ub.getUserId());
				OlderLocation location = new OlderLocation();
				location.setId(user.getId().intValue());
				location.setName(user.getUsername());
				location.setLat(ub.getNowLatitude());
				location.setLng(ub.getNowLongitude());
				location.setAddr(ub.getAddress());
				list.add(location);
			}
		} catch (Exception e) {
			System.err.println(e);
		}
		return list;
	}
	
}
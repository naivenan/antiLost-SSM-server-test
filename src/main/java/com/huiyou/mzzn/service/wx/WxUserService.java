package com.huiyou.mzzn.service.wx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huiyou.mzzn.mapper.UserMapper;
import com.huiyou.mzzn.model.User;
import com.huiyou.mzzn.model.UserExample;
import com.huiyou.mzzn.pojo.WxUser;
import com.huiyou.mzzn.utils.StatusCode;

@Service
public class WxUserService {

	@Autowired
	UserMapper userMapper;

	public Map<String, Object> register(User user) {
		Map<String, Object> map = new HashMap<>();
		try {
			userMapper.insertSelective(user);
			map.put("state", StatusCode.SUCCESS);
			map.put("errMessage", null);
		} catch (Exception e) {
			System.err.println(e);
			map.put("state", StatusCode.FAILURE);
			map.put("errMessage", "duplicated user");
		}
		return map;
	}

	public Map<String, Object> login(String user, String password) {
		Map<String, Object> map = new HashMap<>();
		UserExample example = new UserExample();
		example.createCriteria().andUsernameEqualTo(user);
		List<User> list = userMapper.selectByExample(example);
		if (list != null && list.size() > 0) {
			User u = list.get(0);
			if (password.equals(u.getPassword())) {
				WxUser wxUser = new WxUser();
				wxUser.setId(u.getId());
				wxUser.setType(u.getType());
				wxUser.setUser(u.getUsername());
				wxUser.setPassword(u.getPassword());
				wxUser.setName(u.getUsername());
				wxUser.setMphone(u.getMobile());
				wxUser.setBirthday(u.getBirthday());
				wxUser.setImgUrl(u.getHeadPic());
				map.put("userinfo", wxUser);
				map.put("errMessage", null);
			} else {
				map.put("userinfo", null);
				map.put("errMessage", "password not correct");
			}
		} else {
			map.put("userinfo", null);
			map.put("errMessage", "user not exist");
		}
		return map;
	}

	public Map<String, Object> wxlogin(User user) {
		Map<String, Object> map = new HashMap<>();
		UserExample example = new UserExample();
		example.createCriteria().andUsernameEqualTo(user.getUsername());
		List<User> list = userMapper.selectByExample(example);
		User u = null;
		if (list != null && list.size() > 0) {
			u = list.get(0);
		} else {
			userMapper.insertSelective(user);
			u = userMapper.selectByPrimaryKey(user.getId());
		}
		WxUser wxUser = new WxUser();
		wxUser.setId(u.getId());
		wxUser.setType(u.getType());
		wxUser.setUser(u.getUsername());
		wxUser.setPassword(u.getPassword());
		wxUser.setName(u.getUsername());
		wxUser.setMphone(u.getMobile());
		wxUser.setBirthday(u.getBirthday());
		wxUser.setImgUrl(u.getHeadPic());
		map.put("userinfo", wxUser);
		map.put("errMessage", null);
		return map;
	}

	public int update(User user) {
		int r = 0;
		try {
			r = userMapper.updateByPrimaryKeySelective(user);
		} catch (Exception e) {
			System.err.println(e);
		}
		return r;
	}
}

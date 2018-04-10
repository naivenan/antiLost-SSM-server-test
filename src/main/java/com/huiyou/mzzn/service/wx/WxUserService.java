package com.huiyou.mzzn.service.wx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huiyou.mzzn.mapper.SmsMessageMapper;
import com.huiyou.mzzn.mapper.UserMapper;
import com.huiyou.mzzn.model.SmsMessage;
import com.huiyou.mzzn.model.SmsMessageExample;
import com.huiyou.mzzn.model.User;
import com.huiyou.mzzn.model.UserExample;
import com.huiyou.mzzn.pojo.WxUser;
import com.huiyou.mzzn.utils.StatusCode;

import me.fishlord.common.result.ErrorCode;

@Service
public class WxUserService {

	@Autowired
	UserMapper userMapper;
	@Autowired
	private SmsMessageMapper smsMessageMapper;

	public Map<String, Object> register(User user) {
		Map<String, Object> map = new HashMap<>();
		try {
			SmsMessageExample smsMessageExample = new SmsMessageExample();
			smsMessageExample.setOrderByClause("create_time desc");
			smsMessageExample.or().andStatusEqualTo(0).andMobileEqualTo(user.getMobile()).andTypeEqualTo("1");
			List<SmsMessage> smsMessages = smsMessageMapper.selectByExample(smsMessageExample);
			// 是否过期
			if (smsMessages.size() == 0 || !user.getVerifyCode().equals(smsMessages.get(0).getCode())) {
				map.put("state", StatusCode.FAILURE);
				map.put("errMessage", "验证码无效");
			} else {
				for (SmsMessage smsMessage : smsMessages) {
					smsMessage.setStatus(1);
					smsMessageMapper.updateByPrimaryKeySelective(smsMessage);
				}
				user.setPassword(DigestUtils.md5Hex(user.getPassword()));
				userMapper.insertSelective(user);
				map.put("state", StatusCode.SUCCESS);
				map.put("errMessage", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
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
			if (DigestUtils.md5Hex(password).equals(u.getPassword())) {
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

	public int updateMobile(User user) {
		int r = 0;
		SmsMessageExample smsMessageExample = new SmsMessageExample();
		smsMessageExample.setOrderByClause("create_time desc");
		smsMessageExample.or().andStatusEqualTo(0).andMobileEqualTo(user.getMobile()).andTypeEqualTo("5");
		List<SmsMessage> smsMessages = smsMessageMapper.selectByExample(smsMessageExample);
		// 是否过期
		if (smsMessages.size() == 0 || !user.getVerifyCode().equals(smsMessages.get(0).getCode())) {
			r = -1;
		} else {
			for (SmsMessage smsMessage : smsMessages) {
				smsMessage.setStatus(1);
				smsMessageMapper.updateByPrimaryKeySelective(smsMessage);
			}
			r = userMapper.updateByPrimaryKeySelective(user);
		}
		return r;
	}
}

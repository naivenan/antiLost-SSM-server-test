package com.huiyou.mzzn.controller.wx;

import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huiyou.mzzn.model.User;
import com.huiyou.mzzn.service.wx.WxUserService;
import com.huiyou.mzzn.utils.StatusCode;

@RestController
@RequestMapping("/wx/user")
public class WxUserController {
	
	@Autowired
	WxUserService wxUserService;
	
	@RequestMapping("/hello")
	Map<String, Object> hello(){
		Map<String, Object> result = new HashMap<>();
		result.put("data", "hello");
		result.put("code", StatusCode.SUCCESS_CODE);
		return result;
	}
	
	@RequestMapping("/register")
	Map<String, Object> register(String user,String pswd,String tel){
		Map<String, Object> result = new HashMap<>();
		User record = new User();
		record.setUsername(user);
		record.setPassword(pswd);
		record.setMobile(tel);
		record.setType(1);
		Map<String, Object> map = wxUserService.register(record);
		result.put("data", map);
		result.put("code", StatusCode.SUCCESS_CODE);
		return result;
	}
	
	@RequestMapping("/login")
	Map<String, Object> login(String user,String pswd){
		Map<String, Object> result = new HashMap<>();
		Map<String, Object> data = wxUserService.login(user, pswd);
		if(data.size()>0){
			if(data.get("userinfo")!=null){
				data.put("state",StatusCode.SUCCESS);
			}else {
				data.put("state",StatusCode.FAILURE);
			}
		}
		result.put("code", StatusCode.SUCCESS_CODE);
		result.put("data", data);
		return result;
	}
	
	@RequestMapping("/wxlogin")
	Map<String, Object> wxlogin(String user,String nickName,String sex,String imgUrl){
		Map<String, Object> result = new HashMap<>();
		User record = new User();
		record.setType(1);
		record.setUsername(user);
		record.setHeadPic(imgUrl);
		Map<String, Object> data = wxUserService.wxlogin(record);
		if(data.size()>0){
			if(data.get("userinfo")!=null){
				data.put("state",StatusCode.SUCCESS);
			}else {
				data.put("state",StatusCode.FAILURE);
			}
		}
		result.put("code", StatusCode.SUCCESS_CODE);
		result.put("data", data);
		return result;
	}
	
	@RequestMapping("/update")
	Map<String, Object> update(long id,String mphone,String birthday){
		Map<String, Object> result = new HashMap<>();
		User user = new User();
		user.setId(id);
		user.setMobile(mphone);
		user.setBirthday(birthday);
		int data = wxUserService.update(user);
		result.put("code", StatusCode.SUCCESS_CODE);
		result.put("data", data);
		return result;
	}
}

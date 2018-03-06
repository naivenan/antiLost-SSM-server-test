package com.huiyou.mzzn.controller.wx;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.huiyou.mzzn.pojo.WxUser;
import com.huiyou.mzzn.service.wx.OlderService;
import com.huiyou.mzzn.utils.StatusCode;

@RestController
@RequestMapping("/older")
public class OlderController {
	
	@Autowired
	OlderService olderService;
	
	@RequestMapping("/list")
	Map<String, Object> list(int uid){
		Map<String, Object> result = new HashMap<>();
		List<WxUser> list = olderService.list(uid);
		result.put("data", list);
		result.put("code", StatusCode.SUCCESS_CODE);
		return result;
	}
	
	@RequestMapping("/add")
	Map<String, Object> add(int uid,String user){
		Map<String, Object> result = new HashMap<>();
		int data = olderService.add(uid, user);
		result.put("data", data);
		result.put("code", StatusCode.SUCCESS_CODE);
		return result;
	}
	
	@RequestMapping("/delete")
	Map<String, Object> delete(String list,int uid){
		List<WxUser> userList = JSONObject.parseArray(list, WxUser.class);
		Map<String, Object> result = new HashMap<>();
		List<Integer> data = olderService.delete(userList,uid);
		result.put("data", data);
		result.put("code", StatusCode.SUCCESS_CODE);
		return result;
	}
}

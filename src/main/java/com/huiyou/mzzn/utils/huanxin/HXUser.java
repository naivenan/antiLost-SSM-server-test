package com.huiyou.mzzn.utils.huanxin;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.huiyou.mzzn.utils.huanxin.entity.User;
import com.huiyou.mzzn.utils.huanxin.entity.UserEntitie;

import me.fishlord.common.result.ResultEntity;
import me.fishlord.common.utils.JacksonUtils;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


public class HXUser extends HXBase {
	private static final Logger logger = LoggerFactory.getLogger(HXUser.class);

	private static final String Path = "users";
	private static final String passwd = "123456";

	/**
	 * 注册环信用户
	 * 
	 * @param userName
	 * @param nickname
	 * @return
	 */
	@SuppressWarnings({ "unused", "deprecation" })
	public static ResultEntity createUser(String userName, String nickname) {

		User user = new User();
		user.setPassword(passwd);
		user.setUsername(userName);
		user.setNickname(nickname);
		List<User> users = new ArrayList<User>();
		users.add(user);
		return createUsers(users);

	}
	
	public static void main(String[] args) {
		Map<String, Object> map=new HashMap<String, Object>();
		/*map.put("groupname", "香蕉");
		map.put("desc", "666");
		map.put("public", true);
		map.put("members_only", true);
		map.put("allowinvites", true);
		map.put("maxusers", "1000");
		map.put("owner", "1");*/
		
		map.put("groupname", "苹果");
		/*map.put("description", "修改群名称");
		map.put("maxusers", 400);*/
		//users.add(user);
		ResultEntity  resultEntity= HXUser.editGroupName(34572472483841L, map);
	}
	
	/**
	 * 添加好友
	 * @param userName
	 * @param friendName
	 * @return
	 */
	public static ResultEntity addFriendSingle(String userName, String friendName){
		ResultEntity resultEntity=new ResultEntity();
		String url = InterfaceUrl + Path +"/"+userName+ "/contacts/users/" +friendName;
		String repString = HXHttpUtil.post(url,"");
		logger.debug("注册用户返回：" + repString);
		try {
			Map<String, String> map = new HashMap<String, String>();

			JSONObject jsonObject = JSONObject.fromObject(repString);
			String entities = jsonObject.getString("entities");

			List<UserEntitie> userEntities = (List<UserEntitie>) JSONArray.toList(JSONArray.fromObject(entities),
					UserEntitie.class);
			if (userEntities.size() > 0) {
				resultEntity.setCode(0);
				resultEntity.setMsg("ok");
				resultEntity.setData(userEntities);
				return resultEntity;
			}
			resultEntity.setCode(-1);
			resultEntity.setMsg("添加失败");
			return resultEntity;
		}
		catch (Exception e) {
			resultEntity.setCode(-1);
			resultEntity.setMsg("请求失败");
			resultEntity.setData(repString);
			return resultEntity;
		}
	}
	
	/**
	 * 删除好友
	 * @param userName
	 * @param friendName
	 * @return
	 */
	public static ResultEntity delFriendSingle(String userName, String friendName){
		ResultEntity resultEntity=new ResultEntity();
		String url = InterfaceUrl + Path +"/"+userName+ "/contacts/users/" +friendName;
		String repString = HXHttpUtil.delete(url);
		logger.debug("注册用户返回：" + repString);
		try {
			Map<String, String> map = new HashMap<String, String>();

			JSONObject jsonObject = JSONObject.fromObject(repString);
			String entities = jsonObject.getString("entities");

			List<UserEntitie> userEntities = (List<UserEntitie>) JSONArray.toList(JSONArray.fromObject(entities),
					UserEntitie.class);
			if (userEntities.size() > 0) {
				resultEntity.setCode(0);
				resultEntity.setMsg("ok");
				resultEntity.setData(userEntities);
				return resultEntity;
			}
			resultEntity.setCode(-1);
			resultEntity.setMsg("添加失败");
			return resultEntity;
		}
		catch (Exception e) {
			resultEntity.setCode(-1);
			resultEntity.setMsg("请求失败");
			resultEntity.setData(repString);
			return resultEntity;
		}
	}

	/**
	 * 批量注册用户
	 * 
	 * @param users
	 * @return
	 */
	public static ResultEntity createUsers(List<User> users) {
		ResultEntity resultEntity=new ResultEntity();
		
		String url = InterfaceUrl + Path;
		JSONArray listArray = JSONArray.fromObject(users);
		String repString = HXHttpUtil.post(url, listArray.toString());
		logger.debug("注册用户返回：" + repString);
		try {
			Map<String, String> map = new HashMap<String, String>();
			
			JSONObject jsonObject = JSONObject.fromObject(repString);
			String entities = jsonObject.getString("entities");
			
			List<UserEntitie> userEntities = (List<UserEntitie>) JSONArray.toList(JSONArray.fromObject(entities),
					UserEntitie.class);
			if (userEntities.size() > 0) {
				resultEntity.setCode(0);
				resultEntity.setMsg("ok");
				resultEntity.setData(userEntities);
				return resultEntity;
			}
			resultEntity.setCode(-1);
			resultEntity.setMsg("注册失败");
			return resultEntity;
		}
		catch (Exception e) {
			resultEntity.setCode(-1);
			resultEntity.setMsg("请求失败");
			resultEntity.setData(repString);
			return resultEntity;
		}
	}
	
	//创建群组
	public static ResultEntity createGroup(Map<String, Object> body) {
		ResultEntity resultEntity=new ResultEntity();
		
		String url = InterfaceUrl + "chatgroups";
		try {
			String repString = HXHttpUtil.post(url, JacksonUtils.objectToJson(body));
			logger.debug("注册用户返回：" + repString);

			JSONObject jsonObject = JSONObject.fromObject(repString);
			String data = jsonObject.getString("data");
			JSONObject jsonObject2=jsonObject.fromObject(data);
			String groupId=jsonObject2.getString("groupid");

			if (StringUtils.isNotBlank(groupId)) {
				resultEntity.setCode(0);
				resultEntity.setMsg("ok");
				resultEntity.setData(groupId);
			}else{
				resultEntity.setCode(-1);
				resultEntity.setMsg("创建失败");
			}
			return resultEntity;
		}
		catch (Exception e) {
			resultEntity.setCode(-1);
			resultEntity.setMsg("请求失败");
			return resultEntity;
		}
	}
	
	//解散群组
	public static ResultEntity delGroupSingle(String groupId){
		ResultEntity resultEntity=new ResultEntity();
		String url = InterfaceUrl +"chatgroups/"+groupId;
		String repString = HXHttpUtil.delete(url);
		logger.debug("删除群组返回：" + repString);
		try {			
			JSONObject jsonObject = JSONObject.fromObject(repString);
			String data = jsonObject.getString("data");
			JSONObject jsonObject2=jsonObject.fromObject(data);
			String success=jsonObject2.getString("success");

			if (success=="true") {
				resultEntity.setCode(0);
				resultEntity.setMsg("ok");
				resultEntity.setData(groupId);
			}else{
				resultEntity.setCode(-1);
				resultEntity.setMsg("解散失败");
			}
			resultEntity.setCode(-1);
			resultEntity.setMsg("解散失败");
			return resultEntity;
		}
		catch (Exception e) {
			resultEntity.setCode(-1);
			resultEntity.setMsg("请求失败");
			resultEntity.setData(repString);
			return resultEntity;
		}
	}
	//批量移除群成员
	public static ResultEntity delGroupMemberSingle(String groupId,String memberIds){
		ResultEntity resultEntity=new ResultEntity();
		String url = InterfaceUrl +"chatgroups/"+groupId+"/users/"+memberIds;
		String repString = HXHttpUtil.delete(url);
		logger.debug("删除群成员返回：" + repString);
		try {			
			JSONObject jsonObject = JSONObject.fromObject(repString);
			String data = jsonObject.getString("data");
			JSONObject jsonObject2=jsonObject.fromObject(data);
			String result=jsonObject2.getString("result");
			if (result=="true") {
				resultEntity.setCode(0);
				resultEntity.setMsg("ok");
				resultEntity.setData(groupId);
			}else{
				resultEntity.setCode(-1);
				resultEntity.setMsg("删除失败");
			}
			resultEntity.setCode(-1);
			resultEntity.setMsg("删除失败");
			return resultEntity;
		}
		catch (Exception e) {
			resultEntity.setCode(-1);
			resultEntity.setMsg("请求失败");
			resultEntity.setData(repString);
			return resultEntity;
		}
	}
	//批量添加群成员
	public static ResultEntity createGroupUsers(Long groupId,Map<String, Object> map) {
		ResultEntity resultEntity=new ResultEntity();		
		String url = InterfaceUrl+"chatgroups/"+groupId+"/users";
		/*JSONArray listArray = JSONArray.fromObject(users);
		System.out.println(listArray.toString());*/
		String aaString=JacksonUtils.objectToJson(map).toString();
		String repString = HXHttpUtil.post(url, aaString);
		logger.debug("添加群成员返回：" + repString);
		try {
			//Map<String, String> map = new HashMap<String, String>();
			
			JSONObject jsonObject = JSONObject.fromObject(repString);
			String entities = jsonObject.getString("entities");

			List<UserEntitie> userEntities = (List<UserEntitie>) JSONArray.toList(JSONArray.fromObject(entities),
					UserEntitie.class);
			if (userEntities.size() > 0) {
				resultEntity.setCode(0);
				resultEntity.setMsg("ok");
				resultEntity.setData(userEntities);
				return resultEntity;
			}
			resultEntity.setCode(-1);
			resultEntity.setMsg("添加群成员失败");
			return resultEntity;
		}
		catch (Exception e) {
			resultEntity.setCode(-1);
			resultEntity.setMsg("请求失败");
			resultEntity.setData(repString);
			return resultEntity;
		}
	}
	//修改群组名称
	public static ResultEntity editGroupName(Long groupId,Map<String, Object> map){
		ResultEntity resultEntity=new ResultEntity();
		String url = InterfaceUrl+"chatgroups/"+groupId;
		String repString = HXHttpUtil.put(url,JacksonUtils.objectToJson(map));
		logger.debug("修改群名称返回：" + repString);
		try {
			JSONObject jsonObject = JSONObject.fromObject(repString);
			String entities = jsonObject.getString("entities");
			List<UserEntitie> userEntities = (List<UserEntitie>) JSONArray.toList(JSONArray.fromObject(entities),
					UserEntitie.class);
			if (userEntities.size() > 0) {
				resultEntity.setCode(0);
				resultEntity.setMsg("ok");
				resultEntity.setData(userEntities);
				return resultEntity;
			}
			resultEntity.setCode(-1);
			resultEntity.setMsg("添加群成员失败");
			return resultEntity;
		}catch (Exception e) {
			resultEntity.setCode(-1);
			resultEntity.setMsg("请求失败");
			resultEntity.setData(repString);
			return resultEntity;
		}
	}
}

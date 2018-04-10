package com.huiyou.mzzn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.huiyou.mzzn.model.MessageAlert;
import com.huiyou.mzzn.model.User;
import com.huiyou.mzzn.model.UserBind;
import com.huiyou.mzzn.model.UserBindExample;
import com.huiyou.mzzn.service.MessageAlertService;
import com.huiyou.mzzn.service.MessageService;
import com.huiyou.mzzn.service.UserBindService;
import com.huiyou.mzzn.service.UserService;
import com.huiyou.mzzn.utils.AMapUtils;
import com.huiyou.mzzn.utils.JPushUtil;
import com.huiyou.mzzn.utils.LatLng;

import me.fishlord.common.result.ErrorCode;
import me.fishlord.common.result.ResultEntity;

@Controller
public class MessageAlertController {

	@Autowired
	private MessageAlertService messageAlertService;
	
	@Autowired
	private MessageService messageService;

	@Autowired
	private UserBindService userBindService;
	
	@Autowired
	private UserService userService;


	/**
	 * 消息提醒
	 * 
	 * @param userId
	 * @param longitude
	 * @param latitude
	 * @param bindUserId
	 * @param nowLatitude
	 * @param nowLongitude
	 * @return
	 */
	@RequestMapping(value = "/messageAlert/add")
	@ResponseBody
	public ResultEntity push(@RequestAttribute Long userId,
			String longitude, String latitude,String address) {
		ResultEntity resultEntity = new ResultEntity();		
		LatLng latLng = new LatLng(Double.valueOf(latitude), Double.valueOf(longitude));		
		UserBindExample example = new UserBindExample();
		example.or().andIsDelEqualTo(0).andUserIdEqualTo(userId).andIsSetEqualTo(1);
		List<UserBind> userBinds = userBindService.queryUserBindList(example);
		// 设置的半径
		if (userBinds.size()>0) {
			for (UserBind userBind : userBinds) {
				LatLng nowLatLng = new LatLng(Double.valueOf(userBind.getLatitude()), Double.valueOf(userBind.getLongitude()));
				// 实时的半径
				float aa = AMapUtils.calculateLineDistance(nowLatLng, latLng);
				//绑定半径
				if (userBind.getRailRadius()!=null&&aa>userBind.getRailRadius()) {				
						userBind.setStatus(1);					
				}else {
					//没超出
					userBind.setStatus(0);
				}
				userBind.setNowLatitude(Double.valueOf(latitude));
				userBind.setNowLongitude(Double.valueOf(longitude));
				userBind.setAddress(address);
				userBindService.updateUserBind(userBind);
			}
			resultEntity.setCode(ErrorCode.SUCCESS);
		}else {
			resultEntity.setCode(ErrorCode.ERROR);
			resultEntity.setMsg("您绑定的用户不存在");
		}		
		return resultEntity;
	}
	
	/**
	 *跌倒提醒 
	 * @param userId
	 * @param pushUserId
	 * @return
	 */
	@RequestMapping("/messageAlert/tumble")
	@ResponseBody
	public ResultEntity tumble( Long userId,String latitude,String longitude,String address){
		ResultEntity resultEntity=new ResultEntity();
		User older = userService.queryUserById(userId);
		UserBindExample example=new UserBindExample();
		example.or().andIsDelEqualTo(0).andUserIdEqualTo(userId);
		List<UserBind> userBinds=userBindService.queryUserBindList(example);
		if (userBinds.size()>0) {
			for (UserBind userBind : userBinds) {
				User user=userService.queryUserById(userBind.getBindUserId());
				if (user!=null) {
					String pushToken=user.getPushToken();
					MessageAlert alert=new MessageAlert();
					alert.setStatus(2);
					alert.setUserId(userId);
					alert.setPushUserId(user.getId());
					alert.setType(2);
					if(pushToken==null){
						messageService.sendMessageAlert(user.getMobile(), user.getUsername(), older.getUsername(), address);
					}else{
//						JPushUtil.sendNotification("10000", "4_跌倒了,请前往救助_"+address+"_"+longitude+"_"+latitude, pushToken);
					}
					messageAlertService.createMessageAlert(alert);					
				}
			}
			resultEntity.setCode(ErrorCode.SUCCESS);
		}else {
			resultEntity.setCode(ErrorCode.ERROR);
			resultEntity.setMsg("您未绑定任何家属");
		}
		return resultEntity;
	}
}

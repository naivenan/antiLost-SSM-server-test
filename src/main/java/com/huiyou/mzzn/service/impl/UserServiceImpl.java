package com.huiyou.mzzn.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huiyou.mzzn.mapper.EmailMessageMapper;
import com.huiyou.mzzn.mapper.SmsMessageMapper;
import com.huiyou.mzzn.mapper.UserMapper;
import com.huiyou.mzzn.model.SmsMessage;
import com.huiyou.mzzn.model.SmsMessageExample;
import com.huiyou.mzzn.model.User;
import com.huiyou.mzzn.model.UserExample;
import com.huiyou.mzzn.model.UserExample.Criteria;
import com.huiyou.mzzn.service.UserService;
import com.huiyou.mzzn.utils.TokenUtil;
import com.huiyou.mzzn.utils.huanxin.HXUser;

import me.fishlord.common.result.ErrorCode;
import me.fishlord.common.result.ResultEntity;

@Service
public class UserServiceImpl implements UserService {

	private static final Logger logger = LoggerFactory
			.getLogger(UserServiceImpl.class);

	public static final String MOBILE_PATTERN = "^((13[0-9])|(15[0-9])|(18[0-9])|(17[0-9]))\\d{8}$";

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private SmsMessageMapper smsMessageMapper;
	@Autowired
	private EmailMessageMapper emailMessageMapper;

	@Override
	public int queryUserCount(UserExample example) {
		return userMapper.countByExample(example);
	}

	@Override
	public List<User> queryUserList(UserExample example) {
		return userMapper.selectByExample(example);
	}

	@Override
	public User queryUserById(Long id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public ResultEntity createUser(User user) {
		ResultEntity resultEntity = new ResultEntity();
		try {
			SmsMessageExample smsMessageExample = new SmsMessageExample();
			smsMessageExample.setOrderByClause("create_time desc");
			smsMessageExample.or().andStatusEqualTo(0)
					.andMobileEqualTo(user.getMobile()).andTypeEqualTo("1");
			List<SmsMessage> smsMessages = smsMessageMapper
					.selectByExample(smsMessageExample);
			// 是否过期
			if (smsMessages.size() == 0
					|| !user.getVerifyCode().equals(
							smsMessages.get(0).getCode())) {
				resultEntity.setCode(ErrorCode.ERROR);
				resultEntity.setMsg("验证码无效");
			} else {
				for (SmsMessage smsMessage : smsMessages) {
					smsMessage.setStatus(1);
					smsMessageMapper.updateByPrimaryKeySelective(smsMessage);
				}
				UserExample example = new UserExample();
				example.or().andMobileEqualTo(user.getMobile());
				List<User> list = userMapper.selectByExample(example);
				if (list.size() > 0) {
					resultEntity.setCode(ErrorCode.ERROR);
					resultEntity.setMsg("此号码已注册");
				} else {
					example.clear();
					example.or().andUsernameEqualTo(user.getUsername());
					List<User> list2 = userMapper.selectByExample(example);
					if (list2.size() > 0) {
						resultEntity.setCode(ErrorCode.ERROR);
						resultEntity.setMsg("此用户名已注册");
					} else {
						Pattern p = Pattern.compile(MOBILE_PATTERN);
						Matcher m = p.matcher(user.getUsername());
						if (m.matches()) {
							if (!user.getUsername().equals(user.getMobile())) {
								resultEntity.setCode(ErrorCode.ERROR);
								resultEntity.setMsg("用户名格式错误");
								return resultEntity;
							}
						}

						String token = TokenUtil.getInstance()
								.generateTokenUUID();
						// 密码加密
						user.setPassword(DigestUtils.md5Hex(user.getPassword()));
						user.setToken(token);
						userMapper.insertSelective(user);
						HXUser.createUser(user.getId().toString(),
								user.getUsername());
						resultEntity.setCode(ErrorCode.SUCCESS);
						Map<String, Object> map = new HashMap<String, Object>();
						map.put("token", token);
						map.put("id", user.getId());
						map.put("type", user.getType());
						resultEntity.setData(map);
					}
				}
			}
		} catch (Exception e) {
			logger.error("createUser error", e);
			resultEntity.setCode(ErrorCode.ERROR);
			resultEntity.setMsg("用户注册失败");
		}
		return resultEntity;
	}

	@Override
	public int updateUser(User user) {
		return userMapper.updateByPrimaryKeySelective(user);
	}

	@Override
	public ResultEntity doLogin(User user) {
		ResultEntity resultEntity = new ResultEntity();
		
		UserExample example = new UserExample();
		Criteria criteria = example.or().andIsDelEqualTo(0)
				.andPasswordEqualTo(DigestUtils.md5Hex(user.getPassword()));
		if (StringUtils.isNotBlank(user.getMobile())) {
			criteria.andMobileEqualTo(user.getMobile());
		} else {
			criteria.andUsernameEqualTo(user.getUsername());
		}
		List<User> list = userMapper.selectByExample(example);
		if (list.size() == 0) {
			resultEntity.setCode(ErrorCode.ERROR);
			resultEntity.setMsg("用户名或密码错误");
		} else {
			String token = TokenUtil.getInstance().generateTokenUUID();
			String pushToken=user.getPushToken();
			Integer type=user.getType();
			user = list.get(0);
			user.setToken(token);
			user.setPushToken(pushToken);
			userMapper.updateByPrimaryKeySelective(user);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("token", token);
			map.put("id", user.getId());
			map.put("type", user.getType());
			resultEntity.setData(map);
			resultEntity.setCode(ErrorCode.SUCCESS);
		}
		return resultEntity;
	}

	@Override
	public ResultEntity findPassword(User user) {
		ResultEntity resultEntity = new ResultEntity();
		try {
			SmsMessageExample smsMessageExample = new SmsMessageExample();
			smsMessageExample.setOrderByClause("create_time desc");
			smsMessageExample.or().andStatusEqualTo(0)
					.andMobileEqualTo(user.getMobile()).andTypeEqualTo("2");
			List<SmsMessage> smsMessages = smsMessageMapper
					.selectByExample(smsMessageExample);
			// 是否过期
			if (smsMessages.size() == 0
					|| !user.getVerifyCode().equals(
							smsMessages.get(0).getCode())) {
				resultEntity.setCode(ErrorCode.ERROR);
				resultEntity.setMsg("验证码无效");
			} else {
				for (SmsMessage smsMessage : smsMessages) {
					smsMessage.setStatus(1);
					smsMessageMapper.updateByPrimaryKeySelective(smsMessage);
				}
				UserExample example = new UserExample();
				example.or().andMobileEqualTo(user.getMobile());
				List<User> list = userMapper.selectByExample(example);
				if (list.size() == 0) {
					resultEntity.setCode(ErrorCode.ERROR);
					resultEntity.setMsg("用户未存在");
				} else {
					User exist = list.get(0);
					exist.setPassword(DigestUtils.md5Hex(user.getPassword()));
					String token = TokenUtil.getInstance().generateTokenUUID();
					exist.setToken(token);
					userMapper.updateByPrimaryKeySelective(exist);
					resultEntity.setCode(ErrorCode.SUCCESS);
					resultEntity.setData(token);
				}
			}
		} catch (Exception e) {
			logger.error("modifyMobile error", e);
			resultEntity.setCode(ErrorCode.ERROR);
			resultEntity.setData("服务器繁忙");
		}
		return resultEntity;
	}

	@Override
	public ResultEntity resetPassword(User user, String oldpassword) {
		ResultEntity resultEntity = new ResultEntity();
		try {
			SmsMessageExample smsMessageExample = new SmsMessageExample();
			smsMessageExample.setOrderByClause("create_time desc");
			smsMessageExample.or().andStatusEqualTo(0)
					.andMobileEqualTo(user.getMobile()).andTypeEqualTo("1");
			List<SmsMessage> smsMessages = smsMessageMapper
					.selectByExample(smsMessageExample);
			// 是否过期
			if (smsMessages.size() == 0
					|| !user.getVerifyCode().equals(
							smsMessages.get(0).getCode())) {
				resultEntity.setCode(ErrorCode.ERROR);
				resultEntity.setMsg("验证码无效");
			} else {
				for (SmsMessage smsMessage : smsMessages) {
					smsMessage.setStatus(1);
					smsMessageMapper.updateByPrimaryKeySelective(smsMessage);
				}
				User exist = userMapper.selectByPrimaryKey(user.getId());
				if (exist == null) {
					resultEntity.setCode(ErrorCode.ERROR);
					resultEntity.setMsg("用户未存在");
				} else {
					if (!exist.getPassword().equals(oldpassword)) {
						resultEntity.setCode(ErrorCode.ERROR);
						resultEntity.setMsg("原密码错误");
						return resultEntity;
					}
					if (!exist.getMobile().equals(user.getMobile())) {
						resultEntity.setCode(ErrorCode.ERROR);
						resultEntity.setMsg("手机号和登录手机号不符，请重新输入");
						return resultEntity;
					}
					exist.setPassword(DigestUtils.md5Hex(user.getPassword()));
					String token = TokenUtil.getInstance().generateTokenUUID();
					exist.setToken(token);
					userMapper.updateByPrimaryKeySelective(exist);
					resultEntity.setCode(ErrorCode.SUCCESS);
					resultEntity.setData(token);
				}
			}
		} catch (Exception e) {
			logger.error("modifyMobile error", e);
			resultEntity.setCode(ErrorCode.ERROR);
			resultEntity.setData("服务器繁忙");
		}
		return resultEntity;
	}

	// 通过邮箱找回密码
	@Override
	public ResultEntity emailFindPassword(User user) {
		ResultEntity resultEntity = new ResultEntity();
		try {
			User exist = userMapper.selectByPrimaryKey(user.getId());
			if (exist == null) {
				resultEntity.setCode(ErrorCode.ERROR);
				resultEntity.setMsg("用户未存在");
			} else {
				exist.setPassword(DigestUtils.md5Hex(user.getPassword()));
				String token = TokenUtil.getInstance().generateTokenUUID();
				exist.setToken(token);
				userMapper.updateByPrimaryKeySelective(exist);
				resultEntity.setCode(ErrorCode.SUCCESS);
				resultEntity.setData(token);
			}

		} catch (Exception e) {
			logger.error("modifyMobile error", e);
			resultEntity.setCode(ErrorCode.ERROR);
			resultEntity.setData("服务器繁忙");
		}
		return resultEntity;
	}

	@Override
	public ResultEntity login(String openid, String headimgurl, Integer type,String pushToken) {
		ResultEntity resultEntity=new ResultEntity();
		try {
			UserExample example=new UserExample();
			if (type==1) {
				example.or().andIsDelEqualTo(0).andWeixinTokenEqualTo(openid);
			}else{
				example.or().andIsDelEqualTo(0).andWeiboTokenEqualTo(openid);
			}
			List<User> list = userMapper.selectByExample(example);
			if (list.size() == 0) {
				resultEntity.setCode(3000);
			}else {
				User user = list.get(0);
				String token = TokenUtil.getInstance().generateTokenUUID();
				user.setToken(token);
				user.setPushToken(pushToken);
				userMapper.updateByPrimaryKeySelective(user);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("token", token);
				map.put("id", user.getId());
				map.put("type", user.getType());
				resultEntity.setData(map);
				resultEntity.setCode(ErrorCode.SUCCESS);
			}
			
		} catch (Exception e) {
			logger.error("doLogin error",e);
			resultEntity.setCode(ErrorCode.ERROR);
			resultEntity.setMsg("系统繁忙");
		}
		return resultEntity;
	}

	@Override
	public ResultEntity bind(User user, String openid,
			String headimgurl, Integer type) {
		ResultEntity resultEntity=new ResultEntity();
		try {
			UserExample example = new UserExample();
			Criteria criteria = example.or().andIsDelEqualTo(0)
					.andPasswordEqualTo(DigestUtils.md5Hex(user.getPassword()));
			if (StringUtils.isNotBlank(user.getMobile())) {
				criteria.andMobileEqualTo(user.getMobile());
			} else {
				criteria.andUsernameEqualTo(user.getUsername());
			}
			List<User> list = userMapper.selectByExample(example);
			if (list.size() == 0) {
				resultEntity.setCode(ErrorCode.ERROR);
				resultEntity.setMsg("用户名或密码错误");
			}else{
				String token = TokenUtil.getInstance().generateTokenUUID();
				User update = list.get(0);
				update.setToken(token);
				update.setHeadPic(headimgurl);
				if (type == 1) {
					update.setWeixinToken(openid);
				}else {
					update.setWeiboToken(openid);
				}
				userMapper.updateByPrimaryKeySelective(update);
				Map<String, Object> map = new HashMap<String, Object>();
				map.put("token", token);
				map.put("id", update.getId());
				map.put("type", update.getType());
				resultEntity.setData(map);
				resultEntity.setCode(ErrorCode.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("bind error",e);
			resultEntity.setCode(ErrorCode.ERROR);
			resultEntity.setMsg("系统繁忙");
		}
		return resultEntity;
	}

}

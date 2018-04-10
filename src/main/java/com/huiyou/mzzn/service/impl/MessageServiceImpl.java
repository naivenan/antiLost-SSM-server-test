package com.huiyou.mzzn.service.impl;

import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import com.huiyou.mzzn.mapper.SmsMessageMapper;
import com.huiyou.mzzn.mapper.UserMapper;
import com.huiyou.mzzn.model.SmsMessage;
import com.huiyou.mzzn.model.SmsMessageExample;
import com.huiyou.mzzn.model.User;
import com.huiyou.mzzn.model.UserExample;
import com.huiyou.mzzn.service.MessageService;
import com.huiyou.mzzn.utils.SmsUtils;

import me.fishlord.common.result.ErrorCode;
import me.fishlord.common.result.ResultEntity;

@Service
public class MessageServiceImpl implements MessageService {

	private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

	// 产品名称:云通信短信API产品,开发者无需替换
	static final String product = "Dysmsapi";
	// 产品域名,开发者无需替换
	static final String domain = "dysmsapi.aliyuncs.com";

	// TODO 此处需要替换成开发者自己的AK(在阿里云访问控制台寻找)
	static final String accessKeyId = "LTAIuKJv9Up4lsha";
	static final String accessKeySecret = "9Ywe51N7nGG00wR9CdLDz23Hq0zYDB";

	// 注册模板
	private String regTemplateCode = "SMS_71265642";
	// 找回密码
	private String findTemplateCode = "SMS_109345209";
	// 修改密码
	private String resetTemplateCode = "CODESMS_109565206";
	// 用户反馈回复
	private String replyTemplateCode = "SMS_71310725";
	// 修改手机号
	private String resetMobileCode = "SMS_114985013";
	// 跌倒提醒
	private String messageAlertCode = "SMS_126359699";

	// @Autowired
	// private SendMessageDao sendMessageDao;
	@Autowired
	private UserMapper userMapper;

	@Autowired
	private SmsMessageMapper smsMessageMapper;

	/**
	 * 短信发送接口
	 * 
	 * @param smsMob
	 * @param smsText
	 * @throws Exception
	 */
	public String sendMessage(String smsTemplateCode, String recNum, String extend) {

		try {
			// 可自助调整超时时间
			System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
			System.setProperty("sun.net.client.defaultReadTimeout", "10000");

			// 初始化acsClient,暂不支持region化
			IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId, accessKeySecret);
			DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
			IAcsClient acsClient = new DefaultAcsClient(profile);

			// 组装请求对象-具体描述见控制台-文档部分内容
			SendSmsRequest request = new SendSmsRequest();
			// 必填:待发送手机号
			request.setPhoneNumbers(recNum);
			// 必填:短信签名-可在短信控制台中找到
			request.setSignName("迈臻智能");
			// 必填:短信模板-可在短信控制台中找到
			request.setTemplateCode(smsTemplateCode);
			// 可选:模板中的变量替换JSON串,如模板内容为"亲爱的${name},您的验证码为${code}"时,此处的值为
			request.setTemplateParam("{\"code\":\"" + extend + "\", \"time\":\"5\"}");
			// 可选:outId为提供给业务方扩展字段,最终在短信回执消息中将此值带回给调用者
			// request.setOutId("yourOutId");

			// hint 此处可能会抛出异常，注意catch
			SendSmsResponse sendSmsResponse = acsClient.getAcsResponse(request);
			System.out.println("短信接口返回的数据----------------");
			System.out.println("Code=" + sendSmsResponse.getCode());
			System.out.println("Message=" + sendSmsResponse.getMessage());
			System.out.println("RequestId=" + sendSmsResponse.getRequestId());
			System.out.println("BizId=" + sendSmsResponse.getBizId());
			if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
				return "1";
			} else {
				return "-1";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "-1";
		}
	}

	/**
	 * 随机生成六位数
	 * 
	 * @return
	 */
	private int nextInt() {
		Random random = new Random();
		int x = random.nextInt(899999);
		x = x + 100000;
		return x;
	}

	@Override
	public ResultEntity sendRegisterMessage(String mobile) {
		ResultEntity resultEntity = new ResultEntity();
		try {
			SmsMessageExample smsMessageExample = new SmsMessageExample();
			smsMessageExample.setOrderByClause("create_time desc");
			smsMessageExample.or().andStatusEqualTo(0).andMobileEqualTo(mobile).andTypeEqualTo("1");
			List<SmsMessage> smsMessages = smsMessageMapper.selectByExample(smsMessageExample);
			String verifyCode = "";
			if (smsMessages.size() > 0) {
				verifyCode = smsMessages.get(0).getCode();
			}
			if (!StringUtils.isBlank(verifyCode)) {
				SmsUtils.sendMessage(mobile, verifyCode, resetMobileCode);
				resultEntity.setCode(ErrorCode.SUCCESS);
			} else {
				UserExample example = new UserExample();
				example.or().andIsDelEqualTo(0).andMobileEqualTo(mobile);
				int count = userMapper.countByExample(example);
				if (count > 0) {
					resultEntity.setCode(ErrorCode.ERROR);
					resultEntity.setMsg("此号码已注册");
				} else {
					verifyCode = String.valueOf(nextInt());
					logger.info("验证码为：============================================" + verifyCode);
					boolean result = SmsUtils.sendMessage(mobile, verifyCode, regTemplateCode);
					if (result) {
						// 验证码写入数据库
						SmsMessage smsMessage = new SmsMessage();
						smsMessage.setMobile(mobile);
						smsMessage.setType("1");
						smsMessage.setCode(verifyCode);
						smsMessageMapper.insertSelective(smsMessage);
						resultEntity.setCode(ErrorCode.SUCCESS);
					} else {
						resultEntity.setCode(ErrorCode.ERROR);
						resultEntity.setMsg("系统繁忙");
					}
				}
			}
		} catch (Exception e) {
			logger.error("sendRegisterMessage error", e);
			resultEntity.setCode(ErrorCode.ERROR);
			resultEntity.setMsg("系统繁忙");
		}
		return resultEntity;
	}

	@Override
	public ResultEntity sendReplyMessage(String mobile) {
		ResultEntity resultEntity = new ResultEntity();
		// String verifyCode=String.valueOf(nextInt());
		boolean result = SmsUtils.sendMessage(mobile, null, replyTemplateCode);
		if (result) {
			SmsMessage smsMessage = new SmsMessage();
			// smsMessage.setCode(verifyCode);
			smsMessage.setMobile(mobile);
			smsMessage.setType("4");
			smsMessageMapper.insertSelective(smsMessage);
			resultEntity.setCode(ErrorCode.SUCCESS);
		} else {
			resultEntity.setCode(ErrorCode.ERROR);
			resultEntity.setMsg("系统繁忙");
		}
		return resultEntity;
	}

	@Override
	public ResultEntity sendFindMessage(String mobile) {
		ResultEntity resultEntity = new ResultEntity();
		try {
			SmsMessageExample smsMessageExample = new SmsMessageExample();
			smsMessageExample.setOrderByClause("create_time desc");
			smsMessageExample.or().andStatusEqualTo(0).andMobileEqualTo(mobile).andTypeEqualTo("2");
			List<SmsMessage> smsMessages = smsMessageMapper.selectByExample(smsMessageExample);
			String verifyCode = "";
			if (smsMessages.size() > 0) {
				verifyCode = smsMessages.get(0).getCode();
			}
			if (!StringUtils.isBlank(verifyCode)) {
				SmsUtils.sendMessage(mobile, verifyCode, findTemplateCode);
				resultEntity.setCode(ErrorCode.SUCCESS);
			} else {
				verifyCode = String.valueOf(nextInt());
				logger.info("验证码为：============================================" + verifyCode);
				boolean result = SmsUtils.sendMessage(mobile, verifyCode, findTemplateCode);
				if (result) {
					// 验证码写入数据库
					SmsMessage smsMessage = new SmsMessage();
					smsMessage.setMobile(mobile);
					smsMessage.setType("2");
					smsMessage.setCode(verifyCode);
					smsMessageMapper.insertSelective(smsMessage);
					resultEntity.setCode(ErrorCode.SUCCESS);
				} else {
					resultEntity.setCode(ErrorCode.ERROR);
					resultEntity.setMsg("系统繁忙");
				}
			}
		} catch (Exception e) {
			logger.error("sendRegisterMessage error", e);
			resultEntity.setCode(ErrorCode.ERROR);
			resultEntity.setMsg("系统繁忙");
		}
		return resultEntity;
	}

	@Override
	public ResultEntity sendResetMessage(String mobile, Long userId) {
		ResultEntity resultEntity = new ResultEntity();
		try {
			SmsMessageExample smsMessageExample = new SmsMessageExample();
			smsMessageExample.setOrderByClause("create_time desc");
			smsMessageExample.or().andStatusEqualTo(0).andMobileEqualTo(mobile).andTypeEqualTo("3");
			List<SmsMessage> smsMessages = smsMessageMapper.selectByExample(smsMessageExample);
			String verifyCode = "";
			if (smsMessages.size() > 0) {
				verifyCode = smsMessages.get(0).getCode();
			}
			if (!StringUtils.isBlank(verifyCode)) {
				SmsUtils.sendMessage(mobile, verifyCode, resetTemplateCode);
				resultEntity.setCode(ErrorCode.SUCCESS);
			} else {
				User user = userMapper.selectByPrimaryKey(userId);
				if (!user.getMobile().equals(mobile)) {
					resultEntity.setCode(ErrorCode.ERROR);
					resultEntity.setMsg("手机号和登录手机号不符，请重新输入");
					return resultEntity;
				}
				verifyCode = String.valueOf(nextInt());
				logger.info("验证码为：============================================" + verifyCode);
				boolean result = SmsUtils.sendMessage(mobile, verifyCode, resetTemplateCode);
				if (result) {
					// 验证码写入数据库
					SmsMessage smsMessage = new SmsMessage();
					smsMessage.setMobile(mobile);
					smsMessage.setType("3");
					smsMessage.setCode(verifyCode);
					smsMessageMapper.insertSelective(smsMessage);
					resultEntity.setCode(ErrorCode.SUCCESS);
				} else {
					resultEntity.setCode(ErrorCode.ERROR);
					resultEntity.setMsg("系统繁忙");
				}
			}
		} catch (Exception e) {
			logger.error("sendRegisterMessage error", e);
			resultEntity.setCode(ErrorCode.ERROR);
			resultEntity.setMsg("系统繁忙");
		}
		return resultEntity;
	}

	@Override
	public ResultEntity sendMobileMessage(String mobile) {
		ResultEntity resultEntity = new ResultEntity();
		try {
			SmsMessageExample smsMessageExample = new SmsMessageExample();
			smsMessageExample.setOrderByClause("create_time desc");
			smsMessageExample.or().andStatusEqualTo(0).andMobileEqualTo(mobile).andTypeEqualTo("5");
			List<SmsMessage> smsMessages = smsMessageMapper.selectByExample(smsMessageExample);
			String verifyCode = "";
			if (smsMessages.size() > 0) {
				verifyCode = smsMessages.get(0).getCode();
			}
			if (StringUtils.isNotBlank(verifyCode)) {
				SmsUtils.sendMessage(mobile, verifyCode, resetMobileCode);
				resultEntity.setCode(ErrorCode.SUCCESS);
			} else {
				verifyCode = String.valueOf(nextInt());
				boolean result = SmsUtils.sendMessage(mobile, verifyCode, resetMobileCode);
				if (result) {
					SmsMessage smsMessage = new SmsMessage();
					smsMessage.setMobile(mobile);
					smsMessage.setType("5");
					smsMessage.setCode(verifyCode);
					smsMessageMapper.insertSelective(smsMessage);
					resultEntity.setCode(ErrorCode.SUCCESS);
				} else {
					resultEntity.setCode(ErrorCode.ERROR);
					resultEntity.setMsg("系统繁忙");
				}
			}
		} catch (Exception e) {
			logger.error("sendRegisterMessage error", e);
			resultEntity.setCode(ErrorCode.ERROR);
			resultEntity.setMsg("系统繁忙");
		}
		return resultEntity;
	}

	@Override
	public ResultEntity sendMessageAlert(String mobile, String user, String older, String addr) {
		ResultEntity resultEntity = new ResultEntity();
		try {
			String verifyCode = user + "_" + older + "_" + addr;
			// String result = SmsUtils.sendMessageAlert(mobile, user, older, addr, messageAlertCode);

			SendSmsResponse response = SmsUtils.sendSms(mobile, user, older, addr, messageAlertCode);
			System.out.println("短信接口返回的数据----------------");
			System.out.println("Code=" + response.getCode());
			System.out.println("Message=" + response.getMessage());
			System.out.println("RequestId=" + response.getRequestId());
			System.out.println("BizId=" + response.getBizId());

			if (response.getCode().equals("OK")) {
				SmsMessage smsMessage = new SmsMessage();
				smsMessage.setMobile(mobile);
				// TODO:6表示小程序警报短信
				smsMessage.setType("6");
				smsMessage.setCode(verifyCode);
				smsMessageMapper.insertSelective(smsMessage);
				resultEntity.setCode(ErrorCode.SUCCESS);
			} else {
				resultEntity.setCode(ErrorCode.ERROR);
				resultEntity.setMsg("系统繁忙");
			}
		} catch (Exception e) {
			logger.error("sendRegisterMessage error", e);
			resultEntity.setCode(ErrorCode.ERROR);
			resultEntity.setMsg("系统繁忙");
		}
		return resultEntity;
	}

	@Override
	public int updateSmsMessage(SmsMessage smsMessage) {
		return smsMessageMapper.updateByPrimaryKeySelective(smsMessage);
	}

	@Override
	public List<SmsMessage> querySmsMessage(SmsMessageExample smsMessageExample) {
		return smsMessageMapper.selectByExample(smsMessageExample);
	}
}
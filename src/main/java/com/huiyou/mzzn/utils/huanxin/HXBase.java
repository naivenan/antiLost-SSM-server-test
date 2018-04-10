package com.huiyou.mzzn.utils.huanxin;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import me.fishlord.common.utils.HttpUtils;
import me.fishlord.common.utils.JacksonUtils;
import net.sf.json.JSONObject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.huiyou.mzzn.mapper.UserMapper;
import com.huiyou.mzzn.utils.cache.Cache;
import com.huiyou.mzzn.utils.cache.CacheManager;


public class HXBase extends HXConfig {
	private static final Logger logger = LoggerFactory.getLogger(HXBase.class);
	
	@Autowired
	private UserMapper userMapper;
	
	static String HXTokenCacheKey = "baoyihuiyang_HxToken";
	/**
	 * 缓存过期时间（秒）
	 */
	static final int CacheKeyTimeout = 1000*60 * 60 * 24;

	protected static String getAccessToken() {

		Cache checkCache = CacheManager.getCacheInfo(HXTokenCacheKey);
		String accessToken="";
		if (checkCache!=null) {
			accessToken =(String) checkCache.getValue();
		}
		if (StringUtils.isBlank(accessToken)) {

			Map<String, String> map = new HashMap<String, String>();
			map.put("grant_type", "client_credentials");
			map.put("client_id",ClientID);
			map.put("client_secret",ClientSecret);

			try {
				String rep = HttpUtils.post(InterfaceUrl + "token", JacksonUtils.objectToJson(map));
				logger.debug("获取token返回的信息：" + rep);
				JSONObject jsonObject = JSONObject.fromObject(rep);
				accessToken = jsonObject.getString("access_token");
				Cache cache = new Cache();
				cache.setKey(HXTokenCacheKey);
				cache.setValue(accessToken);
				cache.setTimeOut(new Date().getTime() + CacheKeyTimeout);
				CacheManager.putCache(HXTokenCacheKey,cache);
				return accessToken;
			}
			catch (Exception e) {
			}
		}
		return accessToken;
	}
}

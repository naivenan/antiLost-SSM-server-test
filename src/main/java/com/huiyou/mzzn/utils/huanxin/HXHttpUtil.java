package com.huiyou.mzzn.utils.huanxin;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import me.fishlord.common.utils.JacksonUtils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HXHttpUtil extends HXBase {
	private static final Logger logger = LoggerFactory.getLogger(HXHttpUtil.class);

	public static String post(String url, String content) {
		return post(url, new StringEntity(content, "UTF-8"));

	}
	
	private static String post(String url, HttpEntity postEntity) {
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		BufferedInputStream inputStream = null;
		ByteArrayOutputStream outputStream = null;
		try {

			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader("Authorization", "Bearer " + getAccessToken());

			httpPost.setEntity(postEntity);
			response = httpclient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					outputStream = new ByteArrayOutputStream(512);
					inputStream = new BufferedInputStream(entity.getContent());
					byte[] buffer = new byte[1024];
					int len;
					while ((len = inputStream.read(buffer)) != -1) {
						outputStream.write(buffer, 0, len);
					}

					String str = new String(outputStream.toByteArray(), "UTF-8");
					return str;
				}
			}
			else {
				logger.error("http status error!HttpStatus:" + statusCode);
			}

		}
		catch (Exception ex) {
			logger.error("send post error!", ex);
		}
		finally {
			if (response != null) {
				try {
					response.close();
				}
				catch (IOException e) {
					logger.error("close response error!", e);
				}
			}

			if (inputStream != null) {
				try {
					inputStream.close();
				}
				catch (IOException e) {
					logger.error("close inputStream error!", e);
				}
			}

			if (outputStream != null) {
				try {
					outputStream.close();
				}
				catch (IOException e) {
					logger.error("close outputStream error!", e);
				}
			}

			try {
				httpclient.close();
			}
			catch (IOException e) {
				logger.error("close httpclient error!", e);
			}
		}
		return null;
	}

	public static String get(String url, Map<String, String> params) {
		URI uri = buildURI(url, params);
		if (uri == null) {
			return "";
		}
		CloseableHttpClient httpclient = HttpClients.createDefault();
		CloseableHttpResponse response = null;
		try {
			HttpGet httpGet = new HttpGet(uri);
			response = httpclient.execute(httpGet);
			int statusCode = response.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					BufferedInputStream inputStream = null;
					ByteArrayOutputStream outputStream = null;
					try {
						outputStream = new ByteArrayOutputStream(512);
						inputStream = new BufferedInputStream(entity.getContent());
						byte[] buffer = new byte[1024];
						int len;
						while ((len = inputStream.read(buffer)) != -1) {
							outputStream.write(buffer, 0, len);
						}

						return new String(outputStream.toByteArray(), "UTF-8");

					}
					finally {
						if (inputStream != null) {
							inputStream.close();
						}
						if (outputStream != null) {
							outputStream.close();
						}
					}
				}
			}
			else {
				logger.error("get request error! HttpStatus:" + statusCode);
			}

		}
		catch (Exception e) {
			logger.error("get request error!", e);
		}
		finally {
			if (response != null) {
				try {
					response.close();
				}
				catch (IOException e) {
					logger.error("e close response error!", e);
				}
			}

			try {
				httpclient.close();
			}
			catch (IOException e) {
				logger.error("close httpclient error!", e);
			}
		}

		return "";

	}

	private static URI buildURI(String url, Map<String, String> params) {
		try {
			URIBuilder uriBuilder = new URIBuilder(url);
			if (params != null) {
				for (String key : params.keySet()) {
					uriBuilder.addParameter(key, params.get(key));
				}
			}
			return uriBuilder.build();
		}
		catch (Exception ex) {
			logger.error("build URI error!", ex);
		}

		return null;

	}
	
	public static String delete(String url){
	    
	    CloseableHttpClient httpClient = HttpClients.createDefault();
	    
	    HttpDelete delete = new HttpDelete(url);
	    
	    delete.addHeader("Authorization", "Bearer " + getAccessToken());
	    
	    RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(30000).setConnectTimeout(30000).setSocketTimeout(30000).build();
	    delete.setConfig(config);
	    CloseableHttpResponse response;
		try {
			response = httpClient.execute(delete);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity,StandardCharsets.UTF_8);
			EntityUtils.consume(entity);
			return content;
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static String put(String url,String postEntity){
		HttpEntity postEntity2=new StringEntity(postEntity, "UTF-8");
	    CloseableHttpClient httpClient = HttpClients.createDefault();
	    
	    HttpPut put = new HttpPut(url);
	    
	    put.addHeader("Authorization", "Bearer " + getAccessToken());
	    put.setEntity(postEntity2);
	    RequestConfig config = RequestConfig.custom().setConnectionRequestTimeout(30000).setConnectTimeout(30000).setSocketTimeout(30000).build();
	    put.setConfig(config);
	    CloseableHttpResponse response;
		try {
			response = httpClient.execute(put);
			HttpEntity entity = response.getEntity();
			String content = EntityUtils.toString(entity,StandardCharsets.UTF_8);
			EntityUtils.consume(entity);
			
			return content;
		} catch (ClientProtocolException e) {
			
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}

package com.fh.util.weixinUtil;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 扫描就获取用户信息
 * 
 * @param accessToken
 *            接口访问凭证
 * @param openId
 *            用户标识
 * @return WeixinUserInfo
 */
public class WeiXinUserUtil {
	
	
	/**
	 * 得到微信用户信息
	 * @param accessToken
	 * @param openId
	 * @return
	 */
	public  User getUserInfo(String accessToken, String openId) {
		
		Logger log = LoggerFactory.getLogger(HttpUtil.class);
		User weixinUserInfo = null;
		// 拼接请求地址
		String requestUrl = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID";
		requestUrl = requestUrl.replace("ACCESS_TOKEN", accessToken).replace(
				"OPENID", openId);
		// 获取用户信息
		JSONObject jsonObject = HttpUtil
				.httpsRequest(requestUrl, "GET", null);
	
		if (null != jsonObject) {
			try {
				weixinUserInfo = new User();
				// 用户的标识
				weixinUserInfo.setOpenId(jsonObject.getString("openid"));
				// 昵称
				weixinUserInfo.setNickname(jsonObject.getString("nickname"));
				// 用户的性别（1是男性，2是女性，0是未知）
				weixinUserInfo.setSex(jsonObject.getInt("sex"));
				// 用户所在国家
				weixinUserInfo.setCountry(jsonObject.getString("country"));
				// 用户所在省份
				weixinUserInfo.setProvince(jsonObject.getString("province"));
				// 用户所在城市
				weixinUserInfo.setCity(jsonObject.getString("city"));
				// 用户头像
				weixinUserInfo.setHeadImgUrl(jsonObject.getString("headimgurl"));
			} catch (Exception e) {
				if (0 == weixinUserInfo.getSubscribe())
					log.error("用户{}已取消关注", weixinUserInfo.getOpenId());
				else {
					int errorCode = jsonObject.getInt("errcode");
					String errorMsg = jsonObject.getString("errmsg");
					log.error("获取用户信息失败 errcode:{} errmsg:{}", errorCode,
							errorMsg);
				}
			}
		}
		return weixinUserInfo;
	}
	public static void sendMsg(String openid,String content) throws ClientProtocolException, IOException{
		String appid = WxConfig.APPID;//自己的配置appid
		String appsecret = WxConfig.APPSECRET;//自己的配置APPSECRET;
		String tokenUrl = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=SECRET";
		tokenUrl = tokenUrl.replace("APPID", appid).replace("SECRET", appsecret);
		System.out.println(tokenUrl);
		HttpClient client = null;
       // Map<String,Object> result =new HashMap<String,Object>();
        client = new DefaultHttpClient();
        HttpGet httpget = new HttpGet(tokenUrl);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        String response = client.execute(httpget, responseHandler);
        JSONObject OpenidJSONO=JSONObject.fromObject(response);
        System.out.println(OpenidJSONO.toString());
        //OpenidJSONO可以得到的内容：access_token expires_in  refresh_token openid scope 
        String token =String.valueOf(OpenidJSONO.get("access_token"));
		String sendurl = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token="+token;
		//首先确定是发送文字消息，还是图文消息，这里是手写的json数据.
		//发送文字消息，无连接
		String json = "{\"touser\":\""+openid+"\",\"msgtype\":\"text\",\"text\":{\"content\":\""+content+"\"}}";     
//		//图文消息，有链接连接
//		String jsonpic = "{\"touser\":\""+这里是Openid+"\","+ "\"msgtype\":\"news\",\"news\":{\"articles\":["+ "{\"title\":\"HelloWorld\",\"url\":\"要跳转的链接"}]}}";
//		System.out.println("这里是json"+jsonpic);
		//请求方法，然后放回OK 成功，否则错误。这里这个请求方法在下边
		HttpPost post = new HttpPost(sendurl);
        post.setEntity(new StringEntity(json, "UTF-8"));
        System.out.println("executing request" + post.getRequestLine());
        String response2 = client.execute(post, responseHandler);
        System.out.println(response2);
	}
	/**
	 * 
	 * @param code 识别得到用户id必须的一个值
	 * 得到网页授权凭证和用户id
	 * @return
	 */
	public static Map<String,Object> oauth2GetOpenid(String code) {
		String appid = WxConfig.APPID;//自己的配置appid
		String appsecret = WxConfig.APPSECRET;//自己的配置APPSECRET;
		String requestUrl = WeiXinUserUtil.GetPageAccessTokenUrl.replace("APPID", appid).replace("SECRET", appsecret).replace("CODE", code);
        HttpClient client = null;
        Map<String,Object> result =new HashMap<String,Object>();
        try {	
            client = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(requestUrl);
            ResponseHandler<String> responseHandler = new BasicResponseHandler();
            String response = client.execute(httpget, responseHandler);
            JSONObject OpenidJSONO=JSONObject.fromObject(response);
            
            //OpenidJSONO可以得到的内容：access_token expires_in  refresh_token openid scope 
            
            String Openid =String.valueOf(OpenidJSONO.get("openid"));
            String AccessToken=String.valueOf(OpenidJSONO.get("access_token"));
            String Scope=String.valueOf(OpenidJSONO.get("scope"));//用户保存的作用域
            String refresh_token=String.valueOf(OpenidJSONO.get("refresh_token"));
            
            result.put("Openid", Openid);
            result.put("AccessToken", AccessToken);
            result.put("scope",Scope);
            result.put("refresh_token", refresh_token);
        } catch (Exception e) {
            e.printStackTrace(); 
        } finally {
            client.getConnectionManager().shutdown();
        }
        return result;
    }
// 网页授权获取code
public final static String GetPageCode = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=APPID&redirect_uri=URL&response_type=code&scope=snsapi_userinfo&state=1#wechat_redirect";
	
// 网页授权接口
public final static String GetPageAccessTokenUrl = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code";

// 网页授权得到用户基本信息接口
public final static String GetPageUsersUrl = "https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";	
}

package com.fh.util;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.URI;
import org.apache.commons.httpclient.methods.GetMethod;


/**
 * 发送短信验证码
 * @author  yangsitong
 *
 */
public class MessageUtil {
	/**
	 * 发送短信
	 * @param url 应用地址，类似于http://ip:port/msg/
	 * @param account 账号
	 * @param pswd 密码
	 * @param mobile 手机号码，多个号码使用","分割
	 * @param msg 短信内容
	 * @param needstatus 是否需要状态报告，需要true，不需要false
	 * @return 返回值定义参见HTTP协议文档
	 * @throws Exception
	 */
	public static String batchSend(String tel, String msg) throws Exception {
		HttpClient client = new HttpClient();
		GetMethod method = new GetMethod();
		try {
//		    String url = "http://222.73.117.158/msg/";// 应用地址
////		    String account = "jiekou-clcs-03";// 账号
//		    String account = "N15222090717";// 账号
////		    String pswd = "Admin789";// 密码
//		    String pswd = "380292";// 密码
//		    boolean needstatus = false;// 是否需要状态报告，需要true，不需要false
//	        String product = null;// 产品ID
//	        String extno = null;// 扩展码
//	        
//			URI base = new URI(url, false);
//			method.setURI(new URI(base, "HttpBatchSendSM", false));
//			method.setQueryString(new NameValuePair[] { 
//					new NameValuePair("account", account),
//					new NameValuePair("pswd", pswd), 
//					new NameValuePair("mobile", tel),
//					new NameValuePair("needstatus", String.valueOf(needstatus)), 
//					new NameValuePair("msg", msg),
//					new NameValuePair("product", product),
//					new NameValuePair("extno", extno), 
//				});
//			int result = client.executeMethod(method);
//			if (result == HttpStatus.SC_OK) {
//				InputStream in = method.getResponseBodyAsStream();
//				ByteArrayOutputStream baos = new ByteArrayOutputStream();
//				byte[] buffer = new byte[1024];
//				int len = 0;
//				while ((len = in.read(buffer)) != -1) {
//					baos.write(buffer, 0, len);
//				}
//				return URLDecoder.decode(baos.toString(), "UTF-8");
//			} else {
//				throw new Exception("HTTP ERROR Status: " + method.getStatusCode() + ":" + method.getStatusText());
//			}
			//msg = strToHexStr(msg);
			String urlNameString = "http://222.73.117.138:7891/mt" + "?" + "dc=15&un=N15222090717&pw=380292&da="+tel+"&sm="+msg;
            URL realUrl = new URL(urlNameString);
            // 打开和URL之间的连接
            URLConnection connection = realUrl.openConnection();
            // 设置通用的请求属性
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent",
                    "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            // 建立实际的连接
            connection.connect();
         // 获取所有响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            // 遍历所有的响应头字段
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }
            String result = "";
            BufferedReader in = null;
            // 定义 BufferedReader输入流来读取URL的响应
            in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String line;
            while ((line = in.readLine()) != null) {
                result += line;
            }
		} catch(Exception e) {
			e.printStackTrace();
		}
		return "";
	}
	
	//读取MessageModel.properties文件获取模板
	public static Properties Configuration(){
		Properties propertie = new Properties();
		try {
		 	InputStream in = new BufferedInputStream (MessageUtil.class.getClassLoader().getResourceAsStream("MessageModel.properties"));
			propertie.load(in);
		  	in.close();
		} catch (Exception e){
			e.printStackTrace();
		}
		return propertie;
	}
	
	public static String strToHexStr(String str) {

	    char[] chars = "0123456789ABCDEF".toCharArray();
	    StringBuilder sb = new StringBuilder("");
	    byte[] bs = str.getBytes();
	    int bit;

	    for (int i = 0; i < bs.length; i++) {
	      bit = (bs[i] & 0x0f0) >> 4;
	      sb.append(chars[bit]);
	      bit = bs[i] & 0x0f;
	      sb.append(chars[bit]);
	    }
	    return sb.toString().trim();
	  }
	
	 
}


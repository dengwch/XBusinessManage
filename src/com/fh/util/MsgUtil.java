package com.fh.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;



/**
 * @author Kevin
 * 获取随机的短信校验码
 */
public class MsgUtil {
	/**
	 * 获取一个6位的验证码
	 * @return
	 */
	public String getValidateCode(){
		Random r = new Random();
		String validateCode = "";
		for (int i = 0; i < 6; i++) {
			int num = r.nextInt(10);
			validateCode+=num;
		}
		return validateCode;
	}
	
	/**
	 * 生成消息
	 * 消息内容
	 * @param content
	 * 帖子id
	 * @param forumid
	 * 接收者ID
	 * @param touserid
	 * 创建者userid
	 * @param createuserid
	 * @return
	 */
	public static Map<String, Object> setMsg(String content, String forumid, String touserid, String createuserid) {
		Map<String, Object> param=new HashMap<String, Object>();
		param.put("msgid", UuidUtil.get32UUID());
		param.put("msgtitle","");
		param.put("msgcontent",content);
		param.put("touserid", touserid);
		param.put("createuserid",createuserid);
		param.put("isread", 0);
		param.put("creattime", new Date());
		param.put("forumid",forumid);
		return param;
	}
}

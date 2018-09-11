package com.fh.util;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
		


public class ReturnUtil {
	String appid = "wx8805cce773590606";
	// 应用密钥
	String appsecret = "b45ff028bddf91ce02cf69873addcefd";
	// 商户ID
	String partner = "1441509602";
	// API密钥
	String partnerkey = "96e79218965eb72c92a549dd5a330112";
	public String returnPay(HttpServletRequest request, HttpServletResponse response,String payNo,Double money) throws Exception {
		// 金额转化为分为单位
		Double sessionmoney = money;
		try {
			sessionmoney = money.doubleValue();
		} catch (Exception e) {
			sessionmoney = 0.01;
		}
		String finalmoney = String.format("%.2f", sessionmoney);
		finalmoney = finalmoney.replace(".", "");
		String orderNo = UuidUtil.get32UUID();
		Map<String, Object> dataMap = new HashMap<String, Object>();
		
		// 获取openId后调用统一支付接口https://api.mch.weixin.qq.com/pay/unifiedorder
		String currTime = TenpayUtil.getCurrTime();
		// 8位日期
		String strTime = currTime.substring(8, currTime.length());
		// 四位随机数
		String strRandom = TenpayUtil.buildRandom(4) + "";
		// 10位序列号,可以自行调整。
		String strReq = PayMD5.GetMD5nonce_str();
		
		// 商户号
		String mch_id = partner;
		// 子商户号 非必输
		// String sub_mch_id="";
		// 设备号 非必输
		String device_info = "";
		// 随机数
		String nonce_str = strReq;
		// 商品描述
		// String body = describe;
		
		int intMoney = Integer.parseInt(finalmoney);
		
		// 总金额以分为单位，不带小数点
		int total_fee = intMoney;
		// 订单生成的机器 IP
		String spbill_create_ip = request.getRemoteAddr();
		
		// 非必输
		// String product_id = "";
		SortedMap<String, String> packageParams = new TreeMap<String, String>();
		packageParams.put("appid", appid);
		packageParams.put("mch_id", mch_id);
		packageParams.put("out_trade_no", payNo);
		packageParams.put("transaction_id", "");
		packageParams.put("out_refund_no", orderNo);
		packageParams.put("total_fee", total_fee + "");
		packageParams.put("refund_fee", total_fee + "");
		packageParams.put("refund_fee_type", "CNY");
		
		packageParams.put("op_user_id", mch_id);
		packageParams.put("nonce_str", nonce_str);
		
		RequestHandler reqHandler = new RequestHandler(request, response);
		reqHandler.init(appid, appsecret, partnerkey);
		
		String sign = reqHandler.createSign(packageParams);
		String xml = "<xml>" + "<appid>" + appid + "</appid>" + "<mch_id>"
				+ mch_id + "</mch_id>" + "<out_trade_no>" + payNo
				+ "</out_trade_no>" + "<sign>" + sign + "</sign>"
				+ "<out_refund_no>" + orderNo + "</out_refund_no>"
				+ "<total_fee>" + total_fee + "</total_fee>"
				+ "<transaction_id></transaction_id>" + "<refund_fee>"
				+ total_fee + "</refund_fee>" + "<refund_fee_type>" + "CNY"
				+ "</refund_fee_type>" + "<op_user_id>" + mch_id
				+ "</op_user_id>" + "<nonce_str>" + nonce_str + "</nonce_str>"
				+ "</xml>";
		System.out.println(xml);
		//Boolean flag = ClientCustomSSL.toPrint(xml);
		String text=ClientCustomSSL.toPrint(xml);
		if (text!=null&&!"".equals(text)&&"1".equals(text)) {
			return "1";
		}
		return text;
	}

}

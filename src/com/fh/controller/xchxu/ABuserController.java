package com.fh.controller.xchxu;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fh.util.DateUtil;
import com.fh.util.MD5;
import com.fh.util.MsgUtil;
import com.fh.util.PageData;
import com.fh.util.SendMsg;
import com.fh.util.weixinUtil.WeiXinUserUtil;
import com.fh.controller.base.BaseController;
import com.fh.entity.businessuser.Businessuser;
import com.fh.service.backgroup.bussinessactivity.BussinessActivityService;
import com.fh.service.backgroup.classification.ClassificationService;
import com.fh.service.backgroup.classificationrelation.ClassificationrelationService;
import com.fh.service.backgroup.commodity.CommodityService;
import com.fh.service.backgroup.commodityimg.CommodityimgService;
import com.fh.service.backgroup.commodityspecification.CommoditySpecificationService;
import com.fh.service.backgroup.log.LogService;
import com.fh.service.backgroup.logistics.LogisticsService;
import com.fh.service.backgroup.order.OrderMangerService;
import com.fh.service.backgroup.specificationproperties.SpecificationPropertiesService;
import com.fh.service.backgroup.specifications.SpecificationsService;
import com.fh.service.businessuser.BusinessuserService;

/**
 * 商品详情
 * @author 
 *
 */
@Controller
@RequestMapping(value = "/aBuser/")
public class ABuserController extends BaseController {
	@Resource(name="businessuserService")
	private BusinessuserService businessuserService;
	@Resource(name = "commodityService")
	private CommodityService commodityService;
	@Resource(name = "classificationService")
	private ClassificationService classificationService;
	@Resource(name = "logisticsService")
	private LogisticsService logisticsService;
	@Resource(name = "specificationsService")
	private SpecificationsService specificationsService;
	@Resource(name = "specificationpropertiesService")
	private SpecificationPropertiesService specificationpropertiesService;
	@Resource(name="commodityspecificationService")
	private CommoditySpecificationService commodityspecificationService;
	@Resource(name="classificationrelationService")
	private ClassificationrelationService classificationrelationService;
	@Resource(name = "logService")
	public LogService logService;
	@Resource(name="bussinessactivityService")
	private BussinessActivityService bussinessactivityService;
	@Resource(name="commodityimgService")
	private CommodityimgService commodityimgService;
	@Resource(name="ordermangerService")
	private OrderMangerService ordermangerService;
	
	
	/**
	 * 发送短信验证码
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/smsCode")
	@ResponseBody
	public Map<String, Object> smsCode(HttpServletRequest request,String type) throws Exception {
				
		Map<String, Object> result =new HashMap<String, Object>();
		try {
			String phone=request.getParameter("phone");
			if ("".equals(phone) || phone == null || phone.length() != 11) {
				// 手机格式错误
				result.put("msgCode", "-2");
			} else {
				PageData pd = new PageData();
				pd.put("TEL", phone);
				List<PageData> list = businessuserService.listAll(pd);
				//PageData user=list.get(0);
				/** type=1:注册 type=2:忘记密码 */
				if (("1".equals(type) && list != null && list.size()>0)) {
					// 该用户不可用
					result.put("msgCode", "-3");
					result.put("msg", "用户已存在");
				}else if (("2".equals(type) && list.size()==0)){
					result.put("msgCode", "-3");
					result.put("msg", "用户不存在");
				}else {
					MsgUtil msgUtil = new MsgUtil();
					String number = msgUtil.getValidateCode();
					System.out.println("---验证码---:" + number);
					request.getSession().setAttribute(phone, number);
					//String content = MessageUtil.Configuration().getProperty("msg1").replace("number", number);
					SendMsg.httpSender(phone, number);
					result.put("msgCode", "1");
					result.put("msg", "发送成功");
					//result.put("msgCode", "验证码"+number);
				}
			}
		} catch (Exception e) {
			result.put("msgCode", "0");
			e.printStackTrace();
		}
	   
		return result;
	}
	/**
	 * 注册
	 */
	@RequestMapping(value="/save")
	@ResponseBody
	public Map<String, Object> save(HttpServletRequest request) throws Exception{
		Map<String, Object> result=new HashMap<String, Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String code=(String)pd.get("code");
		String codeString=(String)request.getSession().getAttribute((String)pd.get("TEL"));
		List<PageData> list = businessuserService.listAll(pd);
		//PageData user=list.get(0);
		/** type=1:注册 type=2:忘记密码 */
		if ((list != null && list.size()>0)) {
			// 该用户不可用
			result.put("result", "-1");
			result.put("msg", "用户已存在");
		} else{ 
			if (null!=code&&!"".equals(code)&&code.equals(codeString)) {
				String idString=this.get32UUID();
				pd.put("BUSINESSUSER_ID", idString);	//主键
				pd.put("FLAG", "1"); // 状态
				pd.put("SQFLAG", "1"); // 状态
				pd.put("ZCFLAG", "4"); // 状态
				pd.put("CLJG", "");
				pd.put("TYPE", "2");
				pd.put("FBUSINESSUSER_ID", "0");
				pd.put("FFLAG", "1");
				pd.put("ZUIFLAG", "1");
			
				pd.put("BUSINESSPWD", MD5.md5((String)pd.get("BUSINESSPWD")));
				pd.put("CREATIME", DateUtil.getTime().toString());
				businessuserService.save(pd);
				
				pd.put("TEXTMSG_ID", this.get32UUID());
				pd.put("CONTENT", "手机号为"+(String)pd.get("TEL")+"的商家注册成功请及时审核");
				pd.put("CREATETIME", DateUtil.getTime().toString());
				pd.put("STATUS", "2");
				pd.put("KEYWORD", "暂无审核");
				
				pd.put("BUSINESSUSER_ID", idString);
				businessuserService.savenotice(pd);
				
				
				result.put("result", "1");
				Businessuser businessuser=new Businessuser();
				businessuser.setBUSINESSUSER_ID(idString);
				businessuser.setBUSINESSPWD(MD5.md5((String)pd.get("BUSINESSPWD")));
				businessuser.setTEL(pd.getString("TEL"));
				businessuser.setFLAG("1");
				businessuser.setCREATIME(pd.getString("CREATIME"));
				businessuser.setCONTACTSNAME(pd.getString("CONTACTSNAME"));
				businessuser.setSQFLAG(pd.getString("SQFLAG"));
				businessuser.setZCFLAG(pd.getString("ZCFLAG"));
				businessuser.setCLJG(pd.getString("CLJG"));
				if (null!=(Integer)pd.get("VISITNUM") && !"".equals((Integer)pd.get("VISITNUM"))) {
					businessuser.setVISITNUM(Integer.valueOf((Integer)pd.get("VISITNUM")));
				}else{
					businessuser.setVISITNUM(Integer.valueOf("0"));
				}
				Subject currentUser = SecurityUtils.getSubject();
				Session session = currentUser.getSession();
				businessuser.setTYPE(pd.getString("TYPE"));
				businessuser.setFBUSINESSUSER_ID(pd.getString("FBUSINESSUSER_ID"));
				businessuser.setFFLAG(pd.getString("FFLAG"));
				session.setAttribute("TEL",pd.getString("TEL"));
				request.getSession().setAttribute("user", businessuser);
				session.setAttribute("BUSINESSUSER_ID",pd.getString("BUSINESSUSER_ID"));
			}else {
				result.put("result", "0");//验证码不正确
				result.put("msg", "验证码不正确");
			}
		}
		return result;
	}
	
	/**
	 * 去申请页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("goApply")
	public String goApply(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String code = request.getParameter("code");
		Map<String, Object> _map = WeiXinUserUtil.oauth2GetOpenid(code);
		String OPENID ="";
		if (_map.get("Openid") == null) {
			
		} else {
			OPENID = _map.get("Openid").toString();
			PageData pd =new PageData();
			pd.put("OPENID", OPENID);
			PageData Buser = businessuserService.findById(pd);
			if(Buser==null){
				/*String idString=this.get32UUID();
				pd.put("BUSINESSUSER_ID", idString);	//主键
				pd.put("FLAG", "1"); // 状态
				pd.put("SQFLAG", "1"); // 状态
				pd.put("ZCFLAG", "4"); // 状态
				pd.put("CLJG", "");
				pd.put("TYPE", "2");
				pd.put("FBUSINESSUSER_ID", "0");
				pd.put("FFLAG", "1");
				pd.put("ZUIFLAG", "1");
				pd.put("OPENID", OPENID);
				//pd.put("BUSINESSPWD", MD5.md5((String)pd.get("BUSINESSPWD")));
				pd.put("CREATIME", DateUtil.getTime().toString());
				businessuserService.save(pd);*/
				request.getSession().setAttribute("OPENID", OPENID);
				//request.getSession().setAttribute("BUSINESSUSER_ID",idString);
			}else{
				//request.getSession().setAttribute("BUSINESSUSER_ID",Buser.get("BUSINESSUSER_ID"));
				request.getSession().setAttribute("OPENID", OPENID);
			}
		}
		return "xin/shenq";
	}
	/**
	 * 去登录页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("gologin")
	public String gologin(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		return "xin/login";
	}
	/**
	 * 去注册页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("gozhuce")
	public String gozhuce(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String code = request.getParameter("code");
		Map<String, Object> _map = WeiXinUserUtil.oauth2GetOpenid(code);
		String OPENID ="";
		if (_map.get("Openid") == null) {
			return "xin/zhuce";
		} else {
			OPENID = _map.get("Openid").toString();
			PageData pd =new PageData();
			pd.put("OPENID", OPENID);
			PageData Buser = businessuserService.findById(pd);
			if(Buser==null){
				
				request.getSession().setAttribute("OPENID", OPENID);
				return "xin/zhuce";
			}else{
				request.getSession().setAttribute("OPENID", OPENID);
				return "xin/shangpin";
			}
		}
		
	}
	/**
	 * 去管理商品页面
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("goDL")
	public String goDL(HttpServletRequest request, HttpServletResponse response) throws Exception {
		String code = request.getParameter("code");
		Map<String, Object> _map = WeiXinUserUtil.oauth2GetOpenid(code);
		String OPENID ="";
		if (_map.get("Openid") == null) {
			return "";
		} else {
			OPENID = _map.get("Openid").toString();
			PageData pd =new PageData();
			pd.put("OPENID", OPENID);
			PageData Buser = businessuserService.findById(pd);
			if(Buser==null){
				
				request.getSession().setAttribute("OPENID", OPENID);
				return "xin/shenq";//不存在跳转申请页面
				//request.getSession().setAttribute("BUSINESSUSER_ID",idString);
			}else{
				//request.getSession().setAttribute("BUSINESSUSER_ID",Buser.get("BUSINESSUSER_ID"));
				request.getSession().setAttribute("OPENID", OPENID);
				return "xin/shangpin";//直接跳转商品页面
			}
		}
		
	}
	
}
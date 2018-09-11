package com.fh.controller.xchxu.bussiness;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.entity.businessuser.Businessuser;
import com.fh.service.backgroup.bussinessactivity.BussinessActivityService;
import com.fh.service.backgroup.commodityimg.CommodityimgService;
import com.fh.service.backgroup.membershipcard.MembershipcardService;
import com.fh.service.businessuser.BusinessuserService;
import com.fh.service.work.WorkService;
import com.fh.util.AddressUtils;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.Jurisdiction;
import com.fh.util.MD5;
import com.fh.util.MsgUtil;
import com.fh.util.OSSUtils;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.SendMsg;

import net.sf.json.JSONArray;

/** 
 * 类名称：BusinessuserController
 * 创建人：FH 
 * 创建时间：2017-05-12
 */
@Controller
@RequestMapping(value="/businessuser")
public class BusinessuserController extends BaseController {
	
	String menuUrl = "businessuser/list.do"; //菜单地址(权限用)
	@Resource(name="businessuserService")
	private BusinessuserService businessuserService;
	@Resource(name="workService")
	private WorkService workService;
	@Resource(name="membershipcardService")
	private MembershipcardService membershipcardService;
	@Resource(name="bussinessactivityService")
	private BussinessActivityService bussinessactivityService;
	@Resource(name="commodityimgService")
	private CommodityimgService commodityimgService;
	/**
	 * 访问登录页
	 * @return
	 */
	@RequestMapping(value = "/login_toLogin1")
	public String toLogin() throws Exception {
		return "user/loginX1";
	}
	/**
	 * 访问注册页
	 * 
	 */
	@RequestMapping(value = "/register1")
	public String register1() throws Exception {
		return "user/register1";
	}
	/**
	 * 访问审核中页
	 * 
	 */
	@RequestMapping(value = "/chenggong")
	public String chenggong(HttpServletRequest request) throws Exception {
		ModelAndView mv = this.getModelAndView();
		request.getSession().removeAttribute(Const.SESSION_USER);
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData pd1 = businessuserService.findById(pd);
		request.setAttribute("pd1", pd1);
		List<PageData>	workList = workService.listAll(pd);
		JSONArray json = JSONArray.fromObject(workList);
		request.setAttribute("json", json);
		return "user/chenggong";
	}
	/**
	 * 访问审核失败页
	 * 
	 */
	@RequestMapping(value = "/shibai")
	public String shibai(HttpServletRequest request) throws Exception {
		//request.getSession().removeAttribute(Const.SESSION_USER);
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData pd1 = businessuserService.findById(pd);
		request.setAttribute("pd1", pd1);
		return "user/shibai";
	}
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
	 * 新增
	 */
	@RequestMapping(value="/save1")
	@ResponseBody
	public Map<String, Object> save1() throws Exception{
		logBefore(logger, "新增Businessuser");
		Map<String, Object> result=new HashMap<String, Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		HttpServletRequest request = this.getRequest();
		String BUSINESSUSER_ID=(String)request.getSession().getAttribute("BUSINESSUSER_ID");
		List<PageData> list = businessuserService.listAll(pd);
		//PageData user=list.get(0);
		/** type=1:注册 type=2:忘记密码 */
		if ((list != null && list.size()>0)) {
			// 该用户不可用
			result.put("result", "-1");
			result.put("msg", "用户已存在");
		} else{ 
			pd.put("BUSINESSUSER_ID", this.get32UUID());	//主键
			pd.put("FLAG", "1"); // 状态
			pd.put("SQFLAG", "1"); // 状态
			pd.put("ZCFLAG", "2"); // 状态
			pd.put("CLJG", "");
			pd.put("VISITNUM", 0);
			pd.put("TYPE", "2");
			pd.put("FBUSINESSUSER_ID", BUSINESSUSER_ID);
			pd.put("FFLAG", "1");
			pd.put("ZUIFLAG", "1");
			pd.put("BUSINESSPWD", MD5.md5((String)pd.get("BUSINESSPWD")));
			pd.put("CREATIME", DateUtil.getTime().toString());
			businessuserService.save(pd);
			result.put("result", "1");
			//mv.setViewName("redirect:/businessuser/list.do");
		}
		return result;
	}
	/**
	 * 修改密码
	 * 
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/modifyPassWord")
	@ResponseBody
	public Map<String, Object> modifyPassWord(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String oldPass=request.getParameter("OLDPASS");
			String newPass=request.getParameter("NEWPASS");
			String BUSINESSUSER_ID=(String)request.getSession().getAttribute("BUSINESSUSER_ID");
			PageData pd = new PageData();
			pd = this.getPageData();
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			pd = businessuserService.findById(pd);	//根据ID读取
			if ("".equals(newPass)) {
				//新密码为空
				result.put("msgCode", "-1");
			} else if (!pd.getString("BUSINESSPWD").equals(MD5.md5(oldPass))) {
				//旧密码错误
				result.put("msgCode", "-2");
			} else {
				if(null==newPass || "".equals(newPass)){
					pd.put("BUSINESSPWD", (String)pd.get("BUSINESSPWD"));
				}else {
					pd.put("BUSINESSPWD", MD5.md5(newPass));
					
				}
				businessuserService.editBUSINESSPWD(pd);
				request.getSession().removeAttribute(Const.SESSION_USER);
				result.put("msgCode", "1");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("msgCode", "0");
		}
		return result;
	}
	
	/**
	 * 修改密码
	 * 
	 * @param request
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value = "/forgetPassWord")
	@ResponseBody
	public Map<String, Object> forgetPassWord(HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			String newPass=request.getParameter("NEWPASS");
			String TEL=request.getParameter("TEL");
			String code=request.getParameter("code");
			String codeString=(String)request.getSession().getAttribute(request.getParameter("TEL"));
			PageData pd = new PageData();
			pd = this.getPageData();
			
			pd.put("TEL", TEL);
			List<PageData> list = businessuserService.listAll(pd);
			if ((list != null && list.size()>0)) {
				if (null!=code&&!"".equals(code)&&code.equals(codeString)) {
					if (null==newPass || "".equals(newPass)) {
						//新密码为空
						result.put("result", "-1");
					}else {
						pd=list.get(0);
						pd.put("BUSINESSPWD", MD5.md5(newPass));
						businessuserService.editBUSINESSPWD(pd);
						request.getSession().removeAttribute(Const.SESSION_USER);
						result.put("result", "1");
					}
				}else {
					result.put("result", "0");//验证码不正确
					result.put("msg", "验证码不正确");
				}
			} else{
				result.put("result", "-2");
				result.put("msg", "用户不存在");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			result.put("result", "-3");
		}
		return result;
	}
	
	
	/**
	 * 
	 * 上传tupian
	 * 
	 * @param myfile
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/uploadBPhoto")
	@ResponseBody
	public String  uploadPhoto(@RequestParam MultipartFile myfile, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		//Subject currentUser = SecurityUtils.getSubject();
		//Session session = currentUser.getSession();
		try {
			/*User user;
			user= (User) session.getAttribute(Const.SESSION_USER);
			String userId="";
			if(user!=null){
				userId =user.getUSER_ID();
			}*/
			String fileNa = myfile.getOriginalFilename();  
	        // 获取上传文件扩展名  
	        String fileExt = fileNa.substring(fileNa.lastIndexOf(".") + 1, fileNa.length()); 
			if (myfile.isEmpty()) {
				result.put("flag", 1);
				result.put("msg", "请选择文件后上传");
				return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
				//return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
			} else if (!"jpg".equalsIgnoreCase(fileExt) && !"jpeg".equalsIgnoreCase(fileExt) && !"png".equalsIgnoreCase(fileExt) && !"bmp".equalsIgnoreCase(fileExt)  
	                && !"gif".equalsIgnoreCase(fileExt)) { 
				result.put("flag", 2);
				result.put("msg", "上传文件格式不正确");
				return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
				//return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
	        } else{
					 BufferedImage bi =ImageIO.read(myfile.getInputStream());
					 int ruleWidth = 100;  
	                    int ruleHeight = 100;
					 	long fileSize = myfile.getSize();
			           int imgWidth = bi.getWidth();
			           int imgHeight = bi.getHeight();
			           System.out.println("imgWidth:"+imgWidth);
			           System.out.println("imgHeight:"+imgHeight);
			           if (fileSize <= 0) {  
			        	   result.put("flag", 1);
			        	   result.put("msg", "请选择文件后上传");
			        	   return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
			        	  // return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
			           } else if (fileSize > (100 * 1024)) {  
			        	   result.put("flag", 3);
			        	   result.put("msg", "文件大小不能超过100K");
			        	   return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
			           }else if (ruleWidth != imgWidth && ruleHeight != imgHeight) {
			        	   result.put("flag", 4);
			        	   result.put("msg", "上传失败:文件尺寸为100*100");
			        	   return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
			        	  // return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
			           }  
				// 定义图片存储路径
				String BUSINESSUSER_ID=(String)request.getSession().getAttribute("BUSINESSUSER_ID");
				String path = "gzh/user/"+BUSINESSUSER_ID+"/" +this.get32UUID() + "/";
				String fileName = this.get32UUID();
				OSSUtils.uploadFileOfOSS(path + fileName + ".jpg", myfile);
				result.put("msg", path + fileName + ".jpg");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("flag", 0);
			result.put("msg", "上传失败");
		}
		return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
	}
	/**
	 * 
	 * 上传tupian
	 * 
	 * @param myfile
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/uploadBPhoto2")
	@ResponseBody
	public String  uploadPhoto2(@RequestParam MultipartFile myfile, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		//Subject currentUser = SecurityUtils.getSubject();
		//Session session = currentUser.getSession();
		try {
			/*User user;
			user= (User) session.getAttribute(Const.SESSION_USER);
			String userId="";
			if(user!=null){
				userId =user.getUSER_ID();
			}*/
			String fileNa = myfile.getOriginalFilename();  
	        // 获取上传文件扩展名  
	        String fileExt = fileNa.substring(fileNa.lastIndexOf(".") + 1, fileNa.length()); 
			if (myfile.isEmpty()) {
				result.put("flag", 1);
				result.put("msg", "请选择文件后上传");
				return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
				//return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
			} else if (!"jpg".equalsIgnoreCase(fileExt) && !"jpeg".equalsIgnoreCase(fileExt) && !"png".equalsIgnoreCase(fileExt) && !"bmp".equalsIgnoreCase(fileExt)  
	                && !"gif".equalsIgnoreCase(fileExt)) { 
				result.put("flag", 2);
				result.put("msg", "上传文件格式不正确");
				return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
				//return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
	        } else{
					 
				// 定义图片存储路径
				String BUSINESSUSER_ID=(String)request.getSession().getAttribute("BUSINESSUSER_ID");
				String path = "gzh/user/"+BUSINESSUSER_ID+"/" +this.get32UUID() + "/";
				String fileName = this.get32UUID();
				OSSUtils.uploadFileOfOSS(path + fileName + ".jpg", myfile);
				result.put("msg", path + fileName + ".jpg");
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("flag", 0);
			result.put("msg", "上传失败");
		}
		return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
	}
	/**
	 * 上传logo
	 * 
	 * @param myfile
	 * @param request
	 * @param response
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping("/uploadBPhoto1")
	@ResponseBody
	public Map<String, Object>  uploadPhoto1(@RequestParam MultipartFile myfile, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		//Subject currentUser = SecurityUtils.getSubject();
		//Session session = currentUser.getSession();
		try {
			/*User user;
			user= (User) session.getAttribute(Const.SESSION_USER);
			String userId="";
			if(user!=null){
				userId =user.getUSER_ID();
			}*/
			String fileNa = myfile.getOriginalFilename();  
	        // 获取上传文件扩展名  
	        String fileExt = fileNa.substring(fileNa.lastIndexOf(".") + 1, fileNa.length()); 
			if (myfile.isEmpty()) {
				result.put("flag", 1);
				result.put("msg", "请选择文件后上传");
				return result;
				//return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
			} else if (!"jpg".equalsIgnoreCase(fileExt) && !"jpeg".equalsIgnoreCase(fileExt) && !"png".equalsIgnoreCase(fileExt) && !"bmp".equalsIgnoreCase(fileExt)  
	                && !"gif".equalsIgnoreCase(fileExt)) { 
				result.put("flag", 2);
				result.put("msg", "上传文件格式不正确");
				return result;
				//return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
	        } else{
					 BufferedImage bi =ImageIO.read(myfile.getInputStream());
					 int ruleWidth = 60;  
	                    int ruleHeight = 60;
					 	long fileSize = myfile.getSize();
			           int imgWidth = bi.getWidth();
			           int imgHeight = bi.getHeight();
			           System.out.println("imgWidth:"+imgWidth);
			           System.out.println("imgHeight:"+imgHeight);
			           if (fileSize <= 0) {  
			        	   result.put("flag", 1);
			        	   result.put("msg", "请选择文件后上传");
			        	   return result;
			        	  // return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
			           } else if (fileSize > (100 * 1024)) {  
			        	   result.put("flag", 3);
			        	   result.put("msg", "文件大小不能超过100K");
			        	   return result;
			           }else if (ruleWidth != imgWidth && ruleHeight != imgHeight) {
			        	   result.put("flag", 4);
			        	   result.put("msg", "上传失败:文件尺寸为60*60");
			        	   return result;
			        	  // return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
			           }  
				// 定义图片存储路径
				String BUSINESSUSER_ID=(String)request.getSession().getAttribute("BUSINESSUSER_ID");
				String path = "gzh/user/"+BUSINESSUSER_ID+"/" +this.get32UUID() + "/";
				String fileName = this.get32UUID();
				OSSUtils.uploadFileOfOSS(path + fileName + ".jpg", myfile);
				result.put("msg", path + fileName + ".jpg");
				request.getSession().setAttribute("shareImg",path + fileName + ".jpg");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("flag", 0);
			result.put("msg", "上传失败");
		}
		return result;
		//return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Businessuser");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			businessuserService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	
	/**
	 * 改变状态
	 */
	@RequestMapping(value="/gFlag")
	public void gFlag(PrintWriter out){
		logBefore(logger, "删除Businessuser");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String flag=(String)pd.get("flag");
			PageData pd1 = businessuserService.findById(pd);
			if(null!=flag && !"".equals(flag)){
				pd1.put("FLAG", flag);
			}
			
			String SQFLAG=(String)pd.get("SQFLAG");
			if(null!=SQFLAG && !"".equals(SQFLAG)){
				pd1.put("SQFLAG", SQFLAG);
			}
			String ZCFLAG=(String)pd.get("ZCFLAG");
			if(null!=ZCFLAG && !"".equals(ZCFLAG)){
				pd1.put("ZCFLAG", ZCFLAG);
			}
			businessuserService.edit(pd1);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/edit")
	public ModelAndView edit() throws Exception{
		logBefore(logger, "修改Businessuser");
		ModelAndView mv = this.getModelAndView();
		HttpServletRequest request=this.getRequest();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData pd1 = businessuserService.findById(pd);
		String BUSINESSPWD=(String)pd.get("BUSINESSPWD");
		if(null==BUSINESSPWD || "".equals(BUSINESSPWD)){
			pd.put("BUSINESSPWD", (String)pd1.get("BUSINESSPWD"));
		}else {
			pd.put("BUSINESSPWD", MD5.md5((String)pd.get("BUSINESSPWD")));
			
		}
		String FLAG=(String)pd.get("FLAG");
		if(null==FLAG || "".equals(FLAG)){
			pd.put("FLAG", (String)pd1.get("FLAG"));
		}
		String SQFLAG=(String)pd.get("SQFLAG");
		if(null==SQFLAG || "".equals(SQFLAG)){
			pd.put("SQFLAG", (String)pd1.get("SQFLAG"));
		}
		String ZCFLAG=(String)pd.get("ZCFLAG");
		if(null==ZCFLAG || "".equals(ZCFLAG)){
			pd.put("ZCFLAG", (String)pd1.get("ZCFLAG"));
		}
		String CLJG=(String)pd.get("CLJG");
		if(null==CLJG || "".equals(CLJG)){
			pd.put("CLJG", (String)pd1.get("CLJG"));
		}
		businessuserService.editZT(pd);
		mv.setViewName("redirect:/businessuser/list.do");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表Businessuser");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			HttpServletRequest request = this.getRequest();
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			String  BUSINESSUSER_ID1=(String) request.getSession().getAttribute("BUSINESSUSER_ID1");
			pd = this.getPageData();
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			page.setPd(pd);
			if (null!=BUSINESSUSER_ID1&&!"".equals(BUSINESSUSER_ID1)) {
				pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID1);
			}
			pd = businessuserService.findById(pd);	//根据ID读取
			List<PageData>	varList = businessuserService.datalistPageX(page);	//列出Businessuser列表
			mv.setViewName("businessuser/AccountList");
			
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 申请列表
	 */
	@RequestMapping(value="/SQlist")
	public ModelAndView SQlist(Page page){
		logBefore(logger, "列表Businessuser");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			
			String CLJG = pd.getString("CLJG2");
			String SQFLAG = pd.getString("SQFLAG2");
			String BUSINESSUSER_ID = pd.getString("BUSINESSUSER_ID");
			if(null != CLJG && !"".equals(CLJG.trim())&&null != SQFLAG && !"".equals(SQFLAG.trim())&&null != BUSINESSUSER_ID && !"".equals(BUSINESSUSER_ID.trim())){
				PageData pd1 = businessuserService.findById(pd);	//根据ID读取
				pd1.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
				pd1.put("SQFLAG", SQFLAG);
				pd1.put("CLJG", CLJG);
				businessuserService.edit(pd1);
			}
			
			
			
			
			String BUSINESSUSERNAME = pd.getString("BUSINESSUSERNAME");
			if (null != BUSINESSUSERNAME && !"".equals(BUSINESSUSERNAME)) {
				BUSINESSUSERNAME = BUSINESSUSERNAME.trim();
				pd.put("BUSINESSUSERNAME", BUSINESSUSERNAME);
			}
			String SQFLAG1 = pd.getString("SQFLAG1");
			if (null != SQFLAG1 && !"".equals(SQFLAG1)) {
				SQFLAG1 = SQFLAG1.trim();
				pd.put("SQFLAG", SQFLAG1);
			}
			
			page.setPd(pd);
			List<PageData>	varList = businessuserService.list(page);	//列出Businessuser列表
			List<PageData>	nameList=new ArrayList<PageData>();
			for (int i = 0; i < varList.size(); i++) {
				String nameString="";
				PageData pd1 = new PageData();
				PageData workString=varList.get(i);
				String wString=workString.getString("WORK");
				if (null!=wString&&!"".equals(wString)) {
					String[] woStrings=wString.split("#");
					if (null!=woStrings&& woStrings.length>0) {
						for (int j = 0; j < woStrings.length; j++) {
							PageData pd2 = new PageData();
							pd2.put("WORK_ID", woStrings[j]);
							PageData namework=workService.findById(pd2);
							if(null!=namework){
								if(nameString==""){
									nameString = nameString + (String)namework.get("WORKNAME");
						    	}else{
						    		nameString = nameString +"</br>"+ namework.getString("WORKNAME");
						    	}
							}else {
								pd1.put("nameString", woStrings[j]);
							}
						}
					}
				}
				
				//nameList.add(nameString);
				
				pd1.put("nameString", nameString);
				pd1.put("varList",varList.get(i));
				nameList.add(pd1);
			}
			mv.setViewName("businessuser/businessuser_SQlist");
			
			mv.addObject("nameList", nameList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 注册审核列表
	 */
	@RequestMapping(value="/ZClist")
	public ModelAndView ZClist(Page page){
		logBefore(logger, "列表Businessuser");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String COMPANYNAME = pd.getString("COMPANYNAME");
			if (null != COMPANYNAME && !"".equals(COMPANYNAME)) {
				COMPANYNAME = COMPANYNAME.trim();
				pd.put("COMPANYNAME", COMPANYNAME);
			}
			String ZCFLAG = pd.getString("ZCFLAG");
			String BUSINESSUSER_ID = pd.getString("BUSINESSUSER_ID");
			if(null != ZCFLAG && !"".equals(ZCFLAG.trim())&&null != BUSINESSUSER_ID && !"".equals(BUSINESSUSER_ID.trim())){
				PageData pd1 = businessuserService.findById(pd);	//根据ID读取
				pd1.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
				pd1.put("ZCFLAG", ZCFLAG);
				businessuserService.edit(pd1);
			}
			String ZCFLAG1 = pd.getString("ZCFLAG1");
			if (null != ZCFLAG1 && !"".equals(ZCFLAG1)) {
				ZCFLAG1 = ZCFLAG1.trim();
				pd.put("ZCFLAG", ZCFLAG1);
			}
			page.setPd(pd);
			List<PageData>	varList = businessuserService.list(page);	//列出Businessuser列表
			List<PageData>	nameList=new ArrayList<PageData>();
			for (int i = 0; i < varList.size(); i++) {
				String nameString="";
				PageData pd1 = new PageData();
				PageData workString=varList.get(i);
				String wString=workString.getString("WORK");
				if (null!=wString&&!"".equals(wString)) {
					String[] woStrings=wString.split("#");
					if (null!=woStrings&& woStrings.length>0) {
						for (int j = 0; j < woStrings.length; j++) {
							PageData pd2 = new PageData();
							pd2.put("WORK_ID", woStrings[j]);
							PageData namework=workService.findById(pd2);
							if(null!=namework){
								if(nameString==""){
									nameString = nameString + (String)namework.get("WORKNAME");
						    	}else{
						    		nameString = nameString +"</br>"+ namework.getString("WORKNAME");
						    	}
							}else {
								pd1.put("nameString", woStrings[j]);
							}
						}
					}
				}
				
				//nameList.add(nameString);
				
				pd1.put("nameString", nameString);
				pd1.put("varList",varList.get(i));
				nameList.add(pd1);
			}
			mv.setViewName("businessuser/businessuser_ZClist");
			
			mv.addObject("nameList", nameList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/goAdd")
	public ModelAndView goAdd(){
		HttpServletRequest request=this.getRequest();
		logBefore(logger, "去新增Businessuser页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		request.getSession().removeAttribute("BUSINESSUSER_ID");
		String idString=this.get32UUID();
		request.getSession().setAttribute("BUSINESSUSER_ID", idString);
		try {
			List<PageData>	workList = workService.listAll(pd);
			mv.addObject("workList", workList);
			mv.setViewName("businessuser/businessuser_edit");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	/**
	 * 去新增页面
	 */
	@RequestMapping(value="/goAddZC")
	public ModelAndView goAddZC(){
		logBefore(logger, "去新增Businessuser页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("businessuser/AddAccount");
			mv.addObject("msg", "save1");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	
	
	/**
	 * 修改审核
	 */
	@RequestMapping(value="/buedit")
	public ModelAndView buedit() throws Exception{
		logBefore(logger, "修改Businessuser");
		ModelAndView mv = this.getModelAndView();
		HttpServletRequest request=this.getRequest();
		PageData pd = new PageData();
		pd = this.getPageData();
		String[] WORKList=request.getParameterValues("WORK");
		PageData pd1 = businessuserService.findById(pd);
		if(null==WORKList ||WORKList.length<=0){
			pd.put("WORK", (String)pd1.get("WORK"));
		}else {
			String WORK="";
			for (int i = 0; i < WORKList.length; i++) {
				 if(WORK==""){
					 WORK = WORK + WORKList[i];
			    	}else{
			    		WORK = WORK +"#"+ WORKList[i];
			    	}
			}
			pd.put("WORK", WORK);
		}
		String BUSINESSPWD=(String)pd.get("BUSINESSPWD");
		if(null==BUSINESSPWD || "".equals(BUSINESSPWD)){
			pd.put("BUSINESSPWD", (String)pd1.get("BUSINESSPWD"));
		}else {
			pd.put("BUSINESSPWD", MD5.md5((String)pd.get("BUSINESSPWD")));
			
		}
		String FLAG=(String)pd.get("FLAG");
		if(null==FLAG || "".equals(FLAG)){
			pd.put("FLAG", (String)pd1.get("FLAG"));
		}
		String SQFLAG=(String)pd.get("SQFLAG");
		if(null==SQFLAG || "".equals(SQFLAG)){
			pd.put("SQFLAG", (String)pd1.get("SQFLAG"));
		}
		
			pd.put("ZCFLAG", "1");
		
		String CLJG=(String)pd.get("CLJG");
		if(null==CLJG || "".equals(CLJG)){
			pd.put("CLJG", (String)pd1.get("CLJG"));
		}
		if (null!=pd.get("SHOPADDRESS")) {
			Map<String, Double> json = AddressUtils.getLngAndLat((String) pd.get("SHOPADDRESS"));  
			if(null!=json){
				System.out.println("经度longitude : " + json.get("longitude"));  
	            System.out.println("纬度latitude : " + json.get("latitude"));  
				String longitude2 = String.valueOf(json.get("longitude"));
				String latitude2 = String.valueOf(json.get("latitude"));
				pd.put("LONGITUDE", longitude2);
				pd.put("LATITUDE", latitude2);
			}
			pd.put("COMPANYADDRESS", (String)pd.get("SHOPADDRESS"));
		}
		
		
		businessuserService.editbu(pd);
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		List<PageData>	workList = workService.listAll(pd);
		mv.addObject("workList", workList);
		JSONArray json = JSONArray.fromObject(workList);
		mv.addObject("json", json);
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		pd = businessuserService.findById(pd);	//根据ID读取
		if (null!=pd) {
			String wString=pd.getString("WORK");
			String nameString="";
			if (null!=wString&&!"".equals(wString)) {
				String[] woStrings=wString.split("#");
				if (null!=woStrings&& woStrings.length>0) {
					for (int j = 0; j < woStrings.length; j++) {
						PageData pd2 = new PageData();
						pd2.put("WORK_ID", woStrings[j]);
						PageData namework=workService.findById(pd2);
						if(null!=namework){
							if(nameString==""){
								nameString = nameString + (String)namework.get("WORKNAME");
					    	}else{
					    		nameString = nameString +"</br>"+ namework.getString("WORKNAME");
					    	}
						}else {
							mv.addObject("nameString", woStrings[j]);
						}
					}
				}
			}
			mv.addObject("nameString", nameString);
		}
		Businessuser businessuser=new Businessuser();
		businessuser.setBUSINESSUSER_ID((String)pd1.get("BUSINESSUSER_ID"));
		businessuser.setBUSINESSPWD((String)pd1.get("BUSINESSPWD"));
		businessuser.setTEL(pd1.getString("TEL"));
		businessuser.setFLAG("1");
		businessuser.setCREATIME(pd1.getString("CREATIME"));
		businessuser.setCONTACTSNAME(pd1.getString("CONTACTSNAME"));
		businessuser.setSQFLAG(pd1.getString("SQFLAG"));
		businessuser.setZCFLAG(pd1.getString("ZCFLAG"));
		businessuser.setCLJG(pd1.getString("CLJG"));
		if (null!=(Integer)pd1.get("VISITNUM") && !"".equals((Integer)pd1.get("VISITNUM"))) {
			businessuser.setVISITNUM(Integer.valueOf((Integer)pd1.get("VISITNUM")));
		}else{
			businessuser.setVISITNUM(Integer.valueOf("0"));
		}
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		businessuser.setTYPE(pd1.getString("TYPE"));
		businessuser.setFBUSINESSUSER_ID(pd1.getString("FBUSINESSUSER_ID"));
		businessuser.setFFLAG(pd1.getString("FFLAG"));
		session.setAttribute("TEL",pd1.getString("CONTACTSNAME"));
		request.getSession().setAttribute("user", businessuser);
		session.setAttribute("BUSINESSUSER_ID",pd.getString("BUSINESSUSER_ID"));
		//mv.setViewName("businessuser/chenggong");
		mv.setViewName("redirect:/businessuser/chenggong.do");
		mv.addObject("BUSINESSUSER_ID", pd.getString("BUSINESSUSER_ID"));
		mv.addObject("pd", pd);
		return mv;
	}
	/**
	 * 用户注销
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public ModelAndView logout() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();

		// shiro管理的session
		Subject currentUser = SecurityUtils.getSubject();
		Session session = currentUser.getSession();
		session.removeAttribute("BUSINESSUSER_ID");
		session.removeAttribute(Const.SESSION_USER);
		session.removeAttribute(Const.SESSION_ROLE_RIGHTS);
		session.removeAttribute(Const.SESSION_allmenuList);
		session.removeAttribute(Const.SESSION_menuList);
		session.removeAttribute(Const.SESSION_QX);
		session.removeAttribute(Const.SESSION_userpds);
		session.removeAttribute(Const.SESSION_USERNAME);
		session.removeAttribute(Const.SESSION_USERROL);
		session.removeAttribute("changeMenu");
		this.getRequest().getSession().removeAttribute("user");
		// shiro销毁登录
		Subject subject = SecurityUtils.getSubject();
		subject.logout();

		pd = this.getPageData();
		String msg = pd.getString("msg");
		pd.put("msg", msg);
		mv.setViewName("user/loginX1");
		mv.addObject("pd", pd);
		return mv;
	}
	/**
	 * 修改审核
	 */
	@RequestMapping(value="/buedit1")
	public ModelAndView buedit1() throws Exception{
		logBefore(logger, "修改Businessuser");
		ModelAndView mv = this.getModelAndView();
		HttpServletRequest request=this.getRequest();
		PageData pd = new PageData();
		pd = this.getPageData();
		String[] WORKList=request.getParameterValues("WORK");
		PageData pd1 = businessuserService.findById(pd);
		if(null==WORKList ||WORKList.length<=0){
			pd.put("WORK", (String)pd1.get("WORK"));
		}else {
			String WORK="";
			for (int i = 0; i < WORKList.length; i++) {
				 if(WORK==""){
					 WORK = WORK + WORKList[i];
			    	}else{
			    		WORK = WORK +"#"+ WORKList[i];
			    	}
			}
			pd.put("WORK", WORK);
		}
		String BUSINESSPWD=(String)pd.get("BUSINESSPWD");
		if(null==BUSINESSPWD || "".equals(BUSINESSPWD)){
			pd.put("BUSINESSPWD", (String)pd1.get("BUSINESSPWD"));
		}else {
			pd.put("BUSINESSPWD", MD5.md5((String)pd.get("BUSINESSPWD")));
			
		}
		String FLAG=(String)pd.get("FLAG");
		if(null==FLAG || "".equals(FLAG)){
			pd.put("FLAG", (String)pd1.get("FLAG"));
		}
		String SQFLAG=(String)pd.get("SQFLAG");
		if(null==SQFLAG || "".equals(SQFLAG)){
			pd.put("SQFLAG", (String)pd1.get("SQFLAG"));
		}
		String ZCFLAG=(String)pd.get("ZCFLAG");
		if(null==ZCFLAG || "".equals(ZCFLAG)){
			pd.put("ZCFLAG", "1");
		}
		String CLJG=(String)pd.get("CLJG");
		if(null==CLJG || "".equals(CLJG)){
			pd.put("CLJG", (String)pd1.get("CLJG"));
		}
		businessuserService.editbu(pd);
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		List<PageData>	workList = workService.listAll(pd);
		mv.addObject("workList", workList);
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		pd = businessuserService.findById(pd);	//根据ID读取
		if (null!=pd) {
			String wString=pd.getString("WORK");
			String nameString="";
			if (null!=wString&&!"".equals(wString)) {
				String[] woStrings=wString.split("#");
				if (null!=woStrings&& woStrings.length>0) {
					for (int j = 0; j < woStrings.length; j++) {
						PageData pd2 = new PageData();
						pd2.put("WORK_ID", woStrings[j]);
						PageData namework=workService.findById(pd2);
						if(null!=namework){
							if(nameString==""){
								nameString = nameString + (String)namework.get("WORKNAME");
					    	}else{
					    		nameString = nameString +"</br>"+ namework.getString("WORKNAME");
					    	}
						}else {
							mv.addObject("nameString", woStrings[j]);
						}
					}
				}
			}
			mv.addObject("nameString", nameString);
		}
		mv.setViewName("redirect:/businessuser/chenggong.do");
		mv.addObject("BUSINESSUSER_ID", pd.getString("BUSINESSUSER_ID"));
		mv.addObject("pd", pd);
		return mv;
	}
	
	
	/**
	 * 修改商家信息
	 */
	@RequestMapping(value="/xxedit")
	public ModelAndView xxedit() throws Exception{
		logBefore(logger, "修改Businessuser");
		ModelAndView mv = this.getModelAndView();
		HttpServletRequest request=this.getRequest();
		PageData pd = new PageData();
		pd = this.getPageData();
		String[] WORKList=request.getParameterValues("WORK");
		PageData pd1 = businessuserService.findById(pd);
		if(null==WORKList ||WORKList.length<=0){
			pd.put("WORK", (String)pd1.get("WORK"));
		}else {
			String WORK="";
			for (int i = 0; i < WORKList.length; i++) {
				 if(WORK==""){
					 WORK = WORK + WORKList[i];
			    	}else{
			    		WORK = WORK +"#"+ WORKList[i];
			    	}
			}
			pd.put("WORK", WORK);
		}
		String BUSINESSPWD=(String)pd.get("BUSINESSPWD");
		if(null==BUSINESSPWD || "".equals(BUSINESSPWD)){
			pd.put("BUSINESSPWD", (String)pd1.get("BUSINESSPWD"));
		}else {
			pd.put("BUSINESSPWD", MD5.md5((String)pd.get("BUSINESSPWD")));
			
		}
		String FLAG=(String)pd.get("FLAG");
		if(null==FLAG || "".equals(FLAG)){
			pd.put("FLAG", (String)pd1.get("FLAG"));
		}
		String SQFLAG=(String)pd.get("SQFLAG");
		if(null==SQFLAG || "".equals(SQFLAG)){
			pd.put("SQFLAG", (String)pd1.get("SQFLAG"));
		}
		String ZCFLAG=(String)pd.get("ZCFLAG");
		if(null==ZCFLAG || "".equals(ZCFLAG)){
			pd.put("ZCFLAG", (String)pd1.get("ZCFLAG"));
		}
		String CLJG=(String)pd.get("CLJG");
		if(null==CLJG || "".equals(CLJG)){
			pd.put("CLJG", (String)pd1.get("CLJG"));
		}
		if (null!=pd.get("SHOPADDRESS")) {
			Map<String, Double> json = AddressUtils.getLngAndLat((String) pd.get("SHOPADDRESS"));  
			if(null!=json){
				System.out.println("经度longitude : " + json.get("longitude"));  
	            System.out.println("纬度latitude : " + json.get("latitude"));  
				String longitude2 = String.valueOf(json.get("longitude"));
				String latitude2 = String.valueOf(json.get("latitude"));
				pd.put("LONGITUDE", longitude2);
				pd.put("LATITUDE", latitude2);
			}
			pd.put("COMPANYADDRESS", (String)pd.get("SHOPADDRESS"));
		}
		businessuserService.editxx(pd);
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		List<PageData>	workList = workService.listAll(pd);
		mv.addObject("workList", workList);
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		pd = businessuserService.findById(pd);	//根据ID读取
		if(null!=pd){
			String wString=pd.getString("WORK");
			String nameString="";
			if (null!=wString&&!"".equals(wString)) {
				String[] woStrings=wString.split("#");
				if (null!=woStrings&& woStrings.length>0) {
					for (int j = 0; j < woStrings.length; j++) {
						PageData pd2 = new PageData();
						pd2.put("WORK_ID", woStrings[j]);
						PageData namework=workService.findById(pd2);
						if(null!=namework){
							if(nameString==""){
								nameString = nameString + (String)namework.get("WORKNAME");
					    	}else{
					    		nameString = nameString +"</br>"+ namework.getString("WORKNAME");
					    	}
						}else {
							mv.addObject("nameString", woStrings[j]);
						}
					}
				}
			}
			mv.addObject("nameString", nameString);
		}
		//mv.addObject("msg","success");
		mv.addObject("msg", "xxedit");
		mv.addObject("pd", pd);
		mv.setViewName("businessuser/Store");
		mv.addObject("flag","chenggong");
		//mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 修改商家信息失败审核
	 */
	@RequestMapping(value="/sbedit")
	public ModelAndView sbedit() throws Exception{
		logBefore(logger, "修改Businessuser");
		ModelAndView mv = this.getModelAndView();
		HttpServletRequest request=this.getRequest();
		PageData pd = new PageData();
		pd = this.getPageData();
		String[] WORKList=request.getParameterValues("WORK");
		PageData pd1 = businessuserService.findById(pd);
		if(null==WORKList ||WORKList.length<=0){
			pd.put("WORK", (String)pd1.get("WORK"));
		}else {
			String WORK="";
			for (int i = 0; i < WORKList.length; i++) {
				 if(WORK==""){
					 WORK = WORK + WORKList[i];
			    	}else{
			    		WORK = WORK +"#"+ WORKList[i];
			    	}
			}
			pd.put("WORK", WORK);
		}
		String BUSINESSPWD=(String)pd.get("BUSINESSPWD");
		if(null==BUSINESSPWD || "".equals(BUSINESSPWD)){
			pd.put("BUSINESSPWD", (String)pd1.get("BUSINESSPWD"));
		}else {
			pd.put("BUSINESSPWD", MD5.md5((String)pd.get("BUSINESSPWD")));
			
		}
		String FLAG=(String)pd.get("FLAG");
		if(null==FLAG || "".equals(FLAG)){
			pd.put("FLAG", (String)pd1.get("FLAG"));
		}
		String SQFLAG=(String)pd.get("SQFLAG");
		if(null==SQFLAG || "".equals(SQFLAG)){
			pd.put("SQFLAG", (String)pd1.get("SQFLAG"));
		}
		String ZCFLAG=(String)pd.get("ZCFLAG");
		if(null==ZCFLAG || "".equals(ZCFLAG)){
			pd.put("ZCFLAG", "1");
		}
		String CLJG=(String)pd.get("CLJG");
		if(null==CLJG || "".equals(CLJG)){
			pd.put("CLJG", (String)pd1.get("CLJG"));
		}
		businessuserService.editxx(pd);
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		List<PageData>	workList = workService.listAll(pd);
		mv.addObject("workList", workList);
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		pd = businessuserService.findById(pd);	//根据ID读取
		if(null!=pd){
			String wString=pd.getString("WORK");
			String nameString="";
			if (null!=wString&&!"".equals(wString)) {
				String[] woStrings=wString.split("#");
				if (null!=woStrings&& woStrings.length>0) {
					for (int j = 0; j < woStrings.length; j++) {
						PageData pd2 = new PageData();
						pd2.put("WORK_ID", woStrings[j]);
						PageData namework=workService.findById(pd2);
						if(null!=namework){
							if(nameString==""){
								nameString = nameString + (String)namework.get("WORKNAME");
					    	}else{
					    		nameString = nameString +"</br>"+ namework.getString("WORKNAME");
					    	}
						}else {
							mv.addObject("nameString", woStrings[j]);
						}
					}
				}
			}
			mv.addObject("nameString", nameString);
		}
		//mv.addObject("msg","success");
		mv.addObject("msg", "xxedit");
		mv.addObject("pd", pd);
		mv.setViewName("businessuser/Store");
		mv.addObject("flag","chenggong");
		//mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 去修改审核页面
	 */
	@RequestMapping(value="/goEditbu")
	public ModelAndView goEditbu(){
		logBefore(logger, "去修改Businessuser页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		HttpServletRequest request=this.getRequest();
		try {
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			List<PageData>	workList = workService.listAll(pd);
			mv.addObject("workList", workList);
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			pd = businessuserService.findById(pd);	//根据ID读取
			if (null!=pd) {
				String wString=pd.getString("WORK");
				String nameString="";
				if (null!=wString&&!"".equals(wString)) {
					String[] woStrings=wString.split("#");
					if (null!=woStrings&& woStrings.length>0) {
						for (int j = 0; j < woStrings.length; j++) {
							PageData pd2 = new PageData();
							pd2.put("WORK_ID", woStrings[j]);
							PageData namework=workService.findById(pd2);
							if(null!=namework){
								if(nameString==""){
									nameString = nameString + (String)namework.get("WORKNAME");
						    	}else{
						    		nameString = nameString +"</br>"+ namework.getString("WORKNAME");
						    	}
							}else {
								mv.addObject("nameString", woStrings[j]);
							}
						}
					}
				}
				mv.addObject("nameString", nameString);
			}
			mv.setViewName("businessuser/Storesb");
			
			mv.addObject("msg", "buedit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	/**
	 * 去修改审核页面
	 */
	@RequestMapping(value="/goEditbu1")
	public ModelAndView goEditbu1(){
		logBefore(logger, "去修改Businessuser页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		HttpServletRequest request=this.getRequest();
		try {
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			List<PageData>	workList = workService.listAll(pd);
			mv.addObject("workList", workList);
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			pd = businessuserService.findById(pd);	//根据ID读取
			if (null!=pd) {
				String wString=pd.getString("WORK");
				String nameString="";
				if (null!=wString&&!"".equals(wString)) {
					String[] woStrings=wString.split("#");
					if (null!=woStrings&& woStrings.length>0) {
						for (int j = 0; j < woStrings.length; j++) {
							PageData pd2 = new PageData();
							pd2.put("WORK_ID", woStrings[j]);
							PageData namework=workService.findById(pd2);
							if(null!=namework){
								if(nameString==""){
									nameString = nameString + (String)namework.get("WORKNAME");
						    	}else{
						    		nameString = nameString +"</br>"+ namework.getString("WORKNAME");
						    	}
							}else {
								mv.addObject("nameString", woStrings[j]);
							}
						}
					}
				}
				mv.addObject("nameString", nameString);
			}
			mv.setViewName("businessuser/Storesb");
			
			mv.addObject("msg", "buedit1");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	/**
	 * 去修改商家信息页面
	 */
	@RequestMapping(value="/goEditsb")
	public ModelAndView goEditsb(){
		logBefore(logger, "去修改Businessuser页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		HttpServletRequest request = this.getRequest();
		try {
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			List<PageData>	workList = workService.listAll(pd);
			mv.addObject("workList", workList);
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			pd = businessuserService.findById(pd);	//根据ID读取
			if(null!=pd){
				String wString=pd.getString("WORK");
				String nameString="";
				if (null!=wString&&!"".equals(wString)) {
					String[] woStrings=wString.split("#");
					if (null!=woStrings&& woStrings.length>0) {
						for (int j = 0; j < woStrings.length; j++) {
							PageData pd2 = new PageData();
							pd2.put("WORK_ID", woStrings[j]);
							PageData namework=workService.findById(pd2);
							if(null!=namework){
								if(nameString==""){
									nameString = nameString + (String)namework.get("WORKNAME");
						    	}else{
						    		nameString = nameString +"</br>"+ namework.getString("WORKNAME");
						    	}
							}else {
								mv.addObject("nameString", woStrings[j]);
							}
						}
					}
				}
				mv.addObject("nameString", nameString);
			}
			mv.setViewName("businessuser/Storesb");
			
			mv.addObject("msg", "sbedit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	
	/**
	 * 去修改商家信息页面
	 */
	@RequestMapping(value="/goEditxx")
	public ModelAndView goEditxx(){
		logBefore(logger, "去修改Businessuser页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		HttpServletRequest request = this.getRequest();
		try {
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			List<PageData>	workList = workService.listAll(pd);
			mv.addObject("workList", workList);
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			pd = businessuserService.findById(pd);	//根据ID读取
			if(null!=pd){
				String wString=pd.getString("WORK");
				String nameString="";
				if (null!=wString&&!"".equals(wString)) {
					String[] woStrings=wString.split("#");
					if (null!=woStrings&& woStrings.length>0) {
						for (int j = 0; j < woStrings.length; j++) {
							PageData pd2 = new PageData();
							pd2.put("WORK_ID", woStrings[j]);
							PageData namework=workService.findById(pd2);
							if(null!=namework){
								if(nameString==""){
									nameString = nameString + (String)namework.get("WORKNAME");
						    	}else{
						    		nameString = nameString +","+ namework.getString("WORKNAME");
						    	}
							}else {
								mv.addObject("nameString", woStrings[j]);
							}
						}
					}
				}
				mv.addObject("nameString", nameString);
			}
			mv.setViewName("businessuser/Store");
			
			mv.addObject("msg", "xxedit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}
	
	
	/**
	 * 去修改密码页面
	 */
	@RequestMapping(value="/goEditPassword")
	public ModelAndView goEditPassword(){
		logBefore(logger, "去修改goEditPassword页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			
			mv.setViewName("businessuser/editPassword");
			mv.addObject("msg", "edit1");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	/**
	 * 去修改密码页面
	 */
	@RequestMapping(value="/goforgetPassword")
	public ModelAndView goforgetPassword(){
		logBefore(logger, "去修改goEditPassword页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			
			mv.setViewName("businessuser/forgetPassword");
			mv.addObject("msg", "edit1");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/goEdit")
	public ModelAndView goEdit(){
		logBefore(logger, "去修改Businessuser页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			List<PageData>	workList = workService.listAll(pd);
			mv.addObject("workList", workList);
			pd = businessuserService.findById(pd);	//根据ID读取
			String wString=pd.getString("WORK");
			String nameString="";
			if (null!=wString&&!"".equals(wString)) {
				String[] woStrings=wString.split("#");
				if (null!=woStrings&& woStrings.length>0) {
					for (int j = 0; j < woStrings.length; j++) {
						PageData pd2 = new PageData();
						pd2.put("WORK_ID", woStrings[j]);
						PageData namework=workService.findById(pd2);
						if(null!=namework){
							if(nameString==""){
								nameString = nameString + (String)namework.get("WORKNAME");
					    	}else{
					    		nameString = nameString +"</br>"+ namework.getString("WORKNAME");
					    	}
						}else {
							mv.addObject("nameString", woStrings[j]);
						}
					}
				}
			}
			mv.setViewName("businessuser/businessuser_edit");
			mv.addObject("nameString", nameString);
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	/**
	 * 去详情页面
	 */
	@RequestMapping(value="/goXQing")
	public ModelAndView goXQing(){
		logBefore(logger, "去详情页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			List<PageData>	cardList = membershipcardService.listAll(pd);	//列出Membershipcard列表
			mv.addObject("cardList", cardList);
			List<PageData>	activityList = bussinessactivityService.listAll(pd);	//列出BussinessActivity列表
			mv.addObject("activityList", activityList);
			List<PageData>	workList = workService.listAll(pd);
			mv.addObject("workList", workList);
			pd = businessuserService.findById(pd);	//根据ID读取
			String wString=pd.getString("WORK");
			String nameString="";
			if (null!=wString&&!"".equals(wString)) {
				String[] woStrings=wString.split("#");
				if (null!=woStrings&& woStrings.length>0) {
					for (int j = 0; j < woStrings.length; j++) {
						PageData pd2 = new PageData();
						pd2.put("WORK_ID", woStrings[j]);
						PageData namework=workService.findById(pd2);
						if(null!=namework){
							if(nameString==""){
								nameString = nameString + (String)namework.get("WORKNAME");
					    	}else{
					    		nameString = nameString +"</br>"+ namework.getString("WORKNAME");
					    	}
						}else {
							mv.addObject("nameString", woStrings[j]);
						}
					}
				}
			}
			mv.setViewName("businessuser/businessuser_xq");
			mv.addObject("nameString", nameString);
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() {
		logBefore(logger, "批量删除Businessuser");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				businessuserService.deleteAll(ArrayDATA_IDS);
				pd.put("msg", "ok");
			}else{
				pd.put("msg", "no");
			}
			pdList.add(pd);
			map.put("list", pdList);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		} finally {
			logAfter(logger);
		}
		return AppUtil.returnObject(pd, map);
	}
	
	/*
	 * 导出到excel
	 * @return
	 */
	@RequestMapping(value="/excel")
	public ModelAndView exportExcel(){
		logBefore(logger, "导出Businessuser到excel");
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("商家名称");	//1
			titles.add("登录密码");	//2
			titles.add("手机");	//3
			titles.add("公司地址");	//4
			titles.add("公司名称");	//5
			titles.add("营业执照");	//6
			titles.add("店铺名称");	//7
			titles.add("店铺地址");	//8
			titles.add("店铺logo");	//9
			titles.add("主营业务");	//10
			titles.add("1正常");	//11
			dataMap.put("titles", titles);
			List<PageData> varOList = businessuserService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("BUSINESSUSERNAME"));	//1
				vpd.put("var2", varOList.get(i).getString("BUSINESSPWD"));	//2
				vpd.put("var3", varOList.get(i).getString("TEL"));	//3
				vpd.put("var4", varOList.get(i).getString("COMPANYADDRESS"));	//4
				vpd.put("var5", varOList.get(i).getString("COMPANYNAME"));	//5
				vpd.put("var6", varOList.get(i).getString("BUSINESSLICENSEURL"));	//6
				vpd.put("var7", varOList.get(i).getString("SHOPNAME"));	//7
				vpd.put("var8", varOList.get(i).getString("SHOPADDRESS"));	//8
				vpd.put("var9", varOList.get(i).getString("SHOPLOGO"));	//9
				vpd.put("var10", varOList.get(i).getString("WORK"));	//10
				vpd.put("var11", varOList.get(i).getString("FLAG"));	//11
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv,dataMap);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/* ===============================权限================================== */
	@SuppressWarnings("unchecked")
	public Map<String, String> getHC(){
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>)session.getAttribute(Const.SESSION_QX);
	}
	/* ===============================权限================================== */
	
	@InitBinder
	public void initBinder(WebDataBinder binder){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format,true));
	}
	
	
	
	//***************************************二期修改开始***************************************
	

}

package com.fh.controller.system.login;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.aliyun.common.comm.ServiceClient.Request;
import com.fh.controller.base.BaseController;
import com.fh.entity.businessuser.Businessuser;
import com.fh.service.businessuser.BusinessuserService;
import com.fh.service.system.menu.MenuService;
import com.fh.service.system.role.RoleService;
import com.fh.service.system.user.UserService;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.MD5;
import com.fh.util.PageData;
import com.fh.util.Tools;

/*
 * 总入口
 */
@Controller
public class LoginXController extends BaseController {

	@Resource(name = "userService")
	private UserService userService;
	@Resource(name = "menuService")
	private MenuService menuService;
	@Resource(name = "roleService")
	private RoleService roleService;
	@Resource(name="businessuserService")
	private BusinessuserService businessuserService;
	/**
	 * 获取登录用户的IP
	 * @throws Exception
	 */
	public void getRemortIP(String USERNAME) throws Exception {
		PageData pd = new PageData();
		HttpServletRequest request = this.getRequest();
		String ip = "";
		if (request.getHeader("x-forwarded-for") == null) {
			ip = request.getRemoteAddr();
		} else {
			ip = request.getHeader("x-forwarded-for");
		}
		pd.put("USERNAME", USERNAME);
		pd.put("IP", ip);
		userService.saveIP(pd);
	}

	
	

	/**
	 * 请求登录，验证用户
	 */
	@RequestMapping(value = "/login_login", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object login() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "";
		String KEYDATA[] = pd.getString("KEYDATA").replaceAll("qq123456789fh", "").replaceAll("QQ987654321fh", "").split(",fh,");

		if (null != KEYDATA && KEYDATA.length == 3) {
			// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			//String sessionCode = (String) session.getAttribute(Const.SESSION_SECURITY_CODE); // 获取session中的验证码

			//String code = KEYDATA[2];
			
				String BUSINESSUSERNAME = KEYDATA[0];
				String BUSINESSPWD = KEYDATA[1];
				pd.put("USERNAME", BUSINESSUSERNAME);
				//if (Tools.notEmpty(sessionCode) && sessionCode.equalsIgnoreCase(code)) {
					String passwd=MD5.md5(BUSINESSPWD);// 密码加密
					pd.put("PASSWORD", passwd);
					pd = userService.getUserByNameAndPwd(pd);
					if (pd != null) {
						if (!"0".equals(pd.getString("FBUSINESSUSER_ID").trim())) {
							session.setAttribute("BUSINESSUSER_ID1",pd.getString("BUSINESSUSER_ID"));
							PageData pd1 = new PageData();
							pd.put("BUSINESSUSER_ID", pd.getString("FBUSINESSUSER_ID"));
							pd1 = businessuserService.findById(pd);	//根据ID读取
								Businessuser businessuser=new Businessuser();
								businessuser.setBUSINESSUSER_ID(pd1.getString("BUSINESSUSER_ID"));
								businessuser.setBUSINESSUSERNAME(pd1.getString("BUSINESSUSERNAME"));
								businessuser.setBUSINESSPWD(pd1.getString("BUSINESSPWD"));
								businessuser.setTEL(pd1.getString("TEL"));
								businessuser.setCOMPANYADDRESS(pd1.getString("COMPANYADDRESS"));
								businessuser.setCOMPANYNAME(pd1.getString("COMPANYNAME"));
								businessuser.setBUSINESSLICENSEURL(pd1.getString("BUSINESSLICENSEURL"));
								businessuser.setSHOPNAME(pd1.getString("SHOPNAME"));
								businessuser.setSHOPADDRESS(pd1.getString("SHOPADDRESS"));
								businessuser.setSHOPLOGO(pd1.getString("SHOPLOGO"));
								businessuser.setWORK(pd1.getString("WORK"));
								businessuser.setFLAG(pd1.getString("FLAG"));
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
								
								businessuser.setTYPE(pd1.getString("TYPE"));
								businessuser.setFBUSINESSUSER_ID(pd1.getString("FBUSINESSUSER_ID"));
								businessuser.setFFLAG(pd1.getString("FFLAG"));
								session.setAttribute(Const.SESSION_USER, businessuser);
								if (null!=pd.getString("FFLAG")&&"2".trim().equals(pd.getString("FFLAG").trim())) {
									errInfo = "jinyong"; // 禁用
								}
								session.setAttribute("TEL",pd1.getString("CONTACTSNAME"));
								session.setAttribute(Const.SESSION_USERNAME,BUSINESSUSERNAME);
								session.removeAttribute(Const.SESSION_SECURITY_CODE);
								session.setAttribute("BUSINESSUSER_ID",pd.getString("FBUSINESSUSER_ID"));
								
						}else {
							if ("2".equals(pd.getString("TYPE").trim())) {
								if ("1".equals(pd.getString("ZCFLAG").trim())) {
									errInfo = "daishenhe"; // 待审核
									map.put("BUSINESSUSER_ID", pd.getString("BUSINESSUSER_ID"));
									
									Businessuser businessuser=new Businessuser();
									businessuser.setBUSINESSUSER_ID(pd.getString("BUSINESSUSER_ID"));
									businessuser.setBUSINESSUSERNAME(pd.getString("BUSINESSUSERNAME"));
									businessuser.setBUSINESSPWD(pd.getString("BUSINESSPWD"));
									businessuser.setTEL(pd.getString("TEL"));
									businessuser.setCOMPANYADDRESS(pd.getString("COMPANYADDRESS"));
									businessuser.setCOMPANYNAME(pd.getString("COMPANYNAME"));
									businessuser.setBUSINESSLICENSEURL(pd.getString("BUSINESSLICENSEURL"));
									businessuser.setSHOPNAME(pd.getString("SHOPNAME"));
									businessuser.setSHOPADDRESS(pd.getString("SHOPADDRESS"));
									businessuser.setSHOPLOGO(pd.getString("SHOPLOGO"));
									businessuser.setWORK(pd.getString("WORK"));
									businessuser.setFLAG(pd.getString("FLAG"));
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
									
									businessuser.setTYPE(pd.getString("TYPE"));
									businessuser.setFBUSINESSUSER_ID(pd.getString("FBUSINESSUSER_ID"));
									businessuser.setFFLAG(pd.getString("FFLAG"));
									map.put("BUSINESSUSER_ID", pd.getString("BUSINESSUSER_ID"));
									session.setAttribute(Const.SESSION_USER, businessuser);
									this.getRequest().getSession().setAttribute("user", businessuser);
									session.setAttribute("TEL",pd.getString("CONTACTSNAME"));
									session.setAttribute(Const.SESSION_USERNAME,BUSINESSUSERNAME);
									session.removeAttribute(Const.SESSION_SECURITY_CODE);
									session.setAttribute("BUSINESSUSER_ID",pd.getString("BUSINESSUSER_ID"));
									
								}else if("3".equals(pd.getString("ZCFLAG").trim())){
									errInfo = "shenheshibai"; // 审核失败
									session.setAttribute("BUSINESSUSER_ID",pd.getString("BUSINESSUSER_ID"));
									map.put("BUSINESSUSER_ID", pd.getString("BUSINESSUSER_ID"));
									
									
									Businessuser businessuser=new Businessuser();
									businessuser.setBUSINESSUSER_ID(pd.getString("BUSINESSUSER_ID"));
									businessuser.setBUSINESSUSERNAME(pd.getString("BUSINESSUSERNAME"));
									businessuser.setBUSINESSPWD(pd.getString("BUSINESSPWD"));
									businessuser.setTEL(pd.getString("TEL"));
									businessuser.setCOMPANYADDRESS(pd.getString("COMPANYADDRESS"));
									businessuser.setCOMPANYNAME(pd.getString("COMPANYNAME"));
									businessuser.setBUSINESSLICENSEURL(pd.getString("BUSINESSLICENSEURL"));
									businessuser.setSHOPNAME(pd.getString("SHOPNAME"));
									businessuser.setSHOPADDRESS(pd.getString("SHOPADDRESS"));
									businessuser.setSHOPLOGO(pd.getString("SHOPLOGO"));
									businessuser.setWORK(pd.getString("WORK"));
									businessuser.setFLAG(pd.getString("FLAG"));
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
									
									businessuser.setTYPE(pd.getString("TYPE"));
									businessuser.setFBUSINESSUSER_ID(pd.getString("FBUSINESSUSER_ID"));
									businessuser.setFFLAG(pd.getString("FFLAG"));
									map.put("BUSINESSUSER_ID", pd.getString("BUSINESSUSER_ID"));
									session.setAttribute(Const.SESSION_USER, businessuser);
									this.getRequest().getSession().setAttribute("user", businessuser);
									session.setAttribute("TEL",pd.getString("CONTACTSNAME"));
									session.setAttribute(Const.SESSION_USERNAME,BUSINESSUSERNAME);
									session.removeAttribute(Const.SESSION_SECURITY_CODE);
									session.setAttribute("BUSINESSUSER_ID",pd.getString("BUSINESSUSER_ID"));
									
								}else if("4".equals(pd.getString("ZCFLAG").trim())){
									errInfo = "tianxie"; // 审核信息填写
									Businessuser businessuser=new Businessuser();
									businessuser.setBUSINESSUSER_ID(pd.getString("BUSINESSUSER_ID"));
									businessuser.setBUSINESSUSERNAME(pd.getString("BUSINESSUSERNAME"));
									businessuser.setBUSINESSPWD(pd.getString("BUSINESSPWD"));
									businessuser.setTEL(pd.getString("TEL"));
									businessuser.setCOMPANYADDRESS(pd.getString("COMPANYADDRESS"));
									businessuser.setCOMPANYNAME(pd.getString("COMPANYNAME"));
									businessuser.setBUSINESSLICENSEURL(pd.getString("BUSINESSLICENSEURL"));
									businessuser.setSHOPNAME(pd.getString("SHOPNAME"));
									businessuser.setSHOPADDRESS(pd.getString("SHOPADDRESS"));
									businessuser.setSHOPLOGO(pd.getString("SHOPLOGO"));
									businessuser.setWORK(pd.getString("WORK"));
									businessuser.setFLAG(pd.getString("FLAG"));
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
									
									businessuser.setTYPE(pd.getString("TYPE"));
									businessuser.setFBUSINESSUSER_ID(pd.getString("FBUSINESSUSER_ID"));
									businessuser.setFFLAG(pd.getString("FFLAG"));
									map.put("BUSINESSUSER_ID", pd.getString("BUSINESSUSER_ID"));
									session.setAttribute("TEL",pd.getString("CONTACTSNAME"));
									session.setAttribute(Const.SESSION_USER, businessuser);
									session.setAttribute(Const.SESSION_USERNAME,BUSINESSUSERNAME);
									session.removeAttribute(Const.SESSION_SECURITY_CODE);
									session.setAttribute("BUSINESSUSER_ID",pd.getString("BUSINESSUSER_ID"));
								}else{
									Businessuser businessuser=new Businessuser();
									businessuser.setBUSINESSUSER_ID(pd.getString("BUSINESSUSER_ID"));
									businessuser.setBUSINESSUSERNAME(pd.getString("BUSINESSUSERNAME"));
									businessuser.setBUSINESSPWD(pd.getString("BUSINESSPWD"));
									businessuser.setTEL(pd.getString("TEL"));
									businessuser.setCOMPANYADDRESS(pd.getString("COMPANYADDRESS"));
									businessuser.setCOMPANYNAME(pd.getString("COMPANYNAME"));
									businessuser.setBUSINESSLICENSEURL(pd.getString("BUSINESSLICENSEURL"));
									businessuser.setSHOPNAME(pd.getString("SHOPNAME"));
									businessuser.setSHOPADDRESS(pd.getString("SHOPADDRESS"));
									businessuser.setSHOPLOGO(pd.getString("SHOPLOGO"));
									businessuser.setWORK(pd.getString("WORK"));
									businessuser.setFLAG(pd.getString("FLAG"));
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
									
									businessuser.setTYPE(pd.getString("TYPE"));
									businessuser.setFBUSINESSUSER_ID(pd.getString("FBUSINESSUSER_ID"));
									businessuser.setFFLAG(pd.getString("FFLAG"));
									session.setAttribute(Const.SESSION_USER, businessuser);
									if (null!=pd.getString("FFLAG")&&"2".trim().equals(pd.getString("FFLAG").trim())) {
										errInfo = "jinyong"; // 禁用
									}
									session.setAttribute("TEL",pd.getString("CONTACTSNAME"));
									session.setAttribute(Const.SESSION_USERNAME,BUSINESSUSERNAME);
									session.removeAttribute(Const.SESSION_SECURITY_CODE);
									session.setAttribute("BUSINESSUSER_ID",pd.getString("BUSINESSUSER_ID"));
								}
							}else if ("3".equals(pd.getString("TYPE").trim())){
								
								if ("2".equals(pd.getString("FLAG").trim())) {
									errInfo = "jinyong"; // 禁用
								}else if("3".equals(pd.getString("FLAG").trim())){
									errInfo = "xiajia"; // 下架
								}else {
									Businessuser businessuser=new Businessuser();
									businessuser.setBUSINESSUSER_ID(pd.getString("BUSINESSUSER_ID"));
									businessuser.setBUSINESSUSERNAME(pd.getString("BUSINESSUSERNAME"));
									businessuser.setBUSINESSPWD(pd.getString("BUSINESSPWD"));
									businessuser.setTEL(pd.getString("TEL"));
									businessuser.setCOMPANYADDRESS(pd.getString("COMPANYADDRESS"));
									businessuser.setCOMPANYNAME(pd.getString("COMPANYNAME"));
									businessuser.setBUSINESSLICENSEURL(pd.getString("BUSINESSLICENSEURL"));
									businessuser.setSHOPNAME(pd.getString("SHOPNAME"));
									businessuser.setSHOPADDRESS(pd.getString("SHOPADDRESS"));
									businessuser.setSHOPLOGO(pd.getString("SHOPLOGO"));
									businessuser.setWORK(pd.getString("WORK"));
									businessuser.setFLAG(pd.getString("FLAG"));
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
									
									businessuser.setTYPE(pd.getString("TYPE"));
									businessuser.setFBUSINESSUSER_ID(pd.getString("FBUSINESSUSER_ID"));
									businessuser.setFFLAG(pd.getString("FFLAG"));
									session.setAttribute(Const.SESSION_USER, businessuser);
									if (null!=pd.getString("FFLAG")&&"2".trim().equals(pd.getString("FFLAG").trim())) {
										errInfo = "jinyong"; // 禁用
									}
									session.setAttribute("TEL",pd.getString("CONTACTSNAME"));
									session.setAttribute(Const.SESSION_USERNAME,BUSINESSUSERNAME);
									session.removeAttribute(Const.SESSION_SECURITY_CODE);
									System.out.println(pd);
									System.out.println(pd.getString("BUSINESSUSER_ID"));
									HttpServletRequest request =this.getRequest();
									request.getSession().setAttribute("BUSINESSUSER_ID", pd.getString("BUSINESSUSER_ID"));
									//session.setAttribute("BUSINESSUSER_ID",pd.getString("BUSINESSUSER_ID"));
								}
							 
							}else {
								Businessuser businessuser=new Businessuser();
								businessuser.setBUSINESSUSER_ID(pd.getString("BUSINESSUSER_ID"));
								businessuser.setBUSINESSUSERNAME(pd.getString("BUSINESSUSERNAME"));
								businessuser.setBUSINESSPWD(pd.getString("BUSINESSPWD"));
								businessuser.setTEL(pd.getString("TEL"));
								businessuser.setCOMPANYADDRESS(pd.getString("COMPANYADDRESS"));
								businessuser.setCOMPANYNAME(pd.getString("COMPANYNAME"));
								businessuser.setBUSINESSLICENSEURL(pd.getString("BUSINESSLICENSEURL"));
								businessuser.setSHOPNAME(pd.getString("SHOPNAME"));
								businessuser.setSHOPADDRESS(pd.getString("SHOPADDRESS"));
								businessuser.setSHOPLOGO(pd.getString("SHOPLOGO"));
								businessuser.setWORK(pd.getString("WORK"));
								businessuser.setFLAG(pd.getString("FLAG"));
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
								
								businessuser.setTYPE(pd.getString("TYPE"));
								businessuser.setFBUSINESSUSER_ID(pd.getString("FBUSINESSUSER_ID"));
								businessuser.setFFLAG(pd.getString("FFLAG"));
								session.setAttribute(Const.SESSION_USER, businessuser);
								if (null!=pd.getString("FFLAG")&&"2".trim().equals(pd.getString("FFLAG").trim())) {
									errInfo = "jinyong"; // 禁用
								}
								session.setAttribute("TEL",pd.getString("CONTACTSNAME"));
								session.setAttribute(Const.SESSION_USERNAME,BUSINESSUSERNAME);
								session.removeAttribute(Const.SESSION_SECURITY_CODE);
								session.setAttribute("BUSINESSUSER_ID",pd.getString("BUSINESSUSER_ID"));
							}
						}
						
					} else {
						errInfo = "usererror"; // 用户名或密码有误
					}
				//} 
				if (Tools.isEmpty(errInfo)) {
					errInfo = "success"; // 验证成功
				}
		} else {
			errInfo = "error"; // 缺少参数
		}
		System.out.println("errInfo:----------"+"("+errInfo+")");
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}

	/**
	 * 请求登录，验证用户
	 */
	@RequestMapping(value = "/login_login1", produces = "application/json;charset=UTF-8")
	@ResponseBody
	public Object login1() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String errInfo = "";
		String KEYDATA[] = pd.getString("KEYDATA").replaceAll("qq123456789fh", "").replaceAll("QQ987654321fh", "").split(",fh,");

		if (null != KEYDATA && KEYDATA.length == 3) {
			// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
			//String sessionCode = (String) session.getAttribute(Const.SESSION_SECURITY_CODE); // 获取session中的验证码

			//String code = KEYDATA[2];
			
				String BUSINESSUSERNAME = KEYDATA[0];
				String BUSINESSPWD = KEYDATA[1];
				pd.put("USERNAME", BUSINESSUSERNAME);
				//if (Tools.notEmpty(sessionCode) && sessionCode.equalsIgnoreCase(code)) {
					String passwd=BUSINESSPWD;
					pd.put("PASSWORD", passwd);
					pd = userService.getUserByNameAndPwd(pd);
					if (pd != null) {
						if ("2".equals(pd.getString("TYPE").trim())) {
							if ("1".equals(pd.getString("ZCFLAG").trim())) {
								errInfo = "daishenhe"; // 待审核
								map.put("BUSINESSUSER_ID", pd.getString("BUSINESSUSER_ID"));
								
								Businessuser businessuser=new Businessuser();
								businessuser.setBUSINESSUSER_ID(pd.getString("BUSINESSUSER_ID"));
								businessuser.setBUSINESSUSERNAME(pd.getString("BUSINESSUSERNAME"));
								businessuser.setBUSINESSPWD(pd.getString("BUSINESSPWD"));
								businessuser.setTEL(pd.getString("TEL"));
								businessuser.setCOMPANYADDRESS(pd.getString("COMPANYADDRESS"));
								businessuser.setCOMPANYNAME(pd.getString("COMPANYNAME"));
								businessuser.setBUSINESSLICENSEURL(pd.getString("BUSINESSLICENSEURL"));
								businessuser.setSHOPNAME(pd.getString("SHOPNAME"));
								businessuser.setSHOPADDRESS(pd.getString("SHOPADDRESS"));
								businessuser.setSHOPLOGO(pd.getString("SHOPLOGO"));
								businessuser.setWORK(pd.getString("WORK"));
								businessuser.setFLAG(pd.getString("FLAG"));
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
								
								businessuser.setTYPE(pd.getString("TYPE"));
								businessuser.setFBUSINESSUSER_ID(pd.getString("FBUSINESSUSER_ID"));
								businessuser.setFFLAG(pd.getString("FFLAG"));
								map.put("BUSINESSUSER_ID", pd.getString("BUSINESSUSER_ID"));
								session.setAttribute(Const.SESSION_USER, businessuser);
								this.getRequest().getSession().setAttribute("user", businessuser);
								session.setAttribute("TEL",pd.getString("CONTACTSNAME"));
								session.setAttribute(Const.SESSION_USERNAME,BUSINESSUSERNAME);
								session.removeAttribute(Const.SESSION_SECURITY_CODE);
								session.setAttribute("BUSINESSUSER_ID",pd.getString("BUSINESSUSER_ID"));
								
							}else if("3".equals(pd.getString("ZCFLAG").trim())){
								errInfo = "shenheshibai"; // 审核失败
								session.setAttribute("BUSINESSUSER_ID",pd.getString("BUSINESSUSER_ID"));
								map.put("BUSINESSUSER_ID", pd.getString("BUSINESSUSER_ID"));
								
								
								Businessuser businessuser=new Businessuser();
								businessuser.setBUSINESSUSER_ID(pd.getString("BUSINESSUSER_ID"));
								businessuser.setBUSINESSUSERNAME(pd.getString("BUSINESSUSERNAME"));
								businessuser.setBUSINESSPWD(pd.getString("BUSINESSPWD"));
								businessuser.setTEL(pd.getString("TEL"));
								businessuser.setCOMPANYADDRESS(pd.getString("COMPANYADDRESS"));
								businessuser.setCOMPANYNAME(pd.getString("COMPANYNAME"));
								businessuser.setBUSINESSLICENSEURL(pd.getString("BUSINESSLICENSEURL"));
								businessuser.setSHOPNAME(pd.getString("SHOPNAME"));
								businessuser.setSHOPADDRESS(pd.getString("SHOPADDRESS"));
								businessuser.setSHOPLOGO(pd.getString("SHOPLOGO"));
								businessuser.setWORK(pd.getString("WORK"));
								businessuser.setFLAG(pd.getString("FLAG"));
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
								
								businessuser.setTYPE(pd.getString("TYPE"));
								businessuser.setFBUSINESSUSER_ID(pd.getString("FBUSINESSUSER_ID"));
								businessuser.setFFLAG(pd.getString("FFLAG"));
								map.put("BUSINESSUSER_ID", pd.getString("BUSINESSUSER_ID"));
								session.setAttribute(Const.SESSION_USER, businessuser);
								this.getRequest().getSession().setAttribute("user", businessuser);
								session.setAttribute("TEL",pd.getString("CONTACTSNAME"));
								session.setAttribute(Const.SESSION_USERNAME,BUSINESSUSERNAME);
								session.removeAttribute(Const.SESSION_SECURITY_CODE);
								session.setAttribute("BUSINESSUSER_ID",pd.getString("BUSINESSUSER_ID"));
								
							}else if("4".equals(pd.getString("ZCFLAG").trim())){
								errInfo = "tianxie"; // 审核信息填写
								Businessuser businessuser=new Businessuser();
								businessuser.setBUSINESSUSER_ID(pd.getString("BUSINESSUSER_ID"));
								businessuser.setBUSINESSUSERNAME(pd.getString("BUSINESSUSERNAME"));
								businessuser.setBUSINESSPWD(pd.getString("BUSINESSPWD"));
								businessuser.setTEL(pd.getString("TEL"));
								businessuser.setCOMPANYADDRESS(pd.getString("COMPANYADDRESS"));
								businessuser.setCOMPANYNAME(pd.getString("COMPANYNAME"));
								businessuser.setBUSINESSLICENSEURL(pd.getString("BUSINESSLICENSEURL"));
								businessuser.setSHOPNAME(pd.getString("SHOPNAME"));
								businessuser.setSHOPADDRESS(pd.getString("SHOPADDRESS"));
								businessuser.setSHOPLOGO(pd.getString("SHOPLOGO"));
								businessuser.setWORK(pd.getString("WORK"));
								businessuser.setFLAG(pd.getString("FLAG"));
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
								
								businessuser.setTYPE(pd.getString("TYPE"));
								businessuser.setFBUSINESSUSER_ID(pd.getString("FBUSINESSUSER_ID"));
								businessuser.setFFLAG(pd.getString("FFLAG"));
								map.put("BUSINESSUSER_ID", pd.getString("BUSINESSUSER_ID"));
								session.setAttribute("TEL",pd.getString("CONTACTSNAME"));
								session.setAttribute(Const.SESSION_USER, businessuser);
								session.setAttribute(Const.SESSION_USERNAME,BUSINESSUSERNAME);
								session.removeAttribute(Const.SESSION_SECURITY_CODE);
								session.setAttribute("BUSINESSUSER_ID",pd.getString("BUSINESSUSER_ID"));
							}else{
								Businessuser businessuser=new Businessuser();
								businessuser.setBUSINESSUSER_ID(pd.getString("BUSINESSUSER_ID"));
								businessuser.setBUSINESSUSERNAME(pd.getString("BUSINESSUSERNAME"));
								businessuser.setBUSINESSPWD(pd.getString("BUSINESSPWD"));
								businessuser.setTEL(pd.getString("TEL"));
								businessuser.setCOMPANYADDRESS(pd.getString("COMPANYADDRESS"));
								businessuser.setCOMPANYNAME(pd.getString("COMPANYNAME"));
								businessuser.setBUSINESSLICENSEURL(pd.getString("BUSINESSLICENSEURL"));
								businessuser.setSHOPNAME(pd.getString("SHOPNAME"));
								businessuser.setSHOPADDRESS(pd.getString("SHOPADDRESS"));
								businessuser.setSHOPLOGO(pd.getString("SHOPLOGO"));
								businessuser.setWORK(pd.getString("WORK"));
								businessuser.setFLAG(pd.getString("FLAG"));
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
								
								businessuser.setTYPE(pd.getString("TYPE"));
								businessuser.setFBUSINESSUSER_ID(pd.getString("FBUSINESSUSER_ID"));
								businessuser.setFFLAG(pd.getString("FFLAG"));
								session.setAttribute(Const.SESSION_USER, businessuser);
								if (null!=pd.getString("FFLAG")&&"2".trim().equals(pd.getString("FFLAG").trim())) {
									errInfo = "jinyong"; // 禁用
								}
								session.setAttribute("TEL",pd.getString("CONTACTSNAME"));
								session.setAttribute(Const.SESSION_USERNAME,BUSINESSUSERNAME);
								session.removeAttribute(Const.SESSION_SECURITY_CODE);
								session.setAttribute("BUSINESSUSER_ID",pd.getString("BUSINESSUSER_ID"));
							}
						}else if ("3".equals(pd.getString("TYPE").trim())){
							
							if ("2".equals(pd.getString("FLAG").trim())) {
								errInfo = "jinyong"; // 禁用
							}else if("3".equals(pd.getString("FLAG").trim())){
								errInfo = "xiajia"; // 下架
							}else {
								Businessuser businessuser=new Businessuser();
								businessuser.setBUSINESSUSER_ID(pd.getString("BUSINESSUSER_ID"));
								businessuser.setBUSINESSUSERNAME(pd.getString("BUSINESSUSERNAME"));
								businessuser.setBUSINESSPWD(pd.getString("BUSINESSPWD"));
								businessuser.setTEL(pd.getString("TEL"));
								businessuser.setCOMPANYADDRESS(pd.getString("COMPANYADDRESS"));
								businessuser.setCOMPANYNAME(pd.getString("COMPANYNAME"));
								businessuser.setBUSINESSLICENSEURL(pd.getString("BUSINESSLICENSEURL"));
								businessuser.setSHOPNAME(pd.getString("SHOPNAME"));
								businessuser.setSHOPADDRESS(pd.getString("SHOPADDRESS"));
								businessuser.setSHOPLOGO(pd.getString("SHOPLOGO"));
								businessuser.setWORK(pd.getString("WORK"));
								businessuser.setFLAG(pd.getString("FLAG"));
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
								
								businessuser.setTYPE(pd.getString("TYPE"));
								businessuser.setFBUSINESSUSER_ID(pd.getString("FBUSINESSUSER_ID"));
								businessuser.setFFLAG(pd.getString("FFLAG"));
								session.setAttribute(Const.SESSION_USER, businessuser);
								if (null!=pd.getString("FFLAG")&&"2".trim().equals(pd.getString("FFLAG").trim())) {
									errInfo = "jinyong"; // 禁用
								}
								session.setAttribute("TEL",pd.getString("CONTACTSNAME"));
								session.setAttribute(Const.SESSION_USERNAME,BUSINESSUSERNAME);
								session.removeAttribute(Const.SESSION_SECURITY_CODE);
								System.out.println(pd);
								System.out.println(pd.getString("BUSINESSUSER_ID"));
								HttpServletRequest request =this.getRequest();
								request.getSession().setAttribute("BUSINESSUSER_ID", pd.getString("BUSINESSUSER_ID"));
								//session.setAttribute("BUSINESSUSER_ID",pd.getString("BUSINESSUSER_ID"));
							}
						 
						}else {
							Businessuser businessuser=new Businessuser();
							businessuser.setBUSINESSUSER_ID(pd.getString("BUSINESSUSER_ID"));
							businessuser.setBUSINESSUSERNAME(pd.getString("BUSINESSUSERNAME"));
							businessuser.setBUSINESSPWD(pd.getString("BUSINESSPWD"));
							businessuser.setTEL(pd.getString("TEL"));
							businessuser.setCOMPANYADDRESS(pd.getString("COMPANYADDRESS"));
							businessuser.setCOMPANYNAME(pd.getString("COMPANYNAME"));
							businessuser.setBUSINESSLICENSEURL(pd.getString("BUSINESSLICENSEURL"));
							businessuser.setSHOPNAME(pd.getString("SHOPNAME"));
							businessuser.setSHOPADDRESS(pd.getString("SHOPADDRESS"));
							businessuser.setSHOPLOGO(pd.getString("SHOPLOGO"));
							businessuser.setWORK(pd.getString("WORK"));
							businessuser.setFLAG(pd.getString("FLAG"));
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
							
							businessuser.setTYPE(pd.getString("TYPE"));
							businessuser.setFBUSINESSUSER_ID(pd.getString("FBUSINESSUSER_ID"));
							businessuser.setFFLAG(pd.getString("FFLAG"));
							session.setAttribute(Const.SESSION_USER, businessuser);
							if (null!=pd.getString("FFLAG")&&"2".trim().equals(pd.getString("FFLAG").trim())) {
								errInfo = "jinyong"; // 禁用
							}
							session.setAttribute("TEL",pd.getString("CONTACTSNAME"));
							session.setAttribute(Const.SESSION_USERNAME,BUSINESSUSERNAME);
							session.removeAttribute(Const.SESSION_SECURITY_CODE);
							session.setAttribute("BUSINESSUSER_ID",pd.getString("BUSINESSUSER_ID"));
						}
					} else {
						errInfo = "usererror"; // 用户名或密码有误
					}
				//} 
				if (Tools.isEmpty(errInfo)) {
					errInfo = "success"; // 验证成功
				}
		} else {
			errInfo = "error"; // 缺少参数
		}
		System.out.println("errInfo:----------"+"("+errInfo+")");
		map.put("result", errInfo);
		return AppUtil.returnObject(new PageData(), map);
	}
	
	
	
	
	
	
	/**
	 * 访问系统首页
	 */
	@RequestMapping(value = "/main/index")
	public ModelAndView login_index() {
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {

			// shiro管理的session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();

			Businessuser businessuser = (Businessuser) session.getAttribute(Const.SESSION_USER);
			if (businessuser != null) {
				mv.setViewName("system/admin/index");
				
			} else {
				mv.setViewName("system/admin/loginX");// session失效后跳转登录页面
			}

		} catch (Exception e) {
			mv.setViewName("system/admin/login");
			logger.error(e.getMessage(), e);
		}
		mv.addObject("pd", pd);
		return mv;
	}

	/**
	 * 进入tab标签
	 * @return
	 */
	@RequestMapping(value = "/tab")
	public String tab() {
		return "system/admin/tab";
	}

	/**
	 * 进入首页后的默认页面
	 * @return
	 */
	@RequestMapping(value = "/login_default")
	public String defaultPage() {
		return "system/admin/default";
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
	 * 获取用户权限
	 */
	public Map<String, String> getUQX(Session session) {
		PageData pd = new PageData();
		Map<String, String> map = new HashMap<String, String>();
		try {
			String USERNAME = session.getAttribute(Const.SESSION_USERNAME).toString();
			pd.put(Const.SESSION_USERNAME, USERNAME);
			String ROLE_ID = userService.findByUId(pd).get("ROLE_ID").toString();

			pd.put("ROLE_ID", ROLE_ID);

			PageData pd2 = new PageData();
			pd2.put(Const.SESSION_USERNAME, USERNAME);
			pd2.put("ROLE_ID", ROLE_ID);

			pd = roleService.findObjectById(pd);

			pd2 = roleService.findGLbyrid(pd2);
			if (null != pd2) {
				map.put("FX_QX", pd2.get("FX_QX").toString());
				map.put("FW_QX", pd2.get("FW_QX").toString());
				map.put("QX1", pd2.get("QX1").toString());
				map.put("QX2", pd2.get("QX2").toString());
				map.put("QX3", pd2.get("QX3").toString());
				map.put("QX4", pd2.get("QX4").toString());

				pd2.put("ROLE_ID", ROLE_ID);
				pd2 = roleService.findYHbyrid(pd2);
				map.put("C1", pd2.get("C1").toString());
				map.put("C2", pd2.get("C2").toString());
				map.put("C3", pd2.get("C3").toString());
				map.put("C4", pd2.get("C4").toString());
				map.put("Q1", pd2.get("Q1").toString());
				map.put("Q2", pd2.get("Q2").toString());
				map.put("Q3", pd2.get("Q3").toString());
				map.put("Q4", pd2.get("Q4").toString());
			}

			map.put("adds", pd.getString("ADD_QX"));
			map.put("dels", pd.getString("DEL_QX"));
			map.put("edits", pd.getString("EDIT_QX"));
			map.put("chas", pd.getString("CHA_QX"));

			// System.out.println(map);

			this.getRemortIP(USERNAME);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return map;
	}

}

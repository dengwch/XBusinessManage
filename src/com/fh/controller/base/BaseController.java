package com.fh.controller.base;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.fh.entity.Page;
import com.fh.entity.businessuser.Businessuser;
import com.fh.entity.system.User;
import com.fh.service.backgroup.log.LogService;
import com.fh.util.Const;
import com.fh.util.Logger;
import com.fh.util.PageData;
import com.fh.util.Tools;
import com.fh.util.UuidUtil;

public class BaseController {
	
	String menuUrl = "log/list.do"; //菜单地址(权限用)
	protected Logger logger = Logger.getLogger(this.getClass());

	private static final long serialVersionUID = 6357869213649815390L;
	
	public PageData addLog(String NAME,
			String content,
			String typeName) throws Exception{
		PageData pd = new PageData();
		pd.put("LOG_ID", this.get32UUID());	//主键
		pd.put("NAME", NAME);	//操作名称
		pd.put("CONTENT", content);	//操作内容
		pd.put("TYPENAME", typeName);	//操作模块
		Subject currentUser = SecurityUtils.getSubject();  //shiro管理的session
		Session session = currentUser.getSession();
		Businessuser user=(Businessuser)session.getAttribute(Const.SESSION_USER);
		String createUserId="";
		if(user!=null){
			createUserId=user.getBUSINESSUSER_ID();
		}
		pd.put("createUser",createUserId);
		pd.put("CREATETIME", Tools.date2Str(new Date()));
		return pd;
	}
	
	/**
	 * 得到PageData
	 */
	public PageData getPageData() {
		return new PageData(this.getRequest());
	}

	/**
	 * 得到ModelAndView
	 */
	public ModelAndView getModelAndView() {
		return new ModelAndView();
	}

	/**
	 * 得到request对象
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

		return request;
	}

	/**
	 * 得到32位的uuid
	 * @return
	 */
	public String get32UUID() {

		return UuidUtil.get32UUID();
	}

	/**
	 * 得到分页列表的信息
	 */
	public Page getPage() {

		return new Page();
	}

	public static void logBefore(Logger logger, String interfaceName) {
		logger.info("");
		logger.info("start");
		logger.info(interfaceName);
	}

	public static void logAfter(Logger logger) {
		logger.info("end");
		logger.info("");
	}

}

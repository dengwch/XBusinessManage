package com.fh.controller.xchxu.logo;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

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

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.util.AppUtil;
import com.fh.util.DateUtil;
import com.fh.util.MD5;
import com.fh.util.OSSUtils;
import com.fh.util.ObjectExcelView;
import com.fh.util.Const;
import com.fh.util.PageData;
import com.fh.util.Tools;
import com.fh.util.Jurisdiction;
import com.fh.util.UuidUtil;
import com.fh.service.backgroup.log.LogService;
import com.fh.service.backgroup.logo.LogoService;

/** 
 * 类名称：LogoController
 * 创建人：FH 
 * 创建时间：2016-10-12
 */
@Controller
@RequestMapping(value="/logo")
public class LogoController extends BaseController {
	
	String menuUrl = "logo/list.do"; //菜单地址(权限用)
	@Resource(name="logoService")
	private LogoService logoService;
	@Resource(name="logService")
	public LogService logService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(HttpServletRequest request,
			@RequestParam(required = false) MultipartFile LOGOIMGURL
			) throws Exception{
		logBefore(logger, "新增Logo");
		PageData pageData = this.addLog("新增", "新增LOGO", "Logo");
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
//		pd = this.getPageData();
		if (null != LOGOIMGURL && !LOGOIMGURL.isEmpty()) {
			String fileName = "gzh/logo/"+ DateUtil.getDays()+"/"+UuidUtil.get32UUID()+".jpg"; // 文件上传路径
			OSSUtils.uploadFileOfOSS(fileName, LOGOIMGURL);
			pd.put("LOGOIMGURL", fileName);
		}
		pd.put("LOGO_ID", this.get32UUID());	//主键
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		pd.put("FLAG", 1);	//是否生效(0:失效1：生效)
		logoService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Logo");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			HttpServletRequest request = this.getRequest();
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pd.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
			logoService.delete(pd);
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
	public ModelAndView edit(
			@RequestParam(required = false) MultipartFile LOGOIMGURL,
			String LOGO_ID
			) throws Exception{
		logBefore(logger, "修改Logo");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
//		pd = this.getPageData();
		if (null != LOGOIMGURL && !LOGOIMGURL.isEmpty()) {
			String fileName = "gzh/lb/"+ DateUtil.getDays()+"/"+UuidUtil.get32UUID()+".jpg"; // 文件上传路径
			OSSUtils.uploadFileOfOSS(fileName, LOGOIMGURL);
			pd.put("LOGOIMGURL", fileName);
		}
		pd.put("FLAG", 1);
		pd.put("LOGO_ID",LOGO_ID);	//主键
		HttpServletRequest request = this.getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pd.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logoService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page,HttpServletRequest request){
		logBefore(logger, "列表Logo");
		////if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			page.setPd(pd);
			List<PageData>	varList = logoService.list(page);	//列出Logo列表
			mv.setViewName("backgroup/logo/logo_list");
			mv.addObject("varList", varList);
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
		logBefore(logger, "去新增Logo页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("backgroup/logo/logo_edit");
			mv.addObject("msg", "save");
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
		logBefore(logger, "去修改Logo页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = logoService.findById(pd);	//根据ID读取
			mv.setViewName("backgroup/logo/logo_edit");
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
		logBefore(logger, "批量删除Logo");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				logoService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出Logo到excel");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("logo图片");	//1
			titles.add("是否生效(0:失效1：生效)");	//2
			dataMap.put("titles", titles);
			List<PageData> varOList = logoService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("LOGOIMGURL"));	//1
				vpd.put("var2", varOList.get(i).get("FLAG").toString());	//2
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
}

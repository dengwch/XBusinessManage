package com.fh.controller.xchxu.specificationproperties;

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

import org.apache.http.protocol.HTTP;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.util.AppUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.Const;
import com.fh.util.PageData;
import com.fh.util.Tools;
import com.fh.util.Jurisdiction;
import com.fh.service.backgroup.log.LogService;
import com.fh.service.backgroup.specificationproperties.SpecificationPropertiesService;

/** 
 * 类名称：SpecificationPropertiesController
 * 创建人：FH 
 * 创建时间：2017-03-09
 */
@Controller
@RequestMapping(value="/specificationproperties")
public class SpecificationPropertiesController extends BaseController {
	
	String menuUrl = "specificationproperties/list.do"; //菜单地址(权限用)
	@Resource(name="specificationpropertiesService")
	private SpecificationPropertiesService specificationpropertiesService;
	@Resource(name="logService")
	public LogService logService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(HttpServletRequest request) throws Exception{
		logBefore(logger, "新增SpecificationProperties");
		try{
		
		PageData pageData =this.addLog("新增", "新增SpecificationProperties", "SpecificationProperties");
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("SPECIFICATIONPROPERTIES_ID", this.get32UUID());	//主键
		pd.put("CREATETIME", Tools.date2Str(new Date()));	//创建时间
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		specificationpropertiesService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除SpecificationProperties");
		try{
		
		PageData pageData=this.addLog("删除", "删除SpecificationProperties", "SpecificationProperties");
		HttpServletRequest request = this.getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			specificationpropertiesService.delete(pd);
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
		logBefore(logger, "修改SpecificationProperties");
		try{
		PageData pageData=this.addLog("修改", "修改SpecificationProperties", "SpecificationProperties");
		HttpServletRequest request = this.getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		specificationpropertiesService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page,HttpServletRequest request){
		logBefore(logger, "列表SpecificationProperties");
		try{
		PageData pageData=this.addLog("查看列表", "查看SpecificationProperties列表", "SpecificationProperties");
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		////if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			page.setPd(pd);
			List<PageData>	varList = specificationpropertiesService.list(page);	//列出SpecificationProperties列表
			mv.setViewName("backgroup/specificationproperties/specificationproperties_list");
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
		logBefore(logger, "去新增SpecificationProperties页面");
		try{
		PageData pageData=this.addLog("去新增", "去新增SpecificationProperties页面", "SpecificationProperties");
		HttpServletRequest request = this.getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("backgroup/specificationproperties/specificationproperties_edit");
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
		logBefore(logger, "去修改SpecificationProperties页面");
		try{
		PageData pageData=this.addLog("去修改", "去修改SpecificationProperties页面", "SpecificationProperties");
		HttpServletRequest request = this.getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		HttpServletRequest request = this.getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pd.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		try {
			pd = specificationpropertiesService.findById(pd);	//根据ID读取
			mv.setViewName("backgroup/specificationproperties/specificationproperties_edit");
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
		logBefore(logger, "批量删除SpecificationProperties");
		try{
		PageData pageData=this.addLog("批量删除", "批量删除{objectName}", "SpecificationProperties");
		HttpServletRequest request = this.getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				specificationpropertiesService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出SpecificationProperties到excel");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("所属规格");	//1
			titles.add("属性名");	//2
			titles.add("创建时间");	//3
			dataMap.put("titles", titles);
			HttpServletRequest request = this.getRequest();
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pd.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
			List<PageData> varOList = specificationpropertiesService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("SPECIFICATIONS_ID"));	//1
				vpd.put("var2", varOList.get(i).getString("NAME"));	//2
				vpd.put("var3", varOList.get(i).getString("CREATETIME"));	//3
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

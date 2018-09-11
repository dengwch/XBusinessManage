package com.fh.controller.xchxu.specifications;

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
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.reflection.wrapper.MapWrapper;
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
import com.fh.service.backgroup.specifications.SpecificationsService;

/** 
 * 类名称：SpecificationsController
 * 创建人：FH 
 * 创建时间：2017-03-09
 */
@Controller
@RequestMapping(value="/specifications")
public class SpecificationsController extends BaseController {
	
	String menuUrl = "specifications/list.do"; //菜单地址(权限用)
	@Resource(name="specificationsService")
	private SpecificationsService specificationsService;
	@Resource(name="specificationpropertiesService")
	private SpecificationPropertiesService specificationpropertiesService;
	@Resource(name="logService")
	public LogService logService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(HttpServletRequest request) throws Exception{
		logBefore(logger, "新增Specifications");
		try{
		
		PageData pageData =this.addLog("新增", "新增Specifications", "Specifications");
		HttpServletRequest request1 = this.getRequest();
		String  BUSINESSUSER_ID=(String) request1.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "add")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String content=pd.getString("content");
		String UUID= this.get32UUID();
		if(pd.getString("COMMODITY_ID")==null||"".equals(pd.getString("COMMODITY_ID"))){
			pd.put("COMMODITY_ID","0");
		}
		pd.put("SPECIFICATIONS_ID", UUID);	//主键
		pd.put("CREATETIME", Tools.date2Str(new Date()));	//创建时间
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		specificationsService.save(pd);
		String [] pers=content.split(",");
		if(pers!=null){
			for(String str:pers){
				PageData pageData=new PageData();
				pageData.put("SPECIFICATIONPROPERTIES_ID", this.get32UUID());	//主键
				pageData.put("CREATETIME", Tools.date2Str(new Date()));	//创建时间
				pageData.put("NAME", str);
				pageData.put("SPECIFICATIONS_ID",UUID);
				String  BUSINESSUSER_ID1=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
				pageData.put("BUSINESSUSER_ID", BUSINESSUSER_ID1);
				specificationpropertiesService.save(pageData);
			}
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	/**
	 * 新增
	 */
	@RequestMapping(value="/save1")
	@ResponseBody
	public Map<String , Object> save1(HttpServletRequest request,HttpServletResponse response) throws Exception{
		logBefore(logger, "新增Specifications");
		try{
		
		PageData pageData =this.addLog("新增", "新增Specifications", "Specifications");
		HttpServletRequest request1 = this.getRequest();
		String  BUSINESSUSER_ID=(String) request1.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String content=pd.getString("content");
		String UUID= this.get32UUID();
		if(pd.getString("COMMODITY_ID")==null||"".equals(pd.getString("COMMODITY_ID"))){
			pd.put("COMMODITY_ID","0");
		}
		pd.put("SPECIFICATIONS_ID", UUID);	//主键
		pd.put("CREATETIME", Tools.date2Str(new Date()));	//创建时间
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		specificationsService.save(pd);
		String [] pers=content.split(",");
		if(pers!=null){
			for(String str:pers){
				PageData pageData=new PageData();
				pageData.put("SPECIFICATIONPROPERTIES_ID", this.get32UUID());	//主键
				pageData.put("CREATETIME", Tools.date2Str(new Date()));	//创建时间
				pageData.put("NAME", str);
				pageData.put("SPECIFICATIONS_ID",UUID);
				String  BUSINESSUSER_ID1=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
				pageData.put("BUSINESSUSER_ID", BUSINESSUSER_ID1);
				specificationpropertiesService.save(pageData);
				result.put("msg","1");
			}
		}
		
		return result;
	}
	
	/**
	 * 去修改页面
	 */
	@RequestMapping(value="/goEditx")
	@ResponseBody
	public Map<String ,Object> goEditx(HttpServletRequest request){
		logBefore(logger, "去修改Specifications页面");
		try{
		PageData pageData=this.addLog("去修改", "去修改Specifications页面", "Specifications");
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		PageData pd = new PageData();
		pd = this.getPageData();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		try {
			pd = specificationsService.findById(pd);	//根据ID读取
			String  BUSINESSUSER_ID1=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID1);
			List<PageData> list = specificationpropertiesService.listById(pd);
			result.put("list",list);
			result.put("msg", "1");
			result.put("NAME", (String)pd.get("NAME"));
			result.put("SPECIFICATIONS_ID", (String)pd.get("SPECIFICATIONS_ID"));
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return result;
	}	

	/**
	 * 修改
	 */
	@RequestMapping(value="/editx")
	@ResponseBody
	public Map<String ,Object> editx(HttpServletRequest request) throws Exception{
		logBefore(logger, "修改Specifications");
		try{
		PageData pageData=this.addLog("修改", "修改Specifications", "Specifications");
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "edit")){return null;} //校验权限
		PageData pd = new PageData();
		pd = this.getPageData();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pd.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		String content=pd.getString("content");
		String UUID= pd.getString("SPECIFICATIONS_ID");
		if(pd.getString("COMMODITY_ID")==null||"".equals(pd.getString("COMMODITY_ID"))){
			pd.put("COMMODITY_ID","0");
		}
		specificationsService.deleteById(pd);
		specificationsService.edit(pd);
		String [] pers=content.split(",");
		if(pers!=null){
			for(String str:pers){
				PageData pageData=new PageData();
				pageData.put("SPECIFICATIONPROPERTIES_ID", this.get32UUID());	//主键
				pageData.put("CREATETIME", Tools.date2Str(new Date()));	//创建时间
				pageData.put("NAME", str);
				pageData.put("SPECIFICATIONS_ID",UUID);
				pageData.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
				specificationpropertiesService.save(pageData);
				result.put("msg","1");
			}
		}
		
		return result;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/deletex")
	@ResponseBody
	public Map<String ,Object> deletex(){
		logBefore(logger, "删除Specifications");
		try{
		
		PageData pageData=this.addLog("删除", "删除Specifications", "Specifications");
		HttpServletRequest request = this.getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		Map<String, Object> result = new HashMap<String, Object>();
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "del")){return;} //校验权限
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			specificationsService.delete(pd);
			result.put("msg","1");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return result;
	}
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Specifications");
		try{
		
		PageData pageData=this.addLog("删除", "删除Specifications", "Specifications");
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
			specificationsService.delete(pd);
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
	public ModelAndView edit(HttpServletRequest request) throws Exception{
		logBefore(logger, "修改Specifications");
		try{
		PageData pageData=this.addLog("修改", "修改Specifications", "Specifications");
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
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pd.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		String content=pd.getString("content");
		String UUID= pd.getString("SPECIFICATIONS_ID");
		if(pd.getString("COMMODITY_ID")==null||"".equals(pd.getString("COMMODITY_ID"))){
			pd.put("COMMODITY_ID","0");
		}
		specificationsService.deleteById(pd);
		specificationsService.edit(pd);
		String [] pers=content.split(",");
		if(pers!=null){
			for(String str:pers){
				PageData pageData=new PageData();
				pageData.put("SPECIFICATIONPROPERTIES_ID", this.get32UUID());	//主键
				pageData.put("CREATETIME", Tools.date2Str(new Date()));	//创建时间
				pageData.put("NAME", str);
				pageData.put("SPECIFICATIONS_ID",UUID);
				pageData.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
				specificationpropertiesService.save(pageData);
			}
		}
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page,HttpServletRequest request){
		logBefore(logger, "列表Specifications");
		try{
		PageData pageData=this.addLog("查看列表", "查看Specifications列表", "Specifications");
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
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
			List<PageData>	varList = specificationsService.list(page);	//列出Specifications列表
			mv.setViewName("backgroup/specifications/specifications_list");
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
	public ModelAndView goAdd(HttpServletRequest request){
		logBefore(logger, "去新增Specifications页面");
		try{
		PageData pageData=this.addLog("去新增", "去新增Specifications页面", "Specifications");
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			mv.setViewName("backgroup/specifications/specifications_edit");
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
	public ModelAndView goEdit(HttpServletRequest request){
		logBefore(logger, "去修改Specifications页面");
		try{
		PageData pageData=this.addLog("去修改", "去修改Specifications页面", "Specifications");
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		try {
			pd = specificationsService.findById(pd);	//根据ID读取
			String  BUSINESSUSER_ID1=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID1);
			List<PageData> list = specificationpropertiesService.listById(pd);
			mv.addObject("list",list);
			mv.setViewName("backgroup/specifications/specifications_edit");
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
		logBefore(logger, "批量删除Specifications");
		try{
		PageData pageData=this.addLog("批量删除", "批量删除{objectName}", "Specifications");
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
				specificationsService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出Specifications到excel");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("所属商品");	//1
			titles.add("规格名");	//2
			titles.add("创建时间");	//3
			dataMap.put("titles", titles);
			HttpServletRequest request = this.getRequest();
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pd.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
			List<PageData> varOList = specificationsService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("COMMODITY_ID"));	//1
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

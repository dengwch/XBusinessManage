package com.fh.controller.xchxu.classification;

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

import net.sf.json.JSONArray;

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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.util.AppUtil;
import com.fh.util.JsonUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.Const;
import com.fh.util.PageData;
import com.fh.util.Tools;
import com.fh.util.Jurisdiction;
import com.fh.service.backgroup.log.LogService;
import com.fh.service.backgroup.classification.ClassificationService;

/** 
 * 类名称：ClassificationController
 * 创建人：FH 
 * 创建时间：2017-03-07
 */
@Controller
@RequestMapping(value="/classification")
public class ClassificationController extends BaseController {
	
	String menuUrl = "classification/list.do"; //菜单地址(权限用)
	@Resource(name="classificationService")
	private ClassificationService classificationService;
	@Resource(name="logService")
	public LogService logService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(HttpServletRequest request) throws Exception{
		logBefore(logger, "新增Classification");
		try{
		
		PageData pageData =this.addLog("新增", "新增Classification", "Classification");
		HttpServletRequest request1 = this.getRequest();
		String  BUSINESSUSER_ID=(String) request1.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String optionsRadios=request.getParameter("optionsRadios");
		if (null!=optionsRadios && "option1".equals(optionsRadios.trim())) {
			pd.put("CLASSIFICATION_ID", this.get32UUID());	//主键
			pd.put("SUPERIOR", "0");
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			classificationService.save(pd);
		}else if (null!=optionsRadios && "option2".equals(optionsRadios.trim())) {
			pd.put("CLASSIFICATION_ID", this.get32UUID());	//主键
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			classificationService.save(pd);
		}
		
		mv.addObject("msg","success");
		mv.setViewName("redirect:/classification/list.do");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除Classification");
		try{
		PageData pageData=this.addLog("删除", "删除Classification", "Classification");
		HttpServletRequest request = this.getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			classificationService.delete(pd);
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
		logBefore(logger, "修改Classification");
		HttpServletRequest request = this.getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		try{
		PageData pageData=this.addLog("修改", "修改Classification", "Classification");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String FGNAME=request.getParameter("FGNAME");
		pd.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		PageData pd1 = classificationService.findById(pd);
		pd1.put("NAME",FGNAME);
		classificationService.edit(pd1);
		mv.addObject("msg","success");
		mv.setViewName("redirect:/classification/list.do");
		return mv;
	}
	/**
	 * 查找feilei
	 */
	@RequestMapping("/findFenLei")
	@ResponseBody
	public String findFenLei(String CLASSIFICATION_ID) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			PageData pd = getPageData();
			
			pd.put("CLASSIFICATION_ID", CLASSIFICATION_ID);
			HttpServletRequest request=this.getRequest();
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			PageData listClassT = classificationService.findById(pd);
			result.put("listClassT", listClassT);
			result.put("flag", 1);
		} catch (Exception e) {
			result.put("flag", 0);
		}
		return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
		//return result;
	}
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page,HttpServletRequest request){
		logBefore(logger, "列表Classification");
		try{
		PageData pageData=this.addLog("查看列表", "查看Classification列表", "Classification");
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;} //校验权限
		ModelAndView mv = this.getModelAndView();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			page.setPd(pd);
			List<PageData>	varList = classificationService.list(page);	//列出Classification列表
			List<PageData> list = classificationService.listAll(pd);
			List<PageData> jsonList=new ArrayList<PageData>();
			
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			List<PageData> listClass = classificationService.listAllOne(pd);
			String IMGURL1="";
			if(listClass!=null){
				for (int i = 0; i < listClass.size(); i++) {
					List<Map<String, Object>> List1=new ArrayList<Map<String, Object>>();
					PageData pd2 = new PageData();
					PageData data=listClass.get(i);
					List<PageData> listClassT = classificationService.listAllTh(data);
					pd2.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
					for (int j = 0; j < listClassT.size(); j++) {
						Map<String, Object> map=new HashMap<String, Object>();
						map.put("name", listClassT.get(j).getString("NAME"));
						map.put("id",listClassT.get(j).getString("CLASSIFICATION_ID"));
						List1.add(map);
						if("".equals(IMGURL1)){
							IMGURL1 = IMGURL1 + listClassT.get(j).getString("CLASSIFICATION_ID");
				    	}else{
				    		IMGURL1 = IMGURL1 +","+ listClassT.get(j).getString("CLASSIFICATION_ID");
				    	}
					}
					pd2.put("CLASSIFICATION_ID",IMGURL1);
					pd2.put("yijiname", data.getString("NAME"));
					pd2.put("CLASSIFICATION_IDYI", data.getString("CLASSIFICATION_ID"));
					pd2.put("erjinamelist", List1);
					jsonList.add(pd2);
				}
			}
			
			/*if(list!=null){
				for(PageData pageData:list){
					PageData jsonData=new PageData();
					jsonData.put("id",pageData.getString("CLASSIFICATION_ID"));
					jsonData.put("pId",pageData.getString("SUPERIOR"));
					jsonData.put("name",pageData.getString("NAME"));
					jsonList.add(jsonData);
				}
			}
			JSONArray json = JSONArray.fromObject(jsonList);*/
//			String json = JsonUtil.ToJson(jsonList);
			mv.addObject("jsonList",jsonList);
			mv.addObject("list",listClass);
			mv.setViewName("backgroup/classification/fenlei_list");
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
		logBefore(logger, "去新增Classification页面");
		try{
		PageData pageData=this.addLog("去新增", "去新增Classification页面", "Classification");
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		try {
			List<PageData> list = classificationService.listAllOne(pd);
			mv.addObject("list",list);
			mv.setViewName("backgroup/classification/classification_edit");
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
		logBefore(logger, "去修改Classification页面");
		try{
		PageData pageData=this.addLog("去修改", "去修改Classification页面", "Classification");
		
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		ModelAndView mv = this.getModelAndView();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		try {
			List<PageData> list = classificationService.listAllOne(pd);
			mv.addObject("list",list);
			pd = classificationService.findById(pd);	//根据ID读取
			mv.setViewName("backgroup/classification/classification_edit");
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
	public Object deleteAll(HttpServletRequest request) {
		logBefore(logger, "批量删除Classification");
		try{
		PageData pageData=this.addLog("批量删除", "批量删除{objectName}", "Classification");
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
				classificationService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出Classification到excel");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("上级ID");	//1
			titles.add("分类名称");	//2
			dataMap.put("titles", titles);
			List<PageData> varOList = classificationService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("SUPERIOR"));	//1
				vpd.put("var2", varOList.get(i).getString("NAME"));	//2
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

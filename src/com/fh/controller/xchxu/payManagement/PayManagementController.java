package com.fh.controller.xchxu.payManagement;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
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
import com.fh.util.AppUtil;
import com.fh.util.FileUpload;
import com.fh.util.FileUtil;
import com.fh.util.OSSUtils;
import com.fh.util.ObjectExcelView;
import com.fh.util.Const;
import com.fh.util.PageData;
import com.fh.util.Jurisdiction;
import com.fh.service.backgroup.log.LogService;
import com.fh.service.payManagement.paymanagement.PayManagementService;

/** 
 * 类名称：PayManagementController
 * 创建人：FH 
 * 创建时间：2017-04-28
 */
@Controller
@RequestMapping(value="/paymanagement")
public class PayManagementController extends BaseController {
	
	String menuUrl = "paymanagement/list.do"; //菜单地址(权限用)
	@Resource(name="paymanagementService")
	private PayManagementService paymanagementService;
	@Resource(name="logService")
	public LogService logService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(HttpServletRequest request,@RequestParam("APPID") String APPID,@RequestParam("APPSECRET") String APPSECRET,@RequestParam("PARTNER") String PARTNER,@RequestParam("PARTNERKEY") String PARTNERKEY,@RequestParam MultipartFile CERTIFICATEURL) throws Exception{
		logBefore(logger, "新增PayManagement");
		try{
		
		PageData pageData =this.addLog("新增", "新增PayManagement", "PayManagement");
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("APPID",APPID);
		pd.put("APPSECRET",APPSECRET);
		pd.put("PARTNER",PARTNER);
		pd.put("PARTNERKEY",PARTNERKEY);
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		if (CERTIFICATEURL.isEmpty()) {
			
		} else {
			// 定义图片存储路径
			String path = getAttachmentPath(BUSINESSUSER_ID);
			String fileName = CERTIFICATEURL.getOriginalFilename();
			int idx = fileName.indexOf(".");
			fileName = fileName.substring(0, idx);
			String name=FileUpload.fileUp(CERTIFICATEURL, path, fileName);
			String zongString=path.toString()+name.toString();
			System.out.println(name);
			System.out.println(path+name);
			pd.put("CERTIFICATEURL",zongString);
		}
		pd.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		pd.put("PAYMANAGEMENT_ID", this.get32UUID());	//主键
		paymanagementService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	/**
	 * 上传本地
	 */
	@RequestMapping(value="/shangchuan",produces = "text/plain;charset=UTF-8")
	@ResponseBody
	public String  uploadPhoto(@RequestParam MultipartFile myfile, HttpServletRequest request, HttpServletResponse response) throws IOException {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			if (myfile.isEmpty()) {
				result.put("flag", 0);
				result.put("msg", "请选择文件后上传");
			} else {
				// 定义图片存储路径
				String BUSINESSUSER_ID=(String)request.getSession().getAttribute("BUSINESSUSER_ID");
				String path = getAttachmentPath(BUSINESSUSER_ID);
				String fileName = myfile.getOriginalFilename();
				int idx = fileName.indexOf(".");
				fileName = fileName.substring(0, idx);
				String name=FileUpload.fileUp(myfile, path, fileName);
				System.out.println(name);
				System.out.println(path+name);
				result.put("flag", 1);
				result.put("msg", path + name);
			}
		} catch (Exception e) {
			e.printStackTrace();
			result.put("flag", 0);
		}
		
		return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
	}
	
	 /**
	 * 获取附件上传根目录
	 */
	public static String getAttachmentPath(String BUSINESSUSER_ID){
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name");
		if(os.startsWith("win") || os.startsWith("Win"))
		{
			return "C:\\zhengshu"+java.io.File.separator+BUSINESSUSER_ID+java.io.File.separator;
		}else
		{
			return java.io.File.separator+"home"+java.io.File.separator+"zhengshu"+java.io.File.separator+BUSINESSUSER_ID+java.io.File.separator+java.io.File.separator;
		}

	}

	
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除PayManagement");
		try{
		
		PageData pageData=this.addLog("删除", "删除PayManagement", "PayManagement");
		HttpServletRequest request=this.getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			pd = paymanagementService.findById(pd);	//根据ID读取
			FileUtil.delFile(pd.getString("CERTIFICATEURL"));
			paymanagementService.delete(pd);
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
	public ModelAndView edit(HttpServletRequest request ,@RequestParam("PAYMANAGEMENT_ID") String PAYMANAGEMENT_ID,@RequestParam("APPID") String APPID,@RequestParam("APPSECRET") String APPSECRET,@RequestParam("PARTNER") String PARTNER,@RequestParam("PARTNERKEY") String PARTNERKEY,@RequestParam MultipartFile CERTIFICATEURL) throws Exception{
		logBefore(logger, "修改PayManagement");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("APPID",APPID);
		pd.put("APPSECRET",APPSECRET);
		pd.put("PARTNER",PARTNER);
		pd.put("PARTNERKEY",PARTNERKEY);
		pd.put("PAYMANAGEMENT_ID",PAYMANAGEMENT_ID);
		//MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		//MultipartFile CERTIFICATEURL = multipartRequest.getFile("CERTIFICATEURL");//商品文件
		try{
		PageData pageData=this.addLog("修改", "修改PayManagement", "PayManagement");
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		pd.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		
		if (CERTIFICATEURL.isEmpty()) {
			
		} else {
			// 定义图片存储路径
			String path = getAttachmentPath(BUSINESSUSER_ID);
			String fileName = CERTIFICATEURL.getOriginalFilename();
			int idx = fileName.indexOf(".");
			fileName = fileName.substring(0, idx);
			String name=FileUpload.fileUp(CERTIFICATEURL, path, fileName);
			String zongString=path.toString()+name.toString();
			System.out.println(name);
			System.out.println(path+name);
			pd.put("CERTIFICATEURL",zongString);
		}
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		
		paymanagementService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page){
		logBefore(logger, "列表PayManagement");
		try{
		PageData pageData=this.addLog("查看列表", "查看PayManagement列表", "PayManagement");
		HttpServletRequest request=this.getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			HttpServletRequest request=this.getRequest();
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pd.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
			page.setPd(pd);
			List<PageData>	varList = paymanagementService.list(page);	//列出PayManagement列表
			mv.setViewName("payManagement/paymanagement/paymanagement_list");
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
		logBefore(logger, "去新增PayManagement页面");
		try{
		PageData pageData=this.addLog("去新增", "去新增PayManagement页面", "PayManagement");
		HttpServletRequest request=this.getRequest();
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
			mv.setViewName("payManagement/paymanagement/paymanagement_edit");
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
		logBefore(logger, "去修改PayManagement页面");
		try{
		PageData pageData=this.addLog("去修改", "去修改PayManagement页面", "PayManagement");
		HttpServletRequest request=this.getRequest();
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
			pd = paymanagementService.findById(pd);	//根据ID读取
			mv.setViewName("payManagement/paymanagement/paymanagement_edit");
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
		logBefore(logger, "批量删除PayManagement");
		try{
		PageData pageData=this.addLog("批量删除", "批量删除{objectName}", "PayManagement");
		HttpServletRequest request=this.getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		if(!Jurisdiction.buttonJurisdiction(menuUrl, "dell")){return null;} //校验权限
		PageData pd = new PageData();		
		Map<String,Object> map = new HashMap<String,Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if(null != DATA_IDS && !"".equals(DATA_IDS)){
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				paymanagementService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出PayManagement到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("支付管理id");	//1
			titles.add("appid");	//2
			titles.add("appsecret");	//3
			titles.add("商户id");	//4
			titles.add("api密钥");	//5
			titles.add("商家id");	//6
			dataMap.put("titles", titles);
			List<PageData> varOList = paymanagementService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("PAYMANAGEMENTID"));	//1
				vpd.put("var2", varOList.get(i).getString("APPID"));	//2
				vpd.put("var3", varOList.get(i).getString("APPSECRET"));	//3
				vpd.put("var4", varOList.get(i).getString("PARTNER"));	//4
				vpd.put("var5", varOList.get(i).getString("PARTNERKEY"));	//5
				vpd.put("var6", varOList.get(i).getString("BUSINESSUSER_ID"));	//6
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
}

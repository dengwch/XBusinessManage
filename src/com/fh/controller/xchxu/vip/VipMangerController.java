package com.fh.controller.xchxu.vip;

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
import com.fh.service.backgroup.vip.VipMangerService;

/** 
 * 类名称：VipMangerController
 * 创建人：FH 
 * 创建时间：2017-03-09
 */
@Controller
@RequestMapping(value="/vipmanger")
public class VipMangerController extends BaseController {
	
	String menuUrl = "vipmanger/list.do"; //菜单地址(权限用)
	@Resource(name="vipmangerService")
	private VipMangerService vipmangerService;
	@Resource(name="logService")
	public LogService logService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(HttpServletRequest request) throws Exception{
		logBefore(logger, "新增VipManger");
		try{
		
		PageData pageData =this.addLog("新增", "新增VipManger", "VipManger");
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
		pd.put("VIPMANGER_ID", this.get32UUID());	//主键
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		vipmangerService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除VipManger");
		try{
		
		PageData pageData=this.addLog("删除", "删除VipManger", "VipManger");
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
			vipmangerService.delete(pd);
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
		logBefore(logger, "修改VipManger");
		try{
		PageData pageData=this.addLog("修改", "修改VipManger", "VipManger");
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
		vipmangerService.edit(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page,HttpServletRequest request){
		logBefore(logger, "列表VipManger");
		try{
		PageData pageData=this.addLog("查看列表", "查看VipManger列表", "VipManger");
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
			List<PageData>	varList = vipmangerService.list(page);	//列出VipManger列表
			mv.setViewName("backgroup/vip/vipmanger_list");
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
		logBefore(logger, "去新增VipManger页面");
		try{
		PageData pageData=this.addLog("去新增", "去新增VipManger页面", "VipManger");
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
			mv.setViewName("backgroup/vip/vipmanger_edit");
			mv.addObject("msg", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	/**
	 * 查看会员详情--查看消费记录
	 */
	@RequestMapping(value="/findByVipId")
	public ModelAndView findByVipId(Page page,HttpServletRequest request){
		logBefore(logger, "列表VipManger");
		try{
		PageData pageData=this.addLog("查看列表", "查看会员详情列表", "findByVipId");
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
			List<PageData> newList=new ArrayList<PageData>();
			List<PageData>	vipList = vipmangerService.listBySql("VipMangerMapper.datalistPagefindByCreateVipId",page);	//列出VipManger列表
			for(int i=0;i<vipList.size();i++){
				PageData orderInfo=new PageData();
				orderInfo=vipList.get(i);
				PageData queryPd=new PageData();
				queryPd.put("ORDERID", orderInfo.get("ORDER_ID"));
				String  BUSINESSUSER_ID1=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
				queryPd.put("BUSINESSUSER_ID", BUSINESSUSER_ID1);
				List<PageData> varOList = vipmangerService.listAll("VipMangerMapper.findByOrderId",queryPd);
				PageData resultPd=new PageData();
				resultPd.put("ORDERID", orderInfo.get("ORDER_ID"));
				resultPd.put("CREATETIME", orderInfo.get("CREATETIME"));
				
				
				for(int j=0;j<varOList.size();j++){
					PageData cationPd=new PageData();
					cationPd= varOList.get(j);
					String SPECIFICATIONSLIST=(String) cationPd.get("SPECIFICATIONSLIST");
					String[] SPECIFICATIONSLIST_str=SPECIFICATIONSLIST.split(";");
					String SPECIFICATIONSLIST_strs="";
					for(int k=0;k<SPECIFICATIONSLIST_str.length;k++){
						if(k==0){
							SPECIFICATIONSLIST_strs+="'"+SPECIFICATIONSLIST_str[k]+"'";
						}else{
							SPECIFICATIONSLIST_strs+=",'"+SPECIFICATIONSLIST_str[k]+"'";
						}
					}
					PageData queryCation=new PageData();
					queryCation.put("SPECIFICATIONPROPERTIES_ID", SPECIFICATIONSLIST_strs);
					String  BUSINESSUSER_ID2=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
					queryCation.put("BUSINESSUSER_ID", BUSINESSUSER_ID2);
					List cationList=vipmangerService.listAll("VipMangerMapper.findSpecificationList", queryCation);
					String SPECIFICATIONSLIST_NAME="";
					for(int l=0;l<cationList.size();l++){
						Map cationResult=(Map) cationList.get(l);
						SPECIFICATIONSLIST_NAME+=cationResult.get("NAME");
					}
					cationPd.put("SPECIFICATIONSLIST", SPECIFICATIONSLIST_NAME);
				}
				
				resultPd.put("varOList", varOList);
				newList.add(resultPd);
			}
			mv.setViewName("backgroup/vip/vipinfo_list");
			mv.addObject("vipList", newList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
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
		logBefore(logger, "批量删除VipManger");
		try{
		PageData pageData=this.addLog("批量删除", "批量删除{objectName}", "VipManger");
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
				vipmangerService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出VipManger到excel");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("用户名");	//1
			titles.add("微信id");	//2
			titles.add("手机号");	//3
			dataMap.put("titles", titles);
			HttpServletRequest request = this.getRequest();
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pd.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
			List<PageData> varOList = vipmangerService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("USERNAME"));	//1
				vpd.put("var2", varOList.get(i).getString("WEIXINNO"));	//2
				vpd.put("var3", varOList.get(i).getString("PHONE"));	//3
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

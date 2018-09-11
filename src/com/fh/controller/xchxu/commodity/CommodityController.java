package com.fh.controller.xchxu.commodity;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import oracle.net.aso.i;

import org.apache.commons.lang.ArrayUtils;
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
import com.fh.util.AddressUtils;
import com.fh.util.AppUtil;
import com.fh.util.ChangeUtil;
import com.fh.util.ObjectExcelView;
import com.fh.util.Const;
import com.fh.util.PageData;
import com.fh.util.Tools;
import com.fh.util.Jurisdiction;
import com.fh.util.UuidUtil;
import com.fh.util.weixinUtil.WeiXinUserUtil;
import com.fh.service.backgroup.log.LogService;
import com.fh.service.backgroup.logistics.LogisticsService;
import com.fh.service.backgroup.order.OrderMangerService;
import com.fh.service.backgroup.specificationproperties.SpecificationPropertiesService;
import com.fh.service.backgroup.specifications.SpecificationsService;
import com.fh.service.backgroup.area.AreaService;
import com.fh.service.backgroup.bussinessactivity.BussinessActivityService;
import com.fh.service.backgroup.classification.ClassificationService;
import com.fh.service.backgroup.classificationrelation.ClassificationrelationService;
import com.fh.service.backgroup.commodity.CommodityService;
import com.fh.service.backgroup.commodityimg.CommodityimgService;
import com.fh.service.backgroup.commodityspecification.CommoditySpecificationService;
import com.fh.service.businessuser.BusinessuserService;

/**
 * 类名称：CommodityController 创建人：FH 创建时间：2017-03-08
 */
@Controller
@RequestMapping(value = "/commodity")
public class CommodityController extends BaseController {

	String menuUrl = "commodity/list.do"; // 菜单地址(权限用)
	@Resource(name = "commodityService")
	private CommodityService commodityService;
	@Resource(name = "classificationService")
	private ClassificationService classificationService;
	@Resource(name = "areaService")
	private AreaService areaService;
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
	@Resource(name="businessuserService")
	private BusinessuserService businessuserService;

	/**
	 * 新增
	 */
	@RequestMapping(value = "/save")
	public ModelAndView save(HttpServletRequest request1) throws Exception {
		logBefore(logger, "新增Commodity");
		try {

			PageData pageData = this.addLog("新增", "新增Commodity", "Commodity");
			String  BUSINESSUSER_ID=(String) request1.getSession().getAttribute("BUSINESSUSER_ID");
			pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
			logService.save(pageData);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		HttpServletRequest request = this.getRequest();
		
		
		ModelAndView mv = this.getModelAndView();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		//String uuid=this.get32UUID();
		String uuid=pd.getString("COMMODITY_ID");
		pd.put("COMMODITY_ID",uuid); // 主键
		pd.put("CREATETIME", Tools.date2Str(new Date())); // 创建时间
		
		String [] ggs=request.getParameterValues("gg");
		if(ggs!=null){
			String value="";
			if(ggs!=null){
				for(String str:ggs){
					value+=str+",";
				}
				if(value.length()>0){
					value=value.substring(0,value.length()-1);
				}
			}
			pd.put("GUIGE", value);
		}
		pd.put("CREATETIME", new Date());
		pd.put("FLAG", "2");
		commodityService.save(pd);
		PageData pd1 = new PageData();
		pd1 = this.getPageData();
		pd1.put("CLASSIFICATIONRELATION_ID", this.get32UUID());	//主键
		pd1.put("COMMODITY_ID", uuid);
		pd1.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		pd1.put("CLASSIFICATION_ID", pd.getString("CLASSIFICATION_ID"));
		classificationrelationService.save(pd1);
		
		String num=pd.getString("num");
		createSpe(request, uuid, num);
		
		commodityService.updateImg(pd);
		commodityService.updateSpe(pd);
		PageData pdtime = new PageData();
		pdtime.put("TID",this.get32UUID()); // 主键
		pdtime.put("COMMODITY_ID",uuid);
		pdtime.put("CREATETIME", Tools.date2Str(new Date())); // 创建时间
		pdtime.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		commodityService.savetime(pdtime);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
		PageData pdshu = new PageData();
		pdshu.put("CREATETIME", df.format(new Date()));
		pdshu.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		int shu=commodityService.listAlltime(pdshu).size();
		//int shu=commodityService.listAllshul(pdshu).size();
		if(shu==6){
			PageData pdbu = businessuserService.findById(pd);
			//if(null!=pdbu.get("OPENID")&&!"".equals(pdbu.get("OPENID"))){
				List<PageData> listCodes = commodityService.listAllcodes(pd);
				if(null!=listCodes&&listCodes.size()>0){
					PageData pdbu1=listCodes.get(0);
					String name=pdbu1.getString("codename");
					request.getSession().setAttribute("codename1", name);
					//WeiXinUserUtil.sendMsg(pdbu.get("OPENID").toString(),"今天上传6件商品了得到一个红包码:"+name);
					commodityService.updatecodes(pdbu1);
				}
			//}
		}else{
			mv.addObject("codename",request.getSession().getAttribute("codename1"));
		}
		mv.setViewName("redirect:/commodity/list.do");
		mv.addObject("msg", "success");
		return mv;
	}

	/**
	 * 创建商品规格属性
	 * @param request
	 * @param uuid
	 * @param num
	 * @throws Exception
	 */
	private void createSpe(HttpServletRequest request,  String uuid, String num) throws Exception {
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		if(num!=null){
			Integer number=Integer.valueOf(num);
			if(number>0){
				PageData pd=new PageData();
				pd.put("COMMODITY_ID",uuid);
				commodityspecificationService.deleteById(pd);
			}
			for(int i=0;i<number;i++){
				PageData pageData=new PageData();
				String proper=request.getParameter("proper"+i);
				String kucun=request.getParameter("kucun"+i);
				String jg=request.getParameter("jg"+i);
				pageData.put("SPECIFICATIONSLIST", proper);
				pageData.put("STOCK", kucun);
				pageData.put("PRICE", jg);
				pageData.put("COMMODITY_ID", uuid);
				pageData.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
				
				pageData.put("COMMODITYSPECIFICATION_ID", this.get32UUID());	//主键
				commodityspecificationService.save(pageData);
				
			}
		}
	}
	
	/**
	 * 返回输入地址的经纬度坐标 
	 */
	@RequestMapping("/findZB")
	public String findZB(String address) {
		Map<String, Object> result = new HashMap<String, Object>();
		Map<String, String> json=AddressUtils.getGeocoderLatitude(address);
		 System.out.println("经度lng : " + (String)json.get("longitude"));  
         System.out.println("纬度lat : " + (String)json.get("latitude")); 
         result.put("longitude", (String)json.get("longitude"));
         result.put("latitude", (String)json.get("latitude"));
		return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
		//return result;
	}
	
	/**
	 * 查找feilei
	 */
	@RequestMapping("/findFenLei")
	@ResponseBody
	public String findFenLei(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			PageData pd = getPageData();
			if (id == null || "".equals(id)) {
				id = "0";
			}
			pd.put("CLASSIFICATION_ID", id);
			HttpServletRequest request=this.getRequest();
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			List<PageData> listClassT = classificationService.listAllTh(pd);
			result.put("listClassT", listClassT);
			result.put("flag", 1);
		} catch (Exception e) {
			result.put("flag", 0);
		}
		return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
		//return result;
	}
	/**
	 * 批量上架下架
	 */
	@RequestMapping(value = "/SXAll")
	@ResponseBody
	public Object SXAll() {
		logBefore(logger, "批量上架下架Commodity");
		try {
			PageData pageData = this.addLog("批量上架下架", "批量上架下架{objectName}", "Commodity");
			HttpServletRequest request = this.getRequest();
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
			logService.save(pageData);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		
		PageData pd = new PageData();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if (null != DATA_IDS && !"".equals(DATA_IDS)) {
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				for (int i = 0; i < ArrayDATA_IDS.length; i++) {
					pd.put("COMMODITY_ID", ArrayDATA_IDS[i]);
					commodityService.editFlag(pd);
				}
				pd.put("msg", "ok");
			} else {
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
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public void delete(PrintWriter out) {
		logBefore(logger, "删除Commodity");
		try {

			PageData pageData = this.addLog("删除", "删除Commodity", "Commodity");
			HttpServletRequest request = this.getRequest();
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
			logService.save(pageData);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		
		PageData pd = new PageData();
		try {
			
			
			
			
			pd = this.getPageData();
			String  type = (String)pd.get("type");
			if(null!=type&&!"".equals(type)&&"gai".equals(type.trim())){
				commodityService.editFlag(pd);
			}else {
				commodityService.delete(pd);
			}
			if(null!=(String)pd.get("CLASSIFICATION_IDYI") && !"".equals((String)pd.get("CLASSIFICATION_IDYI").toString().trim())){
				List<PageData> listClass=classificationrelationService.listAllIN(pd);
				if (null!=listClass&&listClass.size()>0) {
					for (int j = 0; j < listClass.size(); j++) {
						commodityService.delete(listClass.get(j));
						classificationrelationService.deleteclass(listClass.get(j));
					}
				}
				pd.put("SUPERIOR", (String)pd.get("CLASSIFICATION_IDYI"));
				pd.put("CLASSIFICATION_ID", (String)pd.get("CLASSIFICATION_IDYI"));
				classificationService.delSUPERIOR(pd);
				classificationService.delete(pd);
				ModelAndView mv = this.getModelAndView();
				mv.setViewName("redirect:/classification/list.do");
			}
			
			out.write("success");
			out.close();
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

	}


	/**
	 * 删除分类
	 */
	@RequestMapping(value = "/deletesu")
	public ModelAndView deletesu() throws Exception {
		logBefore(logger, "修改Commodity");
		HttpServletRequest request = this.getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		try {
			PageData pageData = this.addLog("删除", "删除分类", "Commodity");
			
			pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
			logService.save(pageData);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		pd = this.getPageData();
		String IMGURL1="";
		if(null!=(String)pd.get("CLASSIFICATION_ID")&& !"".equals((String)pd.get("CLASSIFICATION_ID").toString().trim())){
		List<PageData> listClassT =classificationService.listAllTh(pd);
		if (null!=listClassT&&listClassT.size()>0) {
			for (int i = 0; i < listClassT.size(); i++) {
				if("".equals(IMGURL1)){
					IMGURL1 = IMGURL1 + "'"+listClassT.get(i).getString("CLASSIFICATION_ID")+"'";
		    	}else{
		    		IMGURL1 = IMGURL1 +","+ "'"+listClassT.get(i).getString("CLASSIFICATION_ID")+"'";
		    	}
			}
			
			pd.put("CLASSIFICATION_ID", IMGURL1);
			List<PageData> listClass=classificationrelationService.listAllIN(pd);
			if (null!=listClass&&listClass.size()>0) {
				for (int j = 0; j < listClass.size(); j++) {
					commodityService.delete(listClass.get(j));
					classificationrelationService.deleteclass(listClass.get(j));
					pd.put("SUPERIOR", listClass.get(j).getString("CLASSIFICATION_ID"));
					pd.put("CLASSIFICATION_ID", listClass.get(j).getString("CLASSIFICATION_ID"));
					classificationService.delSUPERIOR(pd);
					classificationService.delete(pd);
					pd.put("CLASSIFICATION_ID", this.getRequest().getParameter("CLASSIFICATION_ID"));
					classificationService.delete(pd);
				}
				classificationService.delSUPERIOR(pd);
				classificationService.delete(pd);
			}else {
				pd.put("CLASSIFICATION_ID", this.getRequest().getParameter("CLASSIFICATION_ID"));
				List<PageData> listClass1=classificationService.listAllTh(pd);
				for (int j = 0; j < listClass1.size(); j++) {
					classificationrelationService.deleteclass(listClass1.get(j));
					classificationService.delSUPERIOR(listClass1.get(j));
					classificationService.delete(listClass1.get(j));
					classificationService.delSUPERIOR(listClass1.get(j));
					classificationService.delete(listClass1.get(j));
					
					classificationrelationService.deleteclass(pd);
					classificationService.delSUPERIOR(pd);
					classificationService.delete(pd);
					classificationService.delSUPERIOR(pd);
					classificationService.delete(pd);
					
				}
				
			}
			
		}else {
			String CLASSIFICATION_ID=pd.getString("CLASSIFICATION_ID");
			pd.put("CLASSIFICATION_ID", "'"+CLASSIFICATION_ID+"'");
			List<PageData> listClass=classificationrelationService.listAllIN(pd);
			if (null!=listClass&&listClass.size()>0) {
				for (int j = 0; j < listClass.size(); j++) {
					commodityService.delete(listClass.get(j));
					classificationrelationService.deleteclass(listClass.get(j));
					pd.put("SUPERIOR", listClass.get(j).getString("CLASSIFICATION_ID"));
					pd.put("CLASSIFICATION_ID", listClass.get(j).getString("CLASSIFICATION_ID"));
					classificationService.delSUPERIOR(pd);
					classificationService.delete(pd);
				}
			}
		}
		
		
		
		}
		
		/*String  type = (String)pd.get("type");
		if(null!=type&&!"".equals(type)&&"gai".equals(type.trim())){
			commodityService.editFlag(pd);
		}else {
			commodityService.delete(pd);
		}
		String IMGURL1="";
		if(null!=(String)pd.get("CLASSIFICATION_ID")&& !"".equals((String)pd.get("CLASSIFICATION_ID").toString().trim())){
			String[] CLASSIFICATION_IDs=(String[])pd.get("CLASSIFICATION_ID").toString().split(",");
			for (int i = 0; i < CLASSIFICATION_IDs.length; i++) {
				if("".equals(IMGURL1)){
					IMGURL1 = IMGURL1 + "'"+CLASSIFICATION_IDs[i]+"'";
		    	}else{
		    		IMGURL1 = IMGURL1 +","+ "'"+CLASSIFICATION_IDs[i]+"'";
		    	}
			}
			pd.put("CLASSIFICATION_ID", IMGURL1);
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			List<PageData> listClass=classificationrelationService.listAllIN(pd);
			if (null!=listClass&&listClass.size()>0) {
				for (int j = 0; j < listClass.size(); j++) {
					commodityService.delete(listClass.get(j));
				}
			}
			if(null!=(String)pd.get("CLASSIFICATION_IDYI")&& !"".equals((String)pd.get("CLASSIFICATION_IDYI").toString().trim())){
				pd.put("SUPERIOR", (String)pd.get("CLASSIFICATION_IDYI"));
				pd.put("CLASSIFICATION_ID", (String)pd.get("CLASSIFICATION_IDYI"));
				classificationService.delSUPERIOR(pd);
				classificationService.delete(pd);
			}else {
				classificationService.delete(pd);
			}*/
			
			
		//}
		mv.setViewName("redirect:/classification/list.do");
		pd.put("CLASSIFICATION_ID", this.getRequest().getParameter("CLASSIFICATION_ID"));
		classificationService.delete(pd);
		
		
		return mv;
	}

	
	
	
	/**
	 * 修改
	 */
	@RequestMapping(value = "/edit")
	public ModelAndView edit() throws Exception {
		logBefore(logger, "修改Commodity");
		HttpServletRequest request = this.getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		try {
			PageData pageData = this.addLog("修改", "修改Commodity", "Commodity");
			
			pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
			logService.save(pageData);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		pd.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		PageData pd1 = commodityService.findById(pd); // 根据ID读取
		String [] ggs=request.getParameterValues("gg");
		if(ggs!=null){
			String value="";
			if(ggs!=null){
				for(String str:ggs){
					value+=str+",";
				}
				if(value.length()>0){
					value=value.substring(0,value.length()-1);
				}
			}
			pd.put("GUIGE", value);
		}
		pd.put("FLAG", pd1.getString("FLAG"));
		commodityService.edit(pd);
		classificationrelationService.edit1(pd);
		String num=pd.getString("num");
		createSpe(request, pd.getString("COMMODITY_ID"), num);
		commodityService.updateImg(pd);
		mv.setViewName("redirect:/commodity/list.do");
		return mv;
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page,HttpServletRequest request) {
		logBefore(logger, "列表Commodity");
		try {
			PageData pageData = this.addLog("查看列表", "查看Commodity列表", "Commodity");
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
			logService.save(pageData);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		// if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		// //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");

		try {
			pd = this.getPageData();
			String CLASSIFICATION_ID = pd.getString("CLASSIFICATION_ID");
			if (null != CLASSIFICATION_ID && !"".equals(CLASSIFICATION_ID)) {
				CLASSIFICATION_ID = CLASSIFICATION_ID.trim();
				pd.put("CLASSIFICATION_ID", CLASSIFICATION_ID);
			}
			String SHOPNAME = pd.getString("SHOPNAME");
			if (null != SHOPNAME && !"".equals(SHOPNAME)) {
				SHOPNAME = SHOPNAME.trim();
				pd.put("SHOPNAME", SHOPNAME);
			}
			String FLAG = pd.getString("FLAG");
			if (null != FLAG && !"".equals(FLAG)) {
				FLAG = FLAG.trim();
				pd.put("FLAG", FLAG);
			}
			
			
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			page.setPd(pd);
			List<PageData> listClass = classificationService.listAllOne(pd);
			mv.addObject("listClass", listClass);
			List<PageData> varList = commodityService.list(page); // 列出Commodity列表
			for (int i = 0; i < varList.size(); i++) {
				PageData pd1 = new PageData();
				pd1.put("CLASSIFICATION_ID", varList.get(i).getString("SUPERIOR"));
				PageData data = classificationService.findById(pd1);
				if (data!=null) {
					varList.get(i).put("SUPERIOR", data.getString("NAME"));
				}
			}
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
			PageData pdshu = new PageData();
			pdshu.put("CREATETIME", df.format(new Date()));
			pdshu.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			int shu=commodityService.listAlltime(pdshu).size();
			mv.addObject("codename",request.getSession().getAttribute("codename1") );			
			mv.addObject("shu",shu );
			mv.setViewName("backgroup/commodity/goodslist");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}
	/**
	 * 流量列表
	 */
	@RequestMapping(value = "/listLL")
	public ModelAndView listLL(Page page,HttpServletRequest request) {
		logBefore(logger, "列表Commodity");
		try {
			PageData pageData = this.addLog("查看流量列表", "查看Commodity流量列表", "Commodity");
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
			logService.save(pageData);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		// if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		// //校验权限
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");

		try {
			pd = this.getPageData();
			String CLASSIFICATION_ID = pd.getString("CLASSIFICATION_ID");
			if (null != CLASSIFICATION_ID && !"".equals(CLASSIFICATION_ID)) {
				CLASSIFICATION_ID = CLASSIFICATION_ID.trim();
				pd.put("CLASSIFICATION_ID", CLASSIFICATION_ID);
			}
			String SHOPNAME = pd.getString("SHOPNAME");
			if (null != SHOPNAME && !"".equals(SHOPNAME)) {
				SHOPNAME = SHOPNAME.trim();
				pd.put("SHOPNAME", SHOPNAME);
			}
			String FLAG = pd.getString("FLAG");
			if (null != FLAG && !"".equals(FLAG)) {
				FLAG = FLAG.trim();
				pd.put("FLAG", FLAG);
			}
			
			
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			page.setPd(pd);
			List<PageData> listClass = classificationService.listAllOne(pd);
			mv.addObject("listClass", listClass);
			List<PageData> zongList=new ArrayList<PageData>();
			List<PageData> varList = commodityService.list(page); // 列出Commodity列表
			for (int i = 0; i < varList.size(); i++) {
				PageData pd2 = new PageData();
				PageData pd1=varList.get(i);
				//收藏量
				List<PageData> datas=commodityService.findgoodscollectionList(pd1);
				//浏览量
				List<PageData> datas2=commodityService.findgoodsvisitList(pd1);
				pd2.put("countVisit", String.valueOf(datas2.size()));
				pd2.put("countCollection", String.valueOf(datas.size()));
				pd2.put("varList", varList.get(i));
				zongList.add(pd2);
			}
			mv.setViewName("backgroup/commodity/TrafficInfoList");
			mv.addObject("zongList", zongList);
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}
	public static final String ALLCHAR = "0123456789";  
    public static final String LETTERCHAR = "abcdefghijkllmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";  
    public static final String NUMBERCHAR = "0123456789";  
public static void main(String[] args) {
	/*String qString="hgsda";
	for (int i = 0; i < qString.length(); i++) {
		System.out.println(i);
		if (i==qString.length()-1) {
			System.out.println("sdhkjs");
		}
	}*/
	 
     for (int iq = 0; iq < 10; iq++) {  
    	 StringBuffer sb = new StringBuffer();  
         Random random = new Random();
     for (int i = 0; i < 5; i++) {  
         sb.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));  
     }  
	System.out.println(sb.toString());
     }
}
	/**
	 * 去新增页面
	 */
	@RequestMapping(value = "/goAdd")
	public ModelAndView goAdd(HttpServletRequest request) {
		logBefore(logger, "去新增Commodity页面");
		try {
			PageData pageData = this.addLog("去新增", "去新增Commodity页面", "Commodity");
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
			logService.save(pageData);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String uuid=this.get32UUID();
		request.getSession().setAttribute("COMMODITY_ID", uuid);
		pd.put("COMMODITY_ID",uuid); // 主键
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		try {
			//List<PageData> listClass = classificationService.listAllTwo(getPageData());
			List<PageData> listClass = classificationService.listAllOne(getPageData());
			mv.addObject("listClass", listClass);
			List<PageData> listArea = areaService.listAllOne(getPageData());
			mv.addObject("listArea", listArea);
			List<PageData> listLogistics = logisticsService.listAll(pd);
			mv.addObject("listLogistics", listLogistics);
			
			List<PageData> listActivity = bussinessactivityService.listAll(pd);
			mv.addObject("listActivity", listActivity);

			mv.setViewName("backgroup/commodity/goods_edit");
			mv.addObject("msg", "save");
			mv.addObject("msg1", "save");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 去修改页面
	 */
	@RequestMapping(value = "/goEdit")
	public ModelAndView goEdit(HttpServletRequest request) {
		logBefore(logger, "去修改Commodity页面");
		try {
			PageData pageData = this.addLog("去修改", "去修改Commodity页面", "Commodity");
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
			logService.save(pageData);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		
		try {
			request.getSession().setAttribute("COMMODITY_ID", pd.getString("COMMODITY_ID"));
			pd = commodityService.findById(pd); // 根据ID读取
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			pd.put("CLASSIFICATION_ID", pd.getString("CLASSIFICATION_ID"));
			PageData pd1 =classificationService.findById(pd);
			if(null!=pd1){
				pd.put("SUPERIOR", pd1.getString("SUPERIOR"));
			}
			
			List<PageData> varOList = commodityimgService.listAll(pd);
			List<PageData> deIMGList = ordermangerService.listAllDEIMG(pd);
			mv.addObject("deIMGList", deIMGList);
			mv.addObject("varOList", varOList);
			//List<PageData> listClass = classificationService.listAllTwo(getPageData());
			List<PageData> listClass = classificationService.listAllOne(getPageData());
			mv.addObject("listClass", listClass);
			List<PageData> listArea = areaService.listAllOne(getPageData());
			mv.addObject("listArea", listArea);
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			List<PageData> listLogistics = logisticsService.listAll(pd);
			List<PageData> listActivity = bussinessactivityService.listAll(null);
			mv.addObject("listActivity", listActivity);
			mv.addObject("listLogistics", listLogistics);
			mv.setViewName("backgroup/commodity/goods_edit");
			mv.addObject("msg", "edit");
			mv.addObject("pd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/**
	 * 查找规格
	 */
	@RequestMapping("findGuige")
	@ResponseBody
	public Map<String, Object> findGuige(String id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			PageData pd = getPageData();
			if (id == null || "".equals(id)) {
				id = "0";
			}
			pd.put("id", id);
			HttpServletRequest request=this.getRequest();
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			List<PageData> listSpe = specificationsService.listById(pd);
			result.put("listSpe", listSpe);
			Map<String, Object> mapList = new HashMap<String, Object>();
			if (listSpe != null) {
				for (PageData pageData : listSpe) {
					pageData.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
					List<PageData> listPerties = specificationpropertiesService.listById(pageData);
					mapList.put(pageData.getString("SPECIFICATIONS_ID"), listPerties);
				}
				result.put("mapList", mapList);
			}
			result.put("flag", 1);
		} catch (Exception e) {
			result.put("flag", 0);
		}

		return result;
	}

	/**
	 * 生成表格
	 */
	@RequestMapping("findBiaoGe")
	@ResponseBody
	public Map<String, Object> findBiaoGe(String id, String pausedCause) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			PageData pd = getPageData();
			if (id == null || "".equals(id)) {
				id = "0";
			}
			String[] pausedCauseS = pausedCause.split(",");
			pd.put("id", id);
			HttpServletRequest request = this.getRequest();
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pd.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
			List<PageData> listSpe = specificationsService.listById(pd);
			result.put("listSpe", listSpe);
			Map<String, Object> mapList = new HashMap<String, Object>();
			List<String[]> listStr = new ArrayList<String[]>();
			if (listSpe != null) {
				toData(pausedCauseS, listSpe, listStr);
			}
			List<List<String>> list = new ArrayList();
			Integer num=1;
			if(listStr!=null&&listStr.size()>0){
				for(String [] strs:listStr){
					num*=strs.length;
				}
				result.put("num",num);
			}
			if (listStr.size() < 2) {
				if (listStr != null&&listStr.size()>0) {
					String[] strS = listStr.get(0);
					if (strS != null) {
						for (String str : strS) {
							List<String> s=new ArrayList<String>();
							s.add(str);
							list.add(s);
						}
					}
				}
			} else {
				list = ChangeUtil.doExchange(listStr);
			}
			List<List<Map<String,Object>>> listAllMap=new ArrayList<List<Map<String,Object>>>(); 
			if(list!=null){
				for(List<String> strs:list){
					List<Map<String,Object>> listMap=new ArrayList<Map<String,Object>>();
					PageData pageData=new PageData();
					if(strs!=null){
						for(String str:strs){
							pageData.put("SPECIFICATIONPROPERTIES_ID",str);
							pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
							pageData=specificationpropertiesService.findById(pageData);
							listMap.add(pageData);
						}
						listAllMap.add(listMap);
					}
				}
			}
			result.put("list", listAllMap);
			result.put("flag", 1);
		} catch (Exception e) {
			result.put("flag", 0);
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 转换成数组元素
	 * 
	 * @param pausedCauseS
	 * @param listSpe
	 * @param listStr
	 * @throws Exception
	 */
	private void toData(String[] pausedCauseS, List<PageData> listSpe, List<String[]> listStr) throws Exception {
		for (PageData pageData : listSpe) {
			HttpServletRequest request = this.getRequest();
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
			List<PageData> listPerties = specificationpropertiesService.listById(pageData);
			if (listPerties != null) {
				List<String> pertiesList = new ArrayList<String>();
				Integer count = 0;
				for (PageData perties : listPerties) {
					String SPECIFICATIONPROPERTIES_ID = perties.getString("SPECIFICATIONPROPERTIES_ID");
					if (ArrayUtils.contains(pausedCauseS, SPECIFICATIONPROPERTIES_ID)) {
						pertiesList.add(SPECIFICATIONPROPERTIES_ID);
						count++;
					}
				}

				pageData.put("count", count);
				if (pertiesList.size() > 0) {
					String[] s = new String[pertiesList.size()];
					if (pertiesList != null) {
						for (int i = 0; i < pertiesList.size(); i++) {
							s[i] = pertiesList.get(i);
						}
					}
					listStr.add(s);
				}
			}
		}
	}

	/**
	 * 批量删除
	 */
	@RequestMapping(value = "/deleteAll")
	@ResponseBody
	public Object deleteAll() {
		logBefore(logger, "批量删除Commodity");
		try {
			PageData pageData = this.addLog("批量删除", "批量删除{objectName}", "Commodity");
			HttpServletRequest request = this.getRequest();
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
			logService.save(pageData);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		
		PageData pd = new PageData();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			pd = this.getPageData();
			List<PageData> pdList = new ArrayList<PageData>();
			String DATA_IDS = pd.getString("DATA_IDS");
			if (null != DATA_IDS && !"".equals(DATA_IDS)) {
				String ArrayDATA_IDS[] = DATA_IDS.split(",");
				commodityService.deleteAll(ArrayDATA_IDS);
				pd.put("msg", "ok");
			} else {
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
	 * 
	 * @return
	 */
	@RequestMapping(value = "/excel")
	public ModelAndView exportExcel() {
		logBefore(logger, "导出Commodity到excel");
		if (!Jurisdiction.buttonJurisdiction(menuUrl, "cha")) {
			return null;
		}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("分类ID"); // 1
			titles.add("商品名称"); // 2
			titles.add("商品货号"); // 3
			titles.add("售价"); // 4
			titles.add("库存"); // 5
			titles.add("重量"); // 6
			titles.add("体积"); // 7
			titles.add("商品图片"); // 8
			titles.add("所在地"); // 9
			titles.add("运费模板"); // 10
			titles.add("分佣比例"); // 11
			titles.add("上架模式"); // 12
			dataMap.put("titles", titles);
			List<PageData> varOList = commodityService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for (int i = 0; i < varOList.size(); i++) {
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("CLASSIFICATION_ID")); // 1
				vpd.put("var2", varOList.get(i).getString("NAME")); // 2
				vpd.put("var3", varOList.get(i).get("NUMBER").toString()); // 3
				vpd.put("var4", varOList.get(i).get("PRICE").toString()); // 4
				vpd.put("var5", varOList.get(i).get("STOCK").toString()); // 5
				vpd.put("var6", varOList.get(i).get("WEIGHT").toString()); // 6
				vpd.put("var7", varOList.get(i).getString("VOLUME")); // 7
				vpd.put("var8", varOList.get(i).getString("IMG")); // 8
				vpd.put("var9", varOList.get(i).getString("LOCATION")); // 9
				vpd.put("var10", varOList.get(i).getString("FREIGHTTEMPLATE")); // 10
				vpd.put("var11", varOList.get(i).getString("PERCENTAGECOMMISSION")); // 11
				vpd.put("var12", varOList.get(i).get("TYPE").toString()); // 12
				varList.add(vpd);
			}
			dataMap.put("varList", varList);
			ObjectExcelView erv = new ObjectExcelView();
			mv = new ModelAndView(erv, dataMap);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}

	/* ===============================权限================================== */
	public Map<String, String> getHC() {
		Subject currentUser = SecurityUtils.getSubject(); // shiro管理的session
		Session session = currentUser.getSession();
		return (Map<String, String>) session.getAttribute(Const.SESSION_QX);
	}

	/* ===============================权限================================== */

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(format, true));
	}
}

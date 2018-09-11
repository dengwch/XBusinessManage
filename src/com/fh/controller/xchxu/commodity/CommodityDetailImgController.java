package com.fh.controller.xchxu.commodity;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.util.AddressUtils;
import com.fh.util.AppUtil;
import com.fh.util.ChangeUtil;
import com.fh.util.DateUtil;
import com.fh.util.JsonUtil;
import com.fh.util.OSSUtils;
import com.fh.util.ObjectExcelView;
import com.fh.util.Const;
import com.fh.util.PageData;
import com.fh.util.PropertiesUtil;
import com.fh.util.Tools;
import com.fh.util.Jurisdiction;
import com.fh.util.UuidUtil;
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

/**
 * 类名称：CommodityController 创建人：FH 创建时间：2017-03-08
 */
@Controller
@RequestMapping(value = "/commodityDetailImg")
public class CommodityDetailImgController extends BaseController {

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
	

	/**
	 * 新增详情图片
	 */
	@RequestMapping(value = "/savedetailimg")
	@ResponseBody
	public Object savedetailimg(@RequestParam(required = false) MultipartFile detailimgFile) throws Exception {
		logBefore(logger, "新增Pictures");
		Map<String, Object> map = new HashMap<String, Object>();
		PageData pd = new PageData();
		HttpServletRequest request=this.getRequest();
		if (null != detailimgFile && !detailimgFile.isEmpty()) {
			String fileName = "gzh/detailimg/"+ DateUtil.getDays()+"/"+UuidUtil.get32UUID()+".jpg"; // 文件上传路径
			OSSUtils.uploadFileOfOSS(fileName, detailimgFile);
			pd.put("IMGURL", fileName);
			map.put("msg", fileName);
		}
		String ORDERCOMMODITYIMG_ID=this.get32UUID();
		pd.put("ORDERCOMMODITYIMG_ID", ORDERCOMMODITYIMG_ID); // 主键
		map.put("ORDERCOMMODITYIMG_ID", ORDERCOMMODITYIMG_ID);
		String COMMODITY_ID=(String)request.getSession().getAttribute("COMMODITY_ID");
		pd.put("ORDERCOMMODITY_ID", COMMODITY_ID); 
		pd.put("COMMODITY_ID", COMMODITY_ID); 
		pd.put("CREATETIME", Tools.date2Str(new Date())); // 创建时间
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		pd.put("WEIGHT", "1");
		
		ordermangerService.saveDEIMG(pd);
		List<PageData> varOList = ordermangerService.listAllDEIMG(pd);
		map.put("varOList", varOList);
		map.put("flag", "1");
		return AppUtil.returnObject(pd, map);
	}
	
	
	/**
	 * 查询图片
	 */
	@RequestMapping(value="/finddetail")
	@ResponseBody
	public Object finddetail(HttpServletRequest request){
		logBefore(logger, "删除Commodityimg");
		Map<String, Object> map=new HashMap<String, Object>();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			String shType=request.getParameter("shType");
			String COMMODITY_ID=(String)request.getSession().getAttribute("COMMODITY_ID");
			pd.put("COMMODITY_ID", COMMODITY_ID); 
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			if (null!=shType&&!"".equals(shType.trim())&&"xiangqing".equals(shType.trim())) {
				List<PageData> varOList = ordermangerService.listAllDEIMG(pd);
				map.put("varOList", varOList);
				map.put("flag", "1");
			}else if (null!=shType&&!"".equals(shType.trim())&&"tupian".equals(shType.trim())) {
				List<PageData> varOList = commodityimgService.listAll(pd);
				map.put("varOList", varOList);
				map.put("flag", "1");
			}
			
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return AppUtil.returnObject(pd, map);
	}
	/**
	 * 列表
	 */
	@RequestMapping(value="/listimg")
	public ModelAndView listimg(Page page,HttpServletRequest request){
		logBefore(logger, "列表Classification");
		PageData pd = new PageData();
		ModelAndView mv = this.getModelAndView();
		try{
			pd = this.getPageData();
			String shType=request.getParameter("shType");
			String COMMODITY_ID=(String)request.getSession().getAttribute("COMMODITY_ID");
			pd.put("COMMODITY_ID", COMMODITY_ID); 
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			List<PageData> jsonList =new ArrayList<PageData>();
			String OSSUrl = PropertiesUtil.getProperty("oss.properties", "url");
			
			if (null!=shType&&!"".equals(shType.trim())&&"xiangqing".equals(shType.trim())) {
				List<PageData> varOList = ordermangerService.listAllDEIMG(pd);
				if(varOList!=null){
					for(PageData pageData:varOList){
						PageData jsonData=new PageData();
						jsonData.put("href","");  
						jsonData.put("alt","");
						if(pageData.getString("IMGURL").trim().substring(0,3).equals("gzh".trim())){
							jsonData.put("src","http://wlkeji.oss-cn-beijing.aliyuncs.com/"+pageData.getString("IMGURL"));
							jsonData.put("smallSrc","http://wlkeji.oss-cn-beijing.aliyuncs.com/"+pageData.getString("IMGURL"));
						}else{
							jsonData.put("src",OSSUrl+pageData.getString("IMGURL"));
							jsonData.put("smallSrc",OSSUrl+pageData.getString("IMGURL"));
						}
						
						
						jsonData.put("title","");
						jsonList.add(jsonData);
					}
				}
				JSONArray json = JSONArray.fromObject(jsonList);
				mv.addObject("jsonList",json);
			}else if (null!=shType&&!"".equals(shType.trim())&&"tupian".equals(shType.trim())) {
				List<PageData> varOList = commodityimgService.listAll(pd);
				if(varOList!=null){
					for(PageData pageData:varOList){
						PageData jsonData=new PageData();
						jsonData.put("href","");  
						jsonData.put("alt","");
						if(pageData.getString("IMGURL").trim().substring(0,3).equals("gzh".trim())){
							jsonData.put("src","http://wlkeji.oss-cn-beijing.aliyuncs.com/"+pageData.getString("IMGURL"));
							jsonData.put("smallSrc","http://wlkeji.oss-cn-beijing.aliyuncs.com/"+pageData.getString("IMGURL"));
						}else{
							jsonData.put("src",OSSUrl+pageData.getString("IMGURL"));
							jsonData.put("smallSrc",OSSUrl+pageData.getString("IMGURL"));
						}
						
						jsonData.put("title","");
						jsonList.add(jsonData);
					}
				}
				JSONArray json = JSONArray.fromObject(jsonList);
				mv.addObject("jsonList",json);
			}
			mv.setViewName("backgroup/commodity/index_1");
			mv.addObject("pd", pd);
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	@ResponseBody
	public Map<String, Object> delete(){
		logBefore(logger, "删除Commodityimg");
		Map<String, Object> map=new HashMap<String, Object>();
		PageData pd = new PageData();
		try{
			pd = this.getPageData();
			ordermangerService.deleteDEIMG(pd);
			OSSUtils.deleteBucket(pd.getString("path"));
			map.put("msg", "1");
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return map;
	}
	
}

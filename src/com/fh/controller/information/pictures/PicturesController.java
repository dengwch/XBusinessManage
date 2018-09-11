package com.fh.controller.information.pictures;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fh.controller.base.BaseController;
import com.fh.entity.Page;
import com.fh.util.AppUtil;
import com.fh.util.DateUtil;
import com.fh.util.DelAllFile;
import com.fh.util.FileUpload;
import com.fh.util.Jurisdiction;
import com.fh.util.OSSUtils;
import com.fh.util.ObjectExcelView;
import com.fh.util.Const;
import com.fh.util.PageData;
import com.fh.util.PathUtil;
import com.fh.util.Tools;
import com.fh.util.UuidUtil;
import com.fh.util.Watermark;
import com.fh.service.backgroup.commodityimg.CommodityimgService;
import com.fh.service.backgroup.order.OrderMangerService;
import com.fh.service.information.pictures.PicturesService;

/**
 * 类名称：PicturesController 创建人：FH 创建时间：2015-03-21
 */
@Controller
@RequestMapping(value = "/pictures")
public class PicturesController extends BaseController {

	String menuUrl = "pictures/list.do"; // 菜单地址(权限用)
	@Resource(name = "picturesService")
	private PicturesService picturesService;
	@Resource(name="commodityimgService")
	private CommodityimgService commodityimgService;
	@Resource(name="ordermangerService")
	private OrderMangerService ordermangerService;
	/**
	 * 新增
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public Object save(@RequestParam(required = false) MultipartFile myFile ,HttpServletRequest request1) throws Exception {
		logBefore(logger, "新增Pictures");
		Map<String, Object> map = new HashMap<String, Object>();
//		String ffile = DateUtil.getDays(), fileName = "";
		PageData pd = new PageData();
		HttpServletRequest request=this.getRequest();
//		if (Jurisdiction.buttonJurisdiction(menuUrl, "add")) {
		if (null != myFile && !myFile.isEmpty()) {
			String fileName = "gzh/carousel/"+ DateUtil.getDays()+"/"+UuidUtil.get32UUID()+".jpg"; // 文件上传路径
			OSSUtils.uploadFileOfOSS(fileName, myFile);
			pd.put("IMGURL", fileName);
			map.put("msg", fileName);
		}
//			if (null != file && !file.isEmpty()) {
//				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile; // 文件上传路径
//				fileName = FileUpload.fileUp(file, filePath, this.get32UUID()); // 执行上传
//			} else {
//				System.out.println("上传失败");
//			}
		String COMMODITYIMG_ID=this.get32UUID();
			pd.put("COMMODITYIMG_ID", COMMODITYIMG_ID); // 主键
			map.put("COMMODITYIMG_ID", COMMODITYIMG_ID);
			String COMMODITY_ID=(String)request.getSession().getAttribute("COMMODITY_ID");
			pd.put("COMMODITY_ID", COMMODITY_ID); 
			pd.put("CREATETIME", Tools.date2Str(new Date())); // 创建时间
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			
			// 加水印
//			Watermark.setWatemark(PathUtil.getClasspath() + Const.FILEPATHIMG + ffile + "/" + fileName);
//			picturesService.save(pd);
			
			commodityimgService.save(pd);
			List<PageData> varOList = commodityimgService.listAll(pd);
			map.put("varOList", varOList);
			String  IMGURL=(String) request.getSession().getAttribute("IMGURL");
			if (null==IMGURL || "null".equals(IMGURL)) {
				IMGURL="";
			}
			//String IMGURL1=IMGURL+"#"+i+"#";
			String IMGURL1="";
			if("".equals(IMGURL1)){
				IMGURL1 = IMGURL1 + IMGURL+"#"+pd.getString("IMGURL");
	    	}else{
	    		IMGURL1 = IMGURL1 +"#"+ IMGURL+"#"+pd.getString("IMGURL");
	    	}
			request.getSession().setAttribute("IMGURL", IMGURL1);
			System.out.println((String) request.getSession().getAttribute("IMGURL")+"----------------------------");
			
//		}
		map.put("flag", "1");
		
		return AppUtil.returnObject(pd, map);
	}
	/**
	 * 新增
	 */
	@RequestMapping(value = "/save1")
	@ResponseBody
	public Object save1(HttpServletRequest request) throws Exception {
		logBefore(logger, "新增Pictures");
		Map<String, Object> map = new HashMap<String, Object>();
//		String ffile = DateUtil.getDays(), fileName = "";
		PageData pd = new PageData();
		
		String shType=(String)request.getSession().getAttribute("shType");
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				// int pre = (int) System.currentTimeMillis();//开始时时间
				MultipartFile file = multiRequest.getFile(iter.next());
				if (null!=shType&&!"".equals(shType.trim())&&"xiangqing".equals(shType.trim())) {
					if (null != file && !file.isEmpty()) {
						String fileName = "gzh/detailimg/"+ DateUtil.getDays()+"/"+UuidUtil.get32UUID()+".jpg"; // 文件上传路径
						OSSUtils.uploadFileOfOSS(fileName, file);
						pd.put("IMGURL", fileName);
						map.put("msg", fileName);
					}
					String ORDERCOMMODITYIMG_ID=UuidUtil.get32UUID();
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
				}else if (null!=shType&&!"".equals(shType.trim())&&"tupian".equals(shType.trim())) {
					if (null != file && !file.isEmpty()) {
						String fileName = "gzh/goodstupian/"+ DateUtil.getDays()+"/"+UuidUtil.get32UUID()+".jpg"; // 文件上传路径
						OSSUtils.uploadFileOfOSS(fileName, file);
						pd.put("IMGURL", fileName);
						map.put("msg", fileName);
					}
					String COMMODITYIMG_ID=this.get32UUID();
						pd.put("COMMODITYIMG_ID", COMMODITYIMG_ID); // 主键
						map.put("COMMODITYIMG_ID", COMMODITYIMG_ID);
						String COMMODITY_ID=(String)request.getSession().getAttribute("COMMODITY_ID");
						pd.put("COMMODITY_ID", COMMODITY_ID); 
						pd.put("CREATETIME", Tools.date2Str(new Date())); // 创建时间
						String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
						pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
						commodityimgService.save(pd);
				}
				
			}
		}
		return AppUtil.returnObject(pd, map);
	}
	/**
	 * 删除
	 */
	@RequestMapping(value = "/delete")
	public void delete(PrintWriter out) {
		logBefore(logger, "删除Pictures");
		PageData pd = new PageData();
		try {
			if (Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
				pd = this.getPageData();
				DelAllFile.delFolder(PathUtil.getClasspath() + Const.FILEPATHIMG + pd.getString("PATH")); // 删除图片
				picturesService.delete(pd);
			}
			out.write("success");
			out.close();
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}

	}

	/**
	 * 修改
	 */
	@RequestMapping(value = "/edit")
	public ModelAndView edit(HttpServletRequest request, @RequestParam(value = "tp", required = false) MultipartFile file, @RequestParam(value = "tpz", required = false) String tpz,
			@RequestParam(value = "PICTURES_ID", required = false) String PICTURES_ID, @RequestParam(value = "TITLE", required = false) String TITLE,
			@RequestParam(value = "MASTER_ID", required = false) String MASTER_ID, @RequestParam(value = "BZ", required = false) String BZ) throws Exception {
		logBefore(logger, "修改Pictures");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		if (Jurisdiction.buttonJurisdiction(menuUrl, "edit")) {
			pd.put("PICTURES_ID", PICTURES_ID); // 图片ID
			pd.put("TITLE", TITLE); // 标题
			pd.put("MASTER_ID", MASTER_ID); // 属于ID
			pd.put("BZ", BZ); // 备注

			if (null == tpz) {
				tpz = "";
			}
			String ffile = DateUtil.getDays(), fileName = "";
			if (null != file && !file.isEmpty()) {
				String filePath = PathUtil.getClasspath() + Const.FILEPATHIMG + ffile; // 文件上传路径
				fileName = FileUpload.fileUp(file, filePath, this.get32UUID()); // 执行上传
				pd.put("PATH", ffile + "/" + fileName); // 路径
				pd.put("NAME", fileName);
			} else {
				pd.put("PATH", tpz);
			}
			Watermark.setWatemark(PathUtil.getClasspath() + Const.FILEPATHIMG + ffile + "/" + fileName);// 加水印
			picturesService.edit(pd); // 执行修改数据库
		}
		mv.addObject("msg", "success");
		mv.setViewName("save_result");
		return mv;
	}

	/**
	 * 列表
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(Page page) {
		logBefore(logger, "列表Pictures");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		try {
			pd = this.getPageData();

			String KEYW = pd.getString("keyword");

			if (null != KEYW && !"".equals(KEYW)) {
				KEYW = KEYW.trim();
				pd.put("KEYW", KEYW);
			}

			page.setPd(pd);
			List<PageData> varList = picturesService.list(page); // 列出Pictures列表
			mv.setViewName("information/pictures/pictures_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX, this.getHC()); // 按钮权限
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
		return mv;
	}
	/**
	 * 清除sessionIMGURL
	 * 
	 * 
	 * @param request
	 * @param response
	 * @return
	 * 
	 */
	@RequestMapping("/removeSess")
	@ResponseBody
	public String  checkBusName(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		request.getSession().removeAttribute("IMGURL");
		result.put("msg", "1");
		return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
	}
	/**
	 *	获取sessionIMGURL
	 * 
	 * 
	 * @param request
	 * @param response
	 * @return
	 * 
	 */
	@RequestMapping("/hQSession")
	@ResponseBody
	public String  hQSession(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> result = new HashMap<String, Object>();
		String  IMGURL=(String) request.getSession().getAttribute("IMGURL");
		result.put("msg", "1");
		result.put("IMGURL", IMGURL);
		System.out.println(IMGURL);
		return JSON.toJSONString(result,SerializerFeature.WriteDateUseDateFormat);
	}
	/**
	 * 去新增页面
	 */
	@RequestMapping(value = "/goAdd")
	public ModelAndView goAdd() {
		logBefore(logger, "去新增Pictures页面");
		
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			String COMMODITY_ID=pd.getString("COMMODITY_ID");
			String shType=pd.getString("shType");
			HttpServletRequest request=this.getRequest();
			//request.getSession().removeAttribute("IMGURL");
			request.getSession().setAttribute("COMMODITY_ID", COMMODITY_ID);
			request.getSession().setAttribute("shType", shType);
			//mv.setViewName("information/pictures/pictures_add");
			mv.setViewName("information/pictures/uploader");
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
	public ModelAndView goEdit() {
		logBefore(logger, "去修改Pictures页面");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			pd = picturesService.findById(pd); // 根据ID读取
			mv.setViewName("information/pictures/pictures_edit");
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
	@RequestMapping(value = "/deleteAll")
	@ResponseBody
	public Object deleteAll() {
		logBefore(logger, "批量删除Pictures");
		PageData pd = new PageData();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			pd = this.getPageData();
			if (Jurisdiction.buttonJurisdiction(menuUrl, "del")) {
				List<PageData> pdList = new ArrayList<PageData>();
				List<PageData> pathList = new ArrayList<PageData>();
				String DATA_IDS = pd.getString("DATA_IDS");
				if (null != DATA_IDS && !"".equals(DATA_IDS)) {
					String ArrayDATA_IDS[] = DATA_IDS.split(",");
					pathList = picturesService.getAllById(ArrayDATA_IDS);
					// 删除图片
					for (int i = 0; i < pathList.size(); i++) {
						DelAllFile.delFolder(PathUtil.getClasspath() + Const.FILEPATHIMG + pathList.get(i).getString("PATH"));
					}
					picturesService.deleteAll(ArrayDATA_IDS);
					pd.put("msg", "ok");
				} else {
					pd.put("msg", "no");
				}
				pdList.add(pd);
				map.put("list", pdList);
			}
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
	@RequestMapping(value = "/excel")
	public ModelAndView exportExcel() {
		logBefore(logger, "导出Pictures到excel");
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try {
			Map<String, Object> dataMap = new HashMap<String, Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("标题"); // 1
			titles.add("文件名"); // 2
			titles.add("路径"); // 3
			titles.add("创建时间"); // 4
			titles.add("属于"); // 5
			titles.add("备注"); // 6
			dataMap.put("titles", titles);
			List<PageData> varOList = picturesService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for (int i = 0; i < varOList.size(); i++) {
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("TITLE")); // 1
				vpd.put("var2", varOList.get(i).getString("NAME")); // 2
				vpd.put("var3", varOList.get(i).getString("PATH")); // 3
				vpd.put("var4", varOList.get(i).getString("CREATETIME")); // 4
				vpd.put("var5", varOList.get(i).getString("MASTER_ID")); // 5
				vpd.put("var6", varOList.get(i).getString("BZ")); // 6
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

	// 删除图片
	@RequestMapping(value = "/deltp")
	public void deltp(PrintWriter out) {
		logBefore(logger, "删除图片");
		try {
			PageData pd = new PageData();
			pd = this.getPageData();
			String PATH = pd.getString("PATH"); // 图片路径
			DelAllFile.delFolder(PathUtil.getClasspath() + Const.FILEPATHIMG + pd.getString("PATH")); // 删除图片
			if (PATH != null) {
				picturesService.delTp(pd); // 删除数据中图片数据
			}
			out.write("success");
			out.close();
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}
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

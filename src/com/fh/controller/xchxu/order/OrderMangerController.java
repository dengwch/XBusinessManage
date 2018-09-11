package com.fh.controller.xchxu.order;

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
import com.fh.service.backgroup.log.LogService;
import com.fh.service.backgroup.order.OrderMangerService;
import com.fh.service.backgroup.vip.VipMangerService;
import com.fh.util.AppUtil;
import com.fh.util.Const;
import com.fh.util.DateUtil;
import com.fh.util.Jurisdiction;
import com.fh.util.ObjectExcelView;
import com.fh.util.PageData;
import com.fh.util.ReturnUtil;
import com.fh.util.UuidUtil;

/** 
 * 类名称：OrderMangerController
 * 创建人：FH 
 * 创建时间：2017-03-09
 */
@Controller
@RequestMapping(value="/ordermanger")
public class OrderMangerController extends BaseController {
	
	String menuUrl = "ordermanger/list.do"; //菜单地址(权限用)
	@Resource(name="ordermangerService")
	private OrderMangerService ordermangerService;
	@Resource(name="logService")
	public LogService logService;
	@Resource(name="vipmangerService")
	private VipMangerService vipmangerService;
	
	/**
	 * 新增
	 */
	@RequestMapping(value="/save")
	public ModelAndView save(HttpServletRequest request) throws Exception{
		logBefore(logger, "新增OrderManger");
		try{
		
		PageData pageData =this.addLog("新增", "新增OrderManger", "OrderManger");
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
		pd.put("ORDERMANGER_ID", this.get32UUID());	//主键
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		ordermangerService.save(pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 删除
	 */
	@RequestMapping(value="/delete")
	public void delete(PrintWriter out){
		logBefore(logger, "删除OrderManger");
		try{
		
		PageData pageData=this.addLog("删除", "删除OrderManger", "OrderManger");
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
			ordermangerService.delete(pd);
			out.write("success");
			out.close();
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		
	}
	
	/**
	 * 修改
	 */
	@RequestMapping(value="/editExpress")
	public ModelAndView editExpress(HttpServletRequest request) throws Exception{
		logBefore(logger, "修改OrderManger");
		try{
		PageData pageData=this.addLog("修改", "修改OrderManger", "OrderManger");
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pageData.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		logService.save(pageData);
		}catch(Exception e){
			logger.error(e.toString(), e);
		}
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		PageData queryPd=new PageData();
		String ORDERID=pd.getString("ORDERID");
		queryPd.put("ORDERID", ORDERID);
		PageData resultOrderPd = ordermangerService.findById("OrderMangerMapper.findByIdAddress",queryPd);	//根据ID读取
		PageData resultPd = ordermangerService.findById("OrderMangerMapper.findByIdExpress",queryPd);	//根据ID读取
		
		if(resultPd!=null&&resultPd.size()>0){
			pd.put("EXPRESSORDER_ID", pd.get("EXPRESSORDER_ID"));
			ordermangerService.edit("OrderMangerMapper.editExpress",pd);
		}else{
			pd.put("EXPRESSORDER_ID", UuidUtil.get32UUID());
			pd.put("CREATETIME", DateUtil.getTime());
			pd.put("USERADDRESS_ID", resultOrderPd.get("ADDRESSID"));
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
			ordermangerService.save("OrderMangerMapper.saveExpress",pd);
		}
		PageData orderTypePd=new PageData();
		orderTypePd.put("ORDER_ID", ORDERID);
		orderTypePd.put("ORDERTYPE", "3");
		ordermangerService.edit("OrderMangerMapper.editOrderType",orderTypePd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	
	/**
	 * 列表
	 */
	@RequestMapping(value="/list")
	public ModelAndView list(Page page,HttpServletRequest request){
		logBefore(logger, "列表OrderManger");
		try{
		PageData pageData=this.addLog("查看列表", "查看OrderManger列表", "OrderManger");
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
			String ORDERTYPE=pd.getString("ORDERTYPE");
			if(ORDERTYPE!=null&&!"".equals(ORDERTYPE)){
				if(Integer.parseInt(ORDERTYPE)>4){
					int RETURN_TYPE=Integer.parseInt(ORDERTYPE)-5;
					pd.put("ORDERTYPE","");
					pd.put("RETURN_TYPE", RETURN_TYPE+"");
				}
			}
			page.setPd(pd);
			List<PageData>	varList = ordermangerService.list(page);	//列出OrderManger列表
			mv.setViewName("backgroup/order/ordermanger_list");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	/**
	 * 代发货列表
	 */
	@RequestMapping(value="/waitSendList")
	public ModelAndView waitSendList(Page page,HttpServletRequest request){
		logBefore(logger, "列表OrderManger");
		try{
		PageData pageData=this.addLog("查看列表", "查看OrderManger列表", "OrderManger");
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
			pd.put("ORDERTYPE", "2");
			page.setPd(pd);
			List<PageData>	varList = ordermangerService.list(page);	//列出OrderManger列表
			mv.setViewName("backgroup/order/waitSendList");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	
	
	/**
	 * 退款退货列表
	 */
	@RequestMapping(value="/returnList")
	public ModelAndView returnList(Page page){
		logBefore(logger, "列表OrderManger");
		try{
		PageData pageData=this.addLog("查看列表", "查看OrderManger列表", "OrderManger");
		HttpServletRequest request = this.getRequest();
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
			HttpServletRequest request = this.getRequest();
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pd.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
			
			
			
			String ORDERTYPE=pd.getString("ORDERTYPE");
			if(ORDERTYPE!=null&&!"".equals(ORDERTYPE)){
				if(Integer.parseInt(ORDERTYPE)>4){
					int RETURN_TYPE=Integer.parseInt(ORDERTYPE)-5;
					pd.put("RETURN_TYPE", RETURN_TYPE+"");
					pd.put("ORDERTYPE", "");
				}
			}else{
				pd.put("RETURN_TYPE", "10");
			}
			page.setPd(pd);
			List<PageData>	varList = ordermangerService.list(page);	//列出OrderManger列表
			mv.setViewName("backgroup/order/returnList");
			mv.addObject("varList", varList);
			mv.addObject("pd", pd);
			mv.addObject(Const.SESSION_QX,this.getHC());	//按钮权限
		} catch(Exception e){
			logger.error(e.toString(), e);
		}
		return mv;
	}
	/**
	 * 查看订单详情
	 */
	@RequestMapping(value="/orderInfoByOrderId")
	public ModelAndView orderInfoByOrderId(Page page){
		logBefore(logger, "列表VipManger");
		try{
		PageData pageData=this.addLog("查看列表", "查看会员详情列表", "findByVipId");
		HttpServletRequest request = this.getRequest();
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
			page.setPd(pd);
			String type=pd.getString("type");	//1:退款;2:退货--第一步；3：查看物流凭证;4：普通订单
			HttpServletRequest request = this.getRequest();
			String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
			pd.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
			List<PageData> varOList = vipmangerService.listAll("VipMangerMapper.findByOrderId",pd);
			
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
				queryCation.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
				List cationList=vipmangerService.listAll("VipMangerMapper.findSpecificationList", queryCation);
				String SPECIFICATIONSLIST_NAME="";
				for(int l=0;l<cationList.size();l++){
					Map cationResult=(Map) cationList.get(l);
					SPECIFICATIONSLIST_NAME+=cationResult.get("NAME");
				}
				cationPd.put("SPECIFICATIONSLIST", SPECIFICATIONSLIST_NAME);
			}
			
			
			pd.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
			List<PageData> expressList = vipmangerService.listAll("OrderMangerMapper.findByIdAddress",pd);
			PageData exPd=new PageData();
			if(expressList!=null&&expressList.size()>0){
				
				exPd=expressList.get(0);
			}
			mv.setViewName("backgroup/order/orderinfo_list");
			mv.addObject("varOList", varOList);
			mv.addObject("exPd", exPd);
			mv.addObject("type", type);
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
		logBefore(logger, "去新增OrderManger页面");
		try{
		PageData pageData=this.addLog("去新增", "去新增OrderManger页面", "OrderManger");
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
			mv.setViewName("order/ordermanger/ordermanger_edit");
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
	@RequestMapping(value="/toUpdateExpress")
	public ModelAndView toUpdateExpress(){
		logBefore(logger, "去修改OrderManger页面");
		try{
		PageData pageData=this.addLog("去修改", "去修改OrderManger页面", "OrderManger");
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
		String ORDERID=pd.getString("ORDERID");
		HttpServletRequest request = this.getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pd.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		try {
			pd = ordermangerService.findById("OrderMangerMapper.findByIdExpress",pd);	//根据ID读取
			mv.setViewName("backgroup/order/ordermanger_edit");
			mv.addObject("ORDERID", ORDERID);
			mv.addObject("msg", "editExpress");
			mv.addObject("pd", pd);
			
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	
	/**
	 * 去修改订单状态页面
	 */
	@RequestMapping(value="/toUpdateOrderType")
	public ModelAndView toUpdateOrderType(){
		logBefore(logger, "去修改OrderManger页面");
		try{
		PageData pageData=this.addLog("去修改", "去修改OrderManger页面", "OrderManger");
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
			pd = ordermangerService.findById("OrderMangerMapper.findByIdOrderId",pd);	//根据ID读取
			mv.setViewName("backgroup/order/ordertype_edit");
			mv.addObject("msg", "editOrderType");
			mv.addObject("orderPd", pd);
		} catch (Exception e) {
			logger.error(e.toString(), e);
		}						
		return mv;
	}	
	
	
	
	/**
	 * 修改订单状态
	 */
	@RequestMapping(value="/editOrderType")
	public ModelAndView editOrderType() throws Exception{
		logBefore(logger, "修改OrderManger");
		try{
		PageData pageData=this.addLog("修改", "修改editOrderType", "editOrderType");
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
		ordermangerService.edit("OrderMangerMapper.editOrderType",pd);
		mv.addObject("msg","success");
		mv.setViewName("save_result");
		return mv;
	}
	/**
	 * 修改
	 */
	@RequestMapping(value="/updateReturnType")
	@ResponseBody
	public Map updateReturnType(HttpServletRequest request, HttpServletResponse response) throws Exception{
		logBefore(logger, "修改ApproveDistribution");
		Map resultMap=new HashMap();
		resultMap.put("result", "1");
		ModelAndView mv = this.getModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		String RETURN_TYPE=pd.getString("RETURN_TYPE");
		String isSuccess="";
		if("7".equals(RETURN_TYPE)||"4".equals(RETURN_TYPE)){
			
		
		ReturnUtil returnUtil=new ReturnUtil();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pd.put("BUSINESSUSER_ID",BUSINESSUSER_ID);
		List<PageData> expressList = vipmangerService.listAll("OrderMangerMapper.findByIdAddress",pd);
		PageData exPd=new PageData();
		if(expressList!=null&&expressList.size()>0){
			
			exPd=expressList.get(0);
		}
		String PAYNO=exPd.getString("PAYNO");
		String SUMPRICE=exPd.getString("SUMPRICE");
		Double SUMPRICE_double=0.0;
		if(SUMPRICE!=null&&!"".equals(SUMPRICE)){
			SUMPRICE_double=Double.parseDouble(SUMPRICE);
		}
		isSuccess=returnUtil.returnPay(request, response, PAYNO,SUMPRICE_double );
		}
		if("7".equals(RETURN_TYPE)){
			if("1".equals(isSuccess)){
				pd.put("RETURN_TYPE", "7");
			}else{
				pd.put("RETURN_TYPE", "8");
			}
		}
		else if("4".equals(RETURN_TYPE)){
			if("1".equals(isSuccess)){
				pd.put("RETURN_TYPE", "4");
			}else{
				pd.put("RETURN_TYPE", "5");
			}
		}
		ordermangerService.edit("OrderMangerMapper.editReturnType",pd);
		return resultMap;
	}
	
	/**
	 * 批量删除
	 */
	@RequestMapping(value="/deleteAll")
	@ResponseBody
	public Object deleteAll() {
		logBefore(logger, "批量删除OrderManger");
		try{
		PageData pageData=this.addLog("批量删除", "批量删除{objectName}", "OrderManger");
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
				ordermangerService.deleteAll(ArrayDATA_IDS);
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
		logBefore(logger, "导出OrderManger到excel");
		//if(!Jurisdiction.buttonJurisdiction(menuUrl, "cha")){return null;}
		ModelAndView mv = new ModelAndView();
		PageData pd = new PageData();
		pd = this.getPageData();
		try{
			Map<String,Object> dataMap = new HashMap<String,Object>();
			List<String> titles = new ArrayList<String>();
			titles.add("快递费");	//1
			titles.add("一单的总价");	//2
			titles.add("订单地址");	//3
			titles.add("订单备注");	//4
			titles.add("创建订单用户ID");	//5
			titles.add("下单时间");	//6
			titles.add("退款退货照片");	//7
			titles.add("退款原因类型");	//8
			titles.add("退款备注");	//9
			titles.add("审核原因");	//10
			titles.add("0:待付款 1:取消订单 2:待发货 3:已发货 4:确认收货");	//11
			titles.add("0:无申请1:申请退货中 2: 同意退货 3:拒绝退货 4:退货成功 5:退货失败 6:申请退款中 7:退款成功 8:退款失败9：拒绝退款");	//12
			titles.add("是否删除1：正常，2：删除");	//13
			titles.add("支付号");	//14
			titles.add("支付方式（1:微信支付;2:支付宝）");	//15
			titles.add("申请退款货时间");	//16
			dataMap.put("titles", titles);
			List<PageData> varOList = ordermangerService.listAll(pd);
			List<PageData> varList = new ArrayList<PageData>();
			for(int i=0;i<varOList.size();i++){
				PageData vpd = new PageData();
				vpd.put("var1", varOList.get(i).getString("EXPRESSPRICE"));	//1
				vpd.put("var2", varOList.get(i).getString("SUMPRICE"));	//2
				vpd.put("var3", varOList.get(i).getString("ADDRESSID"));	//3
				vpd.put("var4", varOList.get(i).getString("REMARK"));	//4
				vpd.put("var5", varOList.get(i).getString("CREATEVIPID"));	//5
				vpd.put("var6", varOList.get(i).getString("CREATETIME"));	//6
				vpd.put("var7", varOList.get(i).getString("RETURNURL"));	//7
				vpd.put("var8", varOList.get(i).getString("RETURNTYPE"));	//8
				vpd.put("var9", varOList.get(i).getString("RETURN_REMARK"));	//9
				vpd.put("var10", varOList.get(i).getString("CHECK_REMARK"));	//10
				vpd.put("var11", varOList.get(i).getString("ORDERTYPE"));	//11
				vpd.put("var12", varOList.get(i).getString("RETURN_TYPE"));	//12
				vpd.put("var13", varOList.get(i).getString("FLAG"));	//13
				vpd.put("var14", varOList.get(i).getString("PAYNO"));	//14
				vpd.put("var15", varOList.get(i).getString("PAYTYPE"));	//15
				vpd.put("var16", varOList.get(i).getString("RETURNTIME"));	//16
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

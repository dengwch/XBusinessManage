package com.fh.service.backgroup.classification;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.fh.controller.base.BaseController;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("classificationService")
public class ClassificationService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("ClassificationMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("ClassificationMapper.delete", pd);
	}
	
	public void delSUPERIOR(PageData pd)throws Exception{
		dao.delete("ClassificationMapper.delSUPERIOR", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("ClassificationMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		HttpServletRequest request=new BaseController().getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		
		page.getPd().put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		return (List<PageData>)dao.findForList("ClassificationMapper.datalistPage", page);
	}
	
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		HttpServletRequest request=new BaseController().getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		return (List<PageData>)dao.findForList("ClassificationMapper.listAll", pd);
	}
	/*
	*列表(全部)
	*/
	public List<PageData> listAllTh(PageData pd)throws Exception{
		HttpServletRequest request=new BaseController().getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		return (List<PageData>)dao.findForList("ClassificationMapper.listAllTh", pd);
	}
	/*
	*列表(全部)
	*/
	public List<PageData> listAllOne(PageData pd)throws Exception{
		HttpServletRequest request=new BaseController().getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		return (List<PageData>)dao.findForList("ClassificationMapper.listAllOne", pd);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAllTwo(PageData pd)throws Exception{
		HttpServletRequest request=new BaseController().getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		return (List<PageData>)dao.findForList("ClassificationMapper.listAllTwo", pd);
	}
	/*
	*列表(全部)
	*/
	public List<PageData> listAllOnemb(PageData pd)throws Exception{
		HttpServletRequest request=new BaseController().getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		return (List<PageData>)dao.findForList("ClassificationMapper.listAllOnemb", pd);
	}
	/*
	*列表(全部)
	*/
	public List<PageData> listAllThmb(PageData pd)throws Exception{
		HttpServletRequest request=new BaseController().getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		return (List<PageData>)dao.findForList("ClassificationMapper.listAllThmb", pd);
	}
	/*
	* 通过id获取数据
	*/
	public PageData findByIdmb(PageData pd)throws Exception{
		HttpServletRequest request=new BaseController().getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		return (PageData)dao.findForObject("ClassificationMapper.findByIdmb", pd);
	}
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		HttpServletRequest request=new BaseController().getRequest();
		String  BUSINESSUSER_ID=(String) request.getSession().getAttribute("BUSINESSUSER_ID");
		pd.put("BUSINESSUSER_ID", BUSINESSUSER_ID);
		return (PageData)dao.findForObject("ClassificationMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ClassificationMapper.deleteAll", ArrayDATA_IDS);
	}
	
}


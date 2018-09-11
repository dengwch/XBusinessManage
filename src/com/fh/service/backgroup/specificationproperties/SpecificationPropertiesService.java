package com.fh.service.backgroup.specificationproperties;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("specificationpropertiesService")
public class SpecificationPropertiesService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("SpecificationPropertiesMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("SpecificationPropertiesMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("SpecificationPropertiesMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("SpecificationPropertiesMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listById(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("SpecificationPropertiesMapper.listById", pd);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("SpecificationPropertiesMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("SpecificationPropertiesMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("SpecificationPropertiesMapper.deleteAll", ArrayDATA_IDS);
	}
	
}


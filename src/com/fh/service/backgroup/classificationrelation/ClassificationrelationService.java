package com.fh.service.backgroup.classificationrelation;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("classificationrelationService")
public class ClassificationrelationService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("ClassificationrelationMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("ClassificationrelationMapper.delete", pd);
	}
	/*
	* 删除
	*/
	public void deleteclass(PageData pd)throws Exception{
		dao.delete("ClassificationrelationMapper.deleteclass", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("ClassificationrelationMapper.edit", pd);
	}
	/*
	* 修改
	*/
	public void edit1(PageData pd)throws Exception{
		dao.update("ClassificationrelationMapper.edit1", pd);
	}
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("ClassificationrelationMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ClassificationrelationMapper.listAll", pd);
	}
	/*
	*列表(全部)
	*/
	public List<PageData> listAllIN(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ClassificationrelationMapper.listAllIN", pd);
	}
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ClassificationrelationMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ClassificationrelationMapper.deleteAll", ArrayDATA_IDS);
	}
	
}


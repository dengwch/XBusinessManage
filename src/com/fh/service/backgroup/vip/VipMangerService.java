package com.fh.service.backgroup.vip;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("vipmangerService")
public class VipMangerService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("VipMangerMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("VipMangerMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("VipMangerMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("VipMangerMapper.datalistPage", page);
	}
	/**
	*根据条件查询分页列表
	*/
	public List<PageData> listBySql(String sql,Page page)throws Exception{
		return (List<PageData>)dao.findForList(sql, page);
	}
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("VipMangerMapper.listAll", pd);
	}
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(String sql,PageData pd)throws Exception{
		return (List<PageData>)dao.findForList(sql, pd);
	}
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("VipMangerMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("VipMangerMapper.deleteAll", ArrayDATA_IDS);
	}
	
}


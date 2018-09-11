package com.fh.service.backgroup.dynamic;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("dynamicService")
public class DynamicService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("DynamicMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("DynamicMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("DynamicMapper.edit", pd);
	}
	/*
	 *修改头条新闻 
	 */
	public void updateTop(PageData pd)throws Exception{
		dao.update("DynamicMapper.updateTop", pd);
	}
	
	/*
	*列表(全部非头条新闻)
	*/
	public List<PageData> listAllNotTop(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("DynamicMapper.listAllNotTop", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("DynamicMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("DynamicMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("DynamicMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("DynamicMapper.deleteAll", ArrayDATA_IDS);
	}
	
}


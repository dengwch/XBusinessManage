package com.fh.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("imoocServiceService")
public class ImoocService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("ImoocMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("ImoocMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("ImoocMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		try{
			
		dao.findForList("ImoocMapper.datalistPage", page);
		}catch(Exception e){
			e.printStackTrace();
		}
		return (List<PageData>)dao.findForList("ImoocMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAllOne(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ImoocMapper.listAllOne", pd);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("ImoocMapper.listAll", pd);
	}
	
	/*
	 * 通过家乡ID查找家乡
	 */
	public PageData findByHomeId(String homeId)throws Exception{
		return (PageData)dao.findForObject("ImoocMapper.findByHomeId", homeId);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("ImoocMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("ImoocMapper.deleteAll", ArrayDATA_IDS);
	}
	
}


package com.fh.service.backgroup.area;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("areaService")
public class AreaService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("AreaMapper.save", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("AreaMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("AreaMapper.edit", pd);
	}
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		try{
			
		dao.findForList("AreaMapper.datalistPage", page);
		}catch(Exception e){
			e.printStackTrace();
		}
		return (List<PageData>)dao.findForList("AreaMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAllOne(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("AreaMapper.listAllOne", pd);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("AreaMapper.listAll", pd);
	}
	
	/*
	 * 通过家乡ID查找家乡
	 */
	public PageData findByHomeId(String homeId)throws Exception{
		return (PageData)dao.findForObject("AreaMapper.findByHomeId", homeId);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("AreaMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("AreaMapper.deleteAll", ArrayDATA_IDS);
	}
	
}


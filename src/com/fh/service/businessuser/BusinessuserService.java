package com.fh.service.businessuser;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;

@Service("businessuserService")
public class BusinessuserService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("BusinessuserMapper.save", pd);
	}
	public void savenotice(PageData pd)throws Exception{
		dao.save("BusinessuserMapper.savenotice", pd);
	}
	
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("BusinessuserMapper.delete", pd);
	}
	
	/*
	* 修改
	*/
	public void editZT(PageData pd)throws Exception{
		dao.update("BusinessuserMapper.editZT", pd);
	}
	public void editBUSINESSPWD(PageData pd)throws Exception{
		dao.update("BusinessuserMapper.editBUSINESSPWD", pd);
	}
	public void editOPENID(PageData pd)throws Exception{
		dao.update("BusinessuserMapper.editOPENID", pd);
	}
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("BusinessuserMapper.edit", pd);
	}
	/*
	* 修改审核信息
	*/
	public void editbu(PageData pd)throws Exception{
		dao.update("BusinessuserMapper.editbu", pd);
	}
	/*
	* 修改商家信息
	*/
	public void editxx(PageData pd)throws Exception{
		dao.update("BusinessuserMapper.editxx", pd);
	}
	
	/*
	*列表
	*/
	
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("BusinessuserMapper.datalistPage", page);
	}
	public List<PageData> datalistPageX(Page page)throws Exception{
		return (List<PageData>)dao.findForList("BusinessuserMapper.datalistPageX", page);
	}
	
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("BusinessuserMapper.listAll", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("BusinessuserMapper.findById", pd);
	}
	/*
	* 通过id获取数据
	*/
	public PageData findByPhone(PageData pd)throws Exception{
		return (PageData)dao.findForObject("BusinessuserMapper.findByPhone", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("BusinessuserMapper.deleteAll", ArrayDATA_IDS);
	}
	
}


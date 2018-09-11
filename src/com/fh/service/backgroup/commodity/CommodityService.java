package com.fh.service.backgroup.commodity;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fh.dao.DaoSupport;
import com.fh.entity.Page;
import com.fh.util.PageData;


@Service("commodityService")
public class CommodityService {

	@Resource(name = "daoSupport")
	private DaoSupport dao;
	
	/*
	* 新增
	*/
	public void save(PageData pd)throws Exception{
		dao.save("CommodityMapper.save", pd);
	}
	public void savetime(PageData pd)throws Exception{
		dao.save("CommodityMapper.savetime", pd);
	}
	public List<PageData> listAllshul(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CommodityMapper.listAllshul", pd);
	}
	public List<PageData> listAlltime(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CommodityMapper.listAlltime", pd);
	}
	public List<PageData> listAllcodes(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CommodityMapper.listAllcodes", pd);
	}
	public void updatecodes(PageData pd)throws Exception{
		dao.update("CommodityMapper.updatecodes", pd);
	}
	/*
	* 删除
	*/
	public void delete(PageData pd)throws Exception{
		dao.delete("CommodityMapper.delete", pd);
	}
	
	
	/*
	* 修改
	*/
	public void edit(PageData pd)throws Exception{
		dao.update("CommodityMapper.edit", pd);
	}

	/*
	* 修改
	*/
	public void editFlag(PageData pd)throws Exception{
		dao.update("CommodityMapper.editFlag", pd);
	}
	/*
	 * 修改图片所属
	 */
	public void updateImg(PageData pd)throws Exception{
		dao.update("CommodityMapper.updateImg", pd);
	}
	/*
	 * 修改规格所属
	 */
	public void updateSpe(PageData pd)throws Exception{
		dao.update("CommodityMapper.updateSpe", pd);
	}
	
	
	/*
	*列表
	*/
	public List<PageData> list(Page page)throws Exception{
		return (List<PageData>)dao.findForList("CommodityMapper.datalistPage", page);
	}
	
	/*
	*列表(全部)
	*/
	public List<PageData> listAll(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CommodityMapper.listAll", pd);
	}
	/*
	*列表(全部)浏览
	*/
	public List<PageData> findgoodsvisitList(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CommodityMapper.findgoodsvisitList", pd);
	}
	/*
	*列表(全部)收藏
	*/
	public List<PageData> findgoodscollectionList(PageData pd)throws Exception{
		return (List<PageData>)dao.findForList("CommodityMapper.findgoodscollectionList", pd);
	}
	
	/*
	* 通过id获取数据
	*/
	public PageData findById(PageData pd)throws Exception{
		return (PageData)dao.findForObject("CommodityMapper.findById", pd);
	}
	
	/*
	* 批量删除
	*/
	public void deleteAll(String[] ArrayDATA_IDS)throws Exception{
		dao.delete("CommodityMapper.deleteAll", ArrayDATA_IDS);
	}
	
}


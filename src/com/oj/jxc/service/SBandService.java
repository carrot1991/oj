package com.oj.jxc.service;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.oj.jxc.entity.BandDict;
import com.oj.jxc.entity.SBandDO;

/**
 * 商品品牌
 * 
 * @author wu
 * 
 */
@Component
public class SBandService extends BaseService {
	/**
	 * get all data
	 * @return
	 */
	public List<SBandDO> getList() {
		List<SBandDO> list = this.dao.queryForBeanList("select * from s_band order by level", SBandDO.class);
		return list;
	}

	/**
	 * save or update
	 * @param band
	 * @return
	 */
	public Integer saveOrUpdate(SBandDO entity) {
		if (entity != null) {
			if (entity.getBandid()==null || entity.getBandid()==0) {//{StringUtils.isBlank(String.valueOf(entity.getBandid()))) {
				return this.insert(entity);
			} else {
				this.dao.update("update s_band set title=?,level=? where bandid = ?", entity.getTitle(),entity.getLevel(),entity.getBandid());
			}
		}
		return 0;
	}

	/**
	 * remove by id
	 * @param id
	 */
	public void remove(int id) {
		this.dao.update("delete from s_band where bandid = ?", id);
	}

	/**
	 * find DO by id
	 * @param id
	 * @return
	 */
	public SBandDO findById(String id) {
		return this.dao.queryForBean("select * from s_band where bandid = ?", SBandDO.class, id);
	}
	public List<SBandDO> findByType(int typeid) {
		return this.dao.queryForBeanList("select * from s_band where typeid = ? order by level", SBandDO.class, typeid);
	}
	//bandid mytitle level
	public List<BandDict> getDict(){
		return dao.queryForBeanList("SELECT band.bandid, CONCAT(cat.title,'-',tp.title,'-',band.title) mytitle FROM s_band band INNER JOIN s_type tp ON tp.typeid=band.typeid  INNER JOIN s_category cat ON tp.cateid=cat.id ORDER BY cat.level,tp.level,band.level",BandDict.class);
	}
	 
}

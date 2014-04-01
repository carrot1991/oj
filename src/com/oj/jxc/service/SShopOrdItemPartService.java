package com.oj.jxc.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.oj.jxc.entity.SShopOrdItemPartDO;

/**
 * 订单
 * 
 * @author wu
 * 
 */
@Component
public class SShopOrdItemPartService extends BaseService {
	/**
	 * get all data
	 * @return
	 */
	public List<SShopOrdItemPartDO> getList() {
		List<SShopOrdItemPartDO> list = this.dao.queryForBeanList("select * from s_shop_ord_item_part",
				SShopOrdItemPartDO.class);
		return list;
	}

	/**
	 * save or update
	 * @param band
	 * @return
	 */
	public Integer saveOrUpdate(SShopOrdItemPartDO entity) {
		if (entity != null) {
			if (StringUtils.isBlank(String.valueOf(entity.getItemid()))) {
				return this.insert(entity);
			} else {
				return this.update(entity, "itemid", ",goodsid,amount,price,");
			}
		}
		return 0;
	}

	/**
	 * remove by id
	 * @param id
	 */
	public void remove(String id) {
		this.dao.update("delete from s_shop_ord_item_part where itemid = ?", id);
	}

	/**
	 * find DO by id
	 * @param id
	 * @return
	 */
	public SShopOrdItemPartDO findById(String id) {
		return this.dao.queryForBean("select * from s_shop_ord_item_part where itemid = ?", SShopOrdItemPartDO.class,
				id);
	}
}

package com.oj.jxc.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import com.oj.jxc.entity.BandWithProd;
import com.oj.jxc.entity.ProdDefWithProp;
import com.oj.jxc.entity.SBandDO;
import com.oj.jxc.entity.SProdDefDO;
import com.oj.jxc.entity.STypeDO;

/**
 * 商品定义
 * 
 * @author wu
 * 
 */
@Component
public class SProdDefService extends BaseService {
	/**
	 * get all data
	 * @return
	 */
	public List<SProdDefDO> getList() {
		List<SProdDefDO> list = this.dao.queryForBeanList("select * from s_prod_def ORDER BY bandid,xinghao ", SProdDefDO.class);
		return list;
	}

	//public  List<BandWithProd>  getMapByBand() {
	//	return this.dao.queryForBeanList( "SELECT bandid,id,xinghao FROM s_prod_def ORDER BY bandid,xinghao", BandWithProd.class,null);
	//}
	
	/**
	 * save or update
	 * @param band
	 * @return
	 */
	public Integer saveOrUpdate(SProdDefDO entity) {
		if (entity != null) {
			if (entity.getId()==0){//StringUtils.isBlank(String.valueOf(entity.getId()))) {
				dao.update("insert into s_prod_def(bandid,nickname,config,xinghao,isass)values(?,?,?,?,?)", 
						entity.getBandid(),
						entity.getNickname(),
						entity.getConfig(),
						entity.getXinghao(),
						entity.getIsass()
						);
				
			} else {
				return dao.update("update s_prod_def set nickname=?,config=?,xinghao=?,isass=? where bandid=?",
						entity.getNickname(),
						entity.getConfig(),
						entity.getXinghao(),
						entity.getIsass(),
						entity.getId()
						);
			}
		}
		return 0;
	}

	public Integer saveOrUpdateSimple(SProdDefDO entity ) {
		return dao.update("update s_prod_def set  nickname=?,tingchan=?,xinghao=?,isass=? where id=?",
				entity.getNickname(),
				entity.getTingchan(),
				entity.getXinghao(),
				entity.getIsass(),
				entity.getId());
	}
	
	public Integer saveOrUpdateWithProp(ProdDefWithProp entity ) {
		if (entity != null) {
				
				int prodid = dao.updateAndReturnPrimaryKeyWithKeyColunmName("insert into s_prod_def(bandid,nickname,config,xinghao,isass)values(?,?,?,?,?)",
						"id",
						new Object[]{entity.getBandid(),
						entity.getNickname(),
						entity.getConfig(),
						entity.getXinghao(),
						entity.getIsass()
				}
						);
				
				if(prodid>0){
					String[] ks = entity.getKeys().split(",");
					String[] vs = entity.getVlues().split(",");
					String kstr,vstr;
					for(int i=0;i<ks.length;i++){
						kstr=ks[i];
						vstr=vs[i];
						
						if(kstr!=null && kstr.length()>0 && vstr!=null && vstr.length()>0 ){
						dao.update("insert into s_prod_prop(prodid,propid,vlue)values(?,?,?)",
								prodid,
								Integer.parseInt(kstr),
								vstr
								);
						}
					}
					StringBuilder sb = new StringBuilder();
					sb.append("UPDATE s_prod_def ss SET config= ( ");
					//sb.append("SELECT GROUP_CONCAT( CONCAT(fp.name,':',pp.vlue,fp.unit)) config ");
					sb.append("SELECT GROUP_CONCAT( CONCAT(pp.vlue,fp.unit)) config ");
					sb.append("FROM s_prod_prop pp ");
					sb.append("INNER JOIN s_prod_fix_prop fp ");
					sb.append("ON fp.propid=pp.propid ");
					sb.append("WHERE pp.prodid=? ) where ss.id=?");
					
					dao.update(sb.toString(),prodid,prodid);
				
			}  
		}
		return 0;
	}
	
	/**
	 * remove by id
	 * @param id
	 */
	public void remove(int id) {
		this.dao.update("delete from s_prod_def where id = ?", id);
	}

	/**
	 * find DO by id
	 * @param id
	 * @return
	 */
	public SProdDefDO findById(String id) {
		return this.dao.queryForBean("select * from s_prod_def where id = ?", SProdDefDO.class, id);
	}

	public List<SProdDefDO> findDefByBandId(int bandId) {
		return this.dao.queryForBeanList("select * from s_prod_def where bandid=? ", SProdDefDO.class, bandId);
	}
	//对于已录过的商品，不允许出现在下拉框里。。。
	public List<SProdDefDO> findDefByBandIdForSale(int bandId,String uname) {
		return this.dao.queryForBeanList("SELECT * FROM s_prod_def WHERE bandid=? AND id NOT IN(SELECT prodid FROM s_goods WHERE uname=?) ", SProdDefDO.class, bandId,uname);
	}	
	
	public Map<Integer,String> getDict(){
		String sql = "SELECT sd.id prodid,CONCAT(t.title,'-',b.title,'-',sd.nickname) title FROM s_type t LEFT JOIN s_band b ON b.typeid=t.typeid LEFT JOIN s_prod_def sd ON sd.bandid=b.bandid WHERE sd.id IS NOT null ";
		return dao.queryForMaps(sql, null);
	} 
	
	
	public String getDict(int prodid){
		String sql = "SELECT CONCAT(t.title,'-',b.title,'-',sd.nickname,sd.xinghao) title FROM s_type t LEFT JOIN s_band b ON b.typeid=t.typeid LEFT JOIN s_prod_def sd ON sd.bandid=b.bandid WHERE sd.id IS NOT null and sd.id=? ";
		return dao.queryForObject(sql, String.class, prodid);
	}
	public List<Object> findbyProdid(String prodid) {
		List<Object> list = new ArrayList<Object>();
		SProdDefDO prod = findById(prodid);
		SBandDO bandDo = this.dao.queryForBean("select * from s_band where bandid = ? order by level", SBandDO.class, prodid);
		STypeDO type = this.dao
				.queryForBean("select * from s_type where  typeid=? order by level", STypeDO.class, bandDo.getTypeid());
		list.add(prod);
		list.add(bandDo);
		list.add(type);
		return list;
	}
}

package com.oj.jxc.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.CallableStatementCallback;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import com.oj.jxc.commons.Cn2Spell;
import com.oj.jxc.commons.LoginedUser;
import com.oj.jxc.commons.dto.Pager;
import com.oj.jxc.entity.SShopCartDO;
import com.oj.jxc.entity.SmsOrder;
import com.oj.jxc.entity.SmsOrderForBuyer;

/**
 * 购物车
 * 
 * @author wu
 * 
 */
@Component
public class SShopCartService extends BaseService {
	@Autowired
	JdbcTemplate jdbc;

	/**
	 * get all data
	 * @return
	 */
	public List<SShopCartDO> getList() {
		List<SShopCartDO> list = this.dao.queryForBeanList("select * from s_shop_cart", SShopCartDO.class);
		return list;
	}

	public List<SmsOrderForBuyer> findBuyerMobileNoByOrderID(int ordid){
//		StringBuilder sb = new StringBuilder();
//		
//		sb.append( "SELECT   ");
//		sb.append( "s_user_profile.mobile ");
//		sb.append( "FROM ");
//		sb.append( "s_user_profile ");
//		sb.append( "INNER JOIN s_shop_ord  ");
//		sb.append( "ON (s_user_profile.uname = s_shop_ord.uname) ");
//		sb.append( " WHERE s_shop_ord.id=? limit 1");
		
//		String mobileNo = dao.queryForObject(sb.toString(), String.class,  ordid);
		StringBuilder sb = new StringBuilder();

		//sb.append( "SELECT  s_user_profile.mobile, ");
		//sb.append( "    s_prod_def.nickname ");
		//sb.append( "    , s_prod_def.xinghao ");
		//sb.append( "    , s_shop_ord_item_part.amount ");
		
		sb.append( "SELECT  s_user_profile.mobile, SUM(s_shop_ord_item_part.price*s_shop_ord_item_part.amount) totalfee,");
		sb.append( "GROUP_CONCAT( CONCAT(s_prod_def.nickname,'-' ");
		sb.append( ", s_prod_def.xinghao,'-' ");
		sb.append( ", s_shop_ord_item_part.amount,'台')) details ");
		sb.append( "FROM ");
		sb.append( "    s_shop_ord_item_part ");
		sb.append( "    INNER JOIN s_shop_ord_item "); 
		sb.append( "        ON (s_shop_ord_item_part.itemid = s_shop_ord_item.id) ");
		sb.append( "    INNER JOIN s_shop_ord  ");
		sb.append( "        ON (s_shop_ord.id = s_shop_ord_item.ordid) ");
		sb.append( "    INNER JOIN s_prod_def  ");
		sb.append( "        ON (s_shop_ord_item.prodid = s_prod_def.id) ");
		sb.append( "    INNER JOIN s_goods  ");
		sb.append( "        ON (s_shop_ord_item_part.goodsid = s_goods.goodsid) ");
		sb.append( "    INNER JOIN s_user_profile  ");
		sb.append( "       ON (s_goods.uname = s_user_profile.uname)  ");
		sb.append( "    WHERE s_shop_ord.id=? limit 1");
		 
		List<SmsOrderForBuyer> ret = dao.queryForBeanList(sb.toString(), SmsOrderForBuyer.class,  ordid);
		
		return ret;
	}
	public List<SmsOrder> findSaleMobileNoByOrderID(int ordid){
		StringBuilder sb = new StringBuilder();

		sb.append( "SELECT  s_user_profile.mobile, ");
		sb.append( "    s_prod_def.nickname ");
		sb.append( "    , s_prod_def.xinghao ");
		sb.append( "    , s_shop_ord_item_part.amount ");
		sb.append( "FROM ");
		sb.append( "    s_shop_ord_item_part ");
		sb.append( "    INNER JOIN s_shop_ord_item "); 
		sb.append( "        ON (s_shop_ord_item_part.itemid = s_shop_ord_item.id) ");
		sb.append( "    INNER JOIN s_shop_ord  ");
		sb.append( "        ON (s_shop_ord.id = s_shop_ord_item.ordid) ");
		sb.append( "    INNER JOIN s_prod_def  ");
		sb.append( "        ON (s_shop_ord_item.prodid = s_prod_def.id) ");
		sb.append( "    INNER JOIN s_goods  ");
		sb.append( "        ON (s_shop_ord_item_part.goodsid = s_goods.goodsid) ");
		sb.append( "    INNER JOIN s_user_profile  ");
		sb.append( "       ON (s_goods.uname = s_user_profile.uname)  ");
		sb.append( "    WHERE s_shop_ord.id=? ");
		 
		List<SmsOrder> ret = dao.queryForBeanList(sb.toString(), SmsOrder.class,  ordid);
		
		return ret;
	}
	public List<SmsOrder> findMobileNoByCartID(String cartid){
		StringBuilder sb = new StringBuilder();

		sb.append( "SELECT  s_user_profile.mobile, ");
		sb.append( "    s_prod_def.nickname ");
		sb.append( "    , s_prod_def.xinghao ");
		sb.append( "    , s_shop_ord_item_part.amount ");
		sb.append( "FROM ");
		sb.append( "    s_shop_ord_item_part ");
		sb.append( "    INNER JOIN s_shop_ord_item "); 
		sb.append( "        ON (s_shop_ord_item_part.itemid = s_shop_ord_item.id) ");
		sb.append( "    INNER JOIN s_shop_cart  ");
		sb.append( "        ON (s_shop_cart.id = s_shop_ord_item.cartid) ");
		sb.append( "    INNER JOIN s_prod_def  ");
		sb.append( "        ON (s_shop_ord_item.prodid = s_prod_def.id) ");
		sb.append( "    INNER JOIN s_goods  ");
		sb.append( "        ON (s_shop_ord_item_part.goodsid = s_goods.goodsid) ");
		sb.append( "    INNER JOIN s_user_profile  ");
		sb.append( "       ON (s_goods.uname = s_user_profile.uname)  ");
		sb.append( "    WHERE s_shop_cart.id=? ");
		 
		List<SmsOrder> ret = dao.queryForBeanList(sb.toString(), SmsOrder.class,  cartid);
		
		return ret;
	}
	/**
	 * remove by id
	 * @param id
	 */
	public void remove(String id) {
		this.dao.update("delete from s_shop_cart where id = ?", id);
	}

	/**
	 * find DO by id
	 * @param id
	 * @return
	 */
	public SShopCartDO findById(String id) {
		return this.dao.queryForBean("select * from s_shop_cart where id = ?", SShopCartDO.class, id);
	}

	public Pager findPager(Pager pager, LoginedUser user) {
		//return this
		//		.findPagerComplex(
		//				"select a.*,b.nickname,date_format(a.createtime, '%Y-%m-%d %H-%i-%s') createtime1 from s_shop_cart a,s_prod_def b  where a.prodid = b.id and a.uname = ?",
		//				pager, user.getLoginUser().getUname());
		
		//title amount price createtime
		if(isEmpty("s_shop_cart")){
			return pager;
		}
		StringBuilder sb = new StringBuilder();
		sb.append( "SELECT  ");
		sb.append( "(SELECT CONCAT(t.title,'-', b.title,'-', sd.nickname,'-',sd.xinghao,'-',sd.config) AS title  ");
		sb.append( "FROM s_prod_def sd ");
		sb.append( "INNER JOIN s_band b ");
		sb.append( "    ON (b.bandid = sd.bandid)  ");
		sb.append( "INNER JOIN s_type t ");
		sb.append( "    ON (b.typeid = t.typeid) ");
		sb.append( "     WHERE sd.id = cart.prodid ");
		sb.append( ") title ,cart.id,cart.amount ");
		sb.append( ", cart.price ");
		sb.append( ", cart.prodid ");
		sb.append( ", date_format(cart.createTime, '%Y-%m-%d %H-%i-%s') createtime,");
		sb.append( "GROUP_CONCAT(pt.amount,':',pt.price) partdetails ");
		sb.append( "FROM ");
		sb.append( "s_shop_cart cart ");
		sb.append( "INNER  JOIN s_shop_ord_item item  ");
		sb.append( "ON cart.id=item.cartid ");
		sb.append( "INNER JOIN s_shop_ord_item_part pt ");
		sb.append( "ON item.id=pt.itemid  ");
		sb.append( "WHERE cart.uname=? ");
		sb.append( "order by cart.createtime desc ");
		return findPagerComplex(
				sb.toString(),
				pager, user.getLoginUser().getUname());		
		
	}

	public String update(final String cartid) {
		jdbc.execute("{call removecart(?)}", new CallableStatementCallback<Object>() {
			@Override
			public Object doInCallableStatement(CallableStatement cs) throws SQLException, DataAccessException {
				cs.setString(1, cartid);
				cs.execute();
				return null;
			}
		});
		return "success";
	}

	//orderid,retcode
	public String addOrder(String cartid, final String uname,final String beizhu) {
		
		String ddhSql = "SELECT CONCAT('"+Cn2Spell.converterToFirstSpell(uname).toUpperCase()+"','-',DATE_FORMAT(NOW(), '%Y%m%d'),'-',IFNULL(MAX(id)+1,1) ) FROM s_shop_ord";
		final String ddh = dao.queryForObject(ddhSql, String.class, null);
		
		//JXGF-20140301-10096
		KeyHolder keyHolder = new GeneratedKeyHolder();

		//Map<String,String> map = new HashMap<String,String>();
		jdbc.update(new PreparedStatementCreator() {

			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				String sql = "insert into s_shop_ord (uname,status,createtime,ddh,beizhu) values (?,0,now(),?,?)";
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, uname);
				ps.setString(2, ddh);
				ps.setString(3, beizhu);
				return ps;
			}
		}, keyHolder);
		long id = keyHolder.getKey().longValue();
		
		//map.put("orderid", String.valueOf(id));
		
		String ids = "(";
		String[] s = cartid.split(",");
		for (String cid : s) {
			//			 update s_shop_ord_item set ordid=定单总号  where cartid in(当前的购物车中的ids);
			//			 delete from s_shop_cart  where cartid in (当前的购物车中的ids);
			ids += ("'" + cid + "',");
		}
		ids = ids.substring(0, ids.length() - 1);
		ids += ")";
		this.dao.update("update s_shop_ord_item set ordid=" + id + "  where cartid in" + ids);
		this.dao.update("delete from s_shop_cart  where id in" + ids);
		
		//map.put("retcode", "success");
		
		return "success"; 
	}
}

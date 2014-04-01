package com.oj.jxc.service;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.oj.jxc.commons.LoginedUser;
import com.oj.jxc.commons.dto.Pager;
import com.oj.jxc.entity.SUserDO;

/**
 * 用户
 * 
 * @author wu
 * 
 */
@Component
public class SUserService extends BaseService {
	@Autowired
	JdbcTemplate jdbc;
	@Autowired
	SUserRoleService roleDao;
	@Autowired
	SBizuserBandService prodDao;
	@Autowired
	SBizRegionService regionDao;

	/*
SELECT COUNT(1) FROM s_goods s WHERE s.ispendingchange=1 OR s.ispendingup=1 AND s.prodid IN(SELECT prodid FROM s_bizuser_prod bp  WHERE bp.uname='upbiz');

SELECT COUNT(1) FROM s_shop_ord WHERE STATUS=0 AND uname IN( SELECT uname FROM s_user_profile up WHERE up.regionId IN ( SELECT regionid FROM s_bizuser_region WHERE uname='downbiz'))

SELECT COUNT(1) FROM s_shop_ord WHERE STATUS=1 AND shipfinished=0
	 */
	//3对下商务 4 对上商务 7ship
	public String findMyBusiness(String uname,String role){
		StringBuilder sb = new StringBuilder();
		if(role.equals("4")){
			int num = this.dao.queryForObject("SELECT COUNT(1) FROM s_goods s WHERE s.ispendingchange=1 OR s.ispendingup=1 AND s.prodid IN(SELECT id FROM s_prod_def WHERE bandid IN (SELECT bandid FROM s_bizuser_band bb  WHERE bb.uname=?) )",Integer.class,uname);
			if(num>0)sb.append("您有 " + num + "条 上架/价格变动 商品待审核.");
		}else if(role.equals("3")){
			int num = this.dao.queryForObject("SELECT COUNT(1) FROM s_shop_ord WHERE STATUS=0 AND uname IN( SELECT uname FROM s_user_profile up WHERE up.regionId IN ( SELECT regionid FROM s_bizuser_region WHERE uname=?))",Integer.class,uname);
			if(num>0)sb.append("您有 " + num + "条 定单待审核.");
		}else if(role.equals("7")){
			int num = this.dao.queryForObject("SELECT COUNT(1) FROM s_shop_ord WHERE STATUS=1 AND shipfinished=0 AND uname IN( SELECT uname FROM s_user_profile up WHERE up.regionId IN ( SELECT regionid FROM s_bizuser_region WHERE uname=?))",Integer.class,uname);
			if(num>0)sb.append("您有 " + num + "条 定单待发货.");
		}
		return sb.toString();
	}
	/**
	 * get all data
	 * @return
	 */
	public List<SUserDO> getList() {
		List<SUserDO> list = this.dao.queryForBeanList("select * from s_user", SUserDO.class);
		return list;
	}

	/**
	 * save or update
	 * @param band
	 * @return
	 */
	public Integer saveOrUpdate(SUserDO entity) {
		if (entity != null) {
			if (StringUtils.isBlank(String.valueOf(entity.getUname()))) {
				return this.insert(entity);
			} else {
				return this.update(entity, "uname", ",pwd,isvalid,createdate,isdelete,updatedate,");
			}
		}
		return 0;
	}

	/**
	 * remove by id
	 * @param uname
	 */
	public String remove(String uname) {
		this.dao.update("delete from s_user where uname = ?", uname);
		this.dao.update("delete from s_user_role where uname = ?", uname);
		this.dao.update("delete from s_user_profile where uname = ?", uname);
		return "success";
	}
	public String getPwd (String user ) {
		return dao.queryForObject("select pwd from s_user where uname=?",String.class,user);
	}
	/**
	 * @param uname
	 * @return
	 */
	public LoginedUser login(String user, String pwd) throws Exception {
		SUserDO suser = this.dao.queryForBean("select * from s_user where isvalid=1 and uname = ? and pwd =? ", SUserDO.class, user,
				pwd);
		// if(user.equals("a") && password.equals("a")){
		if (suser == null) {
			return null;
		} else {
			//根据规则查其它...
			LoginedUser loginedUser = new LoginedUser();

			loginedUser.setLoginUser(suser);

			//1.用户的角色
			List<String> roles = roleDao.findByRoleId(user);
			loginedUser.setRoles(roles);
			//1销售商 2管理员 3对下商务 4 对上商务 5代理商 6遊客7 ship
			if (roles.contains("销售商")){
				loginedUser.setRoleInfo("1");
			}else if (roles.contains("管理员"))
				loginedUser.setRoleInfo("2");
			else if (roles.contains("对下商务")) {
				loginedUser.setRoleInfo("3");
				List<Integer> regions = regionDao.findByUser(user);
				loginedUser.setRegions(regions);
			} else if (roles.contains("对上商务")) {
				loginedUser.setRoleInfo("4");
				//2.如果是对上商务，查其拥有的产品
				List<Integer> prodIds = prodDao.findByUserId(user);
				loginedUser.setProdIds(prodIds);
			} else if (roles.contains("供货商"))
				loginedUser.setRoleInfo("5");
			else if (roles.contains("游客"))
				loginedUser.setRoleInfo("6");
			else if (roles.contains("物流")){
				loginedUser.setRoleInfo("7");
				List<Integer> regions = regionDao.findByUser(user);
				loginedUser.setRegions(regions);
			}
			else if (roles.contains("超级商务"))
				loginedUser.setRoleInfo("8");
//			if (roles.contains("销售商") || roles.contains("供货商")){
//				Integer profileCount = dao.queryForObject( "select id from s_user_profile where uname=?",Integer.class,user);
//				loginedUser.setProfileCompleted(profileCount!=null);
//				if(profileCount!=null){
//					loginedUser.setProfileNo(profileCount);
//				}
//			}
			
			return loginedUser;
		}
	}

	public String add(String uname, String pwd, String role,boolean enabled,String tel) {
		if (jdbc.queryForInt("select COUNT(1) from s_user where uname =?", uname) > 0) {
			return "1";
		}
		this.dao.update("insert into s_user (uname,pwd,isvalid,createdate,isdelete,tel) values (?,?,?,now(),0,?)", uname, pwd,enabled?1:0,tel);
		this.dao.update("insert into s_user_role (uname,rname) values (?,?)", uname, role);
		return "success";
	}
	public String audit(String uname,int flag) {
		this.dao.update("update s_user set isvalid=? where uname=?", flag,uname);
		return "success";
	}	
	public String changePwd(String uname, String pwd ) {
		this.dao.update("update s_user set pwd=? where uname=? ",   pwd,uname);
		return "success";
	}
	 
	
	public Pager findPager(Pager pager, String roleId,String key) {
		String sql = null;
		if(StringUtils.isBlank(roleId)){
			sql = "SELECT a.uname,a.isvalid,a.tel,a.email,a.qq,b.rname FROM s_user a inner join s_user_role b on a.uname = b.uname WHERE a.uname not in('super','guest')  ";
			if(StringUtils.isNotBlank(key)){
				sql+=" and a.uname like '%"+key+"%'";
			} 
		}else{
			sql = "SELECT a.uname,a.isvalid,a.tel,a.email,a.qq,b.rname FROM s_user a inner join s_user_role b on a.uname = b.uname  WHERE a.uname not in('super','guest')  and b.rname='"+roleId+"'";
			 if(StringUtils.isNotBlank(key)){
				 sql+=" and a.uname like '%"+key+"%'";
			 }
		}
		sql+=" order by a.isvalid,a.uname";
		return this.findPager(sql,
				pager);
	}
	public SUserDO find(String uname) {
		return this.dao.queryForBean("select tel,email,qq from s_user where uname = ?",SUserDO.class, uname);
	}
	

	public String saveContact(SUserDO u) {
		if (jdbc.queryForInt("select COUNT(1) from s_user where uname =?", u.getUname()) > 0) {
			//return "用户已存在";
			this.dao.update("update s_user set tel=?,email=?,qq=? where uname=?", 
					u.getTel(),
					u.getEmail(),
					u.getQq(),
					u.getUname());
		}else{
			this.dao.update("insert into s_user (uname,pwd,createdate,tel,email,qq) values (?,?,now(),?,?,?)", 
					u.getUname(), u.getPwd(),u.getTel(),u.getEmail(),u.getQq());
			this.dao.update("insert into s_user_role (uname,rname) values (?,?)", u.getUname(), u.getRname());
		}

		return "success";
		 
	}
}

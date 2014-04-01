<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="theme/login/base.css" rel="stylesheet" type="text/css" />
<link href="theme/login/global.css" rel="stylesheet" type="text/css" />
<style type="text/css">
	span.top_r_inf {
	line-height: 45px;
	padding-right: 20px;
}
 
span.top_r_inf a {
	color: #cddfee;
	padding: 0 5px;
}
 
span.top_r_inf a:hover {
	color: #fD0
}

</style>
<script type="text/javascript" src="<%=basePath%>js/jquery.js"></script>
<script type="text/javascript" src="<%=basePath%>js/utils.js"></script>
<script type="text/javascript" src="<%=basePath%>js/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=basePath%>js/layer/layer.js"></script>
<link type="text/css" href="<%=basePath%>js/layer/skin/css.css" rel="stylesheet" />
<script>
var upbizContactHelp = '';
var downbizContactHelp = '';
var lowPriceHelp = '';
function showUpbizHelp(){
	 alert(upbizContactHelp );
}
function showDownBizHelp(){
	 alert(downbizContactHelp );
}
function showLowPriceHelp(){
	 alert(lowPriceHelp + '\n 平台可协助您完成采购诉求。');
}

$(function(){
	
	<c:if test="${sessionScope.OJ_JXC_USER.roleInfo=='5'}">
	$.getJSON("userprod/findContactUp",
			{random: Math.random()},function(jsonstr){
		  if(jsonstr!=null){
			  var str='';
			  
			  var json=eval(jsonstr);
			  for(var i=0;i<json.length;i++){
				  d = json[i];
				  if(i==0){
					  str += '感谢使用本平台，为您服务的商务人员联系方式如下：\n\n';
					  
				  }
				  str += '【'+d.title+'】 : '+d.shortname+'   电话：'+d.tel+'   QQ：'+d.qq +'\n';
				  
			  }
			  //$('#upbizContact').html(str);
			  upbizContactHelp = str;
			  
		  }
	  });
	</c:if>
	
	<c:if test="${sessionScope.OJ_JXC_USER.roleInfo=='1'}">
	$.getJSON("userRegion/findContactDown",
			{random: Math.random()},function(jsonstr){
		  if(jsonstr!=null){
			  var str='';
			  var strLow='';
			  var json=eval(jsonstr);
			  
			  for(var i=0;i<json.length;i++){
				  d = json[i];
				  if(i==0){
					  str += '感谢使用本平台，为您服务的商务人员联系方式如下：'+'\n\n';
					  strLow += '如有低价供货商信息提供，请联系：\n\n';
				  }
				  str +=  d.shortname+'   电话：'+d.tel+'     QQ：'+d.qq +'\n';
				  strLow+=  d.shortname+'   电话：'+d.tel+'     QQ：'+d.qq +'\n';
			  }
			  downbizContactHelp = str;
			  lowPriceHelp = strLow;
			  //$('#downbizContact').html(str); 
		  }
	  });
	</c:if>
	
	$("#update").click(function(){
		//update('修改密码','<%=basePath%>updatePwd.jsp');
	});
	
	//加载购物推荐...
		$.getJSON("shopord/suggest",{random: Math.random(),nums:10},function(jsonstr){
			  if(jsonstr!=null){
				  var str='';
				  var json=eval(jsonstr);//array
				  for(var i=0;i<json.length;i++){
					  d = json[i];
					  str +=  d.uname +' 以 ' +d.price +'的价格， 购买了 ' +d.nickname + ' 数量:'+d.amount + '('+ d.createtime +')    ';
				  }	
				  $('#suggest').append(str);	  
			  }
		  });
		
});
function update(title,url){
	   $.layer({
		   width:368,
		   height:310,
		   isZY:true,
		   moveLayer:false,
		   parent:'main',
        content:[{name:title,url:url}]
		});
}
</script>
</head>
<body style="overflow:hidden;border:solid green 1px;">
	<div id="top"><!-- &nbsp;&nbsp;&nbsp;&nbsp;IT产品信息查询系统 -->
		<div id="logo" class="left"></div>
				<div class="inf_visiter_img left">
					<img src="theme/login/images/ico.png" />
				</div>
				<div class="inf_visiter_doc left">
					<h2>
						${sessionScope.OJ_JXC_USER.loginUser.uname}
					</h2>
					<span>[
					<c:if test="${sessionScope.OJ_JXC_USER.roleInfo=='7'}">
						物流
					</c:if>
					<c:if test="${sessionScope.OJ_JXC_USER.roleInfo=='6'}">
						游客
					</c:if>
					<c:if test="${sessionScope.OJ_JXC_USER.roleInfo=='5'}">
						供货商
					</c:if>
					<c:if test="${sessionScope.OJ_JXC_USER.roleInfo=='4'}">
						 对上商务
					</c:if>
					<c:if test="${sessionScope.OJ_JXC_USER.roleInfo=='3'}">
						对下商务
					</c:if>
					<c:if test="${sessionScope.OJ_JXC_USER.roleInfo=='2'}">
						管理员 
					</c:if>
					<c:if test="${sessionScope.OJ_JXC_USER.roleInfo=='1'}">
						销售商
					</c:if>]
					
					<a href="content.jsp" target="main">欢迎页</a>
					
					</span> <span id="bgclock"> </span>
				</div>
				<span class="right top_r_inf">
				
					<!-- 供货商 -->
					<c:if test="${sessionScope.OJ_JXC_USER.roleInfo=='5'}">
						<button onclick="showUpbizHelp()">联系商务</button>
						<a href="pages/sale/listMyGoods.jsp" target="main">我的商品</a>
						<a href="pages/sale/myOrders.jsp" target="main">我的订单</a>
						<a href="pages/sale/tongji.jsp" target="main">发货统计</a>
						<a href="pages/sale/kucun.jsp" target="main">库存统计</a>
						<a href="pages/changeProfile.jsp" target="main">我的资料</a>
					</c:if>
					
					<!-- 采购商 buy-->
					<c:if test="${sessionScope.OJ_JXC_USER.roleInfo=='1'}">
					    <button onclick="showDownBizHelp()">联系商务</button>
						<a href="pages/buy/myShopCart.jsp" target="main">购物车</a>
						<a href="pages/buy/myOrders.jsp" target="main">我的订单</a>
						<a href="pages/changeProfile.jsp" target="main">我的资料</a>
						
					</c:if>
					
					<!-- 对上商务 -->
					<c:if test="${sessionScope.OJ_JXC_USER.roleInfo=='4'}">
					    <a href="loginProxy.jsp" target="main">代理登录</a>
						<a href="pages/upbiz/cat.jsp" target="main">品牌与分类</a>
						<a href="pages/upbiz/prodPropCat.jsp" target="main">产品配置</a>
						<a href="pages/upbiz/prodCat.jsp" target="main">产品</a>
						<a href="pages/upbiz/listGoods.jsp?type=1&bandId=1" target="main">审核商品</a>
						<a href="pages/upbiz/prodLimit.jsp" target="main">数量价格约束</a>
					</c:if>
					<!-- 对下商务 -->
					<c:if test="${sessionScope.OJ_JXC_USER.roleInfo=='3'}">
						<a href="loginProxy.jsp" target="main">代理登录</a>
						<a href="pages/downbiz/listOrders.jsp" target="main">订单</a>
					</c:if>
					
					<!-- 管理员 -->
					<c:if test="${sessionScope.OJ_JXC_USER.roleInfo=='2'}">
						<a href="pages/super/dict/dict.jsp" target="main">字典</a>
						<a href="pages/upbiz/cat.jsp" target="main">品牌与分类</a>
						<a href="pages/upbiz/prodPropCat.jsp" target="main">产品配置</a>
						<a href="pages/upbiz/prodCat.jsp" target="main">产品</a>
						<a href="pages/super/user/user.jsp" target="main">账号</a>
						<a href="pages/super/user/customers.jsp" target="main">供货商与经销商</a>
						<a href="pages/super/user/userBizSuper.jsp" target="main">超级商务</a>
						<a href="pages/super/user/userBizUp.jsp" target="main">对上商务</a>
						<a href="pages/super/user/userBizDown.jsp" target="main">对下商务</a>
						<a href="pages/super/user/userShip.jsp" target="main">物流帐号</a>
					</c:if>
					<!-- 超级商务 -->
					<c:if test="${sessionScope.OJ_JXC_USER.roleInfo=='8'}">
						<a href="pages/upbiz/cat.jsp" target="main">品牌与分类</a>
						<a href="pages/upbiz/prodPropCat.jsp" target="main">产品配置</a>
						<a href="pages/upbiz/prodCat.jsp" target="main">产品</a>
						<a href="pages/super/user/user.jsp" target="main">账号</a>
						<a href="pages/super/user/customers.jsp" target="main">供货商与经销商</a>
						<a href="pages/super/user/userBizUp.jsp" target="main">对上商务</a>
						<a href="pages/super/user/userBizDown.jsp" target="main">对下商务</a>
						<a href="pages/super/user/userShip.jsp" target="main">物流帐号</a>
					</c:if>
					<c:if test="${sessionScope.OJ_JXC_USER.roleInfo=='7'}">
					   <a href="pages/downbiz/listShipOrders.jsp" target="main">订单</a>
					</c:if>
					<c:if test="${sessionScope.OJ_JXC_USER.roleInfo!='6'}">
					<a href="updatePwd.jsp" target="main" >修改密码</a>
					</c:if>
					<a href="logout.jsp" target="_top">退出系统</a>
				</span>
	
	</div>
	<!-- 
	behavior、bgcolor、direction、width、height、hspace、vspace、loop、scrollamount、scrolldelay
	scrollamount="50" scrolldelay=2000 bgcolor="red"
	 -->
	 <div align=center>
	<marquee id="suggest" width="800" height="30" scrolldelay=400  direction="up" ></marquee>
	</div>
</body>
</html>

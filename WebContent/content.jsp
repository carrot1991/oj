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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<base href="<%=basePath %>">
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/js/datagrid/datagrid.js"></script>
<link type="text/css" href="<%=path%>/js/layer/skin/css.css" rel="stylesheet" />
<link href="<%=path%>/js/datagrid/skin/datagrid.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=path%>/js/utils.js"></script>
<script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>
<script type="text/javascript">
 
</script>
<style type="text/css">
   body{background: #fff;}
   body,p,table,p,img {margin:0; padding:0;font-family:"宋体",Verdana, Arial,Sans; font-size:12px;}
   ul,li{margin:0; padding:0; list-style:none;}
   table,td {border-collapse:collapse;empty-cells:show;}
   .h1{height: 29px;background:#f1f1f1;border-bottom: solid 1px #c6dbee;}
   .h1 span{float:left;height: 24px;line-height: 24px; padding:0 10px;cursor: pointer;margin-top: 4px;display:inline;color: #547392;font-weight: bold;margin-left: 25px;}
   .h1 span.div{background:#ddeefe;border: solid 1px #c7ddeb;border-bottom-width: 0;}
   .h1 span.add{background:url("theme/images/add.gif") no-repeat 0 3px;padding-left: 20px;}
   .keepsoft-table  .tableHeadArea{border-bottom-color:#a0b4c6; }
   .keepsoft-table  .tableHeadArea .input input{margin-top: 8px;}
   .keepsoft-table  .tableBodyArea .input input{margin-top: 5px;}
   .item1{background: #f9fdff;}
   .item2{background: #f1f8fd;}
</style>

<script>
 
/*
var upbizContactHelp = '';
var downbizContactHelp = '';

function showUpbizHelp(){
	alert(upbizContactHelp);
}
function showDownBizHelp(){
	alert(downbizContactHelp);
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
					  str += '您的专属联系商务：<br/>';
				  }
				  str += '【'+d.title+'】 '+d.shortname+' 电话：'+d.tel+' QQ：'+d.qq +'<br/>';
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
			  var json=eval(jsonstr);
			  
			  for(var i=0;i<json.length;i++){
				  d = json[i];
				  if(i==0){
					  str += '您的专属联系商务：'+'<br/>';
				  }
				  str += d.shortname+' 电话：'+d.tel+' QQ：'+d.qq +'<br/>';
			  }
			  downbizContactHelp = str;
			  //$('#downbizContact').html(str); 
		  }
	  });
	</c:if>
	
});
*/
</script>
</head>
<body> 
<%
String msg = (String)session.getAttribute("HOMEPAGE_MESSAGE");
if(msg!=null && msg.length()>0){
%>
<br/><br/><br/><br/>
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<font color=red><%=msg %></font>
<%} %>
<!-- 
<div id="upbizContact" onclick='showUpbizHelp()'>供货商客服</div>
<div id="downbizContact" onclick='showDownBizHelp()'>销售商客服</div>
-->
</body>
</html>
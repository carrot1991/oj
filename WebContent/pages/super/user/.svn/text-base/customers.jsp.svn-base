<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>
<script type="text/javascript">

function myInput(){
	var str="<input size='8' style='height: 10px;'/>";
	return str;
}
function taxShow(value){
	if(value==1){
		return '含税';
	}else{
		return '';
	}
}
function validShow(value){
	if(value==0){
		return '禁用';
	}else{
		return '';
	}
}
//get?uname=${sessionScope.OJ_JXC_USER.loginUser.uname}
$(function(){
	var url="userprofile/listPager";
	var clums=[  
				  {name:'名称',field:'uname',width:100},
				  {name:'状态',field:'isvalid',width:40,fun:validShow},
				  {name:'含税价',field:'containtax',width:40,fun:taxShow},
				  {name:'缩写名称',field:'memname',width:100},
				  {name:'英文名称',field:'enname',width:60},
				  {name:'客户名称',field:'custname',width:100},
				  {name:'地址',field:'custaddr',width:250},
				  /*{name:'税号',field:'custtaxno',width:200},
				  {name:'开户行量',field:'custbank',width:100},
				  {name:'开户帐号',field:'custbankno',width:50},
				  {name:'客户备注',field:'custmem',width:50}, */
				  {name:'QQ',field:'custqq',width:70},
				  {name:'联系人',field:'contact',width:70},
				  {name:'手机1',field:'mobile',width:70},
				  /*{name:'手机2',field:'mobile2',width:50},
				  {name:'电话2',field:'phone2',width:50},
				  */
				  //{name:'区域',field:'regionid',width:50}
		          ];
	$("#table").dataGrid({
		url : url,
		headHeight : 30,
		bodyHeight:280,
		pageHeight : 30,
		isPage:true,
		pageSize:20,
		rowHeight:25,
		isSerialNo:true,
		itemClass:['item1','item2'],
		fields:clums,
	    operates:{width:100, value:[
			{
				name:'修改', fun:function(row){ update('查看','pages/super/user/customerUpdate.jsp?id='+ row.id); }
			
			},
			{
				name:'关联商务', fun:function(row){ update('查看','pages/super/user/customerRelUpdate.jsp?id='+ row.id); }
			
			},			
			{name:'删除帐号', fun:function(row){
				if(confirm('确认删除 '+row.uname+' ?')){ 
	            	$.get("userprofile/remove",{'uname':row.uname},function(json){
	            		  if(json.success=='success'){
	                		  	  alert("账号删除成功！");
	                		  	  refresh();
	                	  	  }else{
	                  		  alert("系统异常:"+json.success);
	                  	  }	      	                                  	  
	                    });
				}
            }}
	       ]
	              }
	});
 
});
//update('查看','pages/super/user/customerUpdate.jsp?uname='+  encodeURI(row.uname));
//encodeURI(row.uname)

   function update(title,url){
	   $.layer({
		   width:368,
		   height:310,
		   isZY:true,
		   moveLayer:true,
		   parent:'keepsoft_1_',
           content:[{name:title,url:url}]
		});
   }
 
   function refresh(){
	   $("#table").dataRefresh();
	   $.unblockUI();
   }
   
   function closed(){
	   $.unblockUI();
   }
   
  function buy(prodid){
	 
	 $.post("shopCart/add",{prodid:id},function(json){
			   $.unblockUI();
			   refresh();
	  });

  }
  
  function search(){
	  var key=$("#key").val();
	  $("#table").dataRefresh({"key":key});
  }

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
</head>
<body>
<div class="h1">
   <span class="div">资料列表</span>
</div>
 
<div style="padding:25px;">
<input name="key" id="key" />
<input type="button" value="&nbsp;&nbsp;&nbsp;&nbsp;搜&nbsp;&nbsp;&nbsp;索&nbsp;&nbsp;&nbsp;&nbsp;"  id="searchButton"	onclick="search();" />
  <div id="table"></div>
</div>
</body>
</html>
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
<title>产品管理</title>
<base href="<%=basePath %>">
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/js/datagrid/datagrid.js"></script>
<link type="text/css" href="<%=path%>/js/layer/skin/css.css" rel="stylesheet" />
<link href="js/datagrid/skin/datagrid.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=path%>/js/utils.js"></script>
<script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>
<script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>
<script type="text/javascript">
$(function(){
	$("#table").dataGrid({
		url : "user/pager",
		headHeight : 30,
		bodyHeight:280,
		pageHeight : 30,
		rowHeight:25,
		isSerialNo:true,
		itemClass:['item1','item2'],
		fields:[  {name:'级别',field:'level',width:30,fun:level},
		          {name:'角色',field:'roleName',width:60},
		          {name:'登录名',field:'loginName',width:60},
		          {name:'姓名',field:'realName',width:60},
		          {name:'电话',field:'telephone',width:80},
		          {name:'电子邮件',field:'email',width:150},
		          {name:'状态',field:'enabled',width:30,fun:enabledfromat}
		          ],
	    operates:{width:130, value:[
	                                {name:'分配角色', fun:function(row){ drole(row.id) } },
	                                {name:'修改', fun:function(row){update('修改用户信息','pages/super/user/update.jsp?id='+row.id); } },
	                                {name:'禁用', fun:function(row){disable(row.id)},display:['enabled',true]},
	                                {name:'启用', fun:function(row){enableUsers(row.id)},display:['enabled',false]}]
	             }
	});

	$(".add").click(function(){
		update('新建用户信息','pages/super/user/update.jsp');
	});
});

function level(value){
	switch(value){
	case 0:return "厅级";
	case 1:return "市级";
	case 2:return "县级";
	}
}

function disable(id){
	var content={text:'是否禁用该用户?',fun:function(){
		 $.post("user/disable",{userId:id},function(json){
			   $.unblockUI();
			    refresh();
		   });
	}};
	$.confirm(content,'用户禁用');  
}

function enableUsers(id){
	   var content={text:'是否启用该用户?',fun:function(){
		   $.post("user/enable",{userId:id},function(json){
			   $.unblockUI();
			    refresh();
		   });
		}};
	$.confirm(content,'用户启用');
}

   function refresh(){
	   $("#table").dataRefresh();
	   $.unblockUI();
   }
   
   function closed(){
	   $.unblockUI();
   }
   
   function update(title,url){
	   $.layer({
		   width:368,
		   height:310,
		   isZY:true,
		   moveLayer:false,
		   parent:'keepsoft_1_',
           content:[{name:title,url:url}]
		});
   }

   function enabledfromat(value){
        if(value){return '启用';}
        else{ return '<font color="red">禁用</font>'}
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
   <span class="div">用户列表</span>
   <span class="add">新建用户信息</span>
</div>
<div style="margin: 10px auto 0; width: 690px;">
  <div id="table"></div>
</div>
</body>
</html>
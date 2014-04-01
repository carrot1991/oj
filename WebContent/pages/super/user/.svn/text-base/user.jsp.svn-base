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
<title>用户管理</title>
<base href="<%=basePath %>">
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/js/datagrid/datagrid.js"></script>
<link type="text/css" href="<%=path%>/js/layer/skin/css.css" rel="stylesheet" />
<link href="js/datagrid/skin/datagrid.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=path%>/js/utils.js"></script>
<script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>
<script type="text/javascript">
$(function(){
	$("#table").dataGrid({
		url : "user/findPager",
		headHeight : 30,
		bodyHeight:280,
		pageHeight : 30,
		rowHeight:25,
		isSerialNo:true,
		itemClass:['item1','item2'],
		fields:[  
		          {name:'账号',field:'uname',width:100},
		          {name:'角色',field:'rname',width:100},
		          {name:'注册电话',field:'tel',width:100},
		          {name:'状态',field:'isvalid',width:30,fun:validValue}
		          ],
	    operates:{width:150, value:[
	                                {name:'重置密码', fun:function(row){ resetPwd(row.uname); } },
	                                {name:'禁用', fun:function(row){disable(row.uname);},display:['isvalid',1]},
	                                {name:'启用', fun:function(row){enable(row.uname);},display:['isvalid',0]},
	                                {name:'删除帐号', fun:function(row){
                        				if(confirm('确认删除 '+row.uname+' ?')){ 
                        	            	$.get("user/remove",{'uname':row.uname},function(json){
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

	$(".add").click(function(){
		update('新建用户信息','pages/super/user/update.jsp');
	});
});
function validValue(val){
	return val==1?'正常':'无效';
}
function disable(u){
	var content={text:'是否禁用该用户?',fun:function(){
		 $.post("user/audit",{uname:u,flag:0},function(json){
			   $.unblockUI();
			    refresh();
		   });
	}};
	$.confirm(content,'用户禁用');  
}

function enable(u){
	   var content={text:'是否启用该用户?',fun:function(){
		   $.post("user/audit",{uname:u,flag:1},function(json){
			   $.unblockUI();
			    refresh();
		   });
		}};
	$.confirm(content,'用户启用');
}
function resetPwd(u){
	   var content={text:'确定重置密码?',fun:function(){
		   $.post("user/resetPwd",{uname:u},function(json){
			   $.unblockUI();
			    refresh();
		   });
		}};
	$.confirm(content,'重置密码');
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
   <span class="div">账号列表</span>
   
</div>
<div style="padding:25px;">
<input name="key" id="key" />
<input type="button" value="&nbsp;&nbsp;&nbsp;&nbsp;搜&nbsp;&nbsp;&nbsp;索&nbsp;&nbsp;&nbsp;&nbsp;"  id="searchButton"	onclick="search();" />
  <div id="table"></div>
</div>
</body>
</html>
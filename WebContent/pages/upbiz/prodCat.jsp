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
var typeDictStr='';
var bandDictStr='';
var newPropIDS='';
var newPropValues='';
var canDelete = false;
<c:if test="${sessionScope.OJ_JXC_USER.roleInfo=='2' || sessionScope.OJ_JXC_USER.roleInfo=='8'}">
	canDelete = true;
</c:if>

function clearList(){
	$('#prodFixDefList').html('');
	$('#prodDefList').html('');
}
function getCategory(){
	$.getJSON("type/findCategory",
			{random: Math.random()},function(jsonstr){
				if(jsonstr!=null){
					  var str='';
					  str += "<option value='---'>请选择大类</option>";
					  var json=eval(jsonstr); 
					   
					  for(var i=0;i<json.length;i++){
						  d = json[i];
						  str += "<option  value="+d.id+">"+d.title+"</option>";
					  }
					  $('#cateid').html(str);
				  }
	  });
}

$(function(){
	//getType('typeid');
	getCategory();
	}
);

function getType(cateid){
	$.getJSON("type/findType",{random: Math.random(),'cateid':cateid},function(jsonstr){
		  if(jsonstr!=null){
			  var str='';
			  str += "<option value='---'>请选择大类</option>";
			  var json=eval(jsonstr);//array
			   
			  for(var i=0;i<json.length;i++){
				  d = json[i];
				  str += "<option  value="+d.typeid+">"+d.title+"</option>";
			  }
			  $('#typeid').html(str);
			  
			  clearList();
			  
		  }
	  });
}
function getProdByBand(bandid){
	$.getJSON("proddef/findDefByBand",{'bandid':bandid,random: Math.random()},function(jsonstr){
		  if(jsonstr!=null){
			  var str='';
			  var json=eval(jsonstr);//array
			  str = "<table border=1><tr><td>型号</td><td>别名</td><td>是否停产</td><td>是否配件</td><td>配置</td><td></td></tr>";
			  for(var i=0;i<json.length;i++){
				  d = json[i];
				  str += "<tr>";
				  
				  str += "<td><input id='prod_nickname_"+d.id+"' value='" +  d.nickname + "' /></td>";
				  str += "<td><input id='prod_xinghao_"+d.id+"' value='" +  d.xinghao + "' /></td>";
				  str += "<td><input type=checkbox id='prod_tingchan_"+d.id+"' value='" +  d.tingchan + "' ";
				  if(d.tingchan==1){
					  str += ' checked';
				  }
				  str += "/></td>";
				  str += "<td><input type=checkbox id='prod_isass_"+d.id+"' value='" +  d.isass + "' ";
				  if(d.isass==1){
					  str += ' checked';
				  }
				  str += "/></td>";
				  str += "<td><input id='prod_config_"+d.id+"' value='" +  d.config + "' /></td>";
				  if(canDelete){
					  str += "<td><input type=button onclick='removeProd(" + d.id +")' value='删除'/>";
				  }else{
					  str += "<td>";
				  }
				  
				  str += "<input type=button onclick=\"updateProd("+ d.id + ")\" value='修改'/>";
				  
				  str += '</tr>';
			  }
			  str += "</table>";
			   
			  $('#prodDefList').html('');
			  
			  $('#prodDefList').html(str);
			  
		  }
	});
}
function getBandByType(tid){
	$.getJSON("band/findBandByType",{random: Math.random(),typeid:tid},function(jsonstr){
		  if(jsonstr!=null){
			  var str='';
			  var json=eval(jsonstr);//array
			  str += "<option value='---'>请选择品牌</option>";
			  for(var i=0;i<json.length;i++){
				  d = json[i];
				  str += "<option value="+d.bandid+">"+d.title+"</option>";
			  }
			  
			  $('#bandid').html(str);
			  
		  }
	});
	
	getFixPropByType(tid);
}
 
function getFixPropByType(tid){
	
	newPropIDS='';
	 
	$.getJSON("prodfixprop/listByType",{random: Math.random(),typeid:tid},function(jsonstr){
		  if(jsonstr!=null){
			  var str='';
			  var json=eval(jsonstr);
			  
			  str = "<table border=1>";
			  for(var i=0;i<json.length;i++){
				  d = json[i];
				  
				  newPropIDS+=d.propid+',';
				  str += '<tr><td>';
				  
				  str += d.name;
				  if(d.vlue!=''){ //如有预定，则使用。
					  var strs=d.vlue.split(",");//字符分割  
					  str += '<select id=prop_'+d.propid+'>';
					  for (var j=0;j<strs.length;j++ )    
				      {    
				          str += '<option value='+strs[j]+'>' + strs[j]  + '</option>';
				      }
					  str += '</select>';
				  }else{
					  str +=  '<input id=prop_'+d.propid+'/>'+'('+ d.vlue +')';
				  }
				  
				  str +=  d.unit + "</td>";
				  str += '</tr>';
			  }
			  str += "</table>";// + " <input type=button onclick='addProp()' value='保存属性'/>";
			  $('#prodFixDefList').html(str);
			  
		  }
	  });	
}

function addProd(){
	
	 var commitStrs = '';
	 var strs=newPropIDS.split(",");//字符分割  
	 var v ='';
	  for (var j=0;j<strs.length;j++ ){
		  
			  v = $('#prop_' + strs[j]).val();
			  if(v!=undefined){
			  	commitStrs += v +',';
			  }
		  
	  }
	  //alert('will commit values :' + commitStrs); 
	  var bandid = $('#bandid').val();
	  
		var param = {
				 'bandid':bandid,
				 nickname:$('#nickname').val(),
				 xinghao:$('#xinghao').val(),
				 tingchan:$('#tingchan').val(),
				 isass:$('#isass').val(),
				 //describ:$('#describ').val(),
				 keys:newPropIDS,
				 vlues:commitStrs
		};
	  
	 $.post("proddef/saveOrUpdateWithProp", param ,function(json){
      	  if(json.success=='success'){
      		$.alert('新增产品成功','产品定义');
      		  getProdByBand( bandid );
      	  }else{
      		  $.alert(json.success,'产品定义');
      	  }
      });
		
}
function updateProd(prodid){
	var nickname= $('#prod_nickname_'+prodid).val();
	var xinghao= $('#prod_xinghao_'+prodid).val();
	var tingchan= $('#prod_tingchan_'+prodid).val();
	var isass= $('#prod_isass_'+prodid).val();
	
	//var typeid=$('#typeid').val();
	
	$.post("proddef/saveOrUpdateSimple", {'id':prodid,'nickname':nickname,'xinghao':xinghao,'tingchan':tingchan,'isass':isass} ,function(json){
  	  if(json.success=='success'){
  		$.alert('成功','修改');
  		//getCategory();
  	  }else{
  		  $.alert(json.success,'修改');
  	  }
    });
}

function removeProd( prodid ){
	if(confirm("确认删除吗？")){
		var bandid =$('#bandid').val();
		$.post("proddef/remove", {id:prodid} ,function(json){
	  	  if(json.success=='success'){
	  		getProdByBand(bandid);
	  	  }else{
	  		  $.alert(json.success,'产品删除成功');
	  	  }
	    }); 
	}
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
   <span class="div">产品维护</span>
</div>

    <div class="div">
         <ul>
         <li><span>大类:</span>
			<select id="cateid" name="cateid" class="v" onchange='getType(this.value);'>
		    </select></li>
         	<li><span>子类:</span>
			<select id="typeid" name="typeid" class="v" onchange='getBandByType(this.value);'>
		    </select></li>
			<li><span>品牌:</span>
			<select id="bandid" name="bandid" class="v" onchange='getProdByBand(this.value);'>
			</select></li>
	        <li><span>优先级(数字越小越靠前):</span>
	         <input id="level" name="level" class="v" />
	        </li>
	        <li><span>我是配件:</span>
	         <input id="isass" type=checkbox value=1 name="isass" class="v" />
	        </li>	        
	        <li><span>产品名称:</span>
	         <input id="nickname" name="nickname" class="v" />
	        </li>
			<li><span>产品型号:</span>
	         <input id="xinghao" name="xinghao" class="v" />
	        </li>
			<li><span>是否停产:</span>
	         <input type=checkbox  id="tingchan" name="tingchan" value=1 class="v" />
	        </li>	        
	        <li><span>产品描述(可选):</span><br/>
	         <textarea id="describ" name="describ" class="v" cols=50 rows=5 ></textarea>
	        </li>
	        <li><span>配置:</span>
	         <div id="prodFixDefList">
	 		 </div>
	        </li>
	        <li><span>已有产品:</span>
	         <div id="prodDefList">
	 		 </div>
	        </li>
	        <input type="button" id="newProdDefButton" onclick='addProd()'  value="新增产品" />
	        
         </ul>
     </div>
      
	
     
</body>
</html>
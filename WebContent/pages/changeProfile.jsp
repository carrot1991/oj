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
<base href="<%=basePath %>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户信息更新</title>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/utils.js"></script>
<script type="text/javascript" src="js/jquery.blockUI.js"></script>
<script type="text/javascript" src="js/layer/layer.js"></script>
<link type="text/css" href="js/layer/skin/css.css" rel="stylesheet" />
<script type="text/javascript">
  $(function(){
		 var uname = '${sessionScope.OJ_JXC_USER.loginUser.uname}';
		 if(uname!=''){
			  
			  $.getJSON("region/getDict",{random: Math.random()},function(jsonstr){
				  if(jsonstr!=null){
					  	var str='<select readonly class=v name=regionid id=regionid>';
						var json=eval(jsonstr);
						   
						for(d in json){
							  str+="<option  value="+d+">"+json[d]+"</option>";
						}
						str+='</select>';
						
						$('#regionList').html(str);
				  }
			  });
	  
			  $.getJSON("userprofile/findByName",{random: Math.random(),'uname':uname},function(jsonstr){
			  if(jsonstr!=null){
				  var json=eval(jsonstr);
				  
				  var str='';
				  $(".v").each(function(){
					 var l=$(this).attr("name");
					  var v=json[l];
					 $(this).val(v);
					 str+=v+' ';
				  });
			  }
		  });
	  } 
	  
	  $("#save").click(function(){
          var param={};
          $(".v").each(function(){
        	  param[$(this).attr("name")]=$(this).val();
          });
          
          $.post("userprofile/update",param,function(json){
        	  if(json.success=='success'){
        		  //var m={text:'用户资料更新成功！',fun:function(){parent.window.refresh();}}
        		  $.alert('用户资料更新成功','用户资料更新',true);
        	  }else{
        		  $.alert(json.success,'用户资料更新');
        	  }
          });

	  });
  });
</script>
<style type="text/css">
   body,p,table,p,img {margin:0; padding:0;font-family:"宋体",Verdana, Arial,Sans; font-size:12px;}
   ul,li{margin:0; padding:0; list-style:none;}
   table,td {border-collapse:collapse;empty-cells:show;}
   body{background: #f3f3f3;}
   .div{padding-top: 15px;}
   li{padding:5px 0;}
   li span{width: 120px;float:left;text-align: right;padding-top: 5px;}
   li i{font-style: normal;float:left;padding-top: 2px;}
   li i input{margin-top: 5px;}
   .div #save{width:58px;height: 25px;background:url("js/layer/skin/images/icon.png") no-repeat 0 -214px;margin-left: 120px;cursor: pointer;float:left;  }
   .div #save.select{background-position:0 -239px;}
   .div #cancel{width:58px;height: 25px;background:url("js/layer/skin/images/icon.png") no-repeat 0 -114px;margin-left: 18px;cursor: pointer;float:left;   }
   .div #cancel.select{background-position:0 -139px;}
</style>
</head>
<body>
  <input type="hidden" class="v" name="id" id="id"  />
    <div class="div">
         <ul>
          <input class="v" type="hidden" name="id" value="0" id="id" /> 
          
	         <li><span>名称</span><input readonly class="v" type="text" name="uname" id="uname" value='${sessionScope.OJ_JXC_USER.loginUser.uname}' readonly /></li>
	         <li><span>缩写名称</span><input readonly class="v" type="text" name="memname"  id="memname"  /></li>
	         <li><span>英文名称</span><input readonly class="v" type="text" name="enname"   id="enname"  /></li>
	         <li><span>客户名称</span><input readonly class="v" type="text" name="custname"  id="custname"  /></li>
	         <li><span>地址</span><input readonly class="v" type="text" name="custaddr"  id="custaddr"  /></li>
	         <li><span>税号</span><input readonly class="v" type="text" name="custtaxno"  id="custtaxno"  /></li>
	         <li><span>开户行量</span><input readonly class="v" type="text" name="custbank"  id="custbank"  /></li>
	         <li><span>开户帐号</span><input readonly class="v" type="text" name="custbankno"  id="custbankno"  /></li>
	         <li><span>客户备注</span><input readonly class="v" type="text" name="custmem"  id="custmem"  /></li>
	         <li><span>QQ</span><input readonly class="v" type="text" name="custqq"  id="custqq"  /></li>
	         <li><span>联系人</span><input readonly class="v" type="text" name="contact"  id="contact"  /></li>
	         <li><span>手机1</span><input readonly class="v" type="text" name="mobile"  id="mobile"  /></li>
	         <li><span>手机2</span><input readonly class="v" type="text" name="mobile2"  id="mobile2"  /></li>
	         <li><span>电话1</span><input readonly class="v" type="text" name="phone"  id="phone"  /></li>
	         <li><span>电话2</span><input readonly class="v" type="text" name="phone2"  id="phone2"  /></li>
	          <li><span>区域</span>
                      <div id="regionList">
                      </div></li>
	        
			  
         </ul>
     </div>
     <div class="div">
         
     </div>
</body>
</html>
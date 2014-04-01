<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>IT产品信息查询系统 - 注册</title>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<script type="text/javascript" src="<%=path%>/js/datagrid/datagrid.js"></script>
<link type="text/css" href="<%=path%>/js/layer/skin/css.css" rel="stylesheet" />
<link href="<%=path%>/js/datagrid/skin/datagrid.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=path%>/js/utils.js"></script>
<script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>
<script type="text/javascript">
	if(window.top != window){
		window.top.location.reload();
	}
  $(function(){
      
	  $("[name = agree]:checkbox").bind("click", function () {
		  var cb = $("#agree");
		  if( cb.attr("checked")=='checked'){
			  alert('平台服务条款\n ...');
			  $("#loginBtn").removeAttr("disabled"); 
		  }else{
			  $("#loginBtn").attr('disabled',true); 
		  }
           
      });
      
	  $("#loginBtn").click(function(){
		  reg();
	   });

	   document.onkeydown = function(e){
	       var ev = document.all ? window.event : e;
		   if(ev.keyCode==13) {
			   loginIn();
		   }
	   }
  });
  
  function ch_length(str){ 
	  var len=0; 
	  for(var i=0;i<str.length;i++){ 
	  if(str.charAt(i)>'~'){len+=2;}else{len++;} 
	  } 
	  return len; 
  }
  
  function reg(){
	  if($("#uname").val()=='' || $("#pwd").val()=='' || $("#pwdCfm").val()==''){
		  $.alert("请输入帐号、密码、重复密码",'注册');
		  return;
	  }
	  if( ch_length($("#uname").val())<6
			  || ch_length($("#pwd").val())<6
			  || ch_length($("#pwdCfm").val())<6
			  || ch_length($("#uname").val())>20
			  || ch_length($("#pwd").val())>20
			  || ch_length($("#pwdCfm").val())>20){
		  $.alert("请输入账号、密码、重复密码且长度大于6位，小于20位",'注册');
		  return;
	  }
	  if( $("#pwd").val() != $("#pwdCfm").val() ){
		  $.alert("两次密码不一致",'注册');
		  return;
	  }
	  $.post("user/reg",{
		  uname:$("#uname").val(),
		  pwd:$("#pwd").val(),
		  role:$("#role").val(),
		  tel:$("#tel").val(),
		  enabled:false
		  },
		  function(data){
		  if(data.success=='success'){
			  //$.alert("注册成功",'注册');
			  alert('注册成功，账号开通中，请等待审核结果。联系电话：0571-56895901');
			  location.href="<%=basePath%>login.jsp";
			} else if(data.success=='1'){
				$.alert("用户名已存在",'注册');
			} else {
				$.alert("注册失败:" + data.success,'注册');
			}
		});
	}
 
//repeat-x ;
</script>
<style type="text/css">
body,div,span,ul,li,p,h1,h2,h3{ margin:0px; padding:0px;}
body{background:url(theme/login/images/bdbg.jpg);}
ul,li{ list-style:none;}
.clr{ clear:both; font-size:0; height:0; line-height:0}
.content{ width:100%; margin:0 auto;}
.contentbg{ width:550px; height:435px; margin:0 auto; padding-top:1px; padding-bottom:30px;} 
.login_info{ width:100%;height:435px; margin:0 auto; margin-top:150px; background:url(theme/login/images/bg3-reg.jpg);}

.login_info h2{ height:45px; text-align:center; padding-top:25px; padding-bottom:15px; font-family:"微软雅黑";font-size:24px; color:#2e528f;  font-weight:normal;}
div.login_list{ width:310px; height:435px; padding-left:120px;float:left; display:inline;}
div.login_list ul{width:300px;}
div.login_list ul li{height:32px; background:#5375a3;margin-top:10px; clear:both; border:1px solid #5375a3  }
div.login_list ul span{ padding:0 20px; height:32px;line-height:31px; text-align:center; display:inline-block; float:left; display:inline; color:#e5e5e5; font-family:"微软雅黑"; font-size:14px;}
 
.login_con input.textbg{ float:right; display:inline; width:190px; height:32px; line-height:32px; display:inline-block;border:none;background:#eff0f0; color:#656565; background:url(theme/login/images/inputbg.jpg) no-repeat; padding:0 6px;}
.login_con select.textbg{ float:right; display:inline; width:200px; height:32px; line-height:32px; display:inline-block;border:none;background:#eff0f0; color:#656565;}
.login_con li.bgnone{ background:none; text-align:left;color:#2e528f; font-size:12px;border:0; vertical-align:middle;}
.login_con li.bgnone input{ margin-top:2px;_margin-top:-4px;  margin-right:6px; _margin-right:0px; float:left; display:inline; }
ul.login_list{ float:left; width:287px; margin-right:10px;}
.btnlogin{ width:81px; height:77px; margin-top:10px; float:left; display:inline; background:url(theme/login/images/btnbg.jpg) no-repeat;cursor:pointer; border:none;}
.content p{ font-family:"微软雅黑",Arial, Helvetica, sans-serif; text-align:left; color:#c9c9c9; font-size:12px; padding-top:10px; }

.rem_ps{width:70px;_width:80px;margin-top:2px; padding-left:234px; _padding-left:227px; padding-top:6px; vertical-align: middle;}
.rem_ps input{float:left;display:inline;margin-right:4px;_margin-right:0px; margin-top:1px; margin-top:0px\9;_margin-top:-4px;}
div.login_list i{font-style: normal;color:#036}

div.error {
	    width: 150px;
	    border: 2px solid red;
	    background-color: yellow;
	    text-align: center;
	    float:left;
	}
	
	div.hide {
	    display: none;
	}
	
	.capslock{position:absolute;display:none; width:94px; height:18px; background:url(${basePath}/theme/login/images/capslockbg.png); font-size:12px; color:#105a63; font-family:Arial, Helvetica, sans-serif; text-align:center; line-height:12px; padding-top:12px;padding-left: 2px}
	.mima{font-size:12px;padding-top:0}
	button {width:70px; height:23px; line-height:25px; text-align:center; border:0px; cursor:pointer;}
	.bot{  font-size:12px; }
</style>
</head>
<body>
<!-- 
<table vlign=center align=center bgcolor="#5375a3">
<th>新用户注册</th>
<tr><td>帐号</td><td><input type="text" id="uname" name="uname"  class="textbg" /> </td></tr>
<tr><td>密码</td><td><input type="password" id="pwd" name="pwd" class="textbg"  /> </td></tr>
<tr><td>确认密码</td><td><input type="password" id="pwdCfm" name="pwdCfm" class="textbg"  /></td></tr>
<tr><td>联系电话</td><td><input type="text" id="tel" name="tel" class="textbg"  /></td></tr>
<tr><td>我是</td><td><select id="role" name="role" class="textbg">
                      	<option value="buy">销售商</option>
                      	<option value="sale">供货商</option>
                      </select></td></tr>
<tr><td> </td><td>
<input type="checkbox" id="agree" name="agree"  value="我同意协议条款"/>
                      <a target="_blank" href="license.html">协议条款文件</a>
</td></tr>
<tr><td> </td><td><input class="textbg" type="button" disabled id="loginBtn" value="&nbsp;&nbsp;注&nbsp;&nbsp;&nbsp;&nbsp;册&nbsp;&nbsp;"/></td></tr>
</table>

 -->
<div class="content">
 <div class="contentbg">
    <div class="login_info">
    <h2>IT产品信息查询系统 - 用户注册</h2>
          <div class="login_con">
              <div class="login_list">
                <ul>
                  <li>
                      <span>帐<i style=" width:1em; display:inline-block"></i>号</span>
                      <input type="text" id="uname" name="uname"  class="textbg" /> 
                  </li>
                  <li>
                      <span>密<i style=" width:1em; display:inline-block"></i>码</span>
                      <input type="password" id="pwd" name="pwd" class="textbg"  /> 
                  </li>  
                   <li>
                      <span>确认密码</span>
                      <input type="password" id="pwdCfm" name="pwdCfm" class="textbg"  />
                  </li>
                   <li>
                      <span>联系电话</span>
                      <input type="text" id="tel" name="tel" class="textbg"  />
                  </li>                  
				  <li>
                      <span>我是</span>
                      <select id="role" name="role" class="textbg">
                      	<option value="buy">销售商</option>
                      	<option value="sale">供货商</option>
                      </select>
                  </li>
                  <li>
                      <span> </span>
                      <span><input type="checkbox" id="agree" name="agree" value=""/>我同意协议条款</a></span>
                  </li>
                  <li>
                      <span> </span>
                      <input class="textbg" type="button" disabled id="loginBtn" value="&nbsp;&nbsp;注&nbsp;&nbsp;&nbsp;&nbsp;册&nbsp;&nbsp;"/>
      
                  </li> 
              </ul> 
               </div>          
               
          </div>
      </div>
      </div>
      
</div>
  
</body>
</html>
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
<LINK href="favicon.ico" type="image/x-icon" rel=icon>
<LINK href="favicon.ico" type="image/x-icon" rel="shortcut icon">
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>IT产品信息查询系统</title>
<meta http-equiv="X-UA-Compatible" content="IE=7" />
<script type="text/javascript" src="<%=path%>/js/jquery.js"></script>
<link type="text/css" href="<%=path%>/js/layer/skin/css.css" rel="stylesheet" />
<script type="text/javascript" src="<%=path%>/js/jquery.blockUI.js"></script>
<script type="text/javascript" src="<%=path%>/js/utils.js"></script>
<script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>
<script type="text/javascript">
	if(window.top != window){
		window.top.location.reload();
	}
  $(function(){
       
	  $("#loginBtn").click(function(){
		  loginIn();
	   });

	   document.onkeydown = function(e){
	       var ev = document.all ? window.event : e;
		   if(ev.keyCode==13) {
			   loginIn();
		   }
	   }
  });
  
  function loginIn(){
	  
	  if($("#loginName").val()=='' || $("#password").val()==''){
		  $.alert("请输入帐号、密码",'登录');
		  return;
	  }
	  
	  $.post("user/login",{uname:$("#loginName").val(),pwd:$("#password").val()},function(data){
		  if(data.success=='main'){
			  location.href="<%=basePath%>index.jsp";
			} else {
				//alert("登录失败");
				$.alert("登录失败",'登录');
			}
		});
	}

  function guestlogin(){
	  $.post("user/login",{uname:'guest',pwd:''},function(data){
		  if(data.success=='main'){
			  location.href="<%=basePath%>index.jsp";
			} else {
				//alert(data.success);
				$.alert(data.success,'登录');
			}
	  });
	}
  
  function reg(){
	  location.href="<%=basePath%>reg.jsp";
  }

</script>
<style type="text/css">
body,div,span,ul,li,p,h1,h2,h3{ margin:0px; padding:0px;}
body{background:url(theme/login/images/bdbg.jpg);}
ul,li{ list-style:none;}
.clr{ clear:both; font-size:0; height:0; line-height:0}
.content{ width:100%; margin:0 auto;}
.contentbg{ width:550px;  margin:0 auto; padding-top:1px; padding-bottom:30px;} 
.login_info{ width:100%;height:235px; margin:0 auto; margin-top:150px; background:url(theme/login/images/bg3.jpg) repeat-x ;}
.login_info h2{ height:45px; text-align:center; padding-top:25px; padding-bottom:15px; font-family:"微软雅黑";font-size:24px; color:#2e528f;  font-weight:normal;}
div.login_list{ width:310px; background:url(theme/login/images/pic01.jpg) no-repeat 35px 10px; padding-left:120px;float:left; display:inline;}
div.login_list ul{width:300px;}
div.login_list ul li{height:32px; background:#5375a3;margin-top:10px; clear:both; border:1px solid #5375a3  }
div.login_list ul span{ padding:0 20px; height:32px;line-height:31px; text-align:center; display:inline-block; float:left; display:inline; color:#e5e5e5; font-family:"微软雅黑"; font-size:14px;}
.login_con input.textbg{ float:right; display:inline; width:190px; height:32px; line-height:32px; display:inline-block;border:none;background:#eff0f0; color:#656565; background:url(theme/login/images/inputbg.jpg) no-repeat; padding:0 6px;}
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
	
.bot{  font-size:12px;  color:white}
</style>
</head>
<body>

 <div class="contentbg">
    <div class="login_info">
        <h2>IT产品信息查询系统</h2>
          <div class="login_con">
              <div class="login_list">
                <ul>
                  <li>
                      <span>用户名</span>
                      <input type="text" id="loginName" name="loginName"  onfocus="if(value==&quot;请输入用户名&quot;){this.value='';}" onblur="if(!value){value=defaultValue;}" class="textbg" />
                  </li>
                  <li>
                      <span>密<i style=" width:1em; display:inline-block"></i>码</span>
                      <input type="password"  id="password" name="password" class="textbg"  />
                  </li>                              
              </ul> 
               </div>          
              <input type="button"  class="btnlogin" id="loginBtn"/>
              <div class="clr"></div>
          </div>
      </div>
</div>

<span style="margin-left: 800px;"><button onclick="reg();" >注册</button>&nbsp;&nbsp;<button onclick="guestlogin();">游客</button>&nbsp;&nbsp;&nbsp;
      </span>
	<br/>	<br/>	<br/>  
	  <div align=center  class="bot"> 本系统适用以下浏览器： IE 8（或基于其内核）以上、 <a class=bot target=_blank href='https://www.google.com/intl/zh-CN/chrome/browser/'> Chrome </a>，<a class=bot target=_blank href='http://www.firefox.com.cn/'> Firefox </a>
	  </div>
</body>
</html>
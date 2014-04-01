//(function($) { 
//  var Class = function(setings){
//	  var config = this.config;
//	  this.config = $.extend({} , config , setings);
//	  this.adjust();
//  };
//  Class.gr = Class.prototype;
//  Class.gr.config = {
//	 width : 250,
//	 height: 100,
//	 parent:'keepsoft_',
//	 type:'iframe',
//	 titleHeight:29,
//	 showOverlay:true,
//	 isZY:false,
//	 content:null
//  };
//  Class.gr.adjust= function(){
//	  var config = this.config;
//	  alert(config.width);
//  };
//  
//  $.layerd = function(deliver){
//	  var o = new Class(deliver);
//  }
//})(jQuery);     


/**
 * Plugin: Jquery.layer Version: 1.0 Beta Update: 2013.03.5
 * import jquery.blockUI.js
 */
$.extend({
	defaults : {
		width : 250,
		height: 100,
		parent:'keepsoft_',
		type:'iframe',
		title:'系统提示',
		titleHeight:29,
		showOverlay:true,
		isZY:false,//
		closefun:null,
		moveLayer:true,
		icon:'tip',
		
		/*top ,left */
		/**
		 *
		 * type
		 * iframe:[{'name':'','url':''},{'name':'','url':''}]
		 * confirm:(yes){text:'',url:'',type:'get/post',data:{}}
		 * alert:null
		 *
		 */
		content:null
	},
	layer:function(options){
		var opts = $.extend({},$.defaults, options || {});
		var type=opts.type;
		if(type=='alert'||type=='confirm')
			type='tooltip';
        var _parent=opts.parent+type;
        $("."+_parent).remove();

		var _content=opts.content;
        var _layer=$('<div class="'+_parent+'" ></div>');
        var title=$('<div class="layertitle" ></div>');
       

        var wrapZY=$('<div class="wrapTitle"></div>');
        if(opts.isZY){
        	wrapZY.append("<div class=\"layerright\"></div>");
        	wrapZY.append("<div class=\"layerleft\"></div>");
        	wrapZY.append(title);
        }else
        	wrapZY=title;

        var closeSpan=$('<span class="closed"></span>');
        closeSpan.click(function(){
        	if(typeof opts.closefun=='function'){
        		(opts.closefun).call(this);
        	}
        	$.unblockUI();
        });
        title.append(closeSpan);
         
        switch(opts.type){
	        case 'iframe':
	        	var _src=null;
	            var ih=parseInt(opts.height-opts.titleHeight);
	            if(opts.offsetH)
	            	ih=ih-opts.offsetH;
	            var ii=1;
	        	$.each(_content,function(i,n){
	        		var span=$("<span></span>");
	        		if(opts.radius){
	        			for(var j=0;j<opts.radius;j++){
	        				span.append("<div class='r_"+j+"'></div>");
	        			}
	        			span.append("<div class='r_"+j+"'>"+n.name+"</div>");
	        		}else{
	        			span.append(n.name);
	        		}
	        		if(i==0){
	        			_src=n.url;
	        		
	        		}
                   if(n.show){
                	   _src=n.url;
	        			ii=i+1;
	        		}
	        		span.not( $(".move")[0]).click(function(){
	        			var ss='<iframe src="'+n.url+'" id="layerIframe" frameborder="0" name="layerIframe" width="100%" scrolling="yes" style="border:none;overflow-x:hidden;height:'+ih+'px;"></iframe>';
	        			_layer.find(".iframe").html(ss);
	        		//	layerIframe.location.href=n.url;
	        			title.find("span").removeClass("selected");
	        			$(this).addClass("selected");
	        		});
	        		title.append(span);
	        	 });
	        	title.find("span:eq("+ii+")").addClass("selected");
	        	_layer.append(wrapZY);
	        	_layer.append('<div class="iframe"><iframe src="'+_src+'" id="layerIframe" frameborder="0" name="layerIframe" width="100%" scrolling="yes" style="border:none;overflow-x:hidden;height:'+ih+'px;"></iframe></div>');
	        	break;
	        case 'alert':
	        case 'confirm':
	        	/*标题*/
	        	wrapZY.find(".layertitle").append("<span class=\"selected\">"+opts.title+"</span>");
	        	/*内容*/
	        	var div=$('<div class="tooltipcontent"></div>');
	        	var divWidth=opts.width-2;
	        	var divHeight=opts.height-31;
	        	div.css({width:divWidth+"px",height:divHeight+"px"});
	        	var wrapdiv=$('<div class="subwrap"><div class="layercontent"></div></div>');
	        	var icon=$("<span class='tipicon "+opts.icon+"'></span>");
	        	var tiptxt=$('<span class="tipcontent"></span>');
	        	tiptxt.width(divWidth-80);
	        	if(typeof opts.content=='string')
	        		tiptxt.empty().append(opts.content);
        	     else
        	    	tiptxt.empty().append(opts.content.text);
	        	div.append(tiptxt);
	          	wrapdiv.find(".layercontent").empty().append(icon);
	        	wrapdiv.find(".layercontent").append(tiptxt);
	          	div.append(wrapdiv);
	        	/*按钮*/
	        	var btnwrap=$('<div class="tooltipbtnwrap"></div>');
	        	if(opts.type=='confirm'){
	        		var qx=$('<span class="qx"></span>');
	        		qx.click(function(){
		        		$.unblockUI();
		        	});
		        	btnwrap.append(qx);
	        	}
	        	var qd=$('<span class="qd"></span>');
	        	qd.click(function(){
	        		if(typeof opts.content.fun=='function'){
       			       (opts.content.fun).call(this);
        		     }
	        		$.unblockUI();
	        	});
	        	btnwrap.append(qd);
	        	
	      
	        	opts.height=105+opts.titleHeight;
	        	_layer.append(wrapZY);
	        	_layer.append(div);
	        	_layer.append(btnwrap);
	        	break;
        }
        var marginLeft=parseInt(opts.width/2)+1;
		var marginTop=parseInt(opts.height/2)+1;
		var winWidth=parseInt($(window).width()/2);
		var winHeight=parseInt($(window).height()/2);
		var left=opts.left?opts.left:winWidth-marginLeft;
		var top=opts.top?opts.top:winHeight-marginTop;
        var _css={cursor:'auto' ,width:opts.width+"px",height:opts.height+"px", top:  top+ 'px',left: left + 'px'};
        if(opts.right)
        	_css={cursor:'auto' ,width:opts.width+"px",height:opts.height+"px", top:  top+ 'px',left:'',right:opts.right+'px'};
        if(opts.border)
        	_css.border=opts.border;
        if(opts.backgroundColor)
        	_css.backgroundColor=opts.backgroundColor;
        $.blockUI({ message: _layer,showOverlay: opts.showOverlay, css: _css });
        
        
        if(opts.moveLayer){
        	   var moveTitle=$("<span close=\"move\" style=\"cursor: move;\"></span>");
               title.append(moveTitle);
               var moveW=180;
               title.find("span").each(function(){
               	if($(this).attr("class")!='move'){
               		moveW=moveW+$(this).width();
               	}
               });
               moveTitle.css("width",(opts.width-moveW)+"px");
               
               var layerE, ismove;
               var moveX, moveY, move, setY = 0;
               var w = $(window);
               moveTitle.on('mousedown', function(M){
               	M.preventDefault();
               	ismove = true;
               	layerE = $(".blockMsg");
               	var xx = layerE.offset().left, yy = layerE.offset().top, ww = layerE.width() - 6, hh = layerE.height() - 6;
               	if(!$('#gr_moves')[0]){
               		$('body').append('<div id="gr_moves" class="xubox_moves" style="left:'+ xx +'px; top:'+ yy +'px; width:'+ ww +'px; height:'+ hh +'px; z-index:300000000"></div>');
               	}
               	move = $('#gr_moves');
               	moveX = M.pageX - move.position().left;
       			moveY = M.pageY - move.position().top;
       			layerE.css('position') !== 'fixed' || (setY = w.scrollTop());
               });
               $(document).mousemove(function(M){			
           		if(ismove){
           			M.preventDefault();
           			var x = M.pageX - moveX;
           			if(layerE.css('position') === 'fixed'){
           				var y = M.pageY - moveY;	
           			}else{
           				var y = M.pageY - moveY;	
           			}
           			move.css({left: x, top: y});									
           		}					  						   
           	}).mouseup(function(){
           		if(ismove){
           			if(parseInt(layerE.css('margin-left')) == 0){
           				var lefts = parseInt(move.css('left'));
           			}else{
           				var lefts = parseInt(move.css('left')) + (-parseInt(layerE.css('margin-left')))
           			}
           			layerE.css('position') === 'fixed' || (lefts = lefts - layerE.parent().offset().left);
           			layerE.css({left: lefts, top: parseInt(move.css('top')) - setY});
           			move.remove();
           		}
           		ismove = false;
           	});
        } 
	},
	_hover:function(el,c1,c2){
		 el.hover(function() {
				$(this).removeClass(c1).addClass(c2);
			}, function() {
				$(this).removeClass(c2).addClass(c1);
			});
	},
	_width:function(){
		return window.innerWidth||document.documentElement.clientWidth;
	},
	_height:function(){
	 return window.innerHeight||document.documentElement.clientHeight;
	},
	layerResize:function(){
		if(typeof $(".blockMsg")=='undefined')
			return;
		var width=$(".blockMsg").width();
		var height=$(".blockMsg").height();
		var position = $(".blockMsg").position();
		var marginLeft=parseInt(width/2)+1;
	    var marginTop=parseInt(height/2)+1;
		var winWidth=parseInt($(window).width()/2);
		var winHeight=parseInt($(window).height()/2);
		var left=winWidth-marginLeft;
		var top=winHeight-marginTop;
		 if(position.left==0){
			$(".blockMsg").css({"width":$(window).width()+"px","height":($(window).height()-position.top)+"px"});
			if(typeof $("#layerIframe")!='undefined'){
				var ih=parseInt($(window).height()-$(".blockMsg .title").height());
				$("#layerIframe").height(ih);
			}
		}else if($._width()>width||$._height()>height){
			$(".blockMsg").css({"left":left+"px","top":top+"px"});

		}
	}
});

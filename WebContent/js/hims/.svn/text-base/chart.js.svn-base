/*画图*/
/**
 * 时间对象的格式化;
 */
Date.prototype.format = function(format) {
	/*
	 * eg:format="YYYY-MM-dd hh:mm:ss";
	 */
	var o = {
		"M+" : this.getMonth() + 1, // month
		"d+" : this.getDate(), // day
		"h+" : this.getHours(), // hour
		"m+" : this.getMinutes(), // minute
		"s+" : this.getSeconds(), // second
		"q+" : Math.floor((this.getMonth() + 3) / 3), // quarter
		"S" : this.getMilliseconds()
	// millisecond
	};

	if (/([y|Y]+)/.test(format)) {
		format = format.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	}

	for ( var k in o) {
		if (new RegExp("(" + k + ")").test(format)) {
			format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k]
					: ("00" + o[k]).substr(("" + o[k]).length));
		}
	}
	return format;
};

/**
 * chart中文语言
 */
var HighChartLangZh_CN = {
	months : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月',
			'十一月', '十二月' ],
	shortMonths:['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月',
	 			'十一月', '十二月'],
	weekdays : [ '周日', '周一', '周二', '周三', '周四', '周五', '周六' ],
	rangeSelectorZoom : '缩放',
	downloadPNG : '下载为PNG图片',
	downloadJPEG : '下载为JPEG图片',
	downloadPDF : '下载为PDF',
	downloadSVG : '下载为SVG',
	exportButtonTitle : '导出',
	loading : '加载....',
	printButtonTitle : '打印',
	rangeSelectorFrom : '从',
	rangeSelectorTo : '到',
	resetZoom : '重置缩放',
	resetZoomTitle : '重置到缩放级别1'
};

var chart = null;
Highcharts.setOptions({lang:HighChartLangZh_CN,global: {    
	useUTC: false 
}});
var options = {
	chart: {
		renderTo: 'chart_div',
		zoomType: 'x'
	},
	credits:{
		enabled:false
	},
	title: {
		text: ''
	},
	subtitle: {
		//text: 'xx地方'
	},
	tooltip:{
		shared: true,
		crosshairs: true
	},
	xAxis: [{//降雨图
		type:'datetime',
		//tickInterval :60*60*1000,//1h
		labels:{
			formatter:function(){
				var date = new Date(this.value);
				return date.format("yyyy-MM-dd hh");
			}//,
			//rotation : 10
			//step:10
		},
		style: {
			color: '#FFF',
			fontWeight: 'bold'
		},
		tickPixelInterval : 200//,
	}],
	yAxis: [{ // Primary yAxis
		labels: {
			formatter: function() {
				return this.value +'';
			},
			style: {
				color: '#89A54E'
			}
		},
		title: {
			text: '降水量(mm)',
			style: {
				color: '#89A54E'
			}
		},
		endOnTick:true,
		opposite: true,
		reversed :true,
		tickPixelInterval :100,
		maxPadding:5
	}, { // Secondary yAxis
		min:0,
		title: {
			text: '流量(m<sup>3</sup>/s)',
			style: {
				color: '#4572A7'
			}
		},
		labels: {
//			formatter: function() {
//				return this.value +'(立方米/秒)';
//			},
			style: {
				color: '#4572A7'
			}
		},
		endOnTick:true,
		//tickInterval:1000,
		tickPixelInterval:100,
		maxPadding:0.5
	}],
	legend: {
		//layout: 'hr',
		align: 'top',
		verticalAlign: 'top',
		x: 30,
		y: 0,
		borderWidth: 0
	},
	exporting:{
		//url:'modelResult/export'
		enabled:false
	},
	plotOptions: {
		series: {
               //enableMouseTracking: false //去掉mousetrack可能性能会好些。
           },
		column: {
			//pointWidth: 20
		},
		spline: {
			lineWidth: 2,
			states: {
				hover: {
					lineWidth: 3
				}
			},
			marker: {
				enabled: false,
				states: {
					hover: {
						enabled: false,
						symbol: 'circle',
						radius: 5,
						lineWidth: 1
					}
				}
			}
		},
		area: {
			lineWidth: 2,
			states: {
				hover: {
					lineWidth: 3
				}
			},
			marker: {
				enabled: false,
				states: {
					hover: {
						enabled: true,
						symbol: 'circle',
						radius: 5,
						lineWidth: 1
					}
				}
			}
		}
	}
};

function drawChart(data){
	var plotLines = data.plotLines;
	if(!!plotLines){
		var genedPlotLines=new Array();
		$.each(plotLines,function(i,n){
			var plotLine = { // Light air
                    width:2,
					value:n,
                    color: '#FF0000'
                }
			genedPlotLines.push(plotLine);
		});
		options.yAxis[1]['plotLines'] = genedPlotLines;
	}
	
	options.series = data.series;
	
	if(!!chart){
		chart.destroy;
	}
    chart = new Highcharts.Chart(options);
}

function drawModelResultChart(planId,sbcd,table){
	$.getJSON('modelResult/fetchChart',{
		'planId':planId,
		'sbcd':sbcd
	}, function(data) {
		if(table){
			$("#table").dataRefresh({
				"planId":planId,
				"sbcd":sbcd
			});
		}
		drawChart(data);
	});
}

/*表单*/
function getTable(){
	$("#table").dataGrid({
		url : "modelResult/fetchGrid",
		headHeight : 20,
		bodyHeight:100,
		rowHeight:18,
		isPage:false,
		flow:$(".floodInfo span"),
		//pageHeight:20,
		fields : [{
			name : '时间',
			field : 'occurTime',
			width : 110
			//fun : formatDate
		}, {
			name : '降水量(mm)',
			field : 'pcp',
			width : 90,
			fun : formatNum
		}, {
			name : '实际流量(m³/s)',
			field : 'qbos',
			width : 100,
			fun : formatNum
		}, {
			name : '模拟流量(m³/s)',
			field : 'flow',
			width : 100,
			fun : formatNum
		},  {
			name : '实际水位(m)',
			field : 'z',
			width :90,
			fun : formatNum
		}, {
			name : '模拟水位(m)',
			field : 'z',
			width : 90,
			fun : formatNum
		}]
	});
}

//function formatFlow(value){
//	if (value === undefined) {
//		return "-";
//	}else{
//		return ((value*24*60*60)/10000).toFixed(2);
//	}
//}

function formatNum(value) {
	if (value==null||value === undefined) {
		return "-";
	}else{
		return value.toFixed(3);
	}
}

function formatDate(value) {
	//return value;
	return $.dateFormat(value,'yyyy-MM-dd hh');
}




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
		type:'linear',
		//tickInterval :60*60*1000,//1h
		labels:{
			formatter:function(){
				return this.value*100;
			}
		},
		title:{text:'频率(%)'},
		style: {
			color: '#FFF',
			fontWeight: 'bold'
		},
		tickPixelInterval : 100//,
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
			text: '',
			style: {
				color: '#89A54E'
			}
		},
		endOnTick:true,
		opposite: true,
		reversed :true,
	//	tickPixelInterval :100,
		maxPadding:5
	}, { // Secondary yAxis
		min:0,
		title: {
			text: '雨量(mm)',
			style: {
				color: '#4572A7'
			}
		},
		labels: {
			style: {
				color: '#4572A7'
			}
		},
	//	endOnTick:true,
		//tickInterval:1000,
		//tickPixelInterval:100,
	//	maxPadding:0.5
	}],
	legend: {
		//layout: 'hr',
		align: 'top',
		verticalAlign: 'top',
		//x: 30,
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








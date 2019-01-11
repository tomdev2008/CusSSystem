/**-----------测试用mock数据-------------- */
(function(){
	
	var area = echarts.init(document.getElementById("area"));
	
	option = {
    title: {
        text: '堆叠区域图'
    },
    tooltip : {
        trigger: 'axis' //是否出发数据显示，默认触发，item与axis
    },
    legend: {
        data:['邮件营销','联盟广告']
    },
    toolbox: {
        feature: {
            saveAsImage: {}
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,//设置画布是否把数据贴近两端
            data : ['周一','周二','周三','周四','周五','周六','周日']
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'邮件营销',
            type:'line',
            stack: '总量',
            areaStyle: {normal: {}},
            data:[120, 132, 101, 134, 90, 230, 210]
        },
        {
            name:'联盟广告',
            type:'line',
            stack: '总量',
            areaStyle: {normal: {}},
            data:[220, 182, 191, 234, 290, 330, 310]
        }
    ]
};

area.setOption(option);
})();


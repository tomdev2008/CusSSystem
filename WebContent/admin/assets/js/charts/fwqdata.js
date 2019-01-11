/**-----------通过websocket获取数据-------------- */
// import {getdatearr,getcpudataarr,getramdataarr} from './getdata.js'
var area = echarts.init(document.getElementById("area"));
let datearr = ['6:00', '7:00', '8:00', '9:00', '10:00', '11:00', '12:00', '13:00'];
let cpudataarr = [0, 10, 80, 50, 40, 100, 50, 50];
let ramdataarr = [40, 50, 35, 15, 45, 90, 10, 10];
let onineSum = [40, 50, 35, 15, 45, 90, 10, 10];

option = {
    title: {
        text: '服务器实时状态'
    },
    tooltip: {
        trigger: 'axis' //是否出发数据显示，默认触发，item与axis
    },
    legend: {
        data: ['CPU使用率', '内存使用率']
    },
    grid: { // 设置画布图表大小
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: [
        {
            type: 'category',
            boundaryGap: false,//设置画布表格是否贴近俩端，默认贴近
            data: datearr
        }
    ],
    yAxis: [{
        type: 'value',
        axisLabel: {
            show: true,
            interval: 'auto',
            formatter: '{value} %'
        },
        show: true
    }],
    series: [
        {
            name: 'CPU使用率',
            type: 'line',    //设置显示格式
            stack: '总量',
            areaStyle: { normal: {} },
            data: cpudataarr
        },
        {
            name: '内存使用率',
            type: 'line',
            stack: '总量',
            areaStyle: { normal: {} },
            data: ramdataarr
        }
    ]
};

function setdatearr(date_str) {
    datearr = arroption(datearr);
    datearr[8] = date_str;
}

function setcpudataarr(cpudata_str) {
    cpudataarr = arroption(cpudataarr);
    cpudataarr[8] = cpudata_str;
}

function setramdataarr(ramdata_str) {
    ramdataarr = arroption(ramdataarr);
    ramdataarr[8] = ramdata_str;
}

function setonineSum(onineSum_str){
    onineSum = arroption(onineSum);
    onineSum[8] = onineSum_str;
}

// 数据前移
function arroption(datalist) {
    for (let i = 0; i < datalist.length; i++) {
        if (i != datalist.length - 1) {
            datalist[i] = datalist[i + 1];
        }
    }
    return datalist;
}

area.setOption(option);

function addMessage(msg) {
    setdatearr(msg.time);
    setcpudataarr(msg.cpuRation);
    setramdataarr(msg.ramRation);
    setonineSum(msg.onineSum);
    area.setOption(option);
}
setInterval(function () {
    var url = parent.localurl123+"/admin/serverStatus";
	$.ajax({
        type: "post",
        url: url,
        data: {},
        cache: false,
        async : false,
        dataType: "json",
        success: function (data ,textStatus, jqXHR)
        {
            // var obj = eval('(' + data.data + ')');
            addMessage(data);
        },
        error:function (XMLHttpRequest, textStatus, errorThrown) {      
//            alert("ajax请求失败！");
        }
     });
}, 5000);
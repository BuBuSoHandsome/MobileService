var prefix = ctx + "system/statistics";

$(function() {
    formCtrl();
    provinceOrder();
    orderStatus();
    hourOrder();
    orderTrend(new Date().getFullYear());


});
//表单控制
function formCtrl(){
    layui.use('laydate', function(){
        var laydate = layui.laydate;
        /*全国各省订单统计时间选择器事件*/
        laydate.render({
            elem: '#order-date',
            max:new Date().getTime(),
            theme: 'molv',
            type:'month',
            calendar:true,
            done:function(value,date){
                provinceOrder(value);
            }
        });
        /*订单状态统计时间选择器事件*/
        laydate.render({
            elem:'#order-status-date',
            max:new Date().getTime(),
            theme: 'molv',
            type:'month',
            done:function(value,date){
                orderStatus(value);
            }
        });
        /*下单时间段统计时间选择器事件*/
        laydate.render({
            elem:'#hour-date',
            max:new Date().getTime(),
            range: true,
            theme: 'molv',
            done:function(value,date1,date2){
                var d = new Date(new Date(date2.year+'-'+date2.month+ '-'+date2.date).getTime()+ 60*60*24*1000);
                var endTime = JSON.stringify(date2)=='{}'? '':d.getFullYear()+'-'+ (d.getMonth()+1)+'-'+(d.getDate());
                hourOrder(value.split(' - ')[0],endTime);
            }
        });
        /*订单成交统计选择器事件*/
        laydate.render({
            elem:'#orderTrend-date',
            max:new Date().getTime(),
            type:'year',
            theme: 'molv',
            value:new Date(),
            done:function(value,date){
                var provinceName=  $('#provinceSelect').find('option:selected').val() == ''?'':$('#provinceSelect').find('option:selected').text();
                var provinceId = $('#provinceSelect').val();
                orderTrend(value,provinceId,provinceName)
            }
        });

        /*订单成交统计省份选择器事件*/
        $('#provinceSelect').select2({width:'100'});
        $('#provinceSelect').on('select2:select', function (e) {
            var provinceName=  $(this).find('option:selected').val() == ''?'':$(this).find('option:selected').text();
            var provinceId = $(this).val();
            var year = $('#orderTrend-date').val();
            orderTrend(year,provinceId,provinceName)
        });

    });
}

function isData(obj,text){
    $(obj).html('<div class="noData">'+ text +'无数据！</div>');
    $(obj).removeAttr('_echarts_instance_');
    return false;
}

function provinceOrder(value){
    $.ajax({
        url:prefix + '/provinceOrder',
        type:'get',
        data: {date:value},
        success:function(data){
            if(data.length == 0)return isData('#provinceOrderChart','各地区订单');
            var provinceName = [],provinceNum = [];
            for(var i=0; i<data.length; i++){
                provinceName.push(data[i].province);
                provinceNum.push(data[i].count);
            }
            provinceOrderChart(provinceName,provinceNum);
        }
    });
}
function provinceOrderChart(provinceName,provinceNum){
    var provinceOrderChart = echarts.init(document.getElementById('provinceOrderChart'),'macarons');
    var option = {
        xAxis: {
            type: 'category',
            axisLabel: {
                interval: 0,
                rotate: 30
            },
            data: provinceName
        },
        yAxis: {
            type: 'value'
        },
        grid:{
            top:20,
            left:30,
            right:30
        },
        series: [{
            data: provinceNum,
            type: 'bar',
            showBackground: true,
            label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            },
            /*backgroundStyle: {
                color: 'rgb(220, 220, 220)'
            },*/
            itemStyle: {
                normal: {
                    color: new echarts.graphic.LinearGradient(
                        0, 0, 0, 1, // 相当于2点的位置 A(0, 0)  B(1, 0)
                        [
                            {offset: 0, color: '#5ee4f8'}, // 柱图渐变色
                            {offset: 0.5, color: '#2cc3f8'}, // 柱图渐变色
                            {offset: 1, color: '#1e97f8'}, // 柱图渐变色
                        ]
                    )
                },
                emphasis: {
                    color: new echarts.graphic.LinearGradient(
                        0, 0, 0, 1,
                        [
                            {offset: 0, color: '#1e97f8'}, // 柱图高亮渐变色
                            {offset: 0.7, color: '#2cc3f8'}, // 柱图高亮渐变色
                            {offset: 1, color: '#5ee4f8'} // 柱图高亮渐变色
                        ]
                    )
                }
            },
        }]
    };
    provinceOrderChart.setOption(option);
    $(window).resize(provinceOrderChart.resize);
}

function orderStatus(date){
    $.ajax({
        url:prefix + '/orderStatus',
        type:'get',
        data: {date:date},
        success:function(data){
            if(data.length == 0)return isData('#orderStatusChart','');
            var statusData = [];
            for(var i=0; i<data.length; i++){
                statusData.push({name:data[i].statusName,value:data[i].num});
            }
            orderStatusPie(statusData)


        }
    })
}
function orderStatusPie(statusData){
    var orderStatusChart = echarts.init(document.getElementById('orderStatusChart'),'macarons');
    var option = {
        tooltip: {
            trigger: 'item',
            formatter: '{a} <br/>{b} : {c} ({d}%)'
        },
        legend: {
            type: 'scroll',
            orient: 'horizontal',
            bottom: 0
        },
        series: [
            {
                name: '订单状态',
                type: 'pie',
                radius: '70%',
                center: ['50%', '50%'],
                label: {
                    normal: {
                        formatter: '{b}:{c} ({d}%)',
                        position: 'outside'
                    }
                },
                data: statusData,

                emphasis: {
                    itemStyle: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }
        ]
    };

    orderStatusChart.setOption(option);
    $(window).resize(orderStatusChart.resize)
}

function hourOrder(beginTime,endTime){
    $.ajax({
        url:prefix + '/getHoursData',
        type:'get',
        data: {beginTime:beginTime,endTime:endTime},
        success:function(data){
            if(data.length == 0)return isData('#hourChart','各时间段');
            var hours = [],num = [];
            for(var i=0; i<data.length; i++){
                hours.push(data[i].hour + '点');
                num.push(data[i].num);
            }
            hourOrderChart(hours,num);
        }
    });
}
function hourOrderChart(hours,num){
    var hourChart = echarts.init(document.getElementById('hourChart'),'macarons');
    var option = {
        xAxis: {
            type: 'category',
            axisLabel: {
                interval: 0,
                rotate: 40
            },
            data: hours
        },
        yAxis: {
            type: 'value'
        },
        grid:{
            top:20,
            left:30,
            right:30
        },
        series: [{
            data: num,
            type: 'bar',
            showBackground: true,
            label: {
                normal: {
                    show: true,
                    position: 'top'
                }
            },
            backgroundStyle: {
                color: 'rgb(220, 220, 220)'
            }
        }]
    };
    hourChart.setOption(option);
    $(window).resize(hourChart.resize);
}

function orderTrend(year,provinceId,provinceName){
    $.ajax({
        url:prefix + '/orderTrend',
        type:'get',
        data: {year:year,provinceId:provinceId,provinceName:provinceName},
        success:function(data){
            console.log(data);
            if(data.length == 0)return isData('#orderTrend','订单');
            var date = [],num = [];
            for(var i=0; i<data.length; i++){
                date.push(data[i].date);
                num.push(data[i].num);
            }
            orderTrendChart(date,num);
        }
    });
}
function orderTrendChart(date,num){
    var orderTrend = echarts.init(document.getElementById('orderTrend'),'macarons');
    var option = {
        toolbox: {
            left:'20px',
            feature: {
                saveAsImage: {show: true}
            }
        },
       /* legend: {
            bottom:0,
            data: ['订单量', '增长率']
        },*/
        xAxis: [
            {
                type: 'category',
                data: date,
                axisPointer: {
                    type: 'shadow'
                },
                axisLabel: {
                    interval: 0,
                    rotate: 30
                },
                axisLine:{
                    show:true,
                    onZero: true,
                },
                yAxisIndex: 1,
            }
        ],
        yAxis: [
            {
                type: 'value',
                name: '订单量',
                axisLabel: {
                    formatter: '{value}'
                }
            },
            /*{
                type: 'value',
                name: '增长率',
                axisLabel: {
                    formatter: '{value}%'
                }
            },*/

        ],
        series: [
            {
                name: '订单量',
                type: 'bar',
                label: {
                    normal: {
                        show: true,
                        position: 'inside'
                    }
                },
                data: num,
            },
            /*{
                name: '增长率',
                type: 'line',
                smooth: true,
                yAxisIndex: 1,
                label: {
                    normal: {
                        show: true,
                        position: 'top',
                        formatter: '{c}%'
                    }
                },
                data: num.map((item,i) => {
                    if(i ==0 || item == 0){
                        return item = 0;
                    }else{
                        return  (((item - num[i-1])/num[i-1])*100).toFixed(1);
                    }
                })
            },*/
        ]
    };
    orderTrend.setOption(option);
    $(window).resize(orderTrend.resize);


}


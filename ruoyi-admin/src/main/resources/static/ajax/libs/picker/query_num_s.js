function IdentityCodeValid(code) {
    var city = {
        11 : "北京",
        12 : "天津",
        13 : "河北",
        14 : "山西",
        15 : "内蒙古",
        21 : "辽宁",
        22 : "吉林",
        23 : "黑龙江 ",
        31 : "上海",
        32 : "江苏",
        33 : "浙江",
        34 : "安徽",
        35 : "福建",
        36 : "江西",
        37 : "山东",
        41 : "河南",
        42 : "湖北 ",
        43 : "湖南",
        44 : "广东",
        45 : "广西",
        46 : "海南",
        50 : "重庆",
        51 : "四川",
        52 : "贵州",
        53 : "云南",
        54 : "西藏 ",
        61 : "陕西",
        62 : "甘肃",
        63 : "青海",
        64 : "宁夏",
        65 : "新疆",
        71 : "台湾",
        81 : "香港",
        82 : "澳门",
        91 : "国外 "
    };
    var tip = "";
    var pass = true;
    var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
    if (!code || !reg.test(code)) {
        tip = "身份证号格式错误";
        pass = false;
    }
    else if (!city[code.substr(0, 2)]) {
        tip = "身份证号格式错误";
        pass = false;
    } else {
        //18位身份证需要验证最后一位校验位
        if (code.length == 18) {
            code = code.split('');
            //∑(ai×Wi)(mod 11)
            //加权因子
            var factor = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2];
            //校验位
            var parity = [1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2];
            var sum = 0;
            var ai = 0;
            var wi = 0;
            for (var i = 0; i < 17; i++) {
                ai = code[i];
                wi = factor[i];
                sum += ai * wi;
            }
            var last = parity[sum % 11];
            if (parity[sum % 11] != code[17]) {
                tip = "身份证号格式错误";
                pass = false;
            }
        }
    }
    if (!pass) {
        alert(tip);
        return true;
    }
}
function checkName(str) {
    var reg = /^[\u4e00-\u9fa5]{2,4}$/i;
    if (!reg.test(str)) {
        return true
    }
}
function pikc_num(target) {
    $('.weui_mask').removeClass('weui_mask_visible');
    $('.weui-custom-pop').removeClass('weui-dialog-visible');
    $("#serial_number").attr("value", $(target).text());
}
function num_page(numarray, current_page) {
    if (current_page == 0) {
        var html = "";
        for (var i = 0; i < 10; i += 2) {
            if (numarray[i] == undefined) {
                numarray[i] = ''
            }
            if (numarray[i + 1] == undefined) {
                numarray[i + 1] = ''
            }
            html += '<tr><td style="text-align:center"><a href="javascript:void(0);" onclick="pikc_num(this);" class="weui_btn weui_btn_default">' + numarray[i] + '</a></td><td style="text-align:center"><a href="javascript:void(0);" onclick="pikc_num(this);" class="weui_btn weui_btn_default">' + numarray[i + 1] + '</a></td></tr>';
        }
        var cur_page = parseInt(current_page) + 1;

        $('#tbody').html(html);
        $('.weui-custom-ft').show();
        $('#num').val(numarray);
        $('#cur_page').val(cur_page);
        $.hideLoading();

    } else {
        var numarray = $('#num').val();
        var cur_page = $('#cur_page').val();
        numarray = numarray.split(',');
        if ((cur_page * 10) < numarray.length) {
            var html = "";
            for (var i = (cur_page * 10); i < ((cur_page * 10) + 10); i += 2) {

                if (numarray[i] == undefined) {
                    numarray[i] = ''
                }
                if (numarray[i + 1] == undefined) {
                    numarray[i + 1] = ''
                }
                html += '<tr><td style="text-align:center"><a href="javascript:void(0);" onclick="pikc_num(this);" class="weui_btn weui_btn_default">' + numarray[i] + '</a></td><td style="text-align:center"><a href="javascript:void(0);" onclick="pikc_num(this);" class="weui_btn weui_btn_default">' + numarray[i + 1] + '</a></td></tr>';
            }
            var cur_page = parseInt(cur_page) + 1;
            $('#tbody').html(html);
            $('.weui-custom-ft').show();
            $('#num').val(numarray);
            $('#cur_page').val(cur_page);
            $.hideLoading();
        } else {
            var cur_page = 0;
            $('#tbody').html(html);
            $('.weui-custom-ft').show();
            $('#cur_page').val(cur_page);
            $.hideLoading();
            //$('#fir_num').empty();
            showmark();
        }
    }
}

function showmark(searchValue) {
    var homeProvinceCode = $('#homeProvinceCode').val();
    var homeCityCode = $('#homeCityCode').val();
    if (homeProvinceCode == '' || homeProvinceCode == undefined) {
        alert('请选择号码归属地');
        return false;
    }
    $('#pick_number_mask_alert').addClass('weui_mask_visible');
    $('#pick_number_alert').addClass('weui-dialog-visible');
    $('#get_num_hint').html("获取号码中，请稍后");
    var card_type = $('#card_type').val();
    var postData = {
        cityCode: homeCityCode,
        provinceCode: homeProvinceCode,
        goodsId:'981610241535',
        searchCategory: '3',
        searchValue :searchValue?searchValue:''
    }
    $.ajax({
        url: "/CuccMobile/selectNum",
        type: "post",
        contentType: 'application/json',
        data:  JSON.stringify(postData),
        success: function(data) {
            //console.log(JSON.stringify(data))
            var current_page = 0;
            if(data.length > 0 ){
                $('#get_num_hint').empty();
                num_page(data, current_page);
            } else {
                var tips = '<p>当前选号人数较多，请关闭选号窗重试</p>'
                if(searchValue){
                    tips = '<p>抱歉没有匹配的号码</p>'
                }
                $('#get_num_hint').empty();
                $('#tbody').html(tips);
                $('#num').val(data);
                $('#cur_page').val(cur_page);
                $('.weui-custom-ft').hide();
                $.hideLoading();
            }
        }
    });
}

function hidemark() {
    $('#pick_number_mask_alert').removeClass('weui_mask_visible');
    $('#pick_number_alert').removeClass('weui-dialog-visible');
    $('.weui_search_input').val('');
}
$('.searchbar_wrap').on('click','.weui_icon_clear',function(){
    $('.weui_search_input').val('');
})

$(function() {
    //复选框事件
    $('#read').on('click',function(){
        if($(this).is(':checked')){
            $(this).parent().find('i').removeClass('fa-circle-thin').addClass('fa-check-circle').css('color','#dba95e');
        }else{
            $(this).parent().find('i').removeClass('fa-check-circle').addClass('fa-circle-thin').css('color','#ccc');
        }
    });
    //号码条件选择
    $('.searchbar_wrap').searchBar({
        cancelText: "取消",
        searchText: '生日、幸运数字等',
        onfocus: function(value) {},
        onblur: function(value) {
            showmark(value);
        },
        oninput: function(value) {console.log('oninput')},
        onsubmit: function(value) {
            showmark(value);
        },
        oncancel: function() {
            showmark();
        },

        onclear: function() {
            showmark();
        }
    });
});

/*
* 第一个是一证五户
第二个是申请验证码
第三个是创建订单
* */
/*
*   点击获取验证码事件
* */
function getSafeCode(){
    var mobilePhone = $('#mobilePhone').val();
    var pt = /^0?1[3|4|5|6|7|8|9][0-9]\d{8}$/;
    var certNo = $('#certNo').val();
    if(IdentityCodeValid(certNo)){
        return false;
    }
    if (!pt.test(mobilePhone)) {
        alert('请输入正确的联系电话');
        return false;
    }
    resetCode();
    var certNo = $('#certNo').val();
    var provinceCode = certNo.substr(0,2) + '0000';
    var cityCode = certNo.substr(0,4) + '00';
    var checkUser = {
        certName : $('#certName').val(),
        certNum : certNo,
        cityCode : cityCode,
        provinceCode : provinceCode,
        goodsId : '981610241535',
        phoneNum : mobilePhone
    };
    //验证用户一证五户和验证重复下单
    $.ajax({
        url: "/CuccMobile/checkOrder",
        type: "post",
        contentType: 'application/json',
        data: JSON.stringify(checkUser),
        success:function(data){
            //console.log(data)
            if(data.code != 0){
                alert(data.message);
                return false;
            }
            //获取验证码并锁住号码
            $.ajax({
                url: "/CuccMobile/lockNumAndApplyCode",
                type: "post",
                contentType: 'application/json',
                data: JSON.stringify({certNum:certNo,provinceCode:$('#homeProvinceCode').val(),cityCode:$('#homeCityCode').val(),contactNum:mobilePhone,phoneNum:$('#serial_number').val()}),
                success:function(data){
                    //console.log(data)
                    if(data.code != 0)return false;
                    $('#custId').val(data.custId);
                    $.toast('验证码已发送！', 1500);
                }
            })
        }
    })
}
/*
* 发送验证码定时器  每个60秒可发送
* */
function resetCode(){
    $("#captchaBtn").hide();
    $("#second_hint").html("60");
    $("#captchaHint").show();
    var second = 60;
    var timer = null;
    timer = setInterval(function(){
        second -= 1;
        if(second > 0){
            $("#second_hint").html(second);
        }else{
            clearInterval(timer);
            $("#captchaBtn").show();
            $("#captchaHint").hide();
        }
    },1000);
}
function getCheckCode(){
    var uuid = '';
    $.ajax({
        url: "/CuccMobile/checkCode",
        type: "post",
        async : false,
        contentType: 'application/json',
        data: JSON.stringify({certNo :$('#certNo').val(),contactNum:$('#mobilePhone').val(),safeCode:$('#safeCode').val()}),
        success:function(data){
            if(data.code != 0){
                alert(data.message);
                return false;
            }
            uuid = JSON.parse(data.data).uuid;
        }
    });
    return uuid;
}


/*
* 表单验证
* */
function summit_form() {
    var captchaId = $('#safeCode').val();//验证码
    var certName = $('#certName').val();
    var certNo  = $('#certNo').val();
    var channel = '';
    var contactNum  = $('#mobilePhone').val();
    var custId  = $('#custId').val();
    var phoneNum  = $('#serial_number').val();
    var productType = '215';
    var provinceCode  = $('#homeProvinceCode').val();
    var cityCode  = $('#homeCityCode').val();
    var postProvinceCode = $('#selProvinceCode').val();
    var postCityCode = $('#selCityCode').val();
    var postDistrictCode = $('#selCityCounty').val();
    var address = $('#address').val();
    var referrerCode = '';

    var pt = /^0?1[3|4|5|6|7|8|9][0-9]\d{8}$/;

    if (checkName(certName)) {
        alert("姓名必须至少包含两个汉字");
        return false;
    }else if (!pt.test(contactNum)) {
        alert('请输入正确的联系号码');
        return false;
    }else if (IdentityCodeValid(certNo)) {
        return false;
    }else if (address.length >= 50 || !address || address.length < 6) {
        alert('请输入正确的详细地址');
        return false;
    }else if (/^[0-9]+$/.test(address)) {
        alert('详细地址填写有误');
        return false;
    } else if (!$('#selCity').val()) {
        alert('请点击选取省市区县');
        return false;
    }
    if (!phoneNum || phoneNum == '') {
        alert("请选择号码");
        return false;
    }
    if (!captchaId || captchaId == '') {
        alert('请输入验证码，谢谢');
        return false;
    }

    var postData = JSON.stringify({
        "address":address,
        "captchaId":captchaId,
        "certName":certName,
        "certNo":certNo,
        "cityCode":cityCode,
        "contactNum":contactNum,
        "custId":custId,
        "phoneNum":phoneNum,
        "productType":productType,
        "provinceCode":provinceCode,
        "postCityCode":postCityCode,
        "postDistrictCode":postDistrictCode,
        "postProvinceCode":postProvinceCode
    });
    $(".submit-btn").hide();
    //$.showLoading('请稍后');
    $.ajax({
        url: '/CuccMobile/createOrder',
        type: 'post',
        contentType: 'application/json',
        data: postData,
        success: function(data) {
            //console.log(data)
            $(".submit-btn").show();
            $.hideLoading();
            if(data.code == 0){
                $.toast(data.message, 1500);
                setTimeout(function(){
                    window.location.href = window.location.href;
                },1500)
            }else{
                alert(data.message);
                window.location.href = window.location.href;
            }
        }
    })
}
/*
* 提交表单
* */
function summit_form_front() {
    var isread = $('#read').is(':checked');
    if (!isread) {
        alert('请您阅读入网协议');
        return false;
    }
    if($('#homeProvinceCode').val() != ''){
        summit_form();
    }else{
        alert('请选择号码归属地');
        return false;
    }

}


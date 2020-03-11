package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.moblie.MobileUtil;
import com.ruoyi.system.domain.ChooseNumberColumn;
import com.ruoyi.system.domain.MobileUrl;
import com.ruoyi.system.domain.Order;
import com.ruoyi.system.domain.mobileRequest.*;
import com.ruoyi.system.domain.mobileResponse.ChooseNumberColumnResponse;
import com.ruoyi.system.domain.mobileResponse.ChooseNumberbusinessResponse;
import com.ruoyi.system.domain.mobileResponse.QueryChooseNumberListResponse;
import com.ruoyi.system.domain.mobileResponse.QueryDiscountNumberListResponse;
import com.ruoyi.system.mapper.ChooseNumberColumnMapper;
import com.ruoyi.system.mapper.MobileUrlMapper;
import com.ruoyi.system.mobile.MobileResponseService;
import com.ruoyi.system.service.TestMobileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.*;

/**
 * Datetime:    2020/2/27   15:37
 * Author:      bjl
 */

@Service
public class TestMobileServiceImpl implements TestMobileService {

    private static final Logger log = LoggerFactory.getLogger(TestMobileServiceImpl.class);

    @Resource
    private ChooseNumberColumnMapper chooseNumberColumnMapper;

    @Resource
    private MobileUrlMapper mobileUrlMapper;

    @Autowired
    private MobileResponseService mobileResponseService;

    @Override
    @Transactional
    public String queryChooseNumberColumn() {
        String url = mobileUrlMapper.selectMobileUrlByEumn("QueryChooseNumberColumn").getUrl();
        Map<String,String> map = new HashMap<>();
        map.put("channel","newWap");
        JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(map));
        String body = itemJSONObj.toString();

        JSONObject responsejsonObject = JSONObject.parseObject(MobileUtil.getResponse(url,body));
        //返回状态为0
        if("0".equals(responsejsonObject.getString("respcode"))){
            String infos = JSONObject.parseObject(responsejsonObject.getString("result")).getString("infos")  ;
            List<ChooseNumberColumnResponse> lists = JSONArray.parseArray(infos, ChooseNumberColumnResponse.class);

           if(lists==null||lists.isEmpty()){
               return "接口查询数据为空";
           }

            //每次insert前把表数据清空
            chooseNumberColumnMapper.deleteAll();
            //循环赋值更新
            lists.stream().forEach(list->{
                ChooseNumberColumn chooseNumberColumn = new ChooseNumberColumn();
                chooseNumberColumn.setChannel(list.getChannel()==null ? "":list.getChannel());
                chooseNumberColumn.setConstraint(list.getConstraint() == null ? "":list.getConstraint());
                chooseNumberColumn.setDescribe(list.getDescribe() ==null ? "": list.getDescribe()) ;
                chooseNumberColumn.setPack(list.getPack() ==null ? "": list.getPack() );
                chooseNumberColumn.setPic(list.getPic() ==null ? "": list.getPic());
                chooseNumberColumn.setStatus(list.getStatus() ==null ? "": list.getStatus() );
                chooseNumberColumn.setPosition(list.getPosition() ==null ? "": list.getPosition());
                chooseNumberColumn.setText(list.getText() ==null ? "": list.getText());
                chooseNumberColumn.setSid(list.getSid() ==null ? 00000 :list.getSid());
                chooseNumberColumn.setupdatetime(DateUtils.getDateFromString(list.getUpdatetime()));
                chooseNumberColumnMapper.insertChooseNumberColumn(chooseNumberColumn);
            });
            return "选项卡类更新成功";
        }
        return "接口调用失败";
    }

    @Override
    public String chooseNumberBusiness(ChooseNumberbusinessRequest request) {
        String url = mobileUrlMapper.selectMobileUrlByEumn("ChooseNumberBusiness").getUrl();
        String body = MobileUtil.getBodyByClass(request);
        return MobileUtil.getResponse(url,body);
    }

    @Override
    public String JDCheckAddress(JDCheckAddressRequest request) {
        String url = mobileUrlMapper.selectMobileUrlByEumn("JDCheckAddress").getUrl();
        String body = MobileUtil.getBodyByClass(request);
        return MobileUtil.getResponse(url,body);
    }

    @Override
    public String queryChooseNumberList(QueryChooseNumberListRequest request) {
        String url = mobileUrlMapper.selectMobileUrlByEumn("QueryChooseNumberList").getUrl();
        String body = MobileUtil.getBodyByClass(request);
        return MobileUtil.getResponse(url,body);
    }

    @Override
    public AirpickinstallnewOrderRequest getRequest(Order order) {

        //查询选号列表
        QueryChooseNumberListRequest queryChooseNumberListRequest = new QueryChooseNumberListRequest();
        queryChooseNumberListRequest.setSid(order.getSid());
        queryChooseNumberListRequest.setRegion(order.getProvincecode());
        queryChooseNumberListRequest.setTag("0");
        queryChooseNumberListRequest.setPackagecode(order.getPack());
        QueryChooseNumberListResponse queryChooseNumberListResponse = mobileResponseService.getQueryChooseNumberList(queryChooseNumberListRequest);


        //查询放号单品业务信息能力
        ChooseNumberbusinessRequest chooseNumberbusinessRequest = new ChooseNumberbusinessRequest();
        chooseNumberbusinessRequest.setTypecode(order.getPack());
        ChooseNumberbusinessResponse chooseNumberbusinessResponse = mobileResponseService.getChooseNumberbusiness(chooseNumberbusinessRequest);


        //京东地址校验是否配送
        JDCheckAddressRequest jdCheckAddressRequest = new JDCheckAddressRequest();
        jdCheckAddressRequest.setAddress(order.getAddress());
        jdCheckAddressRequest.setProvinceCode(order.getProvincecode());
        jdCheckAddressRequest.setEparchyCode(order.getEparchycode());
        jdCheckAddressRequest.setCityCode(order.getCitycode());
        boolean jdCheck = mobileResponseService.JDCheakAddress(jdCheckAddressRequest);


        //选号查询号码可选优惠
        QueryDiscountNumberListRequest queryDiscountNumberListRequest = new QueryDiscountNumberListRequest();
        queryDiscountNumberListRequest.setMobileno(queryChooseNumberListResponse.getMobileno());
        QueryDiscountNumberListResponse queryDiscountNumberListResponse = mobileResponseService.getQueryDiscountNumberList(queryDiscountNumberListRequest);



        AirpickinstallnewOrderRequest request = new AirpickinstallnewOrderRequest();
        newOrderParams newOrderParams = new newOrderParams();
        //渠道编码 工号必传
        newOrderParams.setWayid("GZ08EC200043");
        newOrderParams.setOperatorid("AGZC00000BYJ");

        //号码归属地市编码
        newOrderParams.setAreacode(queryChooseNumberListResponse.getRegion());
        //号码归属地市名称
        newOrderParams.setAreaname(queryChooseNumberListResponse.getCityname());
        //新购号码
        newOrderParams.setServnumber(Long.parseLong(queryChooseNumberListResponse.getMobileno()));


        //品牌
        newOrderParams.setBrand(queryDiscountNumberListResponse.getBrand());
        //受理方式 代客下单
        newOrderParams.setAccepttype("2");
        //此处判断是否支持京东配送 支持4 不支持则0
        if(jdCheck){
            newOrderParams.setReceivetype("4");
        }else{
            newOrderParams.setReceivetype("0");
        }
        //订单金额
        newOrderParams.setOrderamount(Double.parseDouble(queryDiscountNumberListResponse.getPrice()));
        //支付方式
        newOrderParams.setPayway("1");

        //配送省份
        newOrderParams.setProvince(order.getProvince());
        //订单id
        newOrderParams.setOrderid(UUID.randomUUID().toString().replaceAll("-",""));
        //真实姓名
        newOrderParams.setUsername(order.getRealname());
        //配送地址
        newOrderParams.setAddress(order.getAddress());
        //配送地市中文
        newOrderParams.setAddresscity(order.getAddressCity());
        //联系方式
        newOrderParams.setTelno(order.getPhone());
        //证件类型
        newOrderParams.setCertype(order.getCardtype());
        //证件号码
        newOrderParams.setCerno(order.getCardid());
        //配卡方式
        newOrderParams.setOfflinecard("0");
        request.setNewOrderParams(newOrderParams);
        return request;
    }




    @Override
    public String getResponse(QueryDiscountNumberListRequest request) {
        String url = mobileUrlMapper.selectMobileUrlByEumn("QueryDiscountNumberList").getUrl();
        String body = MobileUtil.getBodyByClass(request);
        return MobileUtil.getResponse(url,body);
    }


    @Override
    public String testGetUrl(String eumn) {
        System.out.println("标识："+eumn);
        return mobileUrlMapper.selectMobileUrlByEumn(eumn).getUrl();
    }



}

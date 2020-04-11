package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.MobileUrl;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.moblie.MobileUtil;
import com.ruoyi.system.domain.ChooseNumberColumn;
import com.ruoyi.system.domain.Order;
import com.ruoyi.system.domain.mobileRequest.*;
import com.ruoyi.system.domain.mobileResponse.*;
import com.ruoyi.system.mapper.ChooseNumberColumnMapper;
import com.ruoyi.system.mapper.MobileUrlMapper;
import com.ruoyi.system.mobile.AddressResolutionService;
import com.ruoyi.system.mobile.MobileResponseService;
import com.ruoyi.system.service.IOrderService;
import com.ruoyi.system.service.MobileService;
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
public class MobileServiceImpl implements MobileService {

    private static final Logger log = LoggerFactory.getLogger(MobileServiceImpl.class);

    @Resource
    private ChooseNumberColumnMapper chooseNumberColumnMapper;

    @Resource
    private MobileUrlMapper mobileUrlMapper;

    @Autowired
    private MobileResponseService mobileResponseService;

    @Autowired
    private AddressResolutionService addressResolutionService;

    @Autowired
    private IOrderService orderService;

    @Override
    public String queryChooseNumberColumn() {
        String url = MobileUrl.QueryChooseNumberColumn.getUrl();
        Map<String, String> map = new HashMap<>();
        map.put("channel", "newWap");
        JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(map));
        String body = itemJSONObj.toString();

        JSONObject responsejsonObject = JSONObject.parseObject(MobileUtil.getResponse(url, body));
        //返回状态为0
        if ("0".equals(responsejsonObject.getString("respcode"))) {
            String infos = JSONObject.parseObject(responsejsonObject.getString("result")).getString("infos");
            List<ChooseNumberColumnResponse> lists = JSONArray.parseArray(infos, ChooseNumberColumnResponse.class);

            if (lists == null || lists.isEmpty()) {
                return "接口查询数据为空";
            }

            //每次insert前把表数据清空
            chooseNumberColumnMapper.deleteAll();
            //循环赋值更新
            lists.stream().forEach(list -> {
                ChooseNumberColumn chooseNumberColumn = new ChooseNumberColumn();
                chooseNumberColumn.setChannel(list.getChannel() == null ? "" : list.getChannel());
                chooseNumberColumn.setConstraint(list.getConstraint() == null ? "" : list.getConstraint());
                chooseNumberColumn.setDescribe(list.getDescribe() == null ? "" : list.getDescribe());
                chooseNumberColumn.setPack(list.getPack() == null ? "" : list.getPack());
                chooseNumberColumn.setPic(list.getPic() == null ? "" : list.getPic());
                chooseNumberColumn.setStatus(list.getStatus() == null ? "" : list.getStatus());
                chooseNumberColumn.setPosition(list.getPosition() == null ? "" : list.getPosition());
                chooseNumberColumn.setText(list.getText() == null ? "" : list.getText());
                chooseNumberColumn.setSid(list.getSid() == null ? 00000 : list.getSid());
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
        return MobileUtil.getResponse(url, body);
    }

    @Override
    public JDCheckAddressResponse JDCheckAddress(JDCheckAddressRequest request) {
        return mobileResponseService.JDCheakAddress(request);
    }

    @Override
    public String queryChooseNumberList(QueryChooseNumberListRequest request) {
        String url = mobileUrlMapper.selectMobileUrlByEumn("QueryChooseNumberList").getUrl();
        String body = MobileUtil.getBodyByClass(request);
        return MobileUtil.getResponse(url, body);
    }

    @Override
    public String getResponse(QueryDiscountNumberListRequest request) {
        String url = MobileUrl.QueryDiscountNumberList.getUrl();
        String body = MobileUtil.getBodyByClass(request);
        return MobileUtil.getResponse(url, body);
    }

    @Override
    public Boolean AirpickinstallnewOrder(Order order) {
        return mobileResponseService.getAirpickinstallnewOrder(order);
    }

    @Override
    public DSAirpickinstallQueryOrderResponse getOrderMsg(DSAirpickinstallQueryOrderRequest request) {
        return mobileResponseService.getOrderMsg(request);
    }

    @Override
    public String JDCheckAddress2(String address) {
        String url = MobileUrl.JDCheckAddress.getUrl();
        JDCheckAddressRequest request = addressResolutionService.addressResolution(address);
        String body = MobileUtil.getBodyByClass(request);
        return MobileUtil.getResponse(url, body);
    }

    @Override
    public String getExpressTrace(QryExpressTraceRequest request) {
        return mobileResponseService.getExpressTrace(request);
    }

    @Override
    public AjaxResult addBZCardOrder(Order order) {
        if(order==null){
            return AjaxResult.error("订单发送失败");
        }
        order.setFdId(StringUtils.generateRandomString(12).toUpperCase());
        order.setCardtype("01");
        order.setSid("1000000019");
        order.setPack("prod.10086000025892");
        if(orderService.insertOrder(order)>0){
            return AjaxResult.success("订单发送成功");
        }
        return AjaxResult.error("订单发送失败");
    }

    @Override
    public String testGetUrl(String eumn) {
        System.out.println("标识：" + eumn);
        return mobileUrlMapper.selectMobileUrlByEumn(eumn).getUrl();
    }


}

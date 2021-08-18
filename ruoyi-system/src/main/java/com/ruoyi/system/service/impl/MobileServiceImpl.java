package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.MobileUrl;
import com.ruoyi.common.enums.RedisEnum;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.moblie.MobileUtil;
import com.ruoyi.common.utils.redis.RedisUtil;
import com.ruoyi.system.domain.AddressCode;
import com.ruoyi.system.domain.ChooseNumberColumn;
import com.ruoyi.system.domain.Order;
import com.ruoyi.system.domain.OrderLogistics;
import com.ruoyi.system.domain.mobileRequest.*;
import com.ruoyi.system.domain.mobileResponse.*;
import com.ruoyi.system.mapper.AddressCodeMapper;
import com.ruoyi.system.mapper.ChooseNumberColumnMapper;
import com.ruoyi.system.mapper.MobileUrlMapper;
import com.ruoyi.system.mapper.OrderLogisticsMapper;
import com.ruoyi.system.mobile.AddressResolutionService;
import com.ruoyi.system.mobile.MobileResponseService;
import com.ruoyi.system.service.IOrderService;
import com.ruoyi.system.service.MobileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Resource
    private AddressCodeMapper addressCodeMapper;

    @Resource
    private OrderLogisticsMapper orderLogisticsMapper;

    @Autowired
    private RedisUtil redisUtil;

    @Override
    public JDCheckAddressResponse JDCheckAddress(JDCheckAddressRequest request) {
        return mobileResponseService.JDCheckAddress(request);
    }

    @Override
    public String queryChooseNumberList(QueryChooseNumberListRequest request) {
        String url = mobileUrlMapper.selectMobileUrlByEumn("QueryChooseNumberList").getUrl();
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
    public String JDCheckAddress2(Order order) {
        String url = MobileUrl.JDCheckAddress.getUrl();
        JDCheckAddressRequest request = addressResolutionService.addressResolution(order);
        String body = MobileUtil.getBodyByClass(request);
        return MobileUtil.getResponse(url, body);
    }

    @Override
    public String getExpressTrace(QryExpressTraceRequest request) {
        Order order = orderService.selectOrderById(request.getOrderid());
        request.setOrderid(order.getOrderId());
        return mobileResponseService.getExpressTrace(request);
    }

    @Override
    public AjaxResult addBZCardOrder(Order order) {
        if(order==null){
            return AjaxResult.error("订单发送失败");
        }
        order.setFdId(StringUtils.generateRandomString(12).toUpperCase());
        order.setCardtype("01");
        order.setSid("prod.10086000034120");
        order.setPackageName("19元移动花卡宝藏版");
        order.setStatus("0");
        order.setProvince(RedisEnum.ADDRESSCODE+"|"+(String)redisUtil.get("1|"+order.getProvincecode()));
        order.setAddressCity(RedisEnum.ADDRESSCODE+"|"+(String) redisUtil.get("2|"+order.getEparchycode()));
        order.setAddress(RedisEnum.ADDRESSCODE+"|"+(String) redisUtil.get("1|"+order.getProvincecode())+
                         RedisEnum.ADDRESSCODE+"|"+(String) redisUtil.get("2|"+order.getEparchycode())+
                         RedisEnum.ADDRESSCODE+"|"+(String) redisUtil.get("3|"+order.getCitycode())+ order.getAddress());
        if(orderService.insertOrder(order)>0){
            return AjaxResult.success("订单发送成功");
        }
        return AjaxResult.error("订单发送失败");
    }

    @Override
    public String insertRedisAddressCode() {
        List<AddressCode> addressCodeList = addressCodeMapper.selectAllAddressCodeList();
        for(AddressCode addressCode:addressCodeList){
            redisUtil.set(RedisEnum.ADDRESSCODE+"|"+addressCode.getType()+"|"+addressCode.getCode(),addressCode.getName(),0);
        }
        return ("插入了"+addressCodeList.size()+"区域信息");
    }

    @Override
    public String insertRedisCmccProduct() {
        List<ChooseNumberColumn> cmccProductList = chooseNumberColumnMapper.selectAllChooseNumberColumnList();
        for(ChooseNumberColumn cmccProduct:cmccProductList){
            redisUtil.set(RedisEnum.PRODUCT+"|"+cmccProduct.getSid(),cmccProduct.getPack(),0);
        }
        return ("插入了"+cmccProductList.size()+"条产品信息");
    }

    @Override
    public void insertOrderMsg(DSAirpickinstallQueryOrderRequest request) {
        DSAirpickinstallQueryOrderResponse response = mobileResponseService.getOrderMsg(request);
        OrderLogistics orderLogistics = new OrderLogistics();
        BeanUtils.copyProperties(response, orderLogistics);
        orderLogistics.setFdId(response.getOrderId());
        orderLogisticsMapper.insertOrderLogistics(orderLogistics);
    }

}

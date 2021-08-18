package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.system.domain.Order;
import com.ruoyi.system.domain.mobileRequest.*;
import com.ruoyi.system.domain.mobileResponse.AirpickinstallnewOrderResponse;
import com.ruoyi.system.domain.mobileResponse.DSAirpickinstallQueryOrderResponse;
import com.ruoyi.system.domain.mobileResponse.JDCheckAddressResponse;


/**
 * Datetime:    2020/2/27   15:36
 * Author:      bjl
 */

public interface MobileService {

     JDCheckAddressResponse JDCheckAddress(JDCheckAddressRequest request);

     String queryChooseNumberList(QueryChooseNumberListRequest request);

     Boolean AirpickinstallnewOrder(Order order);

     DSAirpickinstallQueryOrderResponse getOrderMsg(DSAirpickinstallQueryOrderRequest request);

     String JDCheckAddress2(Order order);

     String getExpressTrace(QryExpressTraceRequest request);

     AjaxResult addBZCardOrder(Order order);

     String insertRedisAddressCode();

     String insertRedisCmccProduct();

     void insertOrderMsg(DSAirpickinstallQueryOrderRequest request);
}


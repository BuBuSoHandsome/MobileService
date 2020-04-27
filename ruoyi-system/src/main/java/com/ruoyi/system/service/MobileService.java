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

    public String testGetUrl(String eumn);

    public String queryChooseNumberColumn();

    public String chooseNumberBusiness(ChooseNumberbusinessRequest request);

    public JDCheckAddressResponse JDCheckAddress(JDCheckAddressRequest request);

    public String queryChooseNumberList(QueryChooseNumberListRequest request);

    public String getResponse(QueryDiscountNumberListRequest request);

    public Boolean AirpickinstallnewOrder(Order order);

    public DSAirpickinstallQueryOrderResponse getOrderMsg(DSAirpickinstallQueryOrderRequest request);

    public String JDCheckAddress2(Order order);

    public String getExpressTrace(QryExpressTraceRequest request);

    public AjaxResult addBZCardOrder(Order order);

    public String testReadValueChannel(String field);
}


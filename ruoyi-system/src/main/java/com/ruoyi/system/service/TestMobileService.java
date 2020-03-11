package com.ruoyi.system.service;

import com.ruoyi.system.domain.Order;
import com.ruoyi.system.domain.mobileRequest.*;
import com.ruoyi.system.domain.mobileResponse.QueryDiscountNumberListResponse;

import java.util.List;

/**
 * Datetime:    2020/2/27   15:36
 * Author:      bjl
 */

public interface TestMobileService {

    public String testGetUrl(String eumn);

    public String queryChooseNumberColumn();

    public String chooseNumberBusiness(ChooseNumberbusinessRequest request);

    public String JDCheckAddress(JDCheckAddressRequest request);

    public String queryChooseNumberList(QueryChooseNumberListRequest request);

    public AirpickinstallnewOrderRequest getRequest(Order order);

    public String getResponse(QueryDiscountNumberListRequest request);

}

package com.ruoyi.system.service;

import com.ruoyi.system.domain.mobileRequest.ChooseNumberbusinessRequest;
import com.ruoyi.system.domain.mobileRequest.JDCheckAddressRequest;

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

}

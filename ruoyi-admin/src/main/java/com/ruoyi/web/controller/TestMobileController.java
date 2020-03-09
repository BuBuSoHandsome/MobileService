package com.ruoyi.web.controller;

import com.ruoyi.system.domain.mobileRequest.ChooseNumberbusinessRequest;
import com.ruoyi.system.domain.mobileRequest.JDCheckAddressRequest;
import com.ruoyi.system.domain.mobileRequest.QueryChooseNumberListRequest;
import com.ruoyi.system.service.TestMobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 移动服务相关接口
 * Datetime:    2020/2/27   13:48
 * Author:      bjl
 */

@RestController
@RequestMapping("mobile")
    public class TestMobileController {

    @Autowired
    private TestMobileService testMobileService;

    @RequestMapping("/testGetUrl")
    public String testGetUrl( String eumn){
        return testMobileService.testGetUrl(eumn);
    }

    @RequestMapping("/queryChooseNumberColumn")
    public String queryChooseNumberColumn(){
        return testMobileService.queryChooseNumberColumn();
    }

    /**
     * 查询卡类业务信息
     * @param request
     * @return
     */
    @PostMapping("/chooseNumberBusiness")
    public String chooseNumberBusiness(@RequestBody ChooseNumberbusinessRequest request){
        return testMobileService.chooseNumberBusiness(request);
    }

    /**
     * 京东物流地址校验
     * @param request
     * @return
     */
    @PostMapping("/JDCheckAddress")
    public String JDCheckAddress(@RequestBody JDCheckAddressRequest request){
        return testMobileService.JDCheckAddress(request);
    }

    /**
     * 获取卡类号码列表（选号码）
     * @param request
     * @return
     */
    @PostMapping("queryChooseNumberList")
    public String queryChooseNumberList(@RequestBody QueryChooseNumberListRequest request){
        return testMobileService.queryChooseNumberList(request);
    }



}

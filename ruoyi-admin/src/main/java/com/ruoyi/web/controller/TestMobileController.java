package com.ruoyi.web.controller;

import com.ruoyi.system.domain.mobileRequest.ChooseNumberbusinessRequest;
import com.ruoyi.system.domain.mobileRequest.JDCheckAddressRequest;
import com.ruoyi.system.service.TestMobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
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
    @PostMapping("/chooseNumberBusiness")
    public String chooseNumberBusiness(@RequestBody ChooseNumberbusinessRequest request){
        return testMobileService.chooseNumberBusiness(request);
    }
    @PostMapping("/JDCheckAddress")
    public String JDCheckAddress(@RequestBody JDCheckAddressRequest request){
        return testMobileService.JDCheckAddress(request);
    }

}

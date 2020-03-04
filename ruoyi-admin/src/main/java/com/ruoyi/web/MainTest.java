package com.ruoyi.web;

import com.ruoyi.system.domain.MobileUrl;
import com.ruoyi.system.service.TestMobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

/**
 * Datetime:    2020/2/27   13:48
 * Author:      bjl
 */

@RestController
@RequestMapping("mobile")
public class MainTest {

    @Autowired
    private TestMobileService testMobileService;

    @RequestMapping("/testMobile")
    public String testPost(){
        return testMobileService.testMobile();
    }

    @RequestMapping("/testGetUrl")
    public String testGetUrl( String eumn){
        return testMobileService.testGetUrl(eumn);
    }

    @RequestMapping("/queryChooseNumberColumn")
    public String queryChooseNumberColumn(){
        return testMobileService.queryChooseNumberColumn();
    }

}

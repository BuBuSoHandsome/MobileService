package com.ruoyi.quartz.task;

import com.ruoyi.system.service.IChooseNumberColumnService;
import com.ruoyi.system.service.TestMobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("mobileTask")
public class MobileTask {

    @Autowired
    private IChooseNumberColumnService chooseNumberColumnService;

    @Autowired
    private TestMobileService testMobileService;

    public void testRedis(){
        for (int i=0;i<5;i++){
            chooseNumberColumnService.testRedis("有"+i+"个人说","咘咘帅气");
        }
    }

    public void refreshChooseNumberColunm(){
        testMobileService.queryChooseNumberColumn();
    }

}

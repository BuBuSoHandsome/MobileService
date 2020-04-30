package com.ruoyi.web.controller.system;


import com.ruoyi.system.service.CuccMobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("CuccMobile")

public class CuccMobileController {

    @Autowired
    private CuccMobileService cuccMobileService;

    @RequestMapping("helloCucc")
    public String testCucc(){
        return cuccMobileService.testCucc();
    }

}

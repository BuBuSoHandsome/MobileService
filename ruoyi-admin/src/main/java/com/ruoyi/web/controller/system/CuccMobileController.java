package com.ruoyi.web.controller.system;


import com.ruoyi.system.domain.cuccMobileRequest.CheckUserRequest;
import com.ruoyi.system.domain.cuccMobileRequest.SelectNumRequest;
import com.ruoyi.system.domain.cuccMobileResponse.CheckUserResponse;
import com.ruoyi.system.domain.cuccMobileResponse.SelectNumResponse;
import com.ruoyi.system.service.CuccMobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("CuccMobile")

public class CuccMobileController {

    @Autowired
    private CuccMobileService cuccMobileService;

    @RequestMapping("helloCucc")
    public String testCucc(){
        return cuccMobileService.testCucc();

    }

    @PostMapping("checkUser")
    public CheckUserResponse checkUser(@RequestBody CheckUserRequest request){
        return cuccMobileService.checkUser(request);
    }

    @PostMapping("selectNum")
    public List<String> selectNum(@RequestBody SelectNumRequest request){
        return cuccMobileService.selectNum(request);
    }

}

package com.ruoyi.web.controller.system;


import com.ruoyi.system.domain.cuccMobileRequest.*;
import com.ruoyi.system.domain.cuccMobileResponse.*;
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

    @PostMapping("checkNum")
    public CheckNumResponse checkNum(@RequestBody CheckNumRequest request){
        return cuccMobileService.checkNum(request);
    }

    @PostMapping("safeCode")
    public SafeCodeResponse safeCode(@RequestBody SafeCodeRequest request){
        return cuccMobileService.safeCode(request);
    }

    @PostMapping("checkCode")
    public CheckCodeResponse checkCode(@RequestBody CheckCodeRequest request){
        return cuccMobileService.checkCode(request);
    }

    @PostMapping("checkOrder")
    public CheckOrderResponse checkOrder(@RequestBody CheckOrderRequest request){
        return cuccMobileService.checkOrder(request);
    }


    @PostMapping("lockNum")
    public OccupationNumberResponse lockNum(@RequestBody OccupationNumberRequest request){
        return cuccMobileService.lockNum(request);
    }

    @PostMapping("lockNumAndApplyCode")
    public LockNumAndApplyCodeResponse lockNumAndApplyCode(@RequestBody LockNumAndApplyCodeRequest request){
        return cuccMobileService.lockNumAndApplyCode(request);
    }

    @PostMapping("createOrder")
    public CreateOrderResponse createOrder(@RequestBody CreateOrderRequest request){
        return cuccMobileService.createOrder(request);
    }

}

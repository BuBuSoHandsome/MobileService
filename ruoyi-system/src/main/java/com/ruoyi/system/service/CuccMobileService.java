package com.ruoyi.system.service;

import com.ruoyi.system.domain.OrderCucc;
import com.ruoyi.system.domain.cuccMobileRequest.*;
import com.ruoyi.system.domain.cuccMobileResponse.*;

import java.util.List;

public interface CuccMobileService {

    String testCucc();

    CheckUserResponse checkUser(CheckUserRequest request);

    CheckNumResponse checkNum(CheckNumRequest request);

    List<String> selectNum(SelectNumRequest request);

    SafeCodeResponse safeCode(SafeCodeRequest request);

    CheckCodeResponse checkCode(CheckCodeRequest request);

    CheckOrderResponse checkOrder(CheckOrderRequest request);

    OccupationNumberResponse lockNum( OccupationNumberRequest request);

    LockNumAndApplyCodeResponse lockNumAndApplyCode (LockNumAndApplyCodeRequest request);

    CreateOrderResponse createOrder(CreateOrderRequest request);

    CreateOrderResponse installOrder(OrderCucc orderCucc);





}

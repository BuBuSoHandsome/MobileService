package com.ruoyi.system.mobile;

import com.ruoyi.system.domain.OrderCucc;
import com.ruoyi.system.domain.cuccMobileRequest.CreateOrderRequest;
import com.ruoyi.system.domain.cuccMobileRequest.OccupationNumberRequest;
import com.ruoyi.system.domain.cuccMobileResponse.*;

import java.util.List;

/**
 * @Author Alan
 * @Date 2020/5/4 13:54
 * @Version 1.0
 */

public interface CuccMobileResponseService {

    List<String> selectNumber(SelectNumResponse response);

    CheckOrderResponse checkOrder(CheckUserResponse response1, CheckNumResponse response2);

    LockNumAndApplyCodeResponse lockNumAndApplyCode (OccupationNumberResponse response1,SafeCodeResponse response2,String custId);

    void insertCuccNum (OccupationNumberRequest request, OccupationNumberResponse response);

    CreateOrderResponse createOrder(CreateOrderRequest request,CheckCodeResponse response);

    boolean updateOrder (OrderCucc orderCucc);

}

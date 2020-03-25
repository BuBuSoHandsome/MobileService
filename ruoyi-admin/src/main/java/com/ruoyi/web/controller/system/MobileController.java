package com.ruoyi.web.controller.system;

import com.ruoyi.system.domain.Order;
import com.ruoyi.system.domain.mobileRequest.*;
import com.ruoyi.system.domain.mobileResponse.DSAirpickinstallQueryOrderResponse;
import com.ruoyi.system.domain.mobileResponse.JDCheckAddressResponse;
import com.ruoyi.system.service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 移动服务相关接口
 * Datetime:    2020/2/27   13:48
 * Author:      bjl
 */

@RestController
@RequestMapping("mobile")
    public class MobileController {

    @Autowired
    private MobileService mobileService;

    @RequestMapping("/testGetUrl")
    public String testGetUrl( String eumn){
        return mobileService.testGetUrl(eumn);
    }

    @RequestMapping("/queryChooseNumberColumn")
    public String queryChooseNumberColumn(){
        return mobileService.queryChooseNumberColumn();
    }

    /**
     * 查询卡类业务信息
     * @param request
     * @return
     */
    @PostMapping("/chooseNumberBusiness")
    public String chooseNumberBusiness(@RequestBody ChooseNumberbusinessRequest request){
        return mobileService.chooseNumberBusiness(request);
    }

    /**
     * 京东物流地址校验
     * @param request
     * @return
     */
    @PostMapping("/JDCheckAddress")
    public JDCheckAddressResponse JDCheckAddress(@RequestBody JDCheckAddressRequest request){
        return mobileService.JDCheckAddress(request);
    }

    /**
     * 京东物流地址校验 根据地址校验
     * @param address
     * @return
     */
    @RequestMapping("/JDCheckAddress2")
    public String JDCheckAddress2(@RequestParam String address){
        return mobileService.JDCheckAddress2(address);
    }

    /**
     * 获取卡类号码列表（选号码）
     * @param request
     * @return
     */
    @PostMapping("queryChooseNumberList")
    public String queryChooseNumberList(@RequestBody QueryChooseNumberListRequest request){
        return mobileService.queryChooseNumberList(request);
    }

    /**
     * 选号查询号码可选优惠
     * @param request
     * @return
     */
    @PostMapping("QueryDiscountNumberList")
    public String queryDiscountNumberList(@RequestBody QueryDiscountNumberListRequest request){
        return mobileService.getResponse(request);
    }

    @PostMapping("airpickinstallnewOrder")
    public Boolean airpickinstallnewOrder (@RequestBody Order order){
        return mobileService.AirpickinstallnewOrder(order);
    }

    @PostMapping("getOrderMsg")
    public DSAirpickinstallQueryOrderResponse getOrderMsg(@RequestBody DSAirpickinstallQueryOrderRequest request){
        return mobileService.getOrderMsg(request);
    }
}

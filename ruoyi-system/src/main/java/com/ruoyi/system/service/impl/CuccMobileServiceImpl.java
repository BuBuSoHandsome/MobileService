package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.enums.MobileUrl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.moblie.MobileUtil;
import com.ruoyi.system.domain.OrderCucc;
import com.ruoyi.system.domain.cuccMobileRequest.*;
import com.ruoyi.system.domain.cuccMobileResponse.*;
import com.ruoyi.system.mapper.OrderCuccMapper;
import com.ruoyi.system.mobile.CuccMobileResponseService;
import com.ruoyi.system.service.CuccMobileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


@Service
public class CuccMobileServiceImpl implements CuccMobileService {


    @Autowired
    private CuccMobileResponseService cuccMobileResponseService;

    @Override
    public String testCucc() {
        return "欢迎使用中国联通服务";
    }

    @Override
    public CheckUserResponse checkUser(CheckUserRequest request) {
        String resultJson = "";
        try {
            resultJson = MobileUtil.doPost(MobileUrl.CheckUser.getUrl(), request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!"".equals(resultJson)){
            JSONObject jsStr = JSONObject.parseObject(resultJson);
            CheckUserResponse checkUserResponse = JSONObject.toJavaObject(jsStr,CheckUserResponse.class);
            return checkUserResponse;
        }
        return new CheckUserResponse(){{
            setCode("90");
            setMessage("一户无证校验失败");
        }};
    }

    @Override
    public CheckNumResponse checkNum(CheckNumRequest request) {
        String resultJson = "";
        try {
            resultJson = MobileUtil.doPost(MobileUrl.CheckNum.getUrl(), request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!"".equals(resultJson)){
            JSONObject jsStr = JSONObject.parseObject(resultJson);
            CheckNumResponse checkUserResponse = JSONObject.toJavaObject(jsStr,CheckNumResponse.class);
            return checkUserResponse;
        }
        return new CheckNumResponse(){{
            setCode("90");
            setMessage("重复下单校验失败");
        }};
    }

    @Override
    public List<String> selectNum(SelectNumRequest request) {
        String resultJson = "";
        try {
            resultJson = MobileUtil.doPost(MobileUrl.SelectNum.getUrl(), request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!"".equals(resultJson)){
            JSONObject jsStr = JSONObject.parseObject(resultJson);
            SelectNumResponse selectNumResponse = JSONObject.toJavaObject(jsStr,SelectNumResponse.class);
            return cuccMobileResponseService.selectNumber(selectNumResponse);
        }
        return new ArrayList<>();
    }

    @Override
    public SafeCodeResponse safeCode(SafeCodeRequest request) {
        String resultJson = "";
        try {
            resultJson = MobileUtil.doPost(MobileUrl.SafeCode.getUrl(), request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!"".equals(resultJson)){
            JSONObject jsStr = JSONObject.parseObject(resultJson);
            SafeCodeResponse safeCodeResponse = JSONObject.toJavaObject(jsStr,SafeCodeResponse.class);
            return safeCodeResponse;
        }
        return new SafeCodeResponse(){{
            setCode("90");
            setMessage("验证码获取失败");
        }};
    }

    @Override
    public OccupationNumberResponse lockNum(OccupationNumberRequest request) {
        request.setOccupiedFlag("S");
        request.setOccupiedTimeTag("S8");
        request.setProKey(StringUtils.getProkey());
        String resultJson = "";
        try {
            resultJson = MobileUtil.doPost(MobileUrl.OccupationNumber.getUrl(), request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!"".equals(resultJson)){
            JSONObject jsStr = JSONObject.parseObject(resultJson);
            OccupationNumberResponse occupationNumberResponse = JSONObject.toJavaObject(jsStr,OccupationNumberResponse.class);
            cuccMobileResponseService.insertCuccNum(request, occupationNumberResponse);
            return occupationNumberResponse;
        }
        return new OccupationNumberResponse(){{
            setCode("90");
            setMessage("选占号码失败");
        }};
    }

    @Override
    public CheckCodeResponse checkCode(CheckCodeRequest request) {
        String resultJson = "";
        try {
            resultJson = MobileUtil.doPost(MobileUrl.CheckCode.getUrl(), request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!"".equals(resultJson)){
            JSONObject jsStr = JSONObject.parseObject(resultJson);
            CheckCodeResponse checkCodeResponse = JSONObject.toJavaObject(jsStr,CheckCodeResponse.class);

            return checkCodeResponse;
        }
        return new CheckCodeResponse(){{
            setCode("90");
            setMessage("验证码校验失败");
        }};
    }


    @Override
    public CreateOrderResponse installOrder(OrderCucc orderCucc) {
        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        BeanUtils.copyProperties(orderCucc, createOrderRequest);
        String resultJson = "";
        try {
            resultJson = MobileUtil.doPost(MobileUrl.CreateOrder.getUrl(), createOrderRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!"".equals(resultJson)){
            JSONObject jsStr = JSONObject.parseObject(resultJson);
            CreateOrderResponse createOrderResponse = JSONObject.toJavaObject(jsStr,CreateOrderResponse.class);

            //下单成功改变订单状态
            if(createOrderResponse.getCode().equals(Constants.SUCCESS)){
                cuccMobileResponseService.updateOrder(orderCucc);
            }

            return createOrderResponse;
        }
        return new CreateOrderResponse(){{
            setCode("90");
            setMessage("下订单失败");
        }};
    }

    @Override
    public CheckOrderResponse checkOrder(CheckOrderRequest request) {
        CheckUserRequest checkUserRequest = new CheckUserRequest();
        CheckNumRequest checkNumRequest = new CheckNumRequest();
        BeanUtils.copyProperties(request, checkUserRequest);
        BeanUtils.copyProperties(request, checkNumRequest);
        CheckNumResponse checkNumResponse = this.checkNum(checkNumRequest);
        CheckUserResponse checkUserResponse = this.checkUser(checkUserRequest);
        return cuccMobileResponseService.checkOrder(checkUserResponse, checkNumResponse);
    }


    @Override
    public LockNumAndApplyCodeResponse lockNumAndApplyCode(LockNumAndApplyCodeRequest request) {
        OccupationNumberRequest occupationNumberRequest = new OccupationNumberRequest();
        SafeCodeRequest safeCodeRequest = new SafeCodeRequest();
        BeanUtils.copyProperties(request, occupationNumberRequest);
        BeanUtils.copyProperties(request, safeCodeRequest);
        safeCodeRequest.setCertNo(occupationNumberRequest.getCertNum());
        OccupationNumberResponse  occupationNumberResponse = this.lockNum(occupationNumberRequest);
        SafeCodeResponse safeCodeResponse = this.safeCode(safeCodeRequest);
        return cuccMobileResponseService.lockNumAndApplyCode(occupationNumberResponse, safeCodeResponse,occupationNumberRequest.getProKey());
    }

    @Override
    public CreateOrderResponse createOrder(CreateOrderRequest request) {
        CheckCodeRequest checkCodeRequest = new CheckCodeRequest();
        checkCodeRequest.setCertNo(request.getCertNo());
        checkCodeRequest.setContactNum(request.getContactNum());
        checkCodeRequest.setSafeCode(request.getCaptchaId());
        CheckCodeResponse checkCodeResponse = this.checkCode(checkCodeRequest);
        return cuccMobileResponseService.createOrder(request, checkCodeResponse);
    }



}

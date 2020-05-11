package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.enums.MobileUrl;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.moblie.MobileUtil;
import com.ruoyi.system.domain.cuccMobileRequest.*;
import com.ruoyi.system.domain.cuccMobileResponse.*;
import com.ruoyi.system.mobile.CuccMobileResponseService;
import com.ruoyi.system.service.CuccMobileService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public OccupationNumberResponse lockNum(OccupationNumberRequest request) {

        //如果带着prokey 说明号码已经占领下单
        if(null!=request&&!"".equals(request.getProKey())){
            request.setOccupiedFlag("D");
            request.setOccupiedTimeTag("D8");
        }else{
            request.setOccupiedFlag("D");
            request.setOccupiedTimeTag("D1");
            request.setProKey(StringUtils.getProkey());
        }
        String resultJson = "";
        try {
            resultJson = MobileUtil.doPost(MobileUrl.OccupationNumber.getUrl(), request);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(!"".equals(resultJson)){
            JSONObject jsStr = JSONObject.parseObject(resultJson);
            OccupationNumberResponse occupationNumberResponse = JSONObject.toJavaObject(jsStr,OccupationNumberResponse.class);
            cuccMobileResponseService.updateCuccNum(request, occupationNumberResponse);
            return occupationNumberResponse;
        }
        return new OccupationNumberResponse(){{
            setCode("90");
            setMessage("选占号码失败");
        }};
    }

}

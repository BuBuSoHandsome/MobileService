package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.enums.MobileUrl;
import com.ruoyi.common.utils.moblie.MobileUtil;
import com.ruoyi.system.domain.cuccMobileRequest.*;
import com.ruoyi.system.domain.cuccMobileResponse.*;
import com.ruoyi.system.mobile.CuccMobileResponseService;
import com.ruoyi.system.service.CuccMobileService;
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
        return new CheckUserResponse();
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
        return new CheckNumResponse();
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
        return new SafeCodeResponse();
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
        return new CheckCodeResponse();
    }

}

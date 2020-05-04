package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.enums.MobileUrl;
import com.ruoyi.common.utils.moblie.MobileUtil;
import com.ruoyi.system.domain.cuccMobileRequest.CheckNumRequest;
import com.ruoyi.system.domain.cuccMobileRequest.CheckUserRequest;
import com.ruoyi.system.domain.cuccMobileRequest.SelectNumRequest;
import com.ruoyi.system.domain.cuccMobileResponse.CheckNumResponse;
import com.ruoyi.system.domain.cuccMobileResponse.CheckUserResponse;
import com.ruoyi.system.domain.cuccMobileResponse.SelectNumResponse;
import com.ruoyi.system.mobile.CuccMobileResponseService;
import com.ruoyi.system.service.CuccMobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}

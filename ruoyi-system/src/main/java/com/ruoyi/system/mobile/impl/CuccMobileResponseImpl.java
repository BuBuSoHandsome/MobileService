package com.ruoyi.system.mobile.impl;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.system.domain.cuccMobileResponse.SelectNumResponse;
import com.ruoyi.system.mobile.CuccMobileResponseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author Alan
 * @Date 2020/5/4 13:56
 * @Version 1.0
 */


@Service
public class CuccMobileResponseImpl implements CuccMobileResponseService {

    @Override
    public List<String> selectNumber(SelectNumResponse response) {
        List<String> numList = new ArrayList<>();
        if(response.getCode() != "0" && null == response.getData()){
            return numList;
        }
        JSONObject jsonObject = JSONObject.parseObject(response.getData());
        JSONObject jsonObject1 = JSONObject.parseObject(jsonObject.getString("data"));
        numList = Arrays.asList(jsonObject1.getString("numArray").replace("[", "").replaceAll("]", "").split(","))
                .stream().filter(s -> s.length() >= 11).collect(Collectors.toList());
        return numList;
    }
}

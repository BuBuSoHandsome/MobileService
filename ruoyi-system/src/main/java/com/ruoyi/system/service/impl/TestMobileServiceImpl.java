package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.moblie.AsiainfoHashMap;
import com.ruoyi.common.utils.moblie.AsiainfoHeader;
import com.ruoyi.common.utils.moblie.RestHttpclient;
import com.ruoyi.system.domain.MobileUrl;
import com.ruoyi.system.mapper.MobileUrlMapper;
import com.ruoyi.system.service.TestMobileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Datetime:    2020/2/27   15:37
 * Author:      bjl
 */

@Service
public class TestMobileServiceImpl implements TestMobileService {

    private static final Logger log = LoggerFactory.getLogger(TestMobileServiceImpl.class);


    @Autowired
    private RestTemplate restTemplate;

    @Resource
    private MobileUrlMapper mobileUrlMapper;

    @Override
    public String testMobile() {
        /*Map<String,String> map = new HashMap<>();
        map.put("infoid","2");
        map.put("servnumber","1589187976");
        map.put("authid","bairong_20170616_15989187976_0912398123");
        String url = mobileUrlMapper.selectMobileUrlByEumn("test").getUrl();
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("infoid","2");
        requestBody.add("servnumber","1589187976");
        requestBody.add("authid","bairong_20170616_15989187976_0912398123");
        HttpEntity<MultiValueMap> requestEntity = AsiainfoHashMap.getRequestEntity(map,requestBody);
        log.info("请求接口url："+url);
        //post
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();*/

        AsiainfoHeader header=new AsiainfoHeader();
        //系统id 101443
        header.setAppId("101443");
        //随机uuid 去掉 -   fc3058b83e6943cba6f565d7bf9b28bb
        header.setBusiSerial(UUID.randomUUID().toString().replace("-",""));
        //32位随机数字字母 大写 JQIRCRGAHIWOOLNEEUQ6J5WDVPW6MOEJ
        header.setNonce(StringUtils.generateRandomString(32).toUpperCase());
        //yyyyMMddHHmmssSSS 时间戳 20200303124223541
        header.setTimestamp(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        String url = mobileUrlMapper.selectMobileUrlByEumn("QueryChooseNumberColumn").getUrl();
        Map<String,String> map = new HashMap<>();
        map.put("channel","newWap");
        log.info("请求接口url："+url);
        JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(map));
        String body = itemJSONObj.toString();
        log.info("请求报文体： "+body);

        String response = "";
        try {
             response = RestHttpclient.post(url,header, body);
        } catch (Exception e) {
            e.printStackTrace();
            return "调用接口失败";
        }
        return  response;

    }

    @Override
    public String queryChooseNumberColumn() {
        Map<String,String> map = new HashMap<>();
        map.put("channel","newWap");
        String url = mobileUrlMapper.selectMobileUrlByEumn("QueryChooseNumberColumn").getUrl();
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<>();
        requestBody.add("channel","newWap");
        HttpEntity<MultiValueMap> requestEntity = AsiainfoHashMap.getRequestEntity(map,requestBody);
        log.info("请求接口url："+url);
        //post
        ResponseEntity<String> responseEntity = restTemplate.postForEntity(url, requestEntity, String.class);
        System.out.println(responseEntity.getBody());
        return responseEntity.getBody();
    }

    @Override
    public String testGetUrl(String eumn) {
        System.out.println("标识："+eumn);
        return mobileUrlMapper.selectMobileUrlByEumn(eumn).getUrl();
    }



}

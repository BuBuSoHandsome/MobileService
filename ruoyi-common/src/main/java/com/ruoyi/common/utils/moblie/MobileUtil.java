package com.ruoyi.common.utils.moblie;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.utils.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class MobileUtil {
    /**
     * 把类转为JSON字符串
     * @param object
     * @return
     */
    public static String getBodyByClass(Object object){
        ObjectMapper mapper = new ObjectMapper();
        String body = null;
        try {
            body = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
        return body;
    }


    public static String getResponse(String url,String body){
        AsiainfoHeader header=new AsiainfoHeader();
        //系统id 101443
        header.setAppId("101443");
        //随机uuid 去掉 -   fc3058b83e6943cba6f565d7bf9b28bb
        header.setBusiSerial(UUID.randomUUID().toString().replace("-",""));
        //32位随机数字字母 大写 JQIRCRGAHIWOOLNEEUQ6J5WDVPW6MOEJ
        header.setNonce(StringUtils.generateRandomString(32).toUpperCase());
        //yyyyMMddHHmmssSSS 时间戳 20200303124223541
        header.setTimestamp(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        String response = "";
        try {
            response = RestHttpclient.post(url,header, body);
        } catch (Exception e) {
            e.printStackTrace();
            return "调用接口失败";
        }
        return  response;
    }

}

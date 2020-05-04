package com.ruoyi.common.utils.moblie;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ruoyi.common.utils.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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

    public static String doPost(String url,Object object) throws Exception {
        String result = "";
        StringBuffer stringBuffer = new StringBuffer();
        //处理参数
        List<NameValuePair> nameValuePairs = new ArrayList<>();
        Field[] fields = object.getClass().getDeclaredFields();
        for(Field f:fields){
            f.setAccessible(true);
            System.out.println(f.getName()+":"+f.get(object));
            if(null==f.get(object)||"".equals(f.get(object))) {
                continue;
            }
            stringBuffer.append(f.getName()+"=");
            stringBuffer.append(URLEncoder.encode((String) f.get(object), "UTF-8")+"&");
            NameValuePair valuePair = new BasicNameValuePair(f.getName(), (String) f.get(object));
            nameValuePairs.add(valuePair);
        }
        String sign = stringBuffer.deleteCharAt(stringBuffer.length()-1).toString();
        nameValuePairs.add(new BasicNameValuePair("sign", RSASignature.getSHA256StrJava(sign)));


        System.out.println(sign);
        System.out.println(RSASignature.getSHA256StrJava(sign));

        //设置Client参数
        HttpClient client = HttpClientBuilder.create().build();
        //发送请求
        HttpPost post = new HttpPost(url);
        HttpEntity entity = new UrlEncodedFormEntity(nameValuePairs,"UTF-8");
        post.setEntity(entity);
        HttpResponse response = client.execute(post);

        //处理响应结果
        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != 200) {
            throw new RuntimeException("statusCode = [" + statusCode + "]");
        } else {
            HttpEntity respEntity = response.getEntity();
            result = EntityUtils.toString(respEntity,"UTF-8");
        }
        return result;
    }



}

package com.ruoyi.common.utils.moblie;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.MultiValueMap;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


/**
 * ���ַ����ֵ�ṹ��
 * 
 * @author carver.gu
 * @since 1.0, Sep 13, 2009
 */
public class AsiainfoHashMap extends HashMap<String, String> {

	private static final Logger log = LoggerFactory.getLogger(AsiainfoHashMap.class);

	private static final long serialVersionUID = -1277791390393392630L;
	
    public static final String DATE_TIME_FORMAT               = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_TIMEZONE                  = "GMT+8";

    public static final String sign_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDPOnU7dAvVR5Ohw6H81EnlndEtojixoQwKQiK2Ll4u6jZXjUwG+mZwUB1tnHGDK+eoj5xC9EH8xA6AIZ0CozDuM3f82X3OTsFdf+S7+ky8lLRLzq8nRPSdbx3tmj5yD+UqBvpBgf0biPBYIOF7WlXhBS9N8KXictSDL09SQbQg9b/NhQrmtsBbQNX1RmOhvCJK0nvlW0a/nGWJnMPrv1bzadnVzdQUhV1siJCQQTc2xLaYdBE/bUIedFMoQQOplp9F3/Ib2qkldFAgj5yDYiYcaZFGb2kHaRURLdLmtcqorKU0S1RACTppaAAnJu+vMGyR29sGe9+gE7nYEn/41K/ZAgMBAAECggEBAMNJ4urZbMFcx8r9cy2x3cYPGO75XpY/H/qW4uFvBwENZZLSrKAy39DeHy2J7gtgl9rpI6iRSZJsLQIhKzcuFVGVnXDigcWnwGBdw3BG7IQXW2XJbJUETcYDzl95KXoScrNNig+r8Zw+S8mxiuzVb/M0EKVRsPKbppvOPOFW2+rmGqLSFRr95A3vWqp9e9KsuC+epmZrqC0pGH5l20qCuqMAhjDHyI3Zwq6FYVUEueGwxORt/zm5q13r3ZKtAhX0vFoBclqNL2XjjTfrzkFOdA1lq63Jsftk2qgFfmvFseupqoTxdKVXitw0yBSslqpaM69X87hG9AUMHloK7aqF0AECgYEA7ymNTmgQKtCg7ErqkiFaE7Mi3McGPgW08GKi9wYT/747GupsMAQ2cm3OEuSafiW7OD9tKsklfljyRenWNv68WBrCAAG3lXW94gygaAyBZjP7/l3Ypftqqy5iwDcMCTTz/qXuLw+up+7eZkjEoKB3+4q6eBKGsJD71K1r9dn5bgECgYEA3dFbVCsso0MgLGi4yAeMsXLWaiUb6MK4LUrUI93BotSXO38Z9baxHG1hGJfANgQjpnmLhoKlNJAnCJYzs2qH9xEx5fcr71v0YHFmb3jm4wzI+aUXCuq0tgJxpY4kVUyHHOKmCodtYJPyqdd3pVtbrh+AHLkcxYboj8hpokrYcdkCgYBfGonrjOwo6m+jpyLiW0rjnI+Xdblgvoo2MTMWwh7/ytjcGi5NZc90739L+gYJdd2eP3Kc2gID3UDyVktt8RxaeMF+mTA0ye5pTpYW6iomxzNE8nKthm3jhn2UbA9oNdt0/uzZYAYXF6Du0a8HJOixaXg/OTbATdcmupO83yIWAQKBgBVDHigBQ3Qa2RioqR2za48O3PKrv/Mdxx3AUHlnpkn/asevNUPSUUPSUD5VPX1wBCLO/8rS9xcEwlkWXLpT+dh683eBq9pzzAb6l8ETq74T2afkZUMMoGnZ7h7acy0h+smQCC52MMvTIq2ZE8Tr7uvP5S3bqKBegChYI5gE6J5xAoGBAL582MVmP8TfIzgAd6vaVdu8neM+13SkTm/FNGHSCBgkLV3BMtDskB69+okGc7GZPmWp6eR1vv8lnm1FWUlQC+fG8nQXsz9q+StPxAuNyIkrMP43YE4JfrGM1b6QqzmXpKgOF24/brheBKZXycVnklaKHmLWxtLJACTUbj8fhUWw";

	public AsiainfoHashMap() {
		super();
	}

	public AsiainfoHashMap(Map<? extends String, ? extends String> m) {
		super(m);
	}

	public String put(String key, Object value) {
		String strValue;

		if (value == null) {
			strValue = null;
		} else if (value instanceof String) {
			strValue = (String) value;
		} else if (value instanceof Integer) {
			strValue = ((Integer) value).toString();
		} else if (value instanceof Long) {
			strValue = ((Long) value).toString();
		} else if (value instanceof Float) {
			strValue = ((Float) value).toString();
		} else if (value instanceof Double) {
			strValue = ((Double) value).toString();
		} else if (value instanceof Boolean) {
			strValue = ((Boolean) value).toString();
		} else if (value instanceof Date) {
            DateFormat format = new SimpleDateFormat(DATE_TIME_FORMAT);
            format.setTimeZone(TimeZone.getTimeZone(DATE_TIMEZONE));
			strValue = format.format((Date) value);
		} else {
			strValue = value.toString();
		}

		return this.put(key, strValue);
	}

	public static AsiainfoHashMap toAsiainfoHashMap(AsiainfoHeader header){
		AsiainfoHashMap map=new AsiainfoHashMap();
		
		Field[] fields=header.getClass().getDeclaredFields(); 
		 
		 for(int i=0;i<fields.length;i++){  
    		if(getFieldValueByName(fields[i].getName(), header)!=null){
				 map.put(fields[i].getName(), getFieldValueByName(fields[i].getName(), header));
			 }
 
		    }  
		
		return map;
	}
	
	   private static Object getFieldValueByName(String fieldName, Object o) {
	       try {    
	           String firstLetter = fieldName.substring(0, 1).toUpperCase();    
	           String getter = "get" + firstLetter + fieldName.substring(1);    
	           Method method = o.getClass().getMethod(getter, new Class[] {});    
	           Object value = method.invoke(o, new Object[] {});    
	           return value;    
	       } catch (Exception e) {    
	           return null;    
	       }    
	   }   
	
	public String put(String key, String value) {
		if (areNotEmpty(key, value)) {
			return super.put(key, value);
		} else {
			return null;
		}
	}

	public static boolean areNotEmpty(String... values) {
		boolean result = true;
		if (values == null || values.length == 0) {
			result = false;
		} else {
			for (String value : values) {
				result &= !isEmpty(value);
			}
		}
		return result;
	}
	
	public static boolean isEmpty(String str) {
		return ((str == null) || (str.length() == 0));
	}



	/**
	 * 封装mobile调用参数
	 * @param map
	 * @param requestBody
	 * @return
	 */
	public static HttpEntity<MultiValueMap> getRequestEntity(Map<String,String> map , MultiValueMap<String, String> requestBody){
		AsiainfoHeader header=new AsiainfoHeader();
		//系统id 101443
		header.setAppId("101443");
		//随机uuid 去掉 -   fc3058b83e6943cba6f565d7bf9b28bb
		header.setBusiSerial(UUID.randomUUID().toString().replace("-",""));
		//32位随机数字字母 大写 JQIRCRGAHIWOOLNEEUQ6J5WDVPW6MOEJ
		header.setNonce(StringUtils.generateRandomString(32).toUpperCase());
		//yyyyMMddHHmmssSSS 时间戳 20200303124223541
		header.setTimestamp(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));

		AsiainfoHashMap head = toAsiainfoHashMap(header);
		JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(map));
		String body = itemJSONObj.toString();

		log.info("请求报文体：   "+body);

//		String body="{\"infoid\": \"2\",\"servnumber\": \"1589187976\",\"authid\": \"bairong_20170616_15989187976_0912398123\"}";
		String content=RSASignature.getSignContent(RSASignature.getSortedMap(head))+body;
		String sign=RSASignature.sign(content,sign_private_key);

		HttpHeaders requestHeader  = new HttpHeaders();
		requestHeader .add("Content-type","application/json;charset=UTF-8");
		requestHeader .add("appId",header.getAppId());
		requestHeader .add("timestamp",header.getTimestamp());
		requestHeader .add("busiSerial",header.getBusiSerial());
		requestHeader .add("nonce",header.getNonce());
		requestHeader .add("sign",sign);

		HttpEntity<MultiValueMap> requestEntity = new HttpEntity<MultiValueMap>(requestBody, requestHeader);
		return requestEntity;

	}

	/*public static void main(String[] args) {

		String uuid = UUID.randomUUID().toString().replace("-","");
		String nonce = StringUtils.generateRandomString(32).toUpperCase();
		String timeStamp = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());

		AsiainfoHeader header=new AsiainfoHeader();
		header.setAppId("101443");
		header.setBusiSerial(uuid);
		header.setNonce(nonce);
		header.setTimestamp(timeStamp);

		AsiainfoHashMap head = toAsiainfoHashMap(header);
		Map<String,String> map = new HashMap<>();
		map.put("channel","newWap");
		JSONObject itemJSONObj = JSONObject.parseObject(JSON.toJSONString(map));
		String body = itemJSONObj.toString();
		String content=RSASignature.getSignContent(RSASignature.getSortedMap(head))+body;
		String sign=RSASignature.sign(content,sign_private_key);
		System.out.println("uuid:"+uuid);
		System.out.println("nonce:"+nonce);
		System.out.println("timestamp:"+timeStamp);
		System.out.println("sign:"+sign);
		System.out.println("resquestBody:"+body);
		System.out.println("itemJSONObj-----#"+itemJSONObj);
		System.out.println("content:"+content);
	}*/

	public static void main(String[] args) {


//		AsiainfoHeader header=new AsiainfoHeader();
//		header.setAppId("501143");
//		header.setBusiSerial("0.31823.173.17060211000635212345");
//		header.setNonce("1234567890");
//		header.setTimestamp("20170802094500123");
//		String body="{\"infoid\": \"2\",\"servnumber\": \"1589187976\",\"authid\": \"bairong_20170616_15989187976_0912398123\"}";
//		AsiainfoHashMap head=AsiainfoHashMap.toAsiainfoHashMap(header);
//		String content=RSASignature.getSignContent(RSASignature.getSortedMap(head))+body;
		String sign=RSASignature.sign("",sign_private_key);
		System.out.println(sign);
	}
}

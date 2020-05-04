package com.ruoyi.common.utils.moblie;
  
import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;


  
  
/** 
 * RSAǩ����ǩ�� 
 * 
 * @author liyt
 */  
public class RSASignature{  
      
    /** 
     * ǩ���㷨 
     */  
    public static final String SIGN_ALGORITHMS = "SHA256WithRSA";

        /**
     　　* 利用java原生的摘要实现SHA256加密
     　　* @param str 加密后的报文
     　　* @return
     　　*/
       public static String getSHA256StrJava(String str){
       MessageDigest messageDigest;
       String encodeStr = "";
       try {
        messageDigest = MessageDigest.getInstance("SHA-256");
         messageDigest.update(str.getBytes("UTF-8"));
         encodeStr = byte2Hex(messageDigest.digest());
         } catch (NoSuchAlgorithmException e) {
         e.printStackTrace();
         } catch (UnsupportedEncodingException e) {
        e.printStackTrace();
        }
        return encodeStr.toUpperCase();
        }

        /**
         　　* 将byte转为16进制
         　　* @param bytes
         　　* @return
         　　*/
        private static String byte2Hex(byte[] bytes){
        StringBuffer stringBuffer = new StringBuffer();
        String temp = null;
        for (int i=0;i<bytes.length;i++){
        temp = Integer.toHexString(bytes[i] & 0xFF);
        if (temp.length()==1){
        //1得到一位的进行补0操作
        stringBuffer.append("0");
        }
        stringBuffer.append(temp);
        }
        return stringBuffer.toString();
        }






    public static Map<String, String> getSortedMap(AsiainfoHashMap sysParams) {
        Map<String, String> sortedParams = new TreeMap<String, String>();
        if (sysParams != null && sysParams.size() > 0) {        	
            sortedParams.putAll(sysParams);
        }
        return sortedParams;
    }

	/**
     * 
     * @param sortedParams
     * @return
     */
    public static String getSignContent(Map<String, String> sortedParams) {
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(sortedParams.keySet());
        Collections.sort(keys);
        int index = 0;
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = sortedParams.get(key);
            if (AsiainfoHashMap.areNotEmpty(key, value)) {
                content.append((index == 0 ? "" : "&") + key + "=" + value);
                index++;
            }
        }
        return content.toString();
    } 
    
    
    
    
    /** 
    * RSAǩ�� 
    * @param content ��ǩ������ 
    * @param privateKey �̻�˽Կ 
    * @param encode �ַ������� 
    * @return ǩ��ֵ 
    */  
    public static String sign(String content, String privateKey, String encode)  
    {  
        try   
        {  
            PKCS8EncodedKeySpec priPKCS8    = new PKCS8EncodedKeySpec( Base64.decode(privateKey) );   
              
            KeyFactory keyf                 = KeyFactory.getInstance("RSA");  
            PrivateKey priKey               = keyf.generatePrivate(priPKCS8);  
  
            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);  
  
            signature.initSign(priKey);  
            signature.update( content.getBytes(encode));  
  
            byte[] signed = signature.sign();  
              
            return new String(Base64.encode(signed));  
        }  
        catch (Exception e)   
        {  
            e.printStackTrace();  
        }  
          
        return null;  
    }  
      
    public static String sign(String content, String privateKey)  
    {  
        try   
        {  
            PKCS8EncodedKeySpec priPKCS8    = new PKCS8EncodedKeySpec( Base64.decode(privateKey) );   
            KeyFactory keyf = KeyFactory.getInstance("RSA");  
            PrivateKey priKey = keyf.generatePrivate(priPKCS8);  
            java.security.Signature signature = java.security.Signature.getInstance(SIGN_ALGORITHMS);  
            signature.initSign(priKey);  
            signature.update( content.getBytes("UTF-8"));  
            byte[] signed = signature.sign();  
            return new String(Base64.encode(signed));  
        }  
        catch (Exception e)   
        {  
            e.printStackTrace();  
        }  
        return null;  
    }  
      
    /** 
    * RSA��ǩ����� 
    * @param content ��ǩ������ 
    * @param sign ǩ��ֵ 
    * @param publicKey ����������̹�Կ 
    * @param encode �ַ������� 
    * @return ����ֵ 
    */  
    public static boolean doCheck(String content, String sign, String publicKey,String encode)  
    {  
        try   
        {  
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
            byte[] encodedKey = Base64.decode(publicKey);  
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));  
  
          
            java.security.Signature signature = java.security.Signature  
            .getInstance(SIGN_ALGORITHMS);  
          
            signature.initVerify(pubKey);  
            signature.update( content.getBytes(encode) );  
          
            boolean bverify = signature.verify( Base64.decode(sign) );  
            return bverify;  
              
        }   
        catch (Exception e)   
        {  
            e.printStackTrace();  
        }  
          
        return false;  
    }  
      
    public static boolean doCheck(String content, String sign, String publicKey)  
    {  
        try   
        {  
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");  
            byte[] encodedKey = Base64.decode(publicKey);  
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));  
  
          
            java.security.Signature signature = java.security.Signature  
            .getInstance(SIGN_ALGORITHMS);  
          
            signature.initVerify(pubKey);  
            signature.update( content.getBytes() );  
          
            boolean bverify = signature.verify( Base64.decode(sign) );  
            return bverify;  
              
        }   
        catch (Exception e)   
        {  
            e.printStackTrace();  
        }  
          
        return false;  
    }  
      
}  
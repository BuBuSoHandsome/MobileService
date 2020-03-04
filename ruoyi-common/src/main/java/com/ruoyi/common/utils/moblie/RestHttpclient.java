package com.ruoyi.common.utils.moblie;

import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.Iterator;
import java.util.Set;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;



public class RestHttpclient {

	public static final String sign_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQDPOnU7dAvVR5Ohw6H81EnlndEtojixoQwKQiK2Ll4u6jZXjUwG+mZwUB1tnHGDK+eoj5xC9EH8xA6AIZ0CozDuM3f82X3OTsFdf+S7+ky8lLRLzq8nRPSdbx3tmj5yD+UqBvpBgf0biPBYIOF7WlXhBS9N8KXictSDL09SQbQg9b/NhQrmtsBbQNX1RmOhvCJK0nvlW0a/nGWJnMPrv1bzadnVzdQUhV1siJCQQTc2xLaYdBE/bUIedFMoQQOplp9F3/Ib2qkldFAgj5yDYiYcaZFGb2kHaRURLdLmtcqorKU0S1RACTppaAAnJu+vMGyR29sGe9+gE7nYEn/41K/ZAgMBAAECggEBAMNJ4urZbMFcx8r9cy2x3cYPGO75XpY/H/qW4uFvBwENZZLSrKAy39DeHy2J7gtgl9rpI6iRSZJsLQIhKzcuFVGVnXDigcWnwGBdw3BG7IQXW2XJbJUETcYDzl95KXoScrNNig+r8Zw+S8mxiuzVb/M0EKVRsPKbppvOPOFW2+rmGqLSFRr95A3vWqp9e9KsuC+epmZrqC0pGH5l20qCuqMAhjDHyI3Zwq6FYVUEueGwxORt/zm5q13r3ZKtAhX0vFoBclqNL2XjjTfrzkFOdA1lq63Jsftk2qgFfmvFseupqoTxdKVXitw0yBSslqpaM69X87hG9AUMHloK7aqF0AECgYEA7ymNTmgQKtCg7ErqkiFaE7Mi3McGPgW08GKi9wYT/747GupsMAQ2cm3OEuSafiW7OD9tKsklfljyRenWNv68WBrCAAG3lXW94gygaAyBZjP7/l3Ypftqqy5iwDcMCTTz/qXuLw+up+7eZkjEoKB3+4q6eBKGsJD71K1r9dn5bgECgYEA3dFbVCsso0MgLGi4yAeMsXLWaiUb6MK4LUrUI93BotSXO38Z9baxHG1hGJfANgQjpnmLhoKlNJAnCJYzs2qH9xEx5fcr71v0YHFmb3jm4wzI+aUXCuq0tgJxpY4kVUyHHOKmCodtYJPyqdd3pVtbrh+AHLkcxYboj8hpokrYcdkCgYBfGonrjOwo6m+jpyLiW0rjnI+Xdblgvoo2MTMWwh7/ytjcGi5NZc90739L+gYJdd2eP3Kc2gID3UDyVktt8RxaeMF+mTA0ye5pTpYW6iomxzNE8nKthm3jhn2UbA9oNdt0/uzZYAYXF6Du0a8HJOixaXg/OTbATdcmupO83yIWAQKBgBVDHigBQ3Qa2RioqR2za48O3PKrv/Mdxx3AUHlnpkn/asevNUPSUUPSUD5VPX1wBCLO/8rS9xcEwlkWXLpT+dh683eBq9pzzAb6l8ETq74T2afkZUMMoGnZ7h7acy0h+smQCC52MMvTIq2ZE8Tr7uvP5S3bqKBegChYI5gE6J5xAoGBAL582MVmP8TfIzgAd6vaVdu8neM+13SkTm/FNGHSCBgkLV3BMtDskB69+okGc7GZPmWp6eR1vv8lnm1FWUlQC+fG8nQXsz9q+StPxAuNyIkrMP43YE4JfrGM1b6QqzmXpKgOF24/brheBKZXycVnklaKHmLWxtLJACTUbj8fhUWw";

	public static HttpClient getsslhttpClient() throws Exception {
		
		  SSLContext ctx = SSLContext.getInstance("TLS");  
	        X509TrustManager tm = new X509TrustManager() {
	        	
				@Override
				public void checkClientTrusted(X509Certificate[] arg0,
						String arg1)
						throws java.security.cert.CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void checkServerTrusted(X509Certificate[] arg0,
						String arg1)
						throws java.security.cert.CertificateException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					// TODO Auto-generated method stub
					return null;
				}  
	          
	        };  
	        
	        final X509HostnameVerifier DO_NOT_VERIFY = new X509HostnameVerifier() {
	   		 @Override
	   		  public boolean verify(String hostname, SSLSession session) {
	   	            return true;
	   	        }

	   			@Override
	   			public void verify(String arg0, SSLSocket arg1) throws IOException {
	   				// TODO Auto-generated method stub
	   				
	   			}

	   			@Override
	   			public void verify(String arg0, X509Certificate arg1)
	   					throws SSLException {
	   				// TODO Auto-generated method stub
	   				
	   			}

	   			@Override
	   			public void verify(String arg0, String[] arg1, String[] arg2)
	   					throws SSLException {
	   				// TODO Auto-generated method stub
	   				
	   			}
	   	    };
	        
	        ctx.init(null, new TrustManager[] { tm }, null);  
	        SSLConnectionSocketFactory ssf = new SSLConnectionSocketFactory(ctx,DO_NOT_VERIFY);  
	        CloseableHttpClient httpclient = HttpClients.custom().setSSLSocketFactory(ssf).build();
		return httpclient;
	}
	
	  public static String post(String url,AsiainfoHeader header,String body) throws Exception {
		    HttpClient httpclient=getsslhttpClient();
	        try {  
	        	AsiainfoHashMap head=AsiainfoHashMap.toAsiainfoHashMap(header);
	            HttpPost postMethod = new HttpPost(url);
	            String content=RSASignature.getSignContent(RSASignature.getSortedMap(head))+body; 
	            
	            String signstr=RSASignature.sign(content,sign_private_key);  
	            
	            Set keys=head.keySet();
	            Iterator it=keys.iterator();
	            while(it.hasNext()){
	            	String a= (String)it.next();
	            	postMethod.setHeader(a, head.get(a));
	            }
	            postMethod.setHeader("sign",signstr);
	            
	            StringEntity entity = new StringEntity(body, "application/json", "UTF-8");
	        	 postMethod.setEntity(entity);
	        	 
	        	 HttpResponse response = httpclient.execute(postMethod);
	        	 
	        	 StatusLine respHttpStatus = response.getStatusLine();
	        	 int staus= respHttpStatus.getStatusCode();
	        	 if(staus==200){
		        	 HttpEntity responseBody = response.getEntity();
		        	 return EntityUtils.toString(responseBody,"UTF-8");
	        	 }else{
	        		 return "状态码:"+staus;
	        	 }
	                        
	        }catch(Exception e){
	        	e.printStackTrace();
	        } finally {  
	        	httpclient.getConnectionManager().shutdown();  
	        }  
	        return null;
	    }  
	
	

}

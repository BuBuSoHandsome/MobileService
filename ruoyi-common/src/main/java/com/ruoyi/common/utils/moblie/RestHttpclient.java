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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RestHttpclient {

	public static final String sign_private_key = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCqZW+NnLUbDGJ/5r4FgP4mFnSCzsXvJqsih3ggh63iwVW/y62/Fs9dloewZMbsGibt8LwpSJ/P/5aHrVbh4EoM96OiGCZrzrAbz+SSlCN//OYxSA47TQMqS6mbhKUUs13WCOPdqtiYDUoTz1dTxbHyOkAqltw5wIwN9O6TEZhqVCrw2w1DkjJhatil+vQ+xgCvpooMQhW0W78gGTZerwklHMtLAeA3lZuULrv1F6r4fPn08jPU8L0SQ4zq+Q0BSuQAtyYnlWY+gDMa2hYO8ZjGCoJfSdp+b4y5d0dRe4aEefijtkLHhSf2JdBP6hsGWak8HuO8wwrLozs6gIKGyYTLAgMBAAECggEBAJlbF64FFr4URGZGXkkH0jSnSw8bV+soCyfVcpkcY7RMOoeWoXX06oaZXt+zo5CE7PqDCmXBdgu2RV0w9Zvux9ap3fyZ7aJjb/5n+ZiueNDM5MP/zMc0RaAAXtKX7BcYY2CpuaMcCzm15DC2ECtWJxQRtwRdqDDWCXbaNQnp8Rnn0WAgJoKDMkyrX+ueQ26NMgW4L+h6zGkU83smFK7ZtIcDOTTOJ+1yzMggTHcE70+JszonMcfk7jGGIl/hBi0ttXKDPoWAUpvqjoiNO5HUH9Jiw6/1g68d0nUDc/A5uq4RUAjxnX9mvidIYZN+376c/9bLkkOAvRLO6ls6V7IuXAkCgYEA7gUcE5tVoZ06SBSjRiEASnbuZyFWTX/j8EdFmkAM4NR4odfMAtyhEYNwxnUvpLw8I2q2EmiCDtQEBzp7WNkDpREgWLo1jT3WAzxAJukStl9J0zGpYcAFL2owIEi/7qfndylhtaldiWou2CrA1iSLvnLnS0RX/hqlMwJtOCJYK88CgYEAt0SZ7++/X00iWQ9+s/WErN1IXS2VfXDLsY8Dn0rsf+yBJNqGFP+xvxg92Z71f9VpUOr32skvNE+dx46G56CV8uym7jx17Qt1TtdJ/apub04Kiq6yIjSbRaf3d0uMO6VCqHXxnjfenle29LDc2XsZIsET09TZB1H0Srpo111+akUCgYA39ZuUQIDxbjW1nKSGaFvZJf0G4h+YOOkuJJOIoGZmtY1m/4VHZve3Vl+9SO/amybIbN5BlbB+K/qGeIlnah4hQNWKBr1wZ4lZWWD7xPBRUDXbIWHhSXPuodiOvovhYDhbG6+NNjR3ii9ZQzPzJMR2Ei2u1HKbm0gbpgZnnCt+oQKBgBUBsANon6700HMU65kFE2gdFSZZC0GNpEt98RRZKjKfkU4AHk5Z4K3ewXgoWEuG0sOLq01QsjB0z8kH9T6nkYh+x8OW+o1pOtEGrFx/JJltgJfU3mY9UW7yidMKvdA4aSOWIVPZY5MSys3RV/81tPPe3b7yLs4//phyNYI6HTthAoGBAKi68dWt8jIh28cUsX9HaYEWp5ojEaJV0NVLrfRlmecKepysF71oNj2C8kAa+lxedsrdTYl3dGdnMsnYcw8kn48hMKLDF6miEimrud9FDZRAeCMAFQ1rKGmS8Q3Vmi4x5wD65+fZur6OzZahBbjUH87UW7KgB3Lp3qX8PvSiKwEG";

	private static final Logger log = LoggerFactory.getLogger(RestHttpclient.class);


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

	        	 log.info("请求报文："+body);

	        	 HttpResponse response = httpclient.execute(postMethod);
	        	 
	        	 StatusLine respHttpStatus = response.getStatusLine();
	        	 int staus= respHttpStatus.getStatusCode();
	        	 if(staus==200){
		        	 HttpEntity responseBody = response.getEntity();
		        	 String responseEntity = EntityUtils.toString(responseBody,"UTF-8");

		        	 log.info("返回报文："+ responseEntity);

		        	 return responseEntity;
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

package com.ruoyi.common.utils.moblie;


import lombok.Data;

@Data
public class AsiainfoHeader {
	private String appId;
	private String timestamp;
	private String busiSerial;
	private String sign_method;
	private String nonce;
	private String authCode;
	private String operatorid;
	private String comflowcode;
	private String instanceid;
	private String route_type;
	private String route_value;
	private String unitid;

}

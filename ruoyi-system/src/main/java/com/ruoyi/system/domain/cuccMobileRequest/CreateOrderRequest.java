package com.ruoyi.system.domain.cuccMobileRequest;

import lombok.Data;

/**
 * @Author Alan
 * @Date 2020/5/4 14:46
 * @Version 1.0
 */

@Data
public class CreateOrderRequest {

    private String address;
    private String appKey = "A3B43FEF873E99CAE053491962842D74";
    private String captchaId;
    private String certName ;
    private String certNo ;
    private String channel;
    private String cityCode ;
    private String contactNum ;
    private String custId ;
    private String phoneNum ;
    private String productType;
    private String provinceCode ;
    private String postCityCode;
    private String postDistrictCode;
    private String postProvinceCode ;
    private String referrerCode ;
    private String secret = "kKpuL2DedCk0YfET";


}

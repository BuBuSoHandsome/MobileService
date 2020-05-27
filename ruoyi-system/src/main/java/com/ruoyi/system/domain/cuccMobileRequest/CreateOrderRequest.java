package com.ruoyi.system.domain.cuccMobileRequest;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Alan
 * @Date 2020/5/4 14:46
 * @Version 1.0
 */

@Data
public class CreateOrderRequest implements Serializable {

    private String address;
    private String appKey = "A3B43FEF873E99CAE053491962842D74";
    private String captchaId;
    private String certName ;
    private String certNo ;
    private String channel= "08-2278-6984-9999";
    private String cityCode ;
    private String contactNum ;
    private String custId ;
    private String phoneNum ;
    private String postCityCode;
    private String postDistrictCode;
    private String postProvinceCode ;
    private String productType;
    private String provinceCode ;
    private String referrerCode ="5111725421";
    private String secret = "kKpuL2DedCk0YfET";


}

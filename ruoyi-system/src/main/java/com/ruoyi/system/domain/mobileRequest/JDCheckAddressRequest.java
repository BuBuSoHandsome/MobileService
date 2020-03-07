package com.ruoyi.system.domain.mobileRequest;

import lombok.Data;

@Data
public class JDCheckAddressRequest {

    //省份编码
    private String provinceCode;

    //地市编码
    private String eparchyCode;

    //区县编码
    private String cityCode;

    //详细地址
    private String address;

}

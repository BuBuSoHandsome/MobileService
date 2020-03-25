package com.ruoyi.system.domain.mobileRequest;

import lombok.Data;

import java.io.Serializable;

@Data
/**
 *  下单地址校验
 */

public class JDCheckAddressRequest implements Serializable {

    //省份编码
    private String provinceCode;

    //地市编码
    private String eparchyCode;

    //区县编码
    private String cityCode;

    //详细地址
    private String address;

    //配送省份
    private String addressProvince;

    //配送地市
    private String addrssCity;

    //配送区域
    private String addressArea;

}

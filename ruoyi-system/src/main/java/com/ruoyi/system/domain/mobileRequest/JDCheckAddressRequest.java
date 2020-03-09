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

}

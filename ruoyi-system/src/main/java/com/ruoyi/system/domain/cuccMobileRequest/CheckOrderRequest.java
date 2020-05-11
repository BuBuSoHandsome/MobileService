package com.ruoyi.system.domain.cuccMobileRequest;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Alan
 * @Date 2020/5/11 21:50
 * @Version 1.0
 */

@Data
public class CheckOrderRequest implements Serializable {

    private String certName;

    private String certNum;

    private String cityCode;

    private String provinceCode;

    private String goodsId;

    private String phoneNum;

}

package com.ruoyi.system.domain.cuccMobileRequest;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Alan
 * @Date 2020/5/11 22:25
 * @Version 1.0
 */

@Data
public class LockNumAndApplyCodeRequest implements Serializable {
    private String certNum;
    private String cityCode;
    private String phoneNum;
    private String provinceCode;
    private String contactNum;
}

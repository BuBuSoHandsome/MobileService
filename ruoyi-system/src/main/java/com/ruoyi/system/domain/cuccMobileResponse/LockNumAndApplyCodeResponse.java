package com.ruoyi.system.domain.cuccMobileResponse;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Alan
 * @Date 2020/5/11 22:27
 * @Version 1.0
 */

@Data
public class LockNumAndApplyCodeResponse implements Serializable {

    private String code;
    private String message;
    private String custId;
}

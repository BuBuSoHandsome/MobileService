package com.ruoyi.system.domain.cuccMobileRequest;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Alan
 * @Date 2020/4/30 14:50
 * @Version 1.0
 */

@Data
public class CheckUserRequest implements Serializable{

    private String appKey = "A3B43FEF873E99CAE053491962842D74";

    private String certName;

    private String certNum;

    private String cityCode;

    private String provinceCode;

    private String secret = "kKpuL2DedCk0YfET";
}

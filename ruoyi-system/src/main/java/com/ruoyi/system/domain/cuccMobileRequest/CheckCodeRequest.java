package com.ruoyi.system.domain.cuccMobileRequest;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Alan
 * @Date 2020/5/4 14:39
 * @Version 1.0
 */

@Data
public class CheckCodeRequest implements Serializable {

    private String appKey = "A3B43FEF873E99CAE053491962842D74";
    private String certNo;
    private String channel= "08-2278-6984-9999";
    private String contactNum;
    private String safeCode;
    private String secret = "kKpuL2DedCk0YfET";

}

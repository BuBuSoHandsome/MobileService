package com.ruoyi.system.domain.cuccMobileRequest;

import lombok.Data;

/**
 * @Author Alan
 * @Date 2020/5/4 14:25
 * @Version 1.0
 */

@Data
public class CheckNumRequest {

    private String appKey = "A3B43FEF873E99CAE053491962842D74";

    private String certNum;

    private String goodsId;

    private String phoneNum;

    private String secret = "kKpuL2DedCk0YfET";

}

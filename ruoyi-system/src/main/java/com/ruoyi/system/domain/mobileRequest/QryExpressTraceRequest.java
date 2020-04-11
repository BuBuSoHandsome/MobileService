package com.ruoyi.system.domain.mobileRequest;

import lombok.Data;

@Data
public class QryExpressTraceRequest {

    private String orderid;
    private String servnumber;
    private String expressno;
    private String certid;
    private String telno;

}

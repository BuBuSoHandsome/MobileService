package com.ruoyi.system.domain.cuccMobileRequest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Alan
 * @Date 2020/5/4 14:28
 * @Version 1.0
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OccupationNumberRequest implements Serializable {

    private String appKey = "A3B43FEF873E99CAE053491962842D74";
    private String certNum;
    private String cityCode;
    private String occupiedFlag;
    private String occupiedTimeTag;
    private String phoneNum;
    private String proKey;
    private String provinceCode;
    private String secret = "kKpuL2DedCk0YfET";

}

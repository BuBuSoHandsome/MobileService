package com.ruoyi.system.domain.cuccMobileRequest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Alan
 * @Date 2020/5/3 21:50
 * @Version 1.0
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SelectNumRequest implements Serializable {

    private String appKey = "A3B43FEF873E99CAE053491962842D74";

    private String cityCode;

    private String goodsId;

    private String groupKey;

    private String provinceCode;

    private String searchCategory;

    private String searchValue;

    private String secret = "kKpuL2DedCk0YfET";

}

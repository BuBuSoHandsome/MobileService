package com.ruoyi.system.domain.cuccMobileResponse;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Alan
 * @Date 2020/5/4 14:35
 * @Version 1.0
 */

@Data
public class OccupationNumberResponse implements Serializable {
    private String code;
    private String message;
    private String success;
    private String data;
}

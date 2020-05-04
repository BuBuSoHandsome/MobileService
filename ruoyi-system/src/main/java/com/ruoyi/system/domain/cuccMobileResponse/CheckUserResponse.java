package com.ruoyi.system.domain.cuccMobileResponse;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Alan
 * @Date 2020/4/30 22:25
 * @Version 1.0
 */

@Data
public class CheckUserResponse implements Serializable {

    private String code;
    private String message;
    private String success;
    private String data;


}

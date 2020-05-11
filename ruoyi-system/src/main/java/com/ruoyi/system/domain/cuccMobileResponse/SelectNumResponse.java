package com.ruoyi.system.domain.cuccMobileResponse;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author Alan
 * @Date 2020/5/3 22:09
 * @Version 1.0
 */

@Data
public class SelectNumResponse implements Serializable{

    private String code;
    private String message;
    private String success;
    private String data;

}

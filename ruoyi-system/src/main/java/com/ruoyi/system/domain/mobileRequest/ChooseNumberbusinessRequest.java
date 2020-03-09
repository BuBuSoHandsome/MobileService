package com.ruoyi.system.domain.mobileRequest;

import lombok.Data;

import java.io.Serializable;

@Data

/**
 * 查询放号单品业务信息
 */

public class ChooseNumberbusinessRequest implements Serializable {

    //类型编码（此编码由选号卡类栏目查询接口得到）
    private String typecode;

}

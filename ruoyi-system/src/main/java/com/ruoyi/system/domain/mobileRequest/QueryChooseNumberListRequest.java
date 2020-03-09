package com.ruoyi.system.domain.mobileRequest;

import lombok.Data;

import java.io.Serializable;

/**
 * Datetime:    2020/3/9   14:13
 * Author:      bjl
 */

@Data

/**
 * 选号查询号码列表
 */

public class QueryChooseNumberListRequest implements Serializable {

    private String sid;

    private String region;

    private String tag;

    private String page;

    private String packagecode;
}

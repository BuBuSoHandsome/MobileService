package com.ruoyi.system.domain;

import lombok.Data;

@Data
public class AddressCode {

    /** 主键 */
    private Long fdId;

    /** 省/市名称 */
    private String name;

    /** 编码 */
    private String code;

    /** 上级编码 */
    private String parentCode;

    /** 类型（1：省，2：市） */
    private String type;

}

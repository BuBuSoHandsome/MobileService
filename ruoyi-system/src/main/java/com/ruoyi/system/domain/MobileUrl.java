package com.ruoyi.system.domain;



import lombok.Data;

import java.io.Serializable;

@Data
public class MobileUrl implements Serializable
{
    /** 主键 */
    private String fdId;

    /** 移动接口调用地址 */
    private String url;

    /** 接口标识 */
    private String eumn;


}

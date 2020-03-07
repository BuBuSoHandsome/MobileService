package com.ruoyi.system.domain.mobileResponse;

import lombok.Data;

@Data
public class ChooseNumberColumnResponse {
    /** 编码（唯一id） */
    private Long sid;

    /** 渠道 */
    private String channel;

    /** 显示位置 */
    private String position;

    /** 按钮文字 */
    private String text;

    /** 按钮图标 */
    private String pic;

    /** 卡类约束 */
    private String constraint;

    /** 状态 */
    private String status;

    /** 按钮描述 */
    private String describe;

    /** 套餐 */
    private String pack;

    /** 更新时间 */
    private String updatetime;

}

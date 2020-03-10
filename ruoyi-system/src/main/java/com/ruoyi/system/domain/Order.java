package com.ruoyi.system.domain;

/**
 * Datetime:    2020/3/10   14:17
 * Author:      bjl
 */

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 订单对象 order
 *
 * @author ruoyi
 * @date 2020-03-10
 */
@Data
public class Order extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    private String fdId;

    /**
     * 卡类编码
     */
    @Excel(name = "卡类编码")
    private String sid;

    /**
     * 套餐编码
     */
    @Excel(name = "套餐编码")
    private String pack;

    /**
     * 真实姓名
     */
    @Excel(name = "真实姓名")
    private String realname;

    /**
     * 联系方式
     */
    @Excel(name = "联系方式")
    private String phone;

    /**
     * 证件类型（01：身份证）
     */
    @Excel(name = "证件类型", readConverterExp = "0=1：身份证")
    private String cardtype;

    /**
     * 证件号码
     */
    @Excel(name = "证件号码")
    private String cardid;

    /**
     * 省份编码（200 ：广东省）
     */
    @Excel(name = "省份编码", readConverterExp = "200：广东省")
    private String provincecode;

    /**
     * 地市编码
     */
    @Excel(name = "地市编码")
    private String eparchycode;

    /**
     * 区县编码
     */
    @Excel(name = "区县编码")
    private String citycode;

    /**
     * 配送地址
     */
    @Excel(name = "配送地址")
    private String address;


    /**
     * 配送地址
     */
    @Excel(name = "配送地市中文")
    private String addressCity;

    /**
     * 订单状态（0：未下单，1：下单成功，2：下单失败）
     */
    @Excel(name = "订单状态", readConverterExp = "0：未下单，1：下单成功，2：下单失败")
    private String status;
}

package com.ruoyi.system.domain;

/**
 * Datetime:    2020/3/10   14:17
 * Author:      bjl
 */

import com.ruoyi.common.annotation.Excel;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 订单对象 order
 *
 * @author ruoyi
 * @date 2020-03-10
 */
@Data
public class Order {

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
    private String cardtype;

    /**
     * 证件号码
     */
    @Excel(name = "身份证号码")
    private String cardid;

    /**
     * 省份编码
     */
    private String provincecode;

    /**
     * 地市编码
     */
    private String eparchycode;

    /**
     * 区县编码
     */
    private String citycode;

    /**
     * 配送地址
     */
    @Excel(name = "配送地址")
    private String address;

    /**
     * 配送省份
     */
    @Excel(name = "配送省份")
    private String province;

    /**
     * 配送城市
     */
    @Excel(name = "配送城市")
    private String addressCity;

    /**
     * 订单状态（0：未下单，1：下单成功，2：下单失败 ，3：激活成功）
     */
    private String status;

    /**
     * 创建时间
     */
    @Excel(name = "下单时间")
    private String createTime;

    /**
     * 备注
     */
    @Excel(name = "备注")
    private String remark;

    /** 请求参数 */
    private Map<String, Object> params;

    public Map<String, Object> getParams()
    {
        if (params == null)
        {
            params = new HashMap<>();
        }
        return params;
    }

    public void setParams(Map<String, Object> params)
    {
        this.params = params;
    }
}

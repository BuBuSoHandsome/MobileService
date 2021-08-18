package com.ruoyi.system.domain.mobileRequest;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

/**
 * Datetime:    2020/3/11   10:40
 * Author:      bjl
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class newOrderParams implements Serializable {
    /**
     * 下单渠道（Boss渠道编码）很重要！
     */
    private String wayid;

    /**
     * 子店铺ID
     * （选填）
     */
    private String subsShopId;

    /**
     * 操作员ID（BOSS工号计酬相关）
     */
    private String operatorId;

    /**
     * 操作员名称
     */
    private String operatorName;

    /**
     * 号码归属地市编码
     */
    private String areaCode;

    /**
     * 号码归属地市名称
     */
    private String areaName;

    /**
     * 新购号码
     */
    private String servnumber;

    /**
     * 用户真实姓名
     */
    private String userName;

    /**
     * 品牌
     * （选填）
     */
    private String brand;

    /**
     * 受理方式（1-自助2-代客下单3-预约)
     * 此处填2
     */
    private String acceptType;

    /**
     * 收货方式（0-自动（移动套餐业务无需收货）1-快递2-上门服务3-到店自提 4-京东上门服务
     * 此处填4）
     */
    private String receiveType;

    /**
     * 订单金额(修改成非必填， 默认 0)
     * （选填）
     */
    private double orderAmount;

    /**
     * 优惠金额
     * （选填）
     */
    private double deductCash;

    /**
     * 支付方式（0货到付款1在线支付2到厅支付3语音支付4不需要支付)
     * 此处填1
     */
    private String payWay;

    /**
     * 联系方式
     */
    private String telno;

    /**
     * 证件号码
     */
    private String cerNo;

    /**
     * 证件类型（01-身份证）
     */
    private String cerType;

    /**
     * 邮政编码
     * （选填）
     */
    private String postCode;

    /**
     * 配送省份
     */
    private String province;

    /**
     * 配送地市
     */
    private String addressCity;

    /**
     * 配送区域
     * （选填）
     */
    private String addressArea;

    /**
     * 配送地址
     */
    private String address;

    /**
     * 配送地址经度
     * （选填）
     */
    private String addressLongitude;

    /**
     * 配送地址维度
     * （选填）
     */
    private String addressLatitude;

    /**
     * 推荐人号码
     * （选填）
     */
    private String referenceNumber;

    /**
     * 商品编码
     * (修改为必填)
     */
    private String mainprodid;

    /**
     * 商品名称
     * （选填）
     */
    private String mainprodname;

    /**
     * 内含话费
     * （选填）
     */
    private String charge;

    /**
     * 活动ID
     * （选填）
     */
    private String offerCompId;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 商品编码
     * （选填）
     */
    private String offerId;

    /**
     * 录取地市
     * （选填）
     */
    private String admissionCity;

    /**
     * 录取学校
     * （选填）
     */
    private String admissionUniversity;

    /**
     * 被录取专业
     * （选填）
     */
    private String admissionMajor;

    /**
     * 配卡方式（0-省仓线上配卡（默认值）
     * 1-O2O现场配卡
     * 2-O2O线上配卡
     * 3-外部电商自行配卡
     * 4-外部电商不支持上门（转省仓配卡）
     * ）
     *
     * API 传 值 此 处 为 3 !
     */
    private String offlineCard;

    /**
     * 推荐专区ID
     */
    private String recoid;
}

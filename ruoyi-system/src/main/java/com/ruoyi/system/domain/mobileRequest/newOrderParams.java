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
     */
    private String subsshopid;

    /**
     * 操作员ID（BOSS工号计酬相关）
     */
    private String operatorid;

    /**
     * 操作员名称
     */
    private String operatorname;

    /**
     * 号码归属地市编码
     */
    private String areacode;

    /**
     * 号码归属地市名称
     */
    private String areaname;

    /**
     * 新购号码
     */
    private String servnumber;

    /**
     * 用户真实姓名
     */
    private String username;

    /**
     * 品牌
     */
    private String brand;

    /**
     * 受理方式（1-自助2-代客下单3-预约
     * 此处填2）
     */
    private String accepttype;

    /**
     * 收货方式（0-自动（移动套餐业务无需收货）1-快递2-上门服务3-到店自提 4-京东上门服务
     * 此处填4）
     */
    private String receivetype;

    /**
     * 订单金额
     */
    private String orderamount;

    /**
     * 优惠金额
     */
    private String deductcash;

    /**
     * 支付方式（0货到付款1在线支付2到厅支付3语音支付4不需要支付
     * 此处填1）
     */
    private String payway;

    /**
     * 联系方式
     */
    private String telno;

    /**
     * 证件号码
     */
    private String cerno;

    /**
     * 证件类型（01-身份证）
     */
    private String certype;

    /**
     * 邮政编码
     */
    private String postcode;

    /**
     * 配送省份（如果是广东省可以不填）
     */
    private String province;

    /**
     * 配送地市
     */
    private String addresscity;

    /**
     * 配送区域（如果是广东省可以不填）
     */
    private String addressarea;

    /**
     * 配送地址
     */
    private String address;

    /**
     * 配送地址经度
     */
    private String addresslongitude;

    /**
     * 配送地址维度
     */
    private String addresslatitude;

    /**
     * 推荐人号码
     */
    private String referencenumber;

    /**
     * 主套餐ID
     */
    private String mainprodid;

    /**
     * 主套餐名称
     */
    private String mainprodname;

    /**
     * 营销方案编码
     */
    private String goodsid;

    /**
     * 营销方案名称
     */
    private String goodsname;

    /**
     * 内含话费
     */
    private String charge;

    /**
     * 活动ID
     */
    private String offercompid;

    /**
     * 订单ID
     */
    private String orderid;

    /**
     * 商品编码
     */
    private String offerId;

    /**
     * 录取地市
     */
    private String admissioncity;

    /**
     * 录取学校
     */
    private String admissionuniversity;

    /**
     * 被录取专业
     */
    private String admissionmajor;

    /**
     * 配卡方式（0-省仓线上配卡（默认值）
     * 1-O2O现场配卡
     * 2-O2O线上配卡
     * 3-外部电商自行配卡
     * 4-外部电商不支持上门（转省仓配卡）
     * ）
     */
    private String offlinecard;
}

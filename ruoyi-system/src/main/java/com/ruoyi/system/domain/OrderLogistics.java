package com.ruoyi.system.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单物流对象 order_logistics
 * 
 * @author ruoyi
 * @date 2020-03-15
 */

@Data
public class OrderLogistics
{

    /** 主键 */
    private String fdId;

    /** 订单id */
    private String orderId;

    /** 业务类型 */
    private String orderType;

    /** 订单大类型 */
    private String orderBigType;

    /** 订单状态 */
    private String orderStatus;

    /** 工号 */
    private String operatorId;

    /** 渠道类型 */
    private String chnlCode;

    /** 下单渠道 */
    private String wayId;

    /** 下单地市名词 */
    private String areaName;

    /** 下单地市编码 */
    private String areaCode;

    /** 创建时间**/
    private String createTime;

    /** 结束时间 */
    private String finishTime;

    /** 用户姓名 */
    private String username;

    /** 手机号码 */
    private String servnumber;

    /** 订单备注 */
    private String orderRemark;

    /** 物流单号 */
    private String expressno;

}

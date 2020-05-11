package com.ruoyi.system.domain;

import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

import java.io.Serializable;

/**
 * 联通号码预占对象 number_cucc
 * 
 * @author ruoyi
 * @date 2020-05-11
 */

@Data
public class NumberCucc implements Serializable
{
    /** 占号资源id */
    private String custid;

    /** 产品id */
    private String producttype;

    /** 卡归属地省编码 */
    private String provincecode;

    /** 卡归属地市编码 */
    private String citycode;

    /** 选号（新号码） */
    private String phonenum;

    /** 号码状态标识*/
    private String occupiedflag;

    /** 预占时间标记 */
    private String occupiedtimetag;

    /** 身份证 */
    private String certnum;

}

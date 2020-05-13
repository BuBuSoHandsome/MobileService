package com.ruoyi.system.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 联通订单表 order_cucc
 * @author jyw
 * @date 2020-05-12
 */
@Data
public class OrderCucc
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private String fdId;
	/** 产品编号 */
	private String productType;
	/** 归属地省编码 */
	private String provinceCode;
	/** 归属地地市编码 */
	private String cityCode;
	/** 所选号码 */
	private String phoneNum;
	/** 姓名 */
	private String certName;
	/** 身份证号 */
	private String certNo;
	/** 联系号码 */
	private String contactNum;
	/** 占号资源id */
	private String custId;
	/** 收货省编码 */
	private String postProvinceCode;
	/** 收货地市编码 */
	private String postCityCode;
	/** 收货区编码 */
	private String postDistrictCode;
	/** 详细收货地址 */
	private String address;
	/** 订单状态 */
	private String status;
	/** 更新时间 */
	private String updateTime;
	/** 备注 */
	private String remark;
	/** 验证码回传参数*/
	private String captchaId;

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

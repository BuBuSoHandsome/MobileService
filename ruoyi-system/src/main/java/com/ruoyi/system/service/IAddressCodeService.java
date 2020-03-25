package com.ruoyi.system.service;

import com.ruoyi.system.domain.AddressCode;
import java.util.List;

/**
 * 省市编码Service接口
 * 
 * @author ruoyi
 * @date 2020-03-21
 */
public interface IAddressCodeService 
{
    /**
     * 查询省市编码
     * 
     * @param fdId 省市编码ID
     * @return 省市编码
     */
    public AddressCode selectAddressCodeById(Long fdId);

    /**
     * 查询省市编码列表
     * 
     * @param addressCode 省市编码
     * @return 省市编码集合
     */
    public List<AddressCode> selectAddressCodeList(AddressCode addressCode);

    /**
     * 新增省市编码
     * 
     * @param addressCode 省市编码
     * @return 结果
     */
    public int insertAddressCode(AddressCode addressCode);

    /**
     * 修改省市编码
     * 
     * @param addressCode 省市编码
     * @return 结果
     */
    public int updateAddressCode(AddressCode addressCode);

    /**
     * 批量删除省市编码
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAddressCodeByIds(String ids);

    /**
     * 删除省市编码信息
     * 
     * @param fdId 省市编码ID
     * @return 结果
     */
    public int deleteAddressCodeById(Long fdId);
}

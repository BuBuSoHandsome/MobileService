package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.AddressCodeMapper;
import com.ruoyi.system.domain.AddressCode;
import com.ruoyi.system.service.IAddressCodeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 省市编码Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-03-21
 */
@Service
public class AddressCodeServiceImpl implements IAddressCodeService 
{
    @Autowired
    private AddressCodeMapper addressCodeMapper;

    /**
     * 查询省市编码
     * 
     * @param fdId 省市编码ID
     * @return 省市编码
     */
    @Override
    public AddressCode selectAddressCodeById(Long fdId)
    {
        return addressCodeMapper.selectAddressCodeById(fdId);
    }

    /**
     * 查询省市编码列表
     * 
     * @param addressCode 省市编码
     * @return 省市编码
     */
    @Override
    public List<AddressCode> selectAddressCodeList(AddressCode addressCode)
    {
        return addressCodeMapper.selectAddressCodeList(addressCode);
    }

    /**
     * 新增省市编码
     * 
     * @param addressCode 省市编码
     * @return 结果
     */
    @Override
    public int insertAddressCode(AddressCode addressCode)
    {
        return addressCodeMapper.insertAddressCode(addressCode);
    }

    /**
     * 修改省市编码
     * 
     * @param addressCode 省市编码
     * @return 结果
     */
    @Override
    public int updateAddressCode(AddressCode addressCode)
    {
        return addressCodeMapper.updateAddressCode(addressCode);
    }

    /**
     * 删除省市编码对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAddressCodeByIds(String ids)
    {
        return addressCodeMapper.deleteAddressCodeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除省市编码信息
     * 
     * @param fdId 省市编码ID
     * @return 结果
     */
    @Override
    public int deleteAddressCodeById(Long fdId)
    {
        return addressCodeMapper.deleteAddressCodeById(fdId);
    }
}

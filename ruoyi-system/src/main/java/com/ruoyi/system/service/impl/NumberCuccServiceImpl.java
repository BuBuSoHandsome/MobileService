package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.NumberCuccMapper;
import com.ruoyi.system.domain.NumberCucc;
import com.ruoyi.system.service.INumberCuccService;
import com.ruoyi.common.core.text.Convert;

/**
 * 联通号码预占Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-05-11
 */
@Service
public class NumberCuccServiceImpl implements INumberCuccService 
{
    @Autowired
    private NumberCuccMapper numberCuccMapper;

    /**
     * 查询联通号码预占
     * 
     * @param custid 联通号码预占ID
     * @return 联通号码预占
     */
    @Override
    public NumberCucc selectNumberCuccById(String custid)
    {
        return numberCuccMapper.selectNumberCuccById(custid);
    }

    /**
     * 查询联通号码预占列表
     * 
     * @param numberCucc 联通号码预占
     * @return 联通号码预占
     */
    @Override
    public List<NumberCucc> selectNumberCuccList(NumberCucc numberCucc)
    {
        return numberCuccMapper.selectNumberCuccList(numberCucc);
    }

    /**
     * 新增联通号码预占
     * 
     * @param numberCucc 联通号码预占
     * @return 结果
     */
    @Override
    public int insertNumberCucc(NumberCucc numberCucc)
    {
        return numberCuccMapper.insertNumberCucc(numberCucc);
    }

    /**
     * 修改联通号码预占
     * 
     * @param numberCucc 联通号码预占
     * @return 结果
     */
    @Override
    public int updateNumberCucc(NumberCucc numberCucc)
    {
        return numberCuccMapper.updateNumberCucc(numberCucc);
    }

    /**
     * 删除联通号码预占对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteNumberCuccByIds(String ids)
    {
        return numberCuccMapper.deleteNumberCuccByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除联通号码预占信息
     * 
     * @param custid 联通号码预占ID
     * @return 结果
     */
    @Override
    public int deleteNumberCuccById(String custid)
    {
        return numberCuccMapper.deleteNumberCuccById(custid);
    }
}

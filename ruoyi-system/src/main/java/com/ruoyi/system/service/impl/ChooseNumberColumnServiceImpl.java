package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.utils.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.ChooseNumberColumnMapper;
import com.ruoyi.system.domain.ChooseNumberColumn;
import com.ruoyi.system.service.IChooseNumberColumnService;
import com.ruoyi.common.core.text.Convert;

/**
 * 选号卡类栏目Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-01-01
 */
@Service
public class ChooseNumberColumnServiceImpl implements IChooseNumberColumnService 
{
    @Autowired
    private ChooseNumberColumnMapper chooseNumberColumnMapper;

    @Autowired
    private RedisUtil redisUtil;

    /**
     * 查询选号卡类栏目
     * 
     * @param sid 选号卡类栏目ID
     * @return 选号卡类栏目
     */
    @Override
    public ChooseNumberColumn selectChooseNumberColumnById(Long sid)
    {
        return chooseNumberColumnMapper.selectChooseNumberColumnById(sid);
    }

    /**
     * 查询选号卡类栏目列表
     * 
     * @param chooseNumberColumn 选号卡类栏目
     * @return 选号卡类栏目
     */
    @Override
    public List<ChooseNumberColumn> selectChooseNumberColumnList(ChooseNumberColumn chooseNumberColumn)
    {
        return chooseNumberColumnMapper.selectChooseNumberColumnList(chooseNumberColumn);
    }

    /**
     * 新增选号卡类栏目
     * 
     * @param chooseNumberColumn 选号卡类栏目
     * @return 结果
     */
    @Override
    public int insertChooseNumberColumn(ChooseNumberColumn chooseNumberColumn)
    {
        return chooseNumberColumnMapper.insertChooseNumberColumn(chooseNumberColumn);
    }

    /**
     * 修改选号卡类栏目
     * 
     * @param chooseNumberColumn 选号卡类栏目
     * @return 结果
     */
    @Override
    public int updateChooseNumberColumn(ChooseNumberColumn chooseNumberColumn)
    {
        return chooseNumberColumnMapper.updateChooseNumberColumn(chooseNumberColumn);
    }

    /**
     * 删除选号卡类栏目对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteChooseNumberColumnByIds(String ids)
    {
        return chooseNumberColumnMapper.deleteChooseNumberColumnByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除选号卡类栏目信息
     * 
     * @param sid 选号卡类栏目ID
     * @return 结果
     */
    @Override
    public int deleteChooseNumberColumnById(Long sid)
    {
        return chooseNumberColumnMapper.deleteChooseNumberColumnById(sid);
    }

    @Override
    public boolean testRedis(String key , String value) {




        return redisUtil.set(key, value);
    }
}

package com.ruoyi.system.service;

import com.ruoyi.system.domain.ChooseNumberColumn;
import java.util.List;

/**
 * 【选号卡类栏目】Service接口
 * 
 * @author ruoyi
 * @date 2020-01-01
 */
public interface IChooseNumberColumnService 
{
    /**
     * 查询【选号卡类栏目】
     * 
     * @param sid 【选号卡类栏目】ID
     * @return 【选号卡类栏目】
     */
    public ChooseNumberColumn selectChooseNumberColumnById(Long sid);

    /**
     * 查询【选号卡类栏目】列表
     * 
     * @param chooseNumberColumn 【选号卡类栏目】
     * @return 【选号卡类栏目】集合
     */
    public List<ChooseNumberColumn> selectChooseNumberColumnList(ChooseNumberColumn chooseNumberColumn);

    /**
     * 新增【选号卡类栏目】
     * 
     * @param chooseNumberColumn 【选号卡类栏目】
     * @return 结果
     */
    public int insertChooseNumberColumn(ChooseNumberColumn chooseNumberColumn);

    /**
     * 修改【选号卡类栏目】
     * 
     * @param chooseNumberColumn 【选号卡类栏目】
     * @return 结果
     */
    public int updateChooseNumberColumn(ChooseNumberColumn chooseNumberColumn);

    /**
     * 批量删除【选号卡类栏目】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChooseNumberColumnByIds(String ids);

    /**
     * 删除【选号卡类栏目】信息
     * 
     * @param sid 【选号卡类栏目】ID
     * @return 结果
     */
    public int deleteChooseNumberColumnById(Long sid);

    public boolean testRedis(String key,String value);

}

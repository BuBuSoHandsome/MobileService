package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.ChooseNumberColumn;
import java.util.List;

/**
 * 【选号卡类栏目】Mapper接口
 * 
 * @author ruoyi
 * @date 2020-01-01
 */
public interface ChooseNumberColumnMapper 
{

    /**
     * 查询所有【选号卡类栏目】
     *
     *
     * @return 【选号卡类栏目】
     */
    public List<ChooseNumberColumn> selectAllChooseNumberColumnList();

    /**
     * 查询【选号卡类栏目】
     * 
     * @param sid 【选号卡类栏目】ID
     * @return 【选号卡类栏目】
     */
    public ChooseNumberColumn selectChooseNumberColumnById(String sid);

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
     * 删除【选号卡类栏目】
     * 
     * @param sid 【选号卡类栏目】ID
     * @return 结果
     */
    public int deleteChooseNumberColumnById(Long sid);

    /**
     * 批量删除【选号卡类栏目】
     * 
     * @param sids 需要删除的数据ID
     * @return 结果
     */
    public int deleteChooseNumberColumnByIds(String[] sids);

    /**
     * 全部删除【选号卡类栏目】
     *
     * @return 结果
     */
    public int deleteAll();


}

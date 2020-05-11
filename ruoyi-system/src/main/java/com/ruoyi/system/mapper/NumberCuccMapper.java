package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.NumberCucc;
import java.util.List;

/**
 * 联通号码预占Mapper接口
 * 
 * @author ruoyi
 * @date 2020-05-11
 */
public interface NumberCuccMapper 
{
    /**
     * 查询联通号码预占
     * 
     * @param custid 联通号码预占ID
     * @return 联通号码预占
     */
    public NumberCucc selectNumberCuccById(String custid);

    /**
     * 查询联通号码预占列表
     * 
     * @param numberCucc 联通号码预占
     * @return 联通号码预占集合
     */
    public List<NumberCucc> selectNumberCuccList(NumberCucc numberCucc);

    /**
     * 新增联通号码预占
     * 
     * @param numberCucc 联通号码预占
     * @return 结果
     */
    public int insertNumberCucc(NumberCucc numberCucc);

    /**
     * 修改联通号码预占
     * 
     * @param numberCucc 联通号码预占
     * @return 结果
     */
    public int updateNumberCucc(NumberCucc numberCucc);

    /**
     * 删除联通号码预占
     * 
     * @param custid 联通号码预占ID
     * @return 结果
     */
    public int deleteNumberCuccById(String custid);

    /**
     * 批量删除联通号码预占
     * 
     * @param custids 需要删除的数据ID
     * @return 结果
     */
    public int deleteNumberCuccByIds(String[] custids);
}

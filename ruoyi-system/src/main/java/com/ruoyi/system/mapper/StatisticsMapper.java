package com.ruoyi.system.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface StatisticsMapper {

    public List<Map<String,Object>> provinceOrder(@Param("date") String date);

    public List<Map<String,Object>> orderStatus(@Param("date") String date);

    public List<Map<String, Object>> getHoursData(@Param("beginTime") String beginTime,@Param("endTime") String endTime);

    public List<Map<String, Object>> orderTrend(@Param("year") String year,@Param("provinceName") String provinceName);

    public List<Map<String,Object>> getProvinceList();

    public List<Map<String,Object>> getEachProvinceChart(@Param("startTime") String startTime,@Param("endTime") String endTime);

    public List<Map<String,Object>>getOrderStatusChart(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("provinceName") String provinceName);
    public List<Map<String,Object>>orderLogisticsStatusChart(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("provinceName") String provinceName);

    public List<Map<String,Object>> getEachhourChart(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("provinceName") String provinceName);

    public List<Map<String,Object>> simTypeChart(@Param("startTime") String startTime,@Param("endTime") String endTime,@Param("provinceName") String provinceName);

}

package com.ruoyi.system.service;

import java.util.List;
import java.util.Map;

public interface IStatisticsService {

    public List<Map<String,Object>> provinceOrder(String date);

    public List<Map<String,Object>> orderStatus(String date);

    public  List<Map<String,Object>> getHoursData(String beginTime,String endTime);

    public List<Map<String,Object>> orderTrend(String year,String provinceName);

    public List<Map<String,Object>> getProvinceList();

}

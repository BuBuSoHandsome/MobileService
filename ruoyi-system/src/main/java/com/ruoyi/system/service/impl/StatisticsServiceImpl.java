package com.ruoyi.system.service.impl;

import com.ruoyi.system.mapper.StatisticsMapper;
import com.ruoyi.system.service.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class StatisticsServiceImpl implements IStatisticsService {

    @Autowired
    private StatisticsMapper statisticsMapper;

    @Override
    public List<Map<String, Object>> provinceOrder(String date) {
        System.out.println(statisticsMapper.provinceOrder(date));
        return statisticsMapper.provinceOrder(date);
    }

    @Override
    public List<Map<String, Object>> orderStatus(String date) {
        return statisticsMapper.orderStatus(date);
    }

    @Override
    public List<Map<String, Object>> getHoursData(String beginTime, String endTime) {
        return statisticsMapper.getHoursData(beginTime,endTime);
    }

    @Override
    public List<Map<String, Object>> orderTrend(String year, String provinceName) {
        return statisticsMapper.orderTrend(year,provinceName);
    }

    @Override
    public List<Map<String,Object>> getProvinceList(){
        return statisticsMapper.getProvinceList();
    }

}

package com.ruoyi.system.service.impl;

import com.ruoyi.system.mapper.StatisticsMapper;
import com.ruoyi.system.service.IStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
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

    @Override
    public Map<String,Object>getAllChartData(String startTime, String endTime, String provinceId,String provinceName){

        List<Map<String,Object>> eachProvince = statisticsMapper.getEachProvinceChart(startTime,endTime);//全国各地区订单量
        List<Map<String,Object>> orderList = statisticsMapper.getOrderStatusChart(startTime,endTime,provinceName);//订单状态统计
        List<Map<String,Object>> orderLogisticsList = statisticsMapper.orderLogisticsStatusChart(startTime,endTime,provinceName);//订单物流状态统计
        List<Map<String,Object>> eachHour = statisticsMapper.getEachhourChart(startTime,endTime,provinceName);//各时段订单量
        List<Map<String,Object>> simType = statisticsMapper.simTypeChart(startTime,endTime,provinceName);//sim卡类型订单量与成功订单量统计
        Map<String,Object> resultMap  = new HashMap<String,Object>();
        resultMap.put("eachProvince",eachProvince);
        resultMap.put("orderChart",orderList);
        resultMap.put("orderLChart",orderLogisticsList);
        resultMap.put("eachHour",eachHour);
        resultMap.put("simType",simType);

        return resultMap;
    }

}

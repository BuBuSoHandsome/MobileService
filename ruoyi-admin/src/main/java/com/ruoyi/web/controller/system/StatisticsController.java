package com.ruoyi.web.controller.system;


import com.ruoyi.system.service.IStatisticsService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/system/statistics")
public class StatisticsController {
    private String prefix = "system/statistics";

    @Autowired
    private IStatisticsService iStatisticsService;

    @RequiresPermissions("system:statistics:view")
    @GetMapping()
    public String statistics(ModelMap mmap)
    {
        mmap.put("province", iStatisticsService.getProvinceList());//全国各省的数据
        return prefix + "/statistics";
    }


    /*
    * 全国各省订单统计
    * */
    @GetMapping("/provinceOrder")
    @ResponseBody
    public List<Map<String,Object>> test(String date){
        return iStatisticsService.provinceOrder(date);
    }

    /*
    * 下单状态
    * */
    @GetMapping("/orderStatus")
    @ResponseBody
    public List<Map<String,Object>> orderStatus(String date){
        return iStatisticsService.orderStatus(date);
    }

    /*
    * 统计每个时间段的订单量
    * */
    @GetMapping("/getHoursData")
    @ResponseBody
    public  List<Map<String,Object>> getHoursData(String beginTime,String endTime){
        return iStatisticsService.getHoursData(beginTime,endTime);
    }

    /*
    * 统计客户下定单数量趋势
    * */
    @GetMapping("/orderTrend")
    @ResponseBody
    public List<Map<String,Object>> orderTrend(String year,String provinceId,String provinceName){
        return iStatisticsService.orderTrend(year,provinceName);
    }

}

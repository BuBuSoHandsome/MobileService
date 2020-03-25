package com.ruoyi.quartz.task;

import com.ruoyi.system.service.MobileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.ruoyi.common.utils.StringUtils;

/**
 * 定时任务调度测试
 * 
 * @author ruoyi
 */
@Component("ryTask")
public class RyTask
{

    @Autowired
    private MobileService mobileService;

    public void ryMultipleParams(String s, Boolean b, Long l, Double d, Integer i)
    {
        System.out.println(StringUtils.format("执行多参方法： 字符串类型{}，布尔类型{}，长整型{}，浮点型{}，整形{}", s, b, l, d, i));
    }

    public void ryParams(String params)
    {
        System.out.println("执行有参方法：" + params);
    }

    public void ryNoParams()
    {
        System.out.println("执行无参方法");
    }


    public void testNice(){
        for (int i=0;i<10;i++){
            System.out.println("这也忒帅了吧");
        }
    }

    //更新一次 选号卡类栏目
    public void refreshChooseNumberColumn(){
        System.out.println(mobileService.queryChooseNumberColumn());
    }


}

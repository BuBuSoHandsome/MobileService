package com.ruoyi.web.controller.system;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.OrderLogistics;
import com.ruoyi.system.service.IOrderLogisticsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单物流Controller
 * 
 * @author ruoyi
 * @date 2020-03-15
 */
@Controller
@RequestMapping("/system/logistics")
public class OrderLogisticsController extends BaseController
{
    private String prefix = "system/logistics";

    @Autowired
    private IOrderLogisticsService orderLogisticsService;

    @RequiresPermissions("system:logistics:view")
    @GetMapping()
    public String logistics()
    {
        return prefix + "/logistics";
    }

    /**
     * 查询订单物流列表
     */
    @RequiresPermissions("system:logistics:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OrderLogistics orderLogistics)
    {
        startPage();
        List<OrderLogistics> list = orderLogisticsService.selectOrderLogisticsList(orderLogistics);
        return getDataTable(list);
    }

    /**
     * 导出订单物流列表
     */
    @RequiresPermissions("system:logistics:export")
    @Log(title = "订单物流", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OrderLogistics orderLogistics)
    {
        List<OrderLogistics> list = orderLogisticsService.selectOrderLogisticsList(orderLogistics);
        ExcelUtil<OrderLogistics> util = new ExcelUtil<OrderLogistics>(OrderLogistics.class);
        return util.exportExcel(list, "logistics");
    }

    /**
     * 新增订单物流
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存订单物流
     */
    @RequiresPermissions("system:logistics:add")
    @Log(title = "订单物流", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OrderLogistics orderLogistics)
    {
        return toAjax(orderLogisticsService.insertOrderLogistics(orderLogistics));
    }

    /**
     * 修改订单物流
     */
    @GetMapping("/edit/{fdId}")
    public String edit(@PathVariable("fdId") String fdId, ModelMap mmap)
    {
        OrderLogistics orderLogistics = orderLogisticsService.selectOrderLogisticsById(fdId);
        mmap.put("orderLogistics", orderLogistics);
        return prefix + "/edit";
    }

    /**
     * 修改保存订单物流
     */
    @RequiresPermissions("system:logistics:edit")
    @Log(title = "订单物流", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OrderLogistics orderLogistics)
    {
        return toAjax(orderLogisticsService.updateOrderLogistics(orderLogistics));
    }

    /**
     * 删除订单物流
     */
    @RequiresPermissions("system:logistics:remove")
    @Log(title = "订单物流", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(orderLogisticsService.deleteOrderLogisticsByIds(ids));
    }
}

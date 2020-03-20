package com.ruoyi.web.controller.system;

import java.util.List;
import java.util.UUID;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.ChooseNumberColumn;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.mobileRequest.DSAirpickinstallQueryOrderRequest;
import com.ruoyi.system.domain.mobileResponse.DSAirpickinstallQueryOrderResponse;
import com.ruoyi.system.mapper.ChooseNumberColumnMapper;
import com.ruoyi.system.service.TestMobileService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.Order;
import com.ruoyi.system.service.IOrderService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * 订单Controller
 * 
 * @author ruoyi
 * @date 2020-03-10
 */
@Controller
@RequestMapping("/system/order")
public class OrderController extends BaseController
{
    private String prefix = "system/order";

    @Autowired
    private IOrderService orderService;

    @Autowired
    private TestMobileService testMobileService;

    @Resource
    private ChooseNumberColumnMapper chooseNumberColumnMapper;

    @RequiresPermissions("system:order:view")
    @GetMapping()
    public String order()
    {
        return prefix + "/order";
    }

    /**
     * 查询订单列表
     */
    @RequiresPermissions("system:order:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Order order)
    {
        startPage();
        List<Order> list = orderService.selectOrderList(order);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @RequiresPermissions("system:order:export")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Order order)
    {
        List<Order> list = orderService.selectOrderList(order);
        ExcelUtil<Order> util = new ExcelUtil<Order>(Order.class);
        return util.exportExcel(list, "order");
    }

    /**
     * 新增订单
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存订单
     */
    @RequiresPermissions("system:order:add")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Order order)
    {
        order.setFdId(StringUtils.generateRandomString(12).toUpperCase());
        ChooseNumberColumn chooseNumberColumn = chooseNumberColumnMapper.selectChooseNumberColumnById(Long.parseLong(order.getSid()));
        order.setPack(chooseNumberColumn.getPack());
        return toAjax(orderService.insertOrder(order));
    }

    /**
     * 修改订单
     */
    @GetMapping("/edit/{fdId}")
    public String edit(@PathVariable("fdId") String fdId, ModelMap mmap)
    {
        Order order = orderService.selectOrderById(fdId);
        mmap.put("order", order);
        return prefix + "/edit";
    }

    /**
     * 修改保存订单
     */
    @RequiresPermissions("system:order:edit")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Order order)
    {
        ChooseNumberColumn chooseNumberColumn = chooseNumberColumnMapper.selectChooseNumberColumnById(Long.parseLong(order.getSid()));
        order.setPack(chooseNumberColumn.getPack());
        return toAjax(orderService.updateOrder(order));
    }

    /**
     * 删除订单
     */
    @RequiresPermissions("system:order:remove")
    @Log(title = "订单", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(orderService.deleteOrderByIds(ids));
    }

    /**
     * 下订单
     * @param ids
     * @return
     */
    @PostMapping("/installOrder")
    @ResponseBody
    public AjaxResult installOrderUrl(String ids){
        return success(orderService.installOrder(ids));
    }

    /**
     * 查询订单信息
     * @param request
     * @return
     */
    @PostMapping("getOrderMsg")
    public DSAirpickinstallQueryOrderResponse getOrderMsg(@RequestBody DSAirpickinstallQueryOrderRequest request){
        return testMobileService.getOrderMsg(request);
    }

    /**
     * 更新订单状态
     * @param ids
     * @return
     */
    @PostMapping("/refreshOrderStatus")
    @ResponseBody
    public AjaxResult checkOrderStatus(String ids){
        return success(orderService.refreshOrderStatus(ids));
    }

    /**
     * 批量导入数据
     * @param file
     * @return
     * @throws Exception
     */
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file) throws Exception
    {
        ExcelUtil<Order> util = new ExcelUtil<Order>(Order.class);
        List<Order> orderList = util.importExcel(file.getInputStream());
        String message = orderService.importOrder(orderList);
        return AjaxResult.success(message);
    }


    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<Order> util = new ExcelUtil<Order>(Order.class);
        return util.importTemplateExcel("线上放号订单数据");
    }


}

package com.ruoyi.web.controller.system;

import java.util.List;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;
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
import com.ruoyi.system.domain.OrderCucc;
import com.ruoyi.system.service.IOrderCuccService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 联通订单 信息操作处理
 * 
 * @author jyw
 * @date 2020-05-12
 */
@Controller
@RequestMapping("/system/orderCucc")
public class OrderCuccController extends BaseController
{
    private String prefix = "system/orderCucc";
	
	@Autowired
	private IOrderCuccService orderCuccService;
	
	@RequiresPermissions("system:orderCucc:view")
	@GetMapping()
	public String orderCucc()
	{
	    return prefix + "/orderCucc";
	}
	
	/**
	 * 查询联通订单列表
	 */
	@RequiresPermissions("system:orderCucc:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(OrderCucc orderCucc)
	{
		startPage();
        List<OrderCucc> list = orderCuccService.selectOrderCuccList(orderCucc);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出联通订单列表
	 */
	@RequiresPermissions("system:orderCucc:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OrderCucc orderCucc)
    {
    	List<OrderCucc> list = orderCuccService.selectOrderCuccList(orderCucc);
        ExcelUtil<OrderCucc> util = new ExcelUtil<OrderCucc>(OrderCucc.class);
        return util.exportExcel(list, "orderCucc");
    }
	
	/**
	 * 新增联通订单
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存联通订单
	 */
	@RequiresPermissions("system:orderCucc:add")
	@Log(title = "联通订单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(OrderCucc orderCucc)
	{
		orderCucc.setFdId(StringUtils.generateRandomString(12).toUpperCase());
		orderCucc.setCustId("custId");
		return toAjax(orderCuccService.insertOrderCucc(orderCucc));
	}

	/**
	 * 修改联通订单
	 */
	@GetMapping("/edit/{fdId}")
	public String edit(@PathVariable("fdId") String fdId, ModelMap mmap)
	{
		OrderCucc orderCucc = orderCuccService.selectOrderCuccById(fdId);
		mmap.put("orderCucc", orderCucc);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存联通订单
	 */
	@RequiresPermissions("system:orderCucc:edit")
	@Log(title = "联通订单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(OrderCucc orderCucc)
	{		
		return toAjax(orderCuccService.updateOrderCucc(orderCucc));
	}
	
	/**
	 * 删除联通订单
	 */
	@RequiresPermissions("system:orderCucc:remove")
	@Log(title = "联通订单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids[])
	{		
		return toAjax(orderCuccService.deleteOrderCuccByIds(ids));
	}
	
}

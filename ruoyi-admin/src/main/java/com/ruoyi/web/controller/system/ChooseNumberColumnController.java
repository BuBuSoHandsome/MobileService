package com.ruoyi.web.controller.system;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.ChooseNumberColumn;
import com.ruoyi.system.service.IChooseNumberColumnService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【选号卡类栏目】Controller
 * 
 * @author ruoyi
 * @date 2020-01-01
 */
@Controller
@RequestMapping("/system/column")
public class ChooseNumberColumnController extends BaseController
{
    private String prefix = "system/column";

    @Autowired
    private IChooseNumberColumnService chooseNumberColumnService;

    //@RequiresPermissions("system:column:view")
    @GetMapping()
    public String column()
    {
        return prefix + "/column";
    }

    /**
     * 查询【选号卡类栏目】列表
     */
    //@RequiresPermissions("system:column:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ChooseNumberColumn chooseNumberColumn)
    {
        startPage();
        List<ChooseNumberColumn> list = chooseNumberColumnService.selectChooseNumberColumnList(chooseNumberColumn);
        return getDataTable(list);
    }

    /**
     * 导出【选号卡类栏目】列表
     */
    //@RequiresPermissions("system:column:export")
    @Log(title = "【选号卡类栏目】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ChooseNumberColumn chooseNumberColumn)
    {
        List<ChooseNumberColumn> list = chooseNumberColumnService.selectChooseNumberColumnList(chooseNumberColumn);
        ExcelUtil<ChooseNumberColumn> util = new ExcelUtil<ChooseNumberColumn>(ChooseNumberColumn.class);
        return util.exportExcel(list, "column");
    }

    /**
     * 新增【选号卡类栏目】
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存【选号卡类栏目】
     */
    //@RequiresPermissions("system:column:add")
    @Log(title = "【选号卡类栏目】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ChooseNumberColumn chooseNumberColumn)
    {
        return toAjax(chooseNumberColumnService.insertChooseNumberColumn(chooseNumberColumn));
    }

    /**
     * 修改【选号卡类栏目】
     */
    @GetMapping("/edit/{sid}")
    public String edit(@PathVariable("sid") String sid, ModelMap mmap)
    {
        ChooseNumberColumn chooseNumberColumn = chooseNumberColumnService.selectChooseNumberColumnById(sid);
        mmap.put("chooseNumberColumn", chooseNumberColumn);
        return prefix + "/edit";
    }

    /**
     * 修改保存【选号卡类栏目】
     */
    //@RequiresPermissions("system:column:edit")
    @Log(title = "【选号卡类栏目】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ChooseNumberColumn chooseNumberColumn)
    {
        return toAjax(chooseNumberColumnService.updateChooseNumberColumn(chooseNumberColumn));
    }

    /**
     * 删除【选号卡类栏目】
     */
    //@RequiresPermissions("system:column:remove")
    @Log(title = "【选号卡类栏目】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(chooseNumberColumnService.deleteChooseNumberColumnByIds(ids));
    }
}

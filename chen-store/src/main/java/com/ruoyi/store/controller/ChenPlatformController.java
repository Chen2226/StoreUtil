package com.ruoyi.store.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.store.domain.ChenPlatform;
import com.ruoyi.store.service.IChenPlatformService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 平台Controller
 * 
 * @author chen
 * @date 2023-03-02
 */
@RestController
@RequestMapping("/platform/platformList")
public class ChenPlatformController extends BaseController
{
    @Autowired
    private IChenPlatformService chenPlatformService;

    /**
     * 查询平台列表
     */
    @PreAuthorize("@ss.hasPermi('platform:platformList:list')")
    @GetMapping("/list")
    public TableDataInfo list(ChenPlatform chenPlatform)
    {
        startPage();
        List<ChenPlatform> list = chenPlatformService.selectChenPlatformList(chenPlatform);
        return getDataTable(list);
    }

    /**
     * 导出平台列表
     */
    @PreAuthorize("@ss.hasPermi('platform:platformList:export')")
    @Log(title = "平台", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChenPlatform chenPlatform)
    {
        List<ChenPlatform> list = chenPlatformService.selectChenPlatformList(chenPlatform);
        ExcelUtil<ChenPlatform> util = new ExcelUtil<ChenPlatform>(ChenPlatform.class);
        util.exportExcel(response, list, "平台数据");
    }

    /**
     * 获取平台详细信息
     */
    @PreAuthorize("@ss.hasPermi('platform:platformList:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(chenPlatformService.selectChenPlatformById(id));
    }

    /**
     * 新增平台
     */
    @PreAuthorize("@ss.hasPermi('platform:platformList:add')")
    @Log(title = "平台", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChenPlatform chenPlatform)
    {
        return toAjax(chenPlatformService.insertChenPlatform(chenPlatform));
    }

    /**
     * 修改平台
     */
    @PreAuthorize("@ss.hasPermi('platform:platformList:edit')")
    @Log(title = "平台", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChenPlatform chenPlatform)
    {
        return toAjax(chenPlatformService.updateChenPlatform(chenPlatform));
    }

    /**
     * 删除平台
     */
    @PreAuthorize("@ss.hasPermi('platform:platformList:remove')")
    @Log(title = "平台", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(chenPlatformService.deleteChenPlatformByIds(ids));
    }
}

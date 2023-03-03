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
import com.ruoyi.store.domain.ChenSkuPrice;
import com.ruoyi.store.service.IChenSkuPriceService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * sku价格对照Controller
 * 
 * @author ruoyi
 * @date 2023-03-03
 */
@RestController
@RequestMapping("/SkuPrice/SkuPrice")
public class ChenSkuPriceController extends BaseController
{
    @Autowired
    private IChenSkuPriceService chenSkuPriceService;

    /**
     * 查询sku价格对照列表
     */
    @PreAuthorize("@ss.hasPermi('SkuPrice:SkuPrice:list')")
    @GetMapping("/list")
    public TableDataInfo list(ChenSkuPrice chenSkuPrice)
    {
        startPage();
        List<ChenSkuPrice> list = chenSkuPriceService.selectChenSkuPriceList(chenSkuPrice);
        return getDataTable(list);
    }

    /**
     * 导出sku价格对照列表
     */
    @PreAuthorize("@ss.hasPermi('SkuPrice:SkuPrice:export')")
    @Log(title = "sku价格对照", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, ChenSkuPrice chenSkuPrice)
    {
        List<ChenSkuPrice> list = chenSkuPriceService.selectChenSkuPriceList(chenSkuPrice);
        ExcelUtil<ChenSkuPrice> util = new ExcelUtil<ChenSkuPrice>(ChenSkuPrice.class);
        util.exportExcel(response, list, "sku价格对照数据");
    }

    /**
     * 获取sku价格对照详细信息
     */
    @PreAuthorize("@ss.hasPermi('SkuPrice:SkuPrice:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(chenSkuPriceService.selectChenSkuPriceById(id));
    }

    /**
     * 新增sku价格对照
     */
    @PreAuthorize("@ss.hasPermi('SkuPrice:SkuPrice:add')")
    @Log(title = "sku价格对照", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody ChenSkuPrice chenSkuPrice)
    {
        return toAjax(chenSkuPriceService.insertChenSkuPrice(chenSkuPrice));
    }

    /**
     * 修改sku价格对照
     */
    @PreAuthorize("@ss.hasPermi('SkuPrice:SkuPrice:edit')")
    @Log(title = "sku价格对照", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody ChenSkuPrice chenSkuPrice)
    {
        return toAjax(chenSkuPriceService.updateChenSkuPrice(chenSkuPrice));
    }

    /**
     * 删除sku价格对照
     */
    @PreAuthorize("@ss.hasPermi('SkuPrice:SkuPrice:remove')")
    @Log(title = "sku价格对照", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(chenSkuPriceService.deleteChenSkuPriceByIds(ids));
    }
}

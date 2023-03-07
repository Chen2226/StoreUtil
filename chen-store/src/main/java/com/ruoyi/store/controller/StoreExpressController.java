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
import com.ruoyi.store.domain.StoreExpress;
import com.ruoyi.store.service.IStoreExpressService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 店铺-快递费用Controller
 *
 * @author cwh
 * @date 2023-03-06
 */
@RestController
@RequestMapping("/store_express/StoreExpress")
public class StoreExpressController extends BaseController {
    @Autowired
    private IStoreExpressService storeExpressService;

    /**
     * 查询店铺-快递费用列表
     */
    @PreAuthorize("@ss.hasPermi('store_express:StoreExpress:list')")
    @GetMapping("/list")
    public TableDataInfo list(StoreExpress storeExpress) {
        startPage();
        List<StoreExpress> list = storeExpressService.selectStoreExpressList(storeExpress);
        return getDataTable(list);
    }

    /**
     * 导出店铺-快递费用列表
     */
    @PreAuthorize("@ss.hasPermi('store_express:StoreExpress:export')")
    @Log(title = "店铺-快递费用", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StoreExpress storeExpress) {
        List<StoreExpress> list = storeExpressService.selectStoreExpressList(storeExpress);
        ExcelUtil<StoreExpress> util = new ExcelUtil<StoreExpress>(StoreExpress.class);
        util.exportExcel(response, list, "店铺-快递费用数据");
    }

    /**
     * 获取店铺-快递费用详细信息
     */
    @PreAuthorize("@ss.hasPermi('store_express:StoreExpress:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id) {
        return success(storeExpressService.selectStoreExpressById(id));
    }

    /**
     * 新增店铺-快递费用
     */
    @PreAuthorize("@ss.hasPermi('store_express:StoreExpress:add')")
    @Log(title = "店铺-快递费用", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StoreExpress storeExpress) {
        return toAjax(storeExpressService.insertStoreExpress(storeExpress));
    }

    /**
     * 修改店铺-快递费用
     */
    @PreAuthorize("@ss.hasPermi('store_express:StoreExpress:edit')")
    @Log(title = "店铺-快递费用", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StoreExpress storeExpress) {
        return toAjax(storeExpressService.updateStoreExpress(storeExpress));
    }

    /**
     * 删除店铺-快递费用
     */
    @PreAuthorize("@ss.hasPermi('store_express:StoreExpress:remove')")
    @Log(title = "店铺-快递费用", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids) {
        return toAjax(storeExpressService.deleteStoreExpressByIds(ids));
    }
}

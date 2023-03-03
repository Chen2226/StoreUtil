package com.ruoyi.store.service;

import java.util.List;
import com.ruoyi.store.domain.ChenSkuPrice;

/**
 * sku价格对照Service接口
 * 
 * @author ruoyi
 * @date 2023-03-03
 */
public interface IChenSkuPriceService 
{
    /**
     * 查询sku价格对照
     * 
     * @param id sku价格对照主键
     * @return sku价格对照
     */
    public ChenSkuPrice selectChenSkuPriceById(Long id);

    /**
     * 查询sku价格对照列表
     * 
     * @param chenSkuPrice sku价格对照
     * @return sku价格对照集合
     */
    public List<ChenSkuPrice> selectChenSkuPriceList(ChenSkuPrice chenSkuPrice);

    /**
     * 新增sku价格对照
     * 
     * @param chenSkuPrice sku价格对照
     * @return 结果
     */
    public int insertChenSkuPrice(ChenSkuPrice chenSkuPrice);

    /**
     * 修改sku价格对照
     * 
     * @param chenSkuPrice sku价格对照
     * @return 结果
     */
    public int updateChenSkuPrice(ChenSkuPrice chenSkuPrice);

    /**
     * 批量删除sku价格对照
     * 
     * @param ids 需要删除的sku价格对照主键集合
     * @return 结果
     */
    public int deleteChenSkuPriceByIds(Long[] ids);

    /**
     * 删除sku价格对照信息
     * 
     * @param id sku价格对照主键
     * @return 结果
     */
    public int deleteChenSkuPriceById(Long id);
}

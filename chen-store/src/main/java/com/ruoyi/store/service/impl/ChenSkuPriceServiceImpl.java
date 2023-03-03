package com.ruoyi.store.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.store.mapper.ChenSkuPriceMapper;
import com.ruoyi.store.domain.ChenSkuPrice;
import com.ruoyi.store.service.IChenSkuPriceService;

/**
 * sku价格对照Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-03
 */
@Service
public class ChenSkuPriceServiceImpl implements IChenSkuPriceService 
{
    @Autowired
    private ChenSkuPriceMapper chenSkuPriceMapper;

    /**
     * 查询sku价格对照
     * 
     * @param id sku价格对照主键
     * @return sku价格对照
     */
    @Override
    public ChenSkuPrice selectChenSkuPriceById(Long id)
    {
        return chenSkuPriceMapper.selectChenSkuPriceById(id);
    }

    /**
     * 查询sku价格对照列表
     * 
     * @param chenSkuPrice sku价格对照
     * @return sku价格对照
     */
    @Override
    public List<ChenSkuPrice> selectChenSkuPriceList(ChenSkuPrice chenSkuPrice)
    {
        return chenSkuPriceMapper.selectChenSkuPriceList(chenSkuPrice);
    }

    /**
     * 新增sku价格对照
     * 
     * @param chenSkuPrice sku价格对照
     * @return 结果
     */
    @Override
    public int insertChenSkuPrice(ChenSkuPrice chenSkuPrice)
    {
        chenSkuPrice.setCreateTime(DateUtils.getNowDate());
        return chenSkuPriceMapper.insertChenSkuPrice(chenSkuPrice);
    }

    /**
     * 修改sku价格对照
     * 
     * @param chenSkuPrice sku价格对照
     * @return 结果
     */
    @Override
    public int updateChenSkuPrice(ChenSkuPrice chenSkuPrice)
    {
        chenSkuPrice.setUpdateTime(DateUtils.getNowDate());
        return chenSkuPriceMapper.updateChenSkuPrice(chenSkuPrice);
    }

    /**
     * 批量删除sku价格对照
     * 
     * @param ids 需要删除的sku价格对照主键
     * @return 结果
     */
    @Override
    public int deleteChenSkuPriceByIds(Long[] ids)
    {
        return chenSkuPriceMapper.deleteChenSkuPriceByIds(ids);
    }

    /**
     * 删除sku价格对照信息
     * 
     * @param id sku价格对照主键
     * @return 结果
     */
    @Override
    public int deleteChenSkuPriceById(Long id)
    {
        return chenSkuPriceMapper.deleteChenSkuPriceById(id);
    }
}

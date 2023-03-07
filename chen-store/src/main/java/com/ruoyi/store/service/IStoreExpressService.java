package com.ruoyi.store.service;

import java.util.List;
import com.ruoyi.store.domain.StoreExpress;

/**
 * 店铺-快递费用Service接口
 * 
 * @author cwh
 * @date 2023-03-06
 */
public interface IStoreExpressService 
{
    /**
     * 查询店铺-快递费用
     * 
     * @param id 店铺-快递费用主键
     * @return 店铺-快递费用
     */
    public StoreExpress selectStoreExpressById(Long id);

    /**
     * 查询店铺-快递费用列表
     * 
     * @param storeExpress 店铺-快递费用
     * @return 店铺-快递费用集合
     */
    public List<StoreExpress> selectStoreExpressList(StoreExpress storeExpress);

    /**
     * 新增店铺-快递费用
     * 
     * @param storeExpress 店铺-快递费用
     * @return 结果
     */
    public int insertStoreExpress(StoreExpress storeExpress);

    /**
     * 修改店铺-快递费用
     * 
     * @param storeExpress 店铺-快递费用
     * @return 结果
     */
    public int updateStoreExpress(StoreExpress storeExpress);

    /**
     * 批量删除店铺-快递费用
     * 
     * @param ids 需要删除的店铺-快递费用主键集合
     * @return 结果
     */
    public int deleteStoreExpressByIds(Long[] ids);

    /**
     * 删除店铺-快递费用信息
     * 
     * @param id 店铺-快递费用主键
     * @return 结果
     */
    public int deleteStoreExpressById(Long id);
}

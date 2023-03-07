package com.ruoyi.store.mapper;

import java.util.List;
import com.ruoyi.store.domain.StoreExpress;
import com.ruoyi.store.domain.ChenStoreExpress;

/**
 * 店铺-快递费用Mapper接口
 * 
 * @author cwh
 * @date 2023-03-06
 */
public interface StoreExpressMapper 
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
     * 删除店铺-快递费用
     * 
     * @param id 店铺-快递费用主键
     * @return 结果
     */
    public int deleteStoreExpressById(Long id);

    /**
     * 批量删除店铺-快递费用
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStoreExpressByIds(Long[] ids);

    /**
     * 批量删除快递
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChenStoreExpressByStoreIds(Long[] ids);
    
    /**
     * 批量新增快递
     * 
     * @param chenStoreExpressList 快递列表
     * @return 结果
     */
    public int batchChenStoreExpress(List<ChenStoreExpress> chenStoreExpressList);
    

    /**
     * 通过店铺-快递费用主键删除快递信息
     * 
     * @param id 店铺-快递费用ID
     * @return 结果
     */
    public int deleteChenStoreExpressByStoreId(Long id);
}

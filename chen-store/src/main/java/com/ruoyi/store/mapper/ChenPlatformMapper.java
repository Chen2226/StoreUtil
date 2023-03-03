package com.ruoyi.store.mapper;

import java.util.List;
import com.ruoyi.store.domain.ChenPlatform;
import com.ruoyi.store.domain.ChenStore;

/**
 * 平台
Mapper接口
 * 
 * @author ruoyi
 * @date 2023-03-03
 */
public interface ChenPlatformMapper 
{
    /**
     * 查询平台

     * 
     * @param id 平台
主键
     * @return 平台

     */
    public ChenPlatform selectChenPlatformById(Long id);

    /**
     * 查询平台
列表
     * 
     * @param chenPlatform 平台

     * @return 平台
集合
     */
    public List<ChenPlatform> selectChenPlatformList(ChenPlatform chenPlatform);

    /**
     * 新增平台

     * 
     * @param chenPlatform 平台

     * @return 结果
     */
    public int insertChenPlatform(ChenPlatform chenPlatform);

    /**
     * 修改平台

     * 
     * @param chenPlatform 平台

     * @return 结果
     */
    public int updateChenPlatform(ChenPlatform chenPlatform);

    /**
     * 删除平台

     * 
     * @param id 平台
主键
     * @return 结果
     */
    public int deleteChenPlatformById(Long id);

    /**
     * 批量删除平台

     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChenPlatformByIds(Long[] ids);

    /**
     * 批量删除店铺
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteChenStoreByPlatformIds(Long[] ids);
    
    /**
     * 批量新增店铺
     * 
     * @param chenStoreList 店铺列表
     * @return 结果
     */
    public int batchChenStore(List<ChenStore> chenStoreList);
    

    /**
     * 通过平台
主键删除店铺信息
     * 
     * @param id 平台
ID
     * @return 结果
     */
    public int deleteChenStoreByPlatformId(Long id);
}

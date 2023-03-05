package com.ruoyi.store.service;

import java.util.List;
import com.ruoyi.store.domain.ChenPlatform;

/**
 * 平台
Service接口
 * 
 * @author cwh
 * @date 2023-03-03
 */
public interface IChenPlatformService 
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
     * 批量删除平台

     * 
     * @param ids 需要删除的平台
主键集合
     * @return 结果
     */
    public int deleteChenPlatformByIds(Long[] ids);

    /**
     * 删除平台
信息
     * 
     * @param id 平台
主键
     * @return 结果
     */
    public int deleteChenPlatformById(Long id);
}

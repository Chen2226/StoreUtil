package com.ruoyi.store.mapper;

import java.util.List;
import com.ruoyi.store.domain.ChenPlatform;

/**
 * 平台Mapper接口
 * 
 * @author chen
 * @date 2023-03-02
 */
public interface ChenPlatformMapper 
{
    /**
     * 查询平台
     * 
     * @param id 平台主键
     * @return 平台
     */
    public ChenPlatform selectChenPlatformById(Long id);

    /**
     * 查询平台列表
     * 
     * @param chenPlatform 平台
     * @return 平台集合
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
     * @param id 平台主键
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
}

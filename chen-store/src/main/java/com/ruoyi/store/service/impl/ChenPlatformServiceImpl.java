package com.ruoyi.store.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.store.mapper.ChenPlatformMapper;
import com.ruoyi.store.domain.ChenPlatform;
import com.ruoyi.store.service.IChenPlatformService;

/**
 * 平台Service业务层处理
 * 
 * @author chen
 * @date 2023-03-02
 */
@Service
public class ChenPlatformServiceImpl implements IChenPlatformService 
{
    @Autowired
    private ChenPlatformMapper chenPlatformMapper;

    /**
     * 查询平台
     * 
     * @param id 平台主键
     * @return 平台
     */
    @Override
    public ChenPlatform selectChenPlatformById(Long id)
    {
        return chenPlatformMapper.selectChenPlatformById(id);
    }

    /**
     * 查询平台列表
     * 
     * @param chenPlatform 平台
     * @return 平台
     */
    @Override
    public List<ChenPlatform> selectChenPlatformList(ChenPlatform chenPlatform)
    {
        return chenPlatformMapper.selectChenPlatformList(chenPlatform);
    }

    /**
     * 新增平台
     * 
     * @param chenPlatform 平台
     * @return 结果
     */
    @Override
    public int insertChenPlatform(ChenPlatform chenPlatform)
    {
        chenPlatform.setCreateTime(DateUtils.getNowDate());
        return chenPlatformMapper.insertChenPlatform(chenPlatform);
    }

    /**
     * 修改平台
     * 
     * @param chenPlatform 平台
     * @return 结果
     */
    @Override
    public int updateChenPlatform(ChenPlatform chenPlatform)
    {
        chenPlatform.setUpdateTime(DateUtils.getNowDate());
        return chenPlatformMapper.updateChenPlatform(chenPlatform);
    }

    /**
     * 批量删除平台
     * 
     * @param ids 需要删除的平台主键
     * @return 结果
     */
    @Override
    public int deleteChenPlatformByIds(Long[] ids)
    {
        return chenPlatformMapper.deleteChenPlatformByIds(ids);
    }

    /**
     * 删除平台信息
     * 
     * @param id 平台主键
     * @return 结果
     */
    @Override
    public int deleteChenPlatformById(Long id)
    {
        return chenPlatformMapper.deleteChenPlatformById(id);
    }
}

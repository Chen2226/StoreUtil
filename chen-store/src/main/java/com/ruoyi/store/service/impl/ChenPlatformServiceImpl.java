package com.ruoyi.store.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.store.domain.ChenStore;
import com.ruoyi.store.mapper.ChenPlatformMapper;
import com.ruoyi.store.domain.ChenPlatform;
import com.ruoyi.store.service.IChenPlatformService;

/**
 * 平台
Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-03-03
 */
@Service
public class ChenPlatformServiceImpl implements IChenPlatformService 
{
    @Autowired
    private ChenPlatformMapper chenPlatformMapper;

    /**
     * 查询平台

     * 
     * @param id 平台
主键
     * @return 平台

     */
    @Override
    public ChenPlatform selectChenPlatformById(Long id)
    {
        return chenPlatformMapper.selectChenPlatformById(id);
    }

    /**
     * 查询平台
列表
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
    @Transactional
    @Override
    public int insertChenPlatform(ChenPlatform chenPlatform)
    {
        chenPlatform.setCreateTime(DateUtils.getNowDate());
        int rows = chenPlatformMapper.insertChenPlatform(chenPlatform);
        insertChenStore(chenPlatform);
        return rows;
    }

    /**
     * 修改平台

     * 
     * @param chenPlatform 平台

     * @return 结果
     */
    @Transactional
    @Override
    public int updateChenPlatform(ChenPlatform chenPlatform)
    {
        chenPlatform.setUpdateTime(DateUtils.getNowDate());
        chenPlatformMapper.deleteChenStoreByPlatformId(chenPlatform.getId());
        insertChenStore(chenPlatform);
        return chenPlatformMapper.updateChenPlatform(chenPlatform);
    }

    /**
     * 批量删除平台

     * 
     * @param ids 需要删除的平台
主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteChenPlatformByIds(Long[] ids)
    {
        chenPlatformMapper.deleteChenStoreByPlatformIds(ids);
        return chenPlatformMapper.deleteChenPlatformByIds(ids);
    }

    /**
     * 删除平台
信息
     * 
     * @param id 平台
主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteChenPlatformById(Long id)
    {
        chenPlatformMapper.deleteChenStoreByPlatformId(id);
        return chenPlatformMapper.deleteChenPlatformById(id);
    }

    /**
     * 新增店铺信息
     * 
     * @param chenPlatform 平台
对象
     */
    public void insertChenStore(ChenPlatform chenPlatform)
    {
        List<ChenStore> chenStoreList = chenPlatform.getChenStoreList();
        Long id = chenPlatform.getId();
        if (StringUtils.isNotNull(chenStoreList))
        {
            List<ChenStore> list = new ArrayList<ChenStore>();
            for (ChenStore chenStore : chenStoreList)
            {
                chenStore.setPlatformId(id);
                list.add(chenStore);
            }
            if (list.size() > 0)
            {
                chenPlatformMapper.batchChenStore(list);
            }
        }
    }
}

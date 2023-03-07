package com.ruoyi.store.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.store.domain.ChenStoreExpress;
import com.ruoyi.store.mapper.StoreExpressMapper;
import com.ruoyi.store.domain.StoreExpress;
import com.ruoyi.store.service.IStoreExpressService;

/**
 * 店铺-快递费用Service业务层处理
 * 
 * @author cwh
 * @date 2023-03-06
 */
@Service
public class StoreExpressServiceImpl implements IStoreExpressService 
{
    @Autowired
    private StoreExpressMapper storeExpressMapper;

    /**
     * 查询店铺-快递费用
     * 
     * @param id 店铺-快递费用主键
     * @return 店铺-快递费用
     */
    @Override
    public StoreExpress selectStoreExpressById(Long id)
    {
        return storeExpressMapper.selectStoreExpressById(id);
    }

    /**
     * 查询店铺-快递费用列表
     * 
     * @param storeExpress 店铺-快递费用
     * @return 店铺-快递费用
     */
    @Override
    public List<StoreExpress> selectStoreExpressList(StoreExpress storeExpress)
    {
        return storeExpressMapper.selectStoreExpressList(storeExpress);
    }

    /**
     * 新增店铺-快递费用
     * 
     * @param storeExpress 店铺-快递费用
     * @return 结果
     */
    @Transactional
    @Override
    public int insertStoreExpress(StoreExpress storeExpress)
    {
        storeExpress.setCreateTime(DateUtils.getNowDate());
        int rows = storeExpressMapper.insertStoreExpress(storeExpress);
        insertChenStoreExpress(storeExpress);
        return rows;
    }

    /**
     * 修改店铺-快递费用
     * 
     * @param storeExpress 店铺-快递费用
     * @return 结果
     */
    @Transactional
    @Override
    public int updateStoreExpress(StoreExpress storeExpress)
    {
        storeExpress.setUpdateTime(DateUtils.getNowDate());
        storeExpressMapper.deleteChenStoreExpressByStoreId(storeExpress.getId());
        insertChenStoreExpress(storeExpress);
        return storeExpressMapper.updateStoreExpress(storeExpress);
    }

    /**
     * 批量删除店铺-快递费用
     * 
     * @param ids 需要删除的店铺-快递费用主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteStoreExpressByIds(Long[] ids)
    {
        storeExpressMapper.deleteChenStoreExpressByStoreIds(ids);
        return storeExpressMapper.deleteStoreExpressByIds(ids);
    }

    /**
     * 删除店铺-快递费用信息
     * 
     * @param id 店铺-快递费用主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteStoreExpressById(Long id)
    {
        storeExpressMapper.deleteChenStoreExpressByStoreId(id);
        return storeExpressMapper.deleteStoreExpressById(id);
    }

    /**
     * 新增快递信息
     * 
     * @param storeExpress 店铺-快递费用对象
     */
    public void insertChenStoreExpress(StoreExpress storeExpress)
    {
        List<ChenStoreExpress> chenStoreExpressList = storeExpress.getChenStoreExpressList();
        Long id = storeExpress.getId();
        if (StringUtils.isNotNull(chenStoreExpressList))
        {
            List<ChenStoreExpress> list = new ArrayList<ChenStoreExpress>();
            for (ChenStoreExpress chenStoreExpress : chenStoreExpressList)
            {
                chenStoreExpress.setStoreId(id);
                list.add(chenStoreExpress);
            }
            if (list.size() > 0)
            {
                storeExpressMapper.batchChenStoreExpress(list);
            }
        }
    }
}

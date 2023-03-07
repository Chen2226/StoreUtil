package com.ruoyi.store.domain;

import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 店铺-快递费用对象 chen_store
 * 
 * @author cwh
 * @date 2023-03-06
 */
public class StoreExpress extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long uid;

    /** 店铺名字 */
    @Excel(name = "店铺名字")
    private String name;

    /** 平台id */
    @Excel(name = "平台id")
    private Long platformId;

    /** 快递信息 */
    private List<ChenStoreExpress> chenStoreExpressList;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUid(Long uid) 
    {
        this.uid = uid;
    }

    public Long getUid() 
    {
        return uid;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setPlatformId(Long platformId) 
    {
        this.platformId = platformId;
    }

    public Long getPlatformId() 
    {
        return platformId;
    }

    public List<ChenStoreExpress> getChenStoreExpressList()
    {
        return chenStoreExpressList;
    }

    public void setChenStoreExpressList(List<ChenStoreExpress> chenStoreExpressList)
    {
        this.chenStoreExpressList = chenStoreExpressList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("uid", getUid())
            .append("name", getName())
            .append("platformId", getPlatformId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("chenStoreExpressList", getChenStoreExpressList())
            .toString();
    }
}

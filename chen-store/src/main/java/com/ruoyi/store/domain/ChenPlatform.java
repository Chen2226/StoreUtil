package com.ruoyi.store.domain;

import java.math.BigDecimal;
import java.util.List;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 平台
对象 chen_platform
 * 
 * @author ruoyi
 * @date 2023-03-03
 */
public class ChenPlatform extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 归属那个用户 */
    @Excel(name = "归属那个用户")
    private Long uid;

    /** 名字 */
    @Excel(name = "名字")
    private String name;

    /** 平台扣费 */
    @Excel(name = "平台扣费")
    private BigDecimal platformDeduction;

    /** 店铺信息 */
    private List<ChenStore> chenStoreList;

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
    public void setPlatformDeduction(BigDecimal platformDeduction) 
    {
        this.platformDeduction = platformDeduction;
    }

    public BigDecimal getPlatformDeduction() 
    {
        return platformDeduction;
    }

    public List<ChenStore> getChenStoreList()
    {
        return chenStoreList;
    }

    public void setChenStoreList(List<ChenStore> chenStoreList)
    {
        this.chenStoreList = chenStoreList;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("uid", getUid())
            .append("name", getName())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("platformDeduction", getPlatformDeduction())
            .append("chenStoreList", getChenStoreList())
            .toString();
    }
}

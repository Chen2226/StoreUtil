package com.ruoyi.store.domain;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import lombok.EqualsAndHashCode;
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
@EqualsAndHashCode(callSuper = true)
@Data
public class ChenPlatform extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 归属那个用户 */
    @Excel(name = "归属那个用户")
    private Long uid;

    /** 用户名 */
    @Excel(name = "用户名")
    private String userName;

    /** 名字 */
    @Excel(name = "名字")
    private String name;

    /** 平台扣费 */
    @Excel(name = "平台扣费")
    private BigDecimal platformDeduction;

    /** 店铺信息 */
    private List<ChenStore> chenStoreList;
}

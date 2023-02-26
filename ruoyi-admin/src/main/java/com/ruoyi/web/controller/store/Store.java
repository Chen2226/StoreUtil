package com.ruoyi.web.controller.store;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.store.excel.StoreExcel;

/**
 * @Author 陈武豪
 * @Date 2023-02-26 21:56
 **/
@Api("店铺信息管理")
@RestController
@RequestMapping("/store/excel")
public class Store {
    @ApiOperation("测试")
    @GetMapping("/test")
    public String test()
    {
        StoreExcel storeExcel = new StoreExcel();
        return storeExcel.helloTest();
    }
}
